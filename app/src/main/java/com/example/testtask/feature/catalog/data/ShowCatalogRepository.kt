package com.example.testtask.feature.catalog.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.testtask.domain.entity.CatalogItem
import com.example.testtask.domain.usecase.ShowCatalogUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShowCatalogRepository @Inject constructor(
    private val showCatalogUseCase: ShowCatalogUseCase
) {
    fun showCatalog(): Flow<PagingData<CatalogItem>> {
        val config = PagingConfig(
            pageSize = 5,
            enablePlaceholders = true
        )
        return Pager(config) {
            CatalogPagingSource(
                showCatalogUseCase
            )
        }.flow
    }
}