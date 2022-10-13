package com.example.cryptocurrenceapp.data.repositories

import com.example.cryptocurrenceapp.data.api.ApiClient
import com.example.cryptocurrenceapp.data.models.DetailsResponseModel
import com.example.cryptocurrenceapp.domain.repositories.IDetailsRepository
import com.example.cryptocurrenceapp.presentation.common.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailsRepository : IDetailsRepository{

    private val apiService = ApiClient.apiService

    override suspend fun fetchCOinDetails(id: String): DetailsResponseModel? {
        return withContext(Dispatchers.IO) {
            apiService.fetchCoinDetails(Constants.API.DETAILS_TARGET + id).execute().body()
        }
    }
}