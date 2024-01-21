package base

import android.app.Application
import com.ajiedwi.prototype.learnktorandsqldelight.BuildConfig
import timber.log.Timber

class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}