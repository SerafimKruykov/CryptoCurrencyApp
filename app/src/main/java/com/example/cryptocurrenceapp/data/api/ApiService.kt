package com.example.cryptocurrenceapp.data.api

import com.example.cryptocurrenceapp.presentation.common.Constants.API.EUR_TARGET
import com.example.cryptocurrenceapp.presentation.common.Constants.API.USD_TARGET
import com.example.cryptocurrenceapp.data.models.CoinModel
import com.example.cryptocurrenceapp.data.models.DetailsResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET(USD_TARGET)
    fun fetchUsdCoins(): Call<List<CoinModel>>

    @GET(EUR_TARGET)
    fun fetchEurCoins(): Call<List<CoinModel>>

    @GET
    fun fetchCoinDetails(@Url url: String): Call<DetailsResponseModel>
}