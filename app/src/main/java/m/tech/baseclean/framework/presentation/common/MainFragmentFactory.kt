package m.tech.baseclean.framework.presentation.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import m.tech.baseclean.framework.presentation.splash.SplashFragment
import javax.inject.Inject

class MainFragmentFactory
@Inject
constructor() : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when (className) {
            SplashFragment::class.java.name -> {
                SplashFragment()
            }

            else -> super.instantiate(classLoader, className)
        }

    }
}