package m.tech.baseclean.business.interactors

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import m.tech.baseclean.business.data.DataState
import m.tech.baseclean.business.data.cache.abstraction.DummyCacheDataSource
import m.tech.baseclean.business.data.network.NetworkResult
import m.tech.baseclean.business.data.network.abstraction.DummyNetworkDataSource
import m.tech.baseclean.business.data.safeApiCall
import m.tech.baseclean.business.domain.Dummy
import m.tech.baseclean.business.interactors.InteractorEx.removeFromList
import javax.inject.Inject

class GetDummies
@Inject
constructor(
    private val dummyCacheDataSource: DummyCacheDataSource,
    private val dummyNetworkDataSource: DummyNetworkDataSource
) {

    suspend fun getDummies(isFetch: Boolean): Flow<DataState<List<Dummy>>> = flow {
        emit(DataState.Loading()) // emit loading state
        kotlinx.coroutines.delay(1500)
        val caches = dummyCacheDataSource.getDummies()

        if (!isFetch) {
            emit(DataState.Success(caches))
        } else {
            val result = safeApiCall(IO) {
                dummyNetworkDataSource.getDummies()
            }

            val networkDummies = when (result) {
                is NetworkResult.Success -> {
                    result.value ?: ArrayList()
                }
                is NetworkResult.GenericError -> {
                    emit(DataState.Error(result.code, caches))
                    return@flow
                }
            }

            syncDummies(caches.toMutableList(), networkDummies)

            emit(DataState.Success(dummyCacheDataSource.getDummies()))
        }

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
        caches: MutableList<Dummy>,
        networks: List<Dummy>
    ) = withContext(IO) {
        val promise = async {
            for (dummy in networks) {
                launch {
                    dummyCacheDataSource.getDummyById(dummy.id)?.let { cached ->
                        removeFromList(caches, cached)
                        dummyCacheDataSource.updateDummy(dummy)
                    } ?: dummyCacheDataSource.addDummy(dummy)
                }
            }
        }
        promise.await()

        for (cached in caches) {
            launch {
                dummyCacheDataSource.removeDummy(cached)
            }
        }

    }

}
