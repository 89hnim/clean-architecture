package m.tech.baseclean.framework.presentation.splash

import dagger.hilt.android.AndroidEntryPoint
import m.tech.baseclean.databinding.FragmentSplashBinding
import m.tech.baseclean.framework.presentation.common.BaseFragment
import m.tech.baseclean.util.safeDelay
import java.util.logging.Handler

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun initData() {
        commonViewModel.getDummies()
        commonViewModel.getDummiesOneTime()
    }

    override fun initDataBinding() {

    }

    override fun initViews() {
        showDialogLoading()
        safeDelay(5000) { hideDialogLoading() }
    }

}
