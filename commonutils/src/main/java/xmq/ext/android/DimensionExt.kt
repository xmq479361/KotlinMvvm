package xmq.ext.android

import android.content.Context
import android.content.res.Resources
import android.view.View


fun Context.dp2px(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun Context.px2dp(px: Int): Int {
    val scale = resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}

fun View.dp2px(dp: Int): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun View.px2dp(px: Int): Int {
    val scale = resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}

/**
 * 正常编码中一般只会用到 [dp]/[sp] ;
 * 其中[dp]/[sp] 会根据系统分辨率将输入的dp/sp值转换为对应的px
 */
val Number.dp: Float                 // [xxhdpi](360 -> 1080)
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics
    )

fun Number.dp2px(context: Context): Float {                // [xxhdpi](360 -> 1080)
    return (this.toFloat() * context.resources.displayMetrics.density + 0.5f)
}

val Number.sp: Float                 // [xxhdpi](360 -> 1080)
    get() = android.util.TypedValue.applyDimension(
        android.util.TypedValue.COMPLEX_UNIT_SP, this.toFloat(), Resources.getSystem().displayMetrics
    )

fun Number.px2dp(context: Context): Float {            // [xxhdpi](360 -> 1080)
    return (this.toFloat() / context.resources.displayMetrics.density + 0.5f)
}

/**
 * The absolute width of the available display size in pixels
 */
val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

/**
 * The absolute height of the available display size in pixels
 */
val Context.screenHeight
    get() = resources.displayMetrics.heightPixels

fun Context.getStatusbarHeight(): Int {
    var statusBarHeight = 0
    try {
        val c = Class.forName("com.android.internal.R\$dimen")
        val o = c.newInstance()
        val field = c.getField("status_bar_height")
        val x = field.get(o) as Int
        statusBarHeight = resources.getDimensionPixelSize(x)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return statusBarHeight
}


fun Context.getScreenHeightExcludeStatusbar(): Int {
    return resources.displayMetrics.heightPixels - getStatusbarHeight()
}