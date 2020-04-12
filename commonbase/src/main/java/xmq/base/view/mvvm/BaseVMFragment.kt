package xmq.base.view.mvvm

import android.os.Bundle
import androidx.lifecycle.ViewModel
import xmq.base.view.fragment.BaseFragment

abstract class BaseVMFragment<VM: BaseViewModel<*>>(layoutResId: Int): BaseFragment(layoutResId = layoutResId), IMvvm<VM> {

    override fun initData(bundle: Bundle?) {
        super.initData(bundle)
        startObserve(mViewModel)
    }
}