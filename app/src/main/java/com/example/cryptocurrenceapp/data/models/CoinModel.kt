package com.example.cryptocurrenceapp.data.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinModel(
    @Json(name = "id")
    val id: String?,

    @Json(name = "symbol")
    val symbol: String?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "image")
    val image: String?,

    @Json(name = "current_price")
    val current_price: String?,

    @Json(name = "price_change_percentage_24h")
    val price_change_percentage_24h: String?
    ): Parcelable
