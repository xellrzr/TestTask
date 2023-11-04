package com.example.testtask.feature.catalog.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testtask.domain.entity.CatalogItem
import com.example.testtask.domain.usecase.ShowCatalogUseCase
import javax.inject.Inject

class CatalogPagingSource @Inject constructor(
    private val showCatalogUseCase: ShowCatalogUseCase
) : PagingSource<Int, CatalogItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatalogItem> {
        try {
            val offset = params.key ?: 0

            val response = showCatalogUseCase(offset)

            if (response.body()?.isEmpty() == true) {
                return LoadResult.Error(Exception("Error fetch data"))
            }

            val list = response.body() ?: emptyList()

            return LoadResult.Page(
                data = list,
                prevKey = if (offset == 0) null else offset - 5,
                nextKey = if (list.isEmpty()) null else offset + 5
            )
        } catch (t: Throwable) {
            return LoadResult.Error(t)
        }
    }

    override val keyReuseSupported: Boolean
        get() = true

    override fun getRefreshKey(state: PagingState<Int, CatalogItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
