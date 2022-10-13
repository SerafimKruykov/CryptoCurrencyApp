package com.example.cryptocurrenceapp.domain.useCases

import com.example.cryptocurrenceapp.data.models.CoinModel

interface IGetEurCoinsUseCase {
    suspend fun execute(): List<CoinModel>?
}