package com.example.myapplication.Data.api.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "FullPriceList")
data class CoinInfoDto (
    @SerializedName("TYPE")
    @Expose
    public val type: String? = null,

    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    @Expose
    @NonNull public val fromsymbol: String,

    @SerializedName("TOSYMBOL")
    @Expose
    public val tosymbol: String? = null,

    @SerializedName("LASTMARKET")
    @Expose
    public val lastmarket: String? = null,

    @SerializedName("PRICE")
    @Expose
    public val price: String? = null,

    @SerializedName("LASTUPDATE")
    @Expose
    public val lastupdate: Long? = null,

    @SerializedName("HIGHDAY")
    @Expose
    public val highday: Double? = null,

    @SerializedName("LOWDAY")
    @Expose
    public val lowday: Double? = null,

    @SerializedName("IMAGEURL")
    @Expose
    public val imageurl: String? = null
)