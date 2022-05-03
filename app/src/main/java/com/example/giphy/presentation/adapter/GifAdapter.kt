package com.example.giphy.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.pojo.gif.GifData
import com.example.giphy.databinding.ItemGifBinding

class GifAdapter(private val clickListener: (GifData) -> Unit) : RecyclerView.Adapter<GifAdapter.ViewHolder>() {

    private var gifList: List<GifData> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun attachData(gifList: List<GifData>) {
        this.gifList = gifList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemGifBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(gifList[position])
    }

    override fun getItemCount() = gifList.size

    inner class ViewHolder(private val binding: ItemGifBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gif: GifData) {
            Glide.with(binding.root.context)
                .load(gif.images.downsized_medium.url)
                .into(binding.preview)

            binding.root.setOnClickListener {
                clickListener.invoke(gif)
            }
        }
    }
}