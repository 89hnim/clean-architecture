package m.tech.baseclean.business.interactors

import m.tech.baseclean.business.data.DataState
import m.tech.baseclean.business.data.repository.DummyRepository
import m.tech.baseclean.business.domain.DummyModel
import javax.inject.Inject

class GetDummiesOneTimeUseCase
@Inject
constructor(
    private val dummyRepository: DummyRepository
) {

    suspend operator fun invoke(): DataState<List<DummyModel>> {
        return try {
            val list = dummyRepository.getDummiesOneTime()
            DataState.Success(list)
        } catch (e: Exception) {
            DataState.Error(e)
        }
    }


}
