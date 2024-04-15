package com.example.myapplication.di

import androidx.work.ListenableWorker
import com.example.myapplication.Data.workers.ChildWorkerFactory
import com.example.myapplication.Data.workers.RefrashDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefrashDataWorker::class)
    fun bindRefrashDataWorkerFactory(worker : RefrashDataWorker.Factory) : ChildWorkerFactory
}
