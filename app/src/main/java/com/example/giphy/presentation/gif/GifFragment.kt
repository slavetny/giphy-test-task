package com.example.giphy.presentation.gif

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.giphy.databinding.FragmentGifBinding

class GifFragment : Fragment() {

    private var _binding: FragmentGifBinding? = null
    private val binding get() = _binding!!

    private val args: GifFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGifBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupGif()
    }

    private fun setupGif() {
        Glide.with(requireContext())
            .load(args.url)
            .into(binding.gif)

        binding.gifTitle.text = args.title

        binding.openButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(args.url))
            startActivity(browserIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}