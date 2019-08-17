package com.lukma.hcapplication

import android.app.Application
import com.lukma.hcapplication.domain.module.useCaseModule
import com.lukma.hcapplication.presentation.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("unused")
class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AndroidApp)
            modules(
                listOf(
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
