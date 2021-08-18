package m.tech.baseclean.business.data.repository

import kotlinx.coroutines.flow.Flow
import m.tech.baseclean.business.data.DataState
import m.tech.baseclean.business.domain.DummyModel

interface DummyRepository {

    suspend fun getDummiesOneTime(): List<DummyModel>

    suspend fun getDummies(isFetch: Boolean): Flow<DataState<List<DummyModel>>>

}