package com.example.cryptocurrenceapp.domain.useCases

import com.example.cryptocurrenceapp.data.models.DetailsResponseModel

interface IGetCoinDetailsUseCase {
    suspend fun execute(id: String): DetailsResponseModel?
}