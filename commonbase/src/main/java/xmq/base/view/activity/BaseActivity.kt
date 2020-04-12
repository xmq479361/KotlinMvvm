package xmq.base.view.activity

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import xmq.base.view.IBaseView
import kotlin.coroutines.CoroutineContext

open class BaseActivity(override val layoutResId: Int) : AppCompatActivity(), CoroutineScope, IBaseView {
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job ()
        setContentView(layoutResId)
        initView();
        initData(savedInstanceState);
    }

    @CallSuper
    override fun initView() {

    }

    @CallSuper
    override fun initData(bundle: Bundle?) {
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}