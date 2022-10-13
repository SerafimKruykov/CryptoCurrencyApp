package com.example.cryptocurrenceapp.data.useCases

import com.example.cryptocurrenceapp.data.models.CoinModel
import com.example.cryptocurrenceapp.domain.repositories.IListRepository
import com.example.cryptocurrenceapp.domain.useCases.IGetEurCoinsUseCase

class GetEurCoinsUseCase(val repository: IListRepository): IGetEurCoinsUseCase {

    override suspend fun execute() : List<CoinModel>?{
        return repository.fetchEurCurrency()
    }

}