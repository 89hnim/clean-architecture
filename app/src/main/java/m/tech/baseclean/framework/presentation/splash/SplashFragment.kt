package m.tech.baseclean.framework.presentation.splash

import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import m.tech.baseclean.databinding.FragmentSplashBinding
import m.tech.baseclean.framework.presentation.common.BaseFragment

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commonViewModel.getDummies()
        commonViewModel.getDummiesOneTime()
    }

}
