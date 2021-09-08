package m.tech.baseclean.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * https://github.com/android/architecture-components-samples/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/util/AutoClearedValue.kt
 * Thanks Gapo's Android team for adding unregister
 */
class AutoClearedValue<T : Any>(val fragment: Fragment, unregister: (T) -> Unit = {}) :
    ReadWriteProperty<Fragment, T> {
    private var _value: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(
                    fragment,
                    Observer { viewLifecycleOwner ->
                        viewLifecycleOwner?.lifecycle?.addObserver(object :
                            DefaultLifecycleObserver {
                            override fun onDestroy(owner: LifecycleOwner) {
                                _value?.let { unregister(it) }
                                _value = null
                            }
                        })
                    })
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return _value ?: throw IllegalStateException(
            "should never call auto-cleared-value get when it might not be available"
        )
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        _value = value
    }
}

/**
 * Creates an [AutoClearedValue] associated with this fragment.
 */
fun <T : Any> Fragment.autoCleared(unregister: (T) -> Unit = {}) =
    AutoClearedValue<T>(this, unregister)