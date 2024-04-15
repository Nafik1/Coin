package com.example.myapplication.Domain

data class CoinInfo(
    val fromsymbol: String,
    val tosymbol: String? = null,
    val lastmarket: String? = null,
    val price: String? = null,
    val lastupdate: String? = null,
    val highday: Double? = null,
    val lowday: Double? = null,
    val imageurl: String
)