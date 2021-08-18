package m.tech.baseclean.framework.presentation.common

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import m.tech.baseclean.business.data.DataState
import m.tech.baseclean.business.domain.DummyModel
import m.tech.baseclean.business.interactors.GetDummiesOneTimeUseCase
import m.tech.baseclean.business.interactors.GetDummiesUseCase
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CommonViewModel
@Inject
constructor(
    private val savedStateHandler: SavedStateHandle,
    private val getDummiesUseCase: GetDummiesUseCase,
    private val getDummiesOneTimeUseCase: GetDummiesOneTimeUseCase
) : ViewModel() {

    private val _dummiesLiveData = MutableLiveData<List<DummyModel>>()
    val dummiesLiveData: LiveData<List<DummyModel>>
        get() = _dummiesLiveData

    fun getDummies() = viewModelScope.launch {
        getDummiesUseCase(true).collect { result ->
            when (result) {
                is DataState.Loading -> {
                    Timber.e("getDummies loading")
                }
                is DataState.Success -> {
                    Timber.e("getDummies ${result.data.size}")
                    _dummiesLiveData.value = result.data
                }
                is DataState.Error -> {
                    Timber.e("getDummies ${result.error}")
                }
            }
        }
    }

    fun getDummiesOneTime() = viewModelScope.launch {
        when (val result = getDummiesOneTimeUseCase()) {
            is DataState.Success -> {
                Timber.e("getDummiesOneTime ${result.data.size}")
            }
            is DataState.Error -> {
                Timber.e("getDummiesOneTime ${result.error}")
            }
        }
    }

}