package m.tech.baseclean.util

import android.os.Handler
import android.os.Looper
import timber.log.Timber

fun Handler.safeDelay(delayMillis: Long = 0, action: () -> Unit) {
    postDelayed({
        try {
            action()
        } catch (e: Exception) {
            Timber.e("safeDelay: $e")
        }
    }, delayMillis)
}

fun safeDelay(delayMillis: Long = 0, action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        try {
            action()
        } catch (e: Exception) {
            Timber.e("safeDelay: $e")
        }
    }, delayMillis)
}