package m.tech.baseclean.framework.presentation.splash

import android.util.Log
import android.view.View
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import m.tech.baseclean.databinding.FragmentSplashBinding
import m.tech.baseclean.framework.presentation.common.BaseFragment
import m.tech.baseclean.util.displayToast

@AndroidEntryPoint
class SplashFragment(
    private val glide: RequestManager
) : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun init(view: View) {
        commonViewModel.getDummies()
    }

    override fun subscribeObserver(view: View) {
        commonViewModel.dummies.observe(viewLifecycleOwner) {
            handleLoadingState(it, binding.progress) //handle loading with a view
//            handleLoadingStateWithDialog(it) //handle loading with dialog

            getData(it)?.let { list ->
                for (dummy in list) {
                    displayToast("It's working")
                    Log.d(TAG, "getData: ${dummy.name}")
                }
            }

            getErrorCode(it)?.let { code ->
                Log.e(TAG, "error: $code")
                displayToast("Error $code")
            }

        }
    }

    companion object {
        const val TAG = "AppDebug"
    }

}
