package com.example.cryptocurrenceapp.domain.repositories

import com.example.cryptocurrenceapp.data.models.CoinModel

interface IListRepository {
    suspend fun fetchUsdCurrency(): List<CoinModel>?
    suspend fun fetchEurCurrency(): List<CoinModel>?
}