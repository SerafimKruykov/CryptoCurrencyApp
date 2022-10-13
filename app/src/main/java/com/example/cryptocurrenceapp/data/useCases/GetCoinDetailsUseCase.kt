package com.example.cryptocurrenceapp.data.useCases

import com.example.cryptocurrenceapp.data.models.DetailsResponseModel
import com.example.cryptocurrenceapp.domain.repositories.IDetailsRepository
import com.example.cryptocurrenceapp.domain.useCases.IGetCoinDetailsUseCase

class GetCoinDetailsUseCase(val repository: IDetailsRepository): IGetCoinDetailsUseCase{

    override suspend fun execute(id: String): DetailsResponseModel? {
        return repository.fetchCOinDetails(id)
    }
}