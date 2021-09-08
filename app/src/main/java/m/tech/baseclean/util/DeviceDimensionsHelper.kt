package m.tech.baseclean.util

import android.content.Context

object DeviceDimensionsHelper {
    // display width in pixels
    fun Context.getDisplayWidth(): Float {
        return resources.displayMetrics.widthPixels.toFloat()
    }

    // display height in pixels
    fun Context.getDisplayHeight(): Int {
        return resources.displayMetrics.heightPixels
    }
}
