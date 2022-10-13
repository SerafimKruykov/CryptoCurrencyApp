package com.example.cryptocurrenceapp.data.models

import com.squareup.moshi.Json

data class DetailsResponseModel(
    @Json(name = "id")
    val id: String?,
    @Json(name = "categories")
    val categories: List<String?>,
    @Json(name = "description")
    val description: Description?,
    @Json(name = "large")
    val image: String?
)

data class Description(
    @Json(name = "en")
    val en: String?
)