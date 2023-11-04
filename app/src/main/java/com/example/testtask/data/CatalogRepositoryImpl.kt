package com.example.testtask.data

import com.example.testtask.data.remote.RemoteDataSource
import com.example.testtask.data.remote.entity.CatalogRequest
import com.example.testtask.domain.CatalogRepository
import com.example.testtask.domain.entity.CatalogItem
import com.example.testtask.utils.Constants.Companion.PAGE_LIMIT
import retrofit2.Response
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : CatalogRepository {

    override suspend fun getCatalog(offset: Int): Response<List<CatalogItem>> {
        val request = CatalogRequest(
            region = CatalogRequest.Region(
                levels = listOf(
                    "0c5b2444-70a0-4932-980c-b4dc0d3f02b5",
                    null,
                    null
                ),
                lat = 55.693394,
                lng = 37.557502
            ),
            category = listOf(460),
            limit = PAGE_LIMIT,
            offset = offset
        )
        return remoteDataSource.getCatalog(request)
    }
}