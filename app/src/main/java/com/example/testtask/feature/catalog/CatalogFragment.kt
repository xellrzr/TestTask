package com.example.testtask.feature.catalog

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.testtask.R
import com.example.testtask.databinding.FragmentCatalogBinding
import com.example.testtask.utils.catalogDiffCallback
import com.example.testtask.utils.toFormattedString
import com.google.android.material.snackbar.Snackbar
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

        adapter.addLoadStateListener { loadState ->

            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading

            if (loadState.append is LoadState.NotLoading && loadState.prepend is LoadState.NotLoading) {
                val currentItems = adapter.snapshot().items
                viewModel.updateTotalSum(currentItems)
            }

            val errorState = when {
                loadState.mediator?.refresh is LoadState.Error -> loadState.mediator?.refresh as LoadState.Error
                loadState.mediator?.append is LoadState.Error -> loadState.mediator?.append as LoadState.Error
                loadState.mediator?.prepend is LoadState.Error -> loadState.mediator?.prepend as LoadState.Error
                loadState.source.refresh is LoadState.Error -> loadState.source.refresh as LoadState.Error
                loadState.source.append is LoadState.Error -> loadState.source.append as LoadState.Error
                loadState.source.prepend is LoadState.Error -> loadState.source.prepend as LoadState.Error
                else -> null
            }

            errorState?.let {
                showSnackBarError(it.error.message ?: "")
            }

        }

        viewModel.catalogList.observe(viewLifecycleOwner) { pagingData ->
            lifecycleScope.launch {
                adapter.submitData(pagingData)
            }
        }

        viewModel.totalSum.observe(viewLifecycleOwner) { sum ->
            binding.tvTotalSum.text = getString(R.string.catalog_total_sum, sum.toFormattedString())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showSnackBarError(message: String) {
        val errorMessage = getString(R.string.catalog_error) + message
        Snackbar.make(binding.root,  errorMessage, Snackbar.LENGTH_INDEFINITE).apply {
            val view = this.view
            val params = view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.CENTER
            view.layoutParams = params
            setAction(getString(R.string.catalog_repeat)) {
                adapter.retry()
            }
            show()
        }
    }

}