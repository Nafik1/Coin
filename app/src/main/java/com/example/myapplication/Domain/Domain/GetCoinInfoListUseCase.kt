package com.example.myapplication.Domain.Domain

import androidx.lifecycle.LiveData

class GetCoinInfoListUseCase(private val coinrep : CoinRepository) {
    operator fun invoke() = coinrep.getCoinInfoList()
}