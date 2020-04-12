package xmq.ext.android

import android.content.ClipData
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.provider.Settings
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import android.text.TextUtils
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import xmq.ext.notNull

import android.os.Looper


/**
 * Whether horizontal layout direction of this view is from Right to Left.
 */
val Context.isRTLLayout: Boolean
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    get() = resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL

/**
 * 判断网络状态是否可用
 */
fun Context.isNetworkAvailable(): Boolean = applicationContext.isNetworkAvailable()


fun fromM() = fromSpecificVersion(Build.VERSION_CODES.M)
fun beforeM() = beforeSpecificVersion(Build.VERSION_CODES.M)
fun fromN() = fromSpecificVersion(Build.VERSION_CODES.N)
fun beforeN() = beforeSpecificVersion(Build.VERSION_CODES.N)
fun fromO() = fromSpecificVersion(Build.VERSION_CODES.O)
fun beforeO() = beforeSpecificVersion(Build.VERSION_CODES.O)
fun fromP() = fromSpecificVersion(Build.VERSION_CODES.P)
fun beforeP() = beforeSpecificVersion(Build.VERSION_CODES.P)
fun fromSpecificVersion(version: Int): Boolean = Build.VERSION.SDK_INT >= version
fun beforeSpecificVersion(version: Int): Boolean = Build.VERSION.SDK_INT < version


/**
 * Sets the [text] on the clipboard
 */
fun Context.copyToClipboard(text: String, label: String = "KTX") {
    val clipData = ClipData.newPlainText(label, text)
    clipboardManager?.setPrimaryClip(clipData)
}

/**
 * Check if the accessibility Service which name is [serviceName] is enabled
 */
fun Context.checkAccessibilityServiceEnabled(serviceName: String): Boolean {
    val settingValue =
        Settings.Secure.getString(
            applicationContext.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        )
    return settingValue.notNull({
        var result = false
        val splitter = TextUtils.SimpleStringSplitter(':')
        while (splitter.hasNext()) {
            if (splitter.next().equals(serviceName, true)) {
                result = true
                break
            }
        }
        result
    }, { false })
}



/**
 *  @author: hyzhan
 *  @date:   2019/5/21
 *  @desc:   TODO
 */
// R.color.xxx  -> @ColorInt
fun Context.getColorRef(@ColorRes res: Int): Int {
    return ContextCompat.getColor(this, res)
}

fun Context.getDrawableRef(@DrawableRes res: Int): Drawable? {
    return ContextCompat.getDrawable(this, res)
}

fun View.getColorRef(@ColorRes res: Int): Int {
    return ContextCompat.getColor(this.context, res)
}

fun View.getDrawableRef(@DrawableRes res: Int): Drawable? {
    return ContextCompat.getDrawable(this.context, res)
}