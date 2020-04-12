package xmq.base.view.model

import androidx.lifecycle.MutableLiveData


//inline fun <reified UM: IUiModel> MutableLiveData<UM>.emit(builder: ()->UM){
//    value = builder()
//}

//inline fun <reified UM: IUiModel> MutableLiveData<UM>.emitValue(builder: UM.()->UM): Unit{
//    value = builder.invoke(value!!)
//}
inline fun <UM: IUiModel> MutableLiveData<UM>.emitValue(um:UM, builder: UM.()->Unit) {
     value = um?.apply(builder)
}
inline fun <reified UM: IUiModel> MutableLiveData<UM>.emitValue(creator: ()->UM,builder: UM.()->Unit) {
    value = creator().apply(builder)
}
inline fun <reified UM: IUiModel> UM.emitValue(um:UM, builder: UM.()->UM){
     builder.invoke(um)
}
inline fun <reified UM: IUiModel> UM.emitValue(builder: UM.()->UM){
    builder.invoke(this)
}
//public inline fun <T> T.apply(block: T.() -> Unit): T {
//    contract {
//        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
//    }
//    block()
//    return this
//}

fun  MutableLiveData<*>.emit2(){

}