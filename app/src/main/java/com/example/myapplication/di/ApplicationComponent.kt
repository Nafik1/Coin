package com.example.myapplication.di

import android.app.Application
import com.example.myapplication.Presentation.CoinDetailFragment
import com.example.myapplication.Presentation.CoinListActivity
import com.example.myapplication.Presentation.CoinApp
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class, WorkerModule::class])
interface ApplicationComponent {

    fun inject(activity : CoinListActivity)

    fun inject(fragment : CoinDetailFragment)

    fun inject(application: CoinApp)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ) : ApplicationComponent
    }
}