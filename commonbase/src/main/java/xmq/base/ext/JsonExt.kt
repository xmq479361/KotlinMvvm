package xmq.base.ext

import com.google.gson.Gson

 fun Any.toJson(): String{
    return Gson().toJson(this)
}