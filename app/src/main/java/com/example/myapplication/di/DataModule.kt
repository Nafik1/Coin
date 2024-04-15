package com.example.myapplication.di

import android.app.Application
import com.example.cripto.API.ApiFactory
import com.example.cripto.API.ApiService
import com.example.myapplication.Data.dataBase.CoinPriceInfoDao
import com.example.myapplication.Data.dataBase.DataBase
import com.example.myapplication.Data.repository.CoinRepositoryImpl
import com.example.myapplication.Data.workers.ChildWorkerFactory
import com.example.myapplication.Data.workers.RefrashDataWorkerFactory
import com.example.myapplication.Domain.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindCoinRepository(impl : CoinRepositoryImpl) : CoinRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideCoinInfoDao(
            application: Application
        ) : CoinPriceInfoDao {
            return DataBase.getInstance(application).coinPriceInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideApiService() : ApiService {
            return ApiFactory.apiservice
        }
    }
}