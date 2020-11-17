package m.tech.baseclean.framework.presentation.splash

import android.util.Log
import android.view.View
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import m.tech.baseclean.R
import m.tech.baseclean.framework.presentation.common.BaseFragment

@AndroidEntryPoint
class SplashFragment(
    private val glide: RequestManager
) : BaseFragment(R.layout.fragment_splash) {

    override fun init(view: View) {
        commonViewModel.getDummies()
    }

    override fun subscribeObserver(view: View) {
        commonViewModel.dummies.observe(viewLifecycleOwner) {
            handleLoadingStateWithDialog(it)

            getData(it)?.let { list ->
                for (dummy in list) {
                    Log.d(TAG, "getData: ${dummy.name}")
                }
            }

            getErrorCode(it)?.let {
                Log.e(TAG, "error: $it")
            }

        }
    }

    companion object {
        const val TAG = "AppDebug"
    }

}