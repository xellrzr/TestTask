package com.example.testtask.feature.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.testtask.domain.entity.CatalogItem
import com.example.testtask.feature.catalog.data.ShowCatalogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    rep: ShowCatalogRepository
) : ViewModel() {

    private val _totalSum = MutableLiveData(0.0)
    val totalSum: LiveData<Double> get() = _totalSum

    private val loadedItems = mutableListOf<CatalogItem>()
    val catalogList: LiveData<PagingData<CatalogItem>> = rep.showCatalog().asLiveData().cachedIn(viewModelScope)

    fun updateTotalSum(newItems: List<CatalogItem>) {
        // Фильтруем элементы, которые еще не были загружены
        val unprocessedItems = newItems.filter { item -> !loadedItems.contains(item) }
        // Добавляем новые элементы в список загруженных
        loadedItems.addAll(unprocessedItems)
        // Подсчитываем сумму новых элементов
        val newSum = unprocessedItems.sumOf { it.price }
        // Добавляем к общей сумме
        _totalSum.value = _totalSum.value!! + newSum
    }

}