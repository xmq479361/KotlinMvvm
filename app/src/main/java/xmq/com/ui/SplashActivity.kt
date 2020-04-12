package xmq.com.ui

import android.os.Bundle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import xmq.base.view.activity.BaseActivity
import xmq.com.MainActivity
import xmq.com.R
import xmq.ext.android.startKtxActivity

class SplashActivity() : BaseActivity(layoutResId = R.layout.main_act_splash) {

    override fun initData(bundle: Bundle?) {
        super.initData(bundle)
        launch {
            delay(2000)
            startKtxActivity<MainActivity>()
            finish()
        }
    }
}