package m.tech.baseclean.framework.presentation.common

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import m.tech.baseclean.R
import m.tech.baseclean.business.data.DataState
import m.tech.baseclean.util.gone
import m.tech.baseclean.util.show

abstract class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {

    protected val commonViewModel: CommonViewModel by activityViewModels()

    lateinit var stateChangeListener: DataStateChangeListener

    var dialogLoading: MaterialDialog? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            stateChangeListener = context as DataStateChangeListener
        } catch (e: ClassCastException) {
            Log.e(TAG, "$context must implement DataStateChangeListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        subscribeObserver(view)
    }

    abstract fun init(view: View)

    abstract fun subscribeObserver(view: View)

    fun handleLoadingState(data: DataState<*>, view: View) {
        if (data is DataState.Loading)
            view.show()
        else
            view.gone()
    }

    fun handleLoadingStateWithDialog(data: DataState<*>) {
        if (data is DataState.Loading && context != null) {
            if (dialogLoading == null) {
                dialogLoading = MaterialDialog(requireContext()).apply {
                    cancelable(false)
                    customView(R.layout.dialog_loading)
                }
            }
            dialogLoading?.show {
                cornerRadius(16f)
            }
        } else {
            dialogLoading?.dismiss()
        }
    }

    fun <T> getData(data: DataState<T>): T? {
        if (data is DataState.Success || data is DataState.Error) {
            data.data?.let {
                return it
            }
        }
        return null
    }

    fun <T> getErrorCode(data: DataState<T>): Int? {
        if (data is DataState.Error) {
            data.error?.getContentIfNotHandled()?.let {
                return it
            }
        }
        return null
    }

    fun NavController.safeNav(currentDestination: Int, targetDestination: Int) {
        if (this.currentDestination?.id == currentDestination) {
            try {
                this.navigate(targetDestination)
            } catch (e: IllegalArgumentException) {
                Log.e(TAG, "safeNav: ${e.message}")
            }
        }
    }

    companion object {
        private const val TAG = "BaseFragment"
    }
}