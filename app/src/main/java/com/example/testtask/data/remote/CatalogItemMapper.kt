package com.example.testtask.data.remote

import com.example.testtask.data.remote.entity.CatalogResponse
import com.example.testtask.domain.entity.CatalogItem
import com.example.testtask.utils.toFormattedPrice
import com.example.testtask.utils.toImageUrl
import retrofit2.Response

fun Response<CatalogResponse>.asCatalogItem(): Response<List<CatalogItem>> {
    return Response.success(body()?.let { response ->
        response.data.map {
            CatalogItem(
                title = it.name,
                price = it.price,
                formattedPrice = it.price.toFormattedPrice(),
                image = it.images.firstOrNull().toImageUrl(),
                description = it.seoName
            )
        }
    })
}

