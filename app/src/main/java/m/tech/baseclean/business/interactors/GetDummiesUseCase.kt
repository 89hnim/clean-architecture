package m.tech.baseclean.business.interactors

import kotlinx.coroutines.flow.Flow
import m.tech.baseclean.business.data.DataState
import m.tech.baseclean.business.data.repository.DummyRepository
import m.tech.baseclean.business.domain.DummyModel
import javax.inject.Inject

class GetDummiesUseCase
@Inject
constructor(
    private val dummyRepository: DummyRepository
) {

    suspend operator fun invoke(isFetch: Boolean): Flow<DataState<List<DummyModel>>> {
        return dummyRepository.getDummies(isFetch)
    }

}
