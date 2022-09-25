package com.example.cryptocurrenceapp.data.api

import com.example.cryptocurrenceapp.common.Constants.API.EUR_TARGET
import com.example.cryptocurrenceapp.common.Constants.API.USD_TARGET
import com.example.cryptocurrenceapp.data.Coin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET(USD_TARGET)
    fun fetchUsdCoins(): Call<List<Coin>>

    @GET(EUR_TARGET)
    fun fetchEurCoins(): Call<List<Coin>>

    @GET
    fun fetchCoinDetails(@Url url: String): Call<DetailsResponseModel>
}