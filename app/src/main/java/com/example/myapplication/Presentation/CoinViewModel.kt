package com.example.myapplication.Presentation

import androidx.lifecycle.ViewModel
import com.example.myapplication.Domain.GetCoinInfoListUseCase
import com.example.myapplication.Domain.GetCoinInfoUseCase
import com.example.myapplication.Domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val loaddataUseCase: LoadDataUseCase
) : ViewModel() {

    val ccoinInfoList = getCoinInfoListUseCase()

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        loaddataUseCase()
    }
}