package com.example.myapplication.Domain.Domain

import androidx.lifecycle.LiveData

class GetCoinInfoUseCase(private val coinrep : CoinRepository) {
    operator fun invoke(fromsymb : String) = coinrep.getCoinInfo(fromsymb)
}