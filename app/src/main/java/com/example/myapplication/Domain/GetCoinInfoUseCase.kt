package com.example.myapplication.Domain

import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(
    private val coinrep : CoinRepository
) {
    operator fun invoke(fromsymb : String) = coinrep.getCoinInfo(fromsymb)
}