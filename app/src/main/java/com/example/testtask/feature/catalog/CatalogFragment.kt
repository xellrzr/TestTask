package com.example.testtask.feature.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.testtask.R
import com.example.testtask.databinding.FragmentCatalogBinding
import com.example.testtask.utils.catalogDiffCallback
import com.example.testtask.utils.toFormattedString
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CatalogViewModel by viewModels()
    private val adapter = CatalogAdapter(catalogDiffCallback)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCatalog.adapter = adapter

        viewModel.catalogList.observe(viewLifecycleOwner) { pagingData ->
            lifecycleScope.launch {
                adapter.submitData(pagingData)
            }
        }

        viewModel.totalSum.observe(viewLifecycleOwner) { sum ->
            binding.tvTotalSum.text = getString(R.string.catalog_total_sum, sum.toFormattedString())
        }

        adapter.addLoadStateListener { loadState ->
            if (loadState.append is LoadState.NotLoading && loadState.prepend is LoadState.NotLoading) {
                val currentItems = adapter.snapshot().items
                viewModel.updateTotalSum(currentItems)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}