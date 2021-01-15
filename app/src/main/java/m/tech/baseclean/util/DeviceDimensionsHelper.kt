package m.tech.baseclean.util

import android.content.Context
import android.util.TypedValue

object DeviceDimensionsHelper {
    // DeviceDimensionsHelper.getDisplayWidth(context) => (display width in pixels)
    fun Context.getDisplayWidth(): Float {
        val displayMetrics = resources.displayMetrics
        return displayMetrics.widthPixels.toFloat()
    }

    // DeviceDimensionsHelper.getDisplayHeight(context) => (display height in pixels)
    fun Context.getDisplayHeight(): Int {
        val displayMetrics = resources.displayMetrics
        return displayMetrics.heightPixels
    }

    // DeviceDimensionsHelper.convertDpToPixel(25f, context) => (25dp converted to pixels)
    fun Context.convertDpToPixel(dp: Float): Float {
        val r = resources
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.displayMetrics)
    }

    // DeviceDimensionsHelper.convertPixelsToDp(25f, context) => (25px converted to dp)
    fun Context.convertPixelsToDp(px: Float): Float {
        val r = resources
        val metrics = r.displayMetrics
        return px / (metrics.densityDpi / 160f)
    }
}