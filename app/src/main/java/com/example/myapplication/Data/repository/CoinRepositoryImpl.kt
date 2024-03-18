package com.example.myapplication.Data.repository

import android.app.Application
import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.cripto.API.ApiFactory
import com.example.cripto.API.ApiService
import com.example.myapplication.Data.dataBase.DataBase
import com.example.myapplication.Data.mapper.CoinMapper
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

    override suspend fun loadData() {
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
}