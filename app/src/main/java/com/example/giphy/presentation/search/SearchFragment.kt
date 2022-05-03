package com.example.giphy.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.pojo.GifData
import com.example.giphy.databinding.FragmentSearchBinding
import com.example.giphy.isValid
import com.example.giphy.presentation.adapter.GifAdapter
import kotlinx.coroutines.flow.collect
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
                viewModel.getGifList(binding.searchField.text.toString())
            }
        }

        collectGifListFlow()
        setupRecyclerView()
    }

    private fun collectGifListFlow() = lifecycleScope.launchWhenStarted {
        viewModel.gifListFlow.collect { gifList ->
            gifAdapter.attachData(gifList)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gifAdapter
        }
    }

    private fun handleGifClicked(gif: GifData) {
        Toast.makeText(requireContext(), "${gif.title}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}