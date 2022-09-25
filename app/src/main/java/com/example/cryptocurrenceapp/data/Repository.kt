package com.example.cryptocurrenceapp.data

import com.example.cryptocurrenceapp.common.Constants.API.DETAILS_TARGET
import com.example.cryptocurrenceapp.data.api.ApiClient
import com.example.cryptocurrenceapp.data.api.DetailsResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class Repository {

    private val apiService = ApiClient.apiService

    suspend fun fetchUsdCurrency(): List<Coin>? {

        return withContext(Dispatchers.IO) {
            apiService.fetchUsdCoins().execute().body()
        }
    }

    suspend fun fetchEurCurrency(): List<Coin>? {
        return withContext(Dispatchers.IO) {
            apiService.fetchEurCoins().execute().body()
        }
    }

    suspend fun fetchCOinDetails(id: String): DetailsResponseModel? {
        return withContext(Dispatchers.IO) {
            apiService.fetchCoinDetails(DETAILS_TARGET + id).execute().body()
        }
    }
}

