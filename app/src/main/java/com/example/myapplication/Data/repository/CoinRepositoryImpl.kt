package com.example.myapplication.Data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.myapplication.Data.dataBase.CoinPriceInfoDao
import com.example.myapplication.Data.mapper.CoinMapper
import com.example.myapplication.Data.workers.RefrashDataWorker
import com.example.myapplication.Domain.CoinInfo
import com.example.myapplication.Domain.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val application: Application,
    private val coinInfoDao : CoinPriceInfoDao,
    private val mapper : CoinMapper
) : CoinRepository {

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