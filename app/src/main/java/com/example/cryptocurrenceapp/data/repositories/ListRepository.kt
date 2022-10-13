package com.example.cryptocurrenceapp.data.repositories

import com.example.cryptocurrenceapp.data.api.ApiClient
import com.example.cryptocurrenceapp.data.models.CoinModel
import com.example.cryptocurrenceapp.domain.repositories.IListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListRepository : IListRepository{

    private val apiService = ApiClient.apiService

    override suspend fun fetchUsdCurrency(): List<CoinModel>? {

        return withContext(Dispatchers.IO) {
            apiService.fetchUsdCoins().execute().body()
        }
    }

    override suspend fun fetchEurCurrency(): List<CoinModel>? {
        return withContext(Dispatchers.IO) {
            apiService.fetchEurCoins().execute().body()
        }
    }

}