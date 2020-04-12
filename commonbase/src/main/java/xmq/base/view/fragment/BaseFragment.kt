package xmq.base.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import xmq.base.view.IBaseView

open class BaseFragment(override val layoutResId: Int) : Fragment(), IBaseView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView();
        initData(savedInstanceState);
    }

    @CallSuper
    override fun initView() {

    }

    @CallSuper
    override fun initData(bundle: Bundle?) {
    }

}