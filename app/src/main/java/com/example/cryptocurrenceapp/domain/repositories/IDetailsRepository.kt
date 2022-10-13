package com.example.cryptocurrenceapp.domain.repositories

import com.example.cryptocurrenceapp.data.models.DetailsResponseModel
import com.example.cryptocurrenceapp.presentation.common.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface IDetailsRepository {
    suspend fun fetchCOinDetails(id: String): DetailsResponseModel?
}