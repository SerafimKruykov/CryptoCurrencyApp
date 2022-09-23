package com.example.cryptocurrenceapp

class Constants {

    object API {
        const val BASE_URL = "https://api.coingecko.com/api/v3/"
        const val USD_TARGET =
            "coins/markets?vs_currency=usd&order=market_cap_desc&per_page=20&page=1&sparkline=false&price_change_percentage=24h"
        const val EUR_TARGET =
            "coins/markets?vs_currency=eur&order=market_cap_desc&per_page=20&page=1&sparkline=false&price_change_percentage=24h"
        const val DETAILS_TARGET = "coins/"
    }

    object Recycler{
        const val RED_COLOR = "#EB5757"
    }

    object DetailsFragment{
        const val ARG_KEY = "passed_coin"
    }
}