package com.example.myapplication.Data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.cripto.API.ApiFactory
import com.example.cripto.API.ApiService
import com.example.myapplication.Data.dataBase.CoinPriceInfoDao
import com.example.myapplication.Data.dataBase.DataBase
import com.example.myapplication.Data.mapper.CoinMapper
import javax.inject.Inject
import javax.inject.Provider

class RefrashDataWorkerFactory @Inject constructor(
    private val workerProviders : @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when(workerClassName) {
            RefrashDataWorker::class.qualifiedName -> {
                val childWorkerFactory = workerProviders[RefrashDataWorker::class.java]?.get()
                return childWorkerFactory?.create(appContext,workerParameters)
            }
            else -> null
        }
    }
}