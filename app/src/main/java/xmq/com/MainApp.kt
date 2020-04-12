package xmq.com

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.elvishew.xlog.XLog
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import xmq.util.SingletonHolder

class MainApp: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
    override fun onCreate() {
        super.onCreate()
//        MainApp.get
        XLog.init()
        startKoin{
            // Use Koin Android Logger
            androidLogger()
            this.androidContext(this@MainApp)
            modules(appModules)
        }
    }
}