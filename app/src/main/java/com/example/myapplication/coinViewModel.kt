package com.example.myapplication

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cripto.API.ApiFactory
import com.example.cripto.pojo.CoinPriceInfo
import com.example.cripto.pojo.CoinPriceInfoRawData
import com.example.myapplication.dataBase.DataBase
import com.google.gson.Gson
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class coinViewModel(applicaton: Application) : AndroidViewModel(applicaton) {
    private val db = DataBase.getInstance(applicaton)
    val priceList = db.coinPriceInfoDao().getPriceList()
    private val compositedisposable = CompositeDisposable()
    init {
        loadData()
    }

    fun getDetailInfo(fSym:String): LiveData<CoinPriceInfo> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }

    private fun loadData() {
        val disposable: Disposable = ApiFactory.apiservice.getTopCoinsInfo()
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",").toString() }
            .flatMap { ApiFactory.apiservice.getFullPriceList(fSyms = it) }
            .map {getPriceListFromRawData(it)}
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.coinPriceInfoDao().insertPriceList(it)
                it?.let { Log.d("5525zxc", it.toString()) }
            }, {
                Log.d("555zxc", "nice2")
            })
        compositedisposable.add(disposable)
    }

    private fun getPriceListFromRawData(coinpriceinforawdata: CoinPriceInfoRawData): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObject = coinpriceinforawdata.coinPriceInfoJsonObject
        if (jsonObject == null) {
            return result
        }
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositedisposable.dispose()
    }
}