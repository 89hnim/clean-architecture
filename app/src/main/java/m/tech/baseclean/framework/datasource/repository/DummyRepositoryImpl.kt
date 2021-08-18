package m.tech.baseclean.framework.datasource.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import m.tech.baseclean.business.data.DataState
import m.tech.baseclean.business.data.cache.DummyCacheDataSource
import m.tech.baseclean.business.data.network.DummyNetworkDataSource
import m.tech.baseclean.business.data.repository.DummyRepository
import m.tech.baseclean.business.domain.DummyModel
import m.tech.baseclean.business.domain.mapToEntity
import m.tech.baseclean.di.AppDispatchers
import m.tech.baseclean.framework.datasource.cache.model.mapToDomain
import m.tech.baseclean.framework.datasource.network.model.response.mapToDomain

class DummyRepositoryImpl(
    private val dummyCacheDataSource: DummyCacheDataSource,
    private val dummyNetworkDataSource: DummyNetworkDataSource,
    private val appDispatchers: AppDispatchers
) : DummyRepository {

    // Disadvantage: Don't have loading state but can easily handle it in ViewModel
    override suspend fun getDummiesOneTime(): List<DummyModel> = withContext(appDispatchers.io) {
        //get from cache
        val caches = dummyCacheDataSource.getDummies().mapToDomain()
        //fetch from server
        val result = dummyNetworkDataSource.getDummies().mapToDomain()
        //sync to db
        syncDummies(caches.toMutableList(), result)
        //result
        dummyCacheDataSource.getDummies().mapToDomain()
    }

    // Disadvantage: Don't know what error, hard to debug
    // Advantage: Have loading state b/c of stream data
    override suspend fun getDummies(isFetch: Boolean): Flow<DataState<List<DummyModel>>> = flow {
        emit(DataState.Loading()) // emit loading state
        delay(1500)
        val caches = dummyCacheDataSource.getDummies().mapToDomain()

        if (!isFetch) {
            emit(DataState.Success(caches))
        } else {
            val result = dummyNetworkDataSource.getDummies().mapToDomain()
            syncDummies(caches.toMutableList(), result)
            emit(DataState.Success(dummyCacheDataSource.getDummies().mapToDomain()))
        }
    }
        .flowOn(appDispatchers.io)
        .catch {
            emit(
                DataState.Error(
                    Exception("Error while getDummies"),
                    dummyCacheDataSource.getDummies().mapToDomain()
                )
            )
        }


    /*
    *** Sync data online and offline (network to db)
    * 1: Loop through network list
    * 2: Find that network object in db:
        If don't have -> insert, else -> update, then remove from cache list
    * 3: Remove any cache objects left in the db (that objects do not exists in network list)
    ==============
    * note: in some case, you need to sync from cache to network too (in both way)
    * in step 2: check for updated date to sync properly
    * in step 3: instead delete it, upload it to server
 */
    private suspend fun syncDummies(
        caches: MutableList<DummyModel>,
        networks: List<DummyModel>
    ) = withContext(appDispatchers.default) {
        val promise = async {
            for (dummy in networks) {
                launch {
                    dummyCacheDataSource.getDummyById(dummy.id)?.let { cached ->
                        removeFromList(caches, cached.mapToDomain())
                        dummyCacheDataSource.updateDummy(dummy.mapToEntity())
                    } ?: dummyCacheDataSource.addDummy(dummy.mapToEntity())
                }
            }
        }
        promise.await()

        for (cached in caches) {
            dummyCacheDataSource.removeDummy(cached.mapToEntity())
        }
    }

    @Synchronized
    private fun removeFromList(list: MutableList<DummyModel>, item: DummyModel) {
        list.removeAll { item.id == it.id }
    }

}