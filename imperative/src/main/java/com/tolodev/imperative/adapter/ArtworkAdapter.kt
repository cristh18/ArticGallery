package com.tolodev.imperative.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tolodev.imperative.databinding.ItemArtworkBinding
import com.tolodev.imperative.model.ImperativeArtwork

class ArtworkAdapter : ListAdapter<ImperativeArtwork, ArtworkAdapter.ArtworkViewHolder>(ArtworkDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtworkViewHolder {
        val binding = ItemArtworkBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArtworkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtworkViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ArtworkViewHolder(
        private val binding: ItemArtworkBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(imperativeArtwork: ImperativeArtwork) {
            binding.apply {
                tvTitle.text = imperativeArtwork.title
                tvArtist.text = imperativeArtwork.artist
                tvDate.text = imperativeArtwork.date
                
                // Load image using Glide
                Glide.with(itemView)
                    .load(imperativeArtwork.imageUrl)
                    .centerCrop()
                    .into(ivArtwork)
            }
        }
    }

    private class ArtworkDiffCallback : DiffUtil.ItemCallback<ImperativeArtwork>() {
        override fun areItemsTheSame(oldItem: ImperativeArtwork, newItem: ImperativeArtwork): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImperativeArtwork, newItem: ImperativeArtwork): Boolean {
            return oldItem == newItem
        }
    }
}
