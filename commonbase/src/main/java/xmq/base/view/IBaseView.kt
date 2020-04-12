package xmq.base.view
import android.os.Bundle
import androidx.annotation.LayoutRes

interface IBaseView {
     val layoutResId: Int
    fun initView()
    fun initData(bundle: Bundle?)
}