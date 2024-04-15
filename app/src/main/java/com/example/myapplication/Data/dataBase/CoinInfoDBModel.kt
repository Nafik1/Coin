package com.example.myapplication.Data.dataBase

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "FullPriceList")
data class CoinInfoDBModel (
    @PrimaryKey
    val fromsymbol: String,
    val tosymbol: String? = null,
    val lastmarket: String? = null,
    val price: String? = null,
    val lastupdate: Long? = null,
    val highday: Double? = null,
    val lowday: Double? = null,
    val imageurl: String
)