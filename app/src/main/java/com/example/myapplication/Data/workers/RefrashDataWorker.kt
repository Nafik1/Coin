package com.example.myapplication.Data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.cripto.API.ApiFactory
import com.example.myapplication.Data.dataBase.DataBase
import com.example.myapplication.Data.mapper.CoinMapper

import kotlinx.coroutines.delay
import java.lang.reflect.Parameter

class RefrashDataWorker(
    context : Context,
    workerParameter: WorkerParameters
    ) : CoroutineWorker(context,workerParameter) {
    private val coinInfoDao = DataBase.getInstance(context).coinPriceInfoDao()
    private val mapper = CoinMapper()
    private val apiservice = ApiFactory.apiservice
    override suspend fun doWork(): Result {
        while(true) {
            try {
                val topCoins = apiservice.getTopCoinsInfo(limit = 50)
                val fromSymbls = mapper.mapNamesListToString(topCoins)
                val jsonContaine = apiservice.getFullPriceList(fSyms = fromSymbls)
                val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContaine)
                val dbModelList = coinInfoDtoList.map { mapper.mapDtotoDbModel(it) }
                coinInfoDao.insertPriceList(dbModelList)
            } catch (e : Exception) {

            }
            delay(10000)
        }
    }
    companion object {
        const val NAME = "RefreshDataWork"
        fun makeRequest() : OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefrashDataWorker>().build()
        }



    }
}