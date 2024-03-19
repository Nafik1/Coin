package com.example.myapplication.Presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cripto.API.ApiFactory
import com.example.myapplication.Data.api.model.CoinInfoDto
import com.example.myapplication.Data.api.model.CoinInfoJsonContainerDto
import com.example.myapplication.Data.dataBase.DataBase
import com.example.myapplication.Data.repository.CoinRepositoryImpl
import com.example.myapplication.Domain.Domain.CoinInfo
import com.example.myapplication.Domain.Domain.CoinRepository
import com.example.myapplication.Domain.Domain.GetCoinInfoListUseCase
import com.example.myapplication.Domain.Domain.GetCoinInfoUseCase
import com.example.myapplication.Domain.Domain.LoadDataUseCase
import com.google.gson.Gson
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class coinViewModel(applicaton: Application) : AndroidViewModel(applicaton) {
    private val repository = CoinRepositoryImpl(applicaton)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val loaddataUseCase = LoadDataUseCase(repository)
    private val db = DataBase.getInstance(applicaton)
    val ccoinInfoList = getCoinInfoListUseCase()


    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)


    init {
        loaddataUseCase()
    }
}