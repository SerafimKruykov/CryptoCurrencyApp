package com.example.cryptocurrenceapp.presentation.common.Fragments

import com.example.cryptocurrenceapp.data.models.CoinModel

interface Communicator {
    fun openDetails(coin: CoinModel?)
    fun goBack()
}