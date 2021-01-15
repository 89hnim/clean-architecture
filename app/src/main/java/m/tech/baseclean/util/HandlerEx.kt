package m.tech.baseclean.util

import android.os.Handler
import android.util.Log
import java.lang.Exception

fun Handler.safeDelay(delayMillis: Long = 0, action: () -> Unit) {
    postDelayed({
        try {
            action()
        } catch (e: Exception) {
            Log.e("AppDebug", "safeDelay: $e")
        }
    }, delayMillis)
}