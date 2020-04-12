package xmq.base.ext

import com.elvishew.xlog.XLog
import xmq.base.BuildConfig
import xmq.ext.TAG

/**
 * Created by luyao
 * on 2019/7/3 15:37
 */

const val TAG = "ktx"

var showLog = BuildConfig.DEBUG

private enum class LEVEL {
    V, D, I, W, E
}

fun Any.logv(message: String) =
    log(LEVEL.V, this.TAG, message)

fun Any.logd(message: String) =
    log(LEVEL.D, this.TAG, message)

fun Any.logi(message: String, tag: String = this.TAG) =
    log(LEVEL.I, tag, message)

fun Any.logw(message: String) =
    log(LEVEL.W, this.TAG, message)

fun Any.loge(message: String) =
    log(LEVEL.E, this.TAG, message)

fun String.logv(tag: String = TAG) =
    log(LEVEL.V, tag, this)

fun String.logd(tag: String = TAG) =
    log(LEVEL.D, tag, this)

fun String.logi(tag: String = TAG) =
    log(LEVEL.I, tag, this)

fun String.logw(tag: String = TAG) =
    log(LEVEL.W, tag, this)

fun String.loge(tag: String = TAG) =
    log(LEVEL.E, tag, this)

//val FORMAT = "%s: %s"
private fun log(level: LEVEL, tag: String, message: String) {
    if (!showLog) return
    when (level) {
        LEVEL.V -> XLog.tag(tag).v(message)
        LEVEL.D -> XLog.tag(tag).d(message)
        LEVEL.I -> XLog.tag(tag).i(message)
        LEVEL.W -> XLog.tag(tag).w(message)
        LEVEL.E -> XLog.tag(tag).e(message)
    }
}