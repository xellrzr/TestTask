package com.example.testtask.data.remote

import com.example.testtask.data.remote.entity.CatalogRequest
import com.example.testtask.data.remote.entity.CatalogResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NaPolkeService {

    @POST("/catalog-api/search/catalog")
    suspend fun getCatalog(
        @Body request: CatalogRequest
    ) : Response<CatalogResponse>
}
