package com.example.giphy.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.pojo.gif.GifData
import com.example.giphy.R
import com.example.giphy.databinding.FragmentSearchBinding
import com.example.giphy.isValid
import com.example.giphy.presentation.viewmodel.GifViewModel
import com.example.giphy.presentation.adapter.GifAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GifViewModel by viewModel()

    private val gifAdapter = GifAdapter(::handleGifClicked)

    private val minSearchRequestLength: Int = 3

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchButton.setOnClickListener {
            if (binding.searchField.isValid(minSearchRequestLength)) {
                binding.progressBar.isVisible = true
                viewModel.getGifList(binding.searchField.text.toString())
            }
        }

        collectGifListFlow()
        collectErrorFlow()
        setupRecyclerView()
    }

    private fun collectGifListFlow() = lifecycleScope.launchWhenStarted {
        viewModel.gifListFlow.collect { gifList ->
            gifAdapter.attachData(gifList)
            withContext(Dispatchers.Main) {
                binding.progressBar.isVisible = false
                binding.emptyGifListHint.isVisible = gifList.isEmpty()
            }
        }
    }

    private fun collectErrorFlow() = lifecycleScope.launchWhenStarted {
        viewModel.errorFlow.collect { message ->
            withContext(Dispatchers.Main) {
                binding.progressBar.isVisible = false
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gifAdapter
        }
    }

    private fun handleGifClicked(gif: GifData) {
        findNavController().navigate(
            R.id.action_search_fragment_to_gif_fragment,
            bundleOf(
                "url" to gif.images.downsized_large.url,
                "title" to gif.title
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}