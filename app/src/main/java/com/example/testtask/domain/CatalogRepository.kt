package com.example.testtask.domain

import com.example.testtask.domain.entity.CatalogItem
import retrofit2.Response

interface CatalogRepository {

    suspend fun getCatalog(offset: Int): Response<List<CatalogItem>>
}