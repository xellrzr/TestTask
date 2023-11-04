package com.example.testtask.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.testtask.domain.entity.CatalogItem

val catalogDiffCallback = object : DiffUtil.ItemCallback<CatalogItem>() {
    override fun areItemsTheSame(oldItem: CatalogItem, newItem: CatalogItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: CatalogItem, newItem: CatalogItem): Boolean {
        return oldItem == newItem
    }
}