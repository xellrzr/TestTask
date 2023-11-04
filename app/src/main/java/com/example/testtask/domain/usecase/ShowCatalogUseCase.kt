package com.example.testtask.domain.usecase

import com.example.testtask.domain.CatalogRepository
import com.example.testtask.domain.entity.CatalogItem
import retrofit2.Response
import javax.inject.Inject

class ShowCatalogUseCase @Inject constructor(
    private val catalogRepository: CatalogRepository
) {
    suspend operator fun invoke(offset:Int): Response<List<CatalogItem>> {
        return catalogRepository.getCatalog(offset)
    }
}