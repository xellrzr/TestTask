package com.example.testtask.feature.catalog.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testtask.R
import com.example.testtask.databinding.FragmentCatalogItemDetailBinding
import com.example.testtask.utils.Constants.Companion.KEY_ITEM_IMAGE
import com.example.testtask.utils.Constants.Companion.KEY_ITEM_TITLE
import com.example.testtask.utils.loadImage

class CatalogItemDetailFragment : Fragment(R.layout.fragment_catalog_item_detail) {

    private var _binding: FragmentCatalogItemDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatalogItemDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemName: String = arguments?.getString(KEY_ITEM_TITLE).orEmpty()
        val itemImage: String = arguments?.getString(KEY_ITEM_IMAGE).orEmpty()

        with(binding) {
            tvItemTitle.text = itemName
            ivLogo.loadImage(itemImage)
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}