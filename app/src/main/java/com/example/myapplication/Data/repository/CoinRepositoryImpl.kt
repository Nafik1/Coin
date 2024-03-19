package com.example.myapplication.Data.repository

import android.app.Application
import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cripto.API.ApiFactory
import com.example.cripto.API.ApiService
import com.example.myapplication.Data.dataBase.DataBase
import com.example.myapplication.Data.mapper.CoinMapper
import com.example.myapplication.Data.workers.RefrashDataWorker
import com.example.myapplication.Domain.Domain.CoinInfo
import com.example.myapplication.Domain.Domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    private val application: Application
) : CoinRepository {
    private val coinInfoDao = DataBase.getInstance(application).coinPriceInfoDao()
    private val mapper = CoinMapper()
    private val apiservice = ApiFactory.apiservice
    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        val coinInfoList = coinInfoDao.getPriceList()
        return coinInfoList.map {
            it.map {
                mapper.mapDbModeltoEntity(it)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        val coinInfo = coinInfoDao.getPriceInfoAboutCoin(fromSymbol)
        return coinInfo.map {
            mapper.mapDbModeltoEntity(it)
        }
    }

    override fun loadData(){
        val workmanager = WorkManager.getInstance(application)
        workmanager.enqueueUniqueWork(
            RefrashDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefrashDataWorker.makeRequest()
        )
    }
}