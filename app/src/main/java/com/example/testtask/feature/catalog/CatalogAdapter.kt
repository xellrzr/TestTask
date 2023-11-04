package com.example.testtask.feature.catalog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.databinding.ItemCatalogBinding
import com.example.testtask.domain.entity.CatalogItem
import com.example.testtask.utils.loadImage

class CatalogAdapter(diffCallback: DiffUtil.ItemCallback<CatalogItem>)
    : PagingDataAdapter<CatalogItem, CatalogAdapter.CatalogViewHolder>(diffCallback) {

    inner class CatalogViewHolder(private val binding: ItemCatalogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(catalogItem: CatalogItem) {
            binding.apply {
                tvTitle.text = catalogItem.title
                tvPrice.text = catalogItem.formattedPrice
                tvAdditionalInfo.text = catalogItem.description
                ivLogo.loadImage(catalogItem.image)
            }

            itemView.setOnClickListener {
                val action = CatalogFragmentDirections.actionCatalogFragmentToCatalogItemDetailFragment(catalogItem.title, catalogItem.image)
                it.findNavController().navigate(action)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCatalogBinding.inflate(layoutInflater, parent, false)
        return CatalogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }
}

