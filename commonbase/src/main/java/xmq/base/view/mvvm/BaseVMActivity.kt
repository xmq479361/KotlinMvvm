package xmq.base.view.mvvm

import android.os.Bundle
import xmq.base.view.activity.BaseActivity

abstract class BaseVMActivity<VM : BaseViewModel<*>>(layoutResId: Int) :
    BaseActivity(layoutResId = layoutResId), IMvvm<VM> {

    override fun initData(bundle: Bundle?) {
        super.initData(bundle)
        startObserve(mViewModel)
    }
}