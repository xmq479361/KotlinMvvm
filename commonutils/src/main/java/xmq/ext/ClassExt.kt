package xmq.ext

import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType


@Suppress("UNCHECKED_CAST")
fun <T> Any.getTypeClass(): Class<T> {
    // 通过反射 获取当前类的父类的泛型 (T) 对应 Class类
    return (javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments[0]
            as Class<T>
}

@Suppress("UNCHECKED_CAST")
fun <T> Field.getFiledClazz() = genericType as Class<T>