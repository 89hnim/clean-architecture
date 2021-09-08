package m.tech.baseclean.framework.presentation.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import m.tech.baseclean.R
import m.tech.baseclean.util.autoCleared
import timber.log.Timber

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<Binding : ViewDataBinding>(
    private val inflate: Inflate<Binding>
) : Fragment() {

    protected val commonViewModel: CommonViewModel by activityViewModels()

    protected lateinit var activityObserver: ActivityObserver //communicate with the parent activity

    protected var binding by autoCleared<Binding>()

    private var dialogLoading: AlertDialog? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            activityObserver = context as ActivityObserver
        } catch (e: ClassCastException) {
            Timber.e("$context must implement ActivityObserver")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate.invoke(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initDataBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun showDialogLoading() {
        if (dialogLoading == null) {
            dialogLoading = MaterialAlertDialogBuilder(requireContext()).apply {
                setCancelable(false)
                setView(R.layout.dialog_loading)
            }.create()
        }
        dialogLoading?.show()
    }

    fun hideDialogLoading() {
        dialogLoading?.dismiss()
        dialogLoading = null
    }

    fun safeNav(currentDestination: Int, navDirections: NavDirections) {
        val navController = findNavController()
        if (navController.currentDestination?.id == currentDestination) {
            try {
                navController.navigate(navDirections)
            } catch (e: Exception) {
                Timber.e("safeNav: ${e.message}")
            }
        }
    }

    /**
     * setup data binding (if needed)
     */
    open fun initDataBinding() {}

    /**
     * setup views like setup view, listener, subscribe observer .etc.
     */
    abstract fun initViews()

    /**
     * this method will be called in [onCreate]. Avoid duplicate call load data when fragment recreates
     */
    abstract fun initData()

}
