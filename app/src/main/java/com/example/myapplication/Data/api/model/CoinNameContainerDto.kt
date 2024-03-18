package com.example.myapplication.Data.api.model

import androidx.annotation.NonNull
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNameContainerDto (
    @SerializedName("CoinInfo")
    @Expose
    @NonNull val coinNameDto : CoinNameDto
)