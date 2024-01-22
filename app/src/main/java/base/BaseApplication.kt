package base

import android.app.Application
import android.content.Context
import com.ajiedwi.prototype.learnktorandsqldelight.BuildConfig
import timber.log.Timber

class BaseApplication: Application() {

    companion object {
        lateinit var applicationContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        BaseApplication.applicationContext = applicationContext
    }
}