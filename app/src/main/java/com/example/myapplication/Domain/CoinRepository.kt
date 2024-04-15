package com.example.myapplication.Domain

import androidx.lifecycle.LiveData

interface CoinRepository {
    fun getCoinInfoList() : LiveData<List<CoinInfo>>
    fun getCoinInfo(fromSymbol : String) : LiveData<CoinInfo>
    fun loadData()
}