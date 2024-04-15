package com.example.myapplication.Presentation

import android.app.Application
import androidx.work.Configuration
import com.example.myapplication.Data.workers.RefrashDataWorkerFactory
import com.example.myapplication.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: RefrashDataWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
    override fun onCreate() {

        component.inject(this)
        super.onCreate()
    }
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}