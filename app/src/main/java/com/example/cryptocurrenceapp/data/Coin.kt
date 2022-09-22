package com.example.cryptocurrenceapp.data

class Coin(
    val name: String,
    val tag: String,
    val icon: String,
    val currency: String,
    val usd: String,
    val eur: String,
    val currencyChange: String,
    private val description: String,
    private val categories: String
)