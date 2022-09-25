package com.example.cryptocurrenceapp.common.Fragments

import com.example.cryptocurrenceapp.data.Coin

interface Communicator {
    fun openDetails(coin: Coin?)
    fun goBack()
}