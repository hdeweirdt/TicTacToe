package be.harm.deweirdt.tictactoe

import android.app.Application
import be.harm.deweirdt.domain.domainModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            modules(appModule, domainModule)
        }
    }
}
