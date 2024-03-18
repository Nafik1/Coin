package com.example.myapplication.Domain.Domain

import androidx.annotation.NonNull
import androidx.room.PrimaryKey
import com.example.cripto.API.ApiFactory

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfo(
    val fromsymbol: String,
    val tosymbol: String? = null,


    val lastmarket: String? = null,


    val price: String? = null,


    val lastupdate: String? = null,


    val highday: Double? = null,


    val lowday: Double? = null,


    val imageurl: String
) {


}