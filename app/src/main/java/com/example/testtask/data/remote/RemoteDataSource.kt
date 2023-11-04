package com.example.testtask.data.remote

import com.example.testtask.data.remote.entity.CatalogRequest
import com.example.testtask.domain.entity.CatalogItem
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val naPolkeService: NaPolkeService
) {

    suspend fun getCatalog(catalogRequest: CatalogRequest): Response<List<CatalogItem>> {
        return naPolkeService.getCatalog(catalogRequest).asCatalogItem()
    }
}