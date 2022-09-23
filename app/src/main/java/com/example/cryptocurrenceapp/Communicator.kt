package com.example.cryptocurrenceapp

import com.example.cryptocurrenceapp.data.Coin

interface Communicator {
    fun openDetails(coin: Coin?)
    fun goBack()
}