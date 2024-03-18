package com.example.myapplication.Data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.Data.api.model.CoinInfoDto

@Dao
interface CoinPriceInfoDao {
    @Query("SELECT * FROM FullPriceList ORDER BY lastupdate DESC")
    fun getPriceList() : LiveData<List<CoinInfoDBModel>>

    @Query("SELECT * FROM FullPriceList WHERE fromsymbol == :fSym LIMIT 1")
    fun getPriceInfoAboutCoin(fSym : String) : LiveData<CoinInfoDBModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceList(priceList : List<CoinInfoDBModel>)
}