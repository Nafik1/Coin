package com.example.myapplication.Domain

import javax.inject.Inject

class GetCoinInfoListUseCase @Inject constructor(
    private val coinrep : CoinRepository
) {
    operator fun invoke() = coinrep.getCoinInfoList()
}