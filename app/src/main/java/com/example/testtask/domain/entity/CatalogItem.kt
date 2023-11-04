package com.example.testtask.domain.entity

data class CatalogItem(
    val title: String,
    val price: Double,
    val formattedPrice: String,
    val image: String,
    val description: String
)
