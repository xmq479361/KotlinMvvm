package xmq.ext.android

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun String.toastLong(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_LONG)?.apply {
        show();
    }
}

fun String.toastShort(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT)?.apply {
        show();
    }
}

fun String.toast(context: Context, type: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, type)?.apply {
        show();
    }
}

fun Context.toast(msg: String, type: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, type)?.apply {
        show();
    }
}

fun Context.toast(@StringRes msg: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(getString(msg, duration))
}

fun Context.toastLong(@StringRes msg: Int) {
    toast(getString(msg), Toast.LENGTH_LONG)
}

fun Context.toastLong(content: String) {
    toast(content, Toast.LENGTH_LONG)
}

fun Any.toast(context: Context, content: String, duration: Int = Toast.LENGTH_SHORT) {
    context.toast(content, duration)
}

fun Any.toast(context: Context, @StringRes msg: Int, duration: Int = Toast.LENGTH_SHORT) {
    context.toast(msg, duration)
}

fun Any.toastLong(context: Context, content: String) {
    context.toastLong(content)
}

fun Any.toastLong(context: Context, @StringRes msg: Int) {
    context.toastLong(msg)
}