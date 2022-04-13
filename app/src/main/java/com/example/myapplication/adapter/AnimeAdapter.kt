package com.example.myapplication.adapter

import android.view.LayoutInflater

import android.view.ViewGroup

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.CardBinding
import com.example.myapplication.dto.Anime


interface OnInteractionListener {
    fun clickedOnCard(anime: Anime) {}
}


class AnimeAdapter(
    private val onInteractionListener: OnInteractionListener,
) : PagingDataAdapter<Anime, AnimeViewHolder>(AnimeDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = CardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val fact = getItem(position) ?: return
        holder.bind(fact)
    }
}

class AnimeViewHolder(
    private val binding: CardBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(anime: Anime) {
        val imageURL = anime.data.images.jpg.imageUrl
        binding.apply {
            titleName.text = anime.data.title
            synopsis.text = anime.data.synopsis
            score.text = anime.data.score.toString()
            Glide.with(binding.picture)
                .load(imageURL)
                .fitCenter()
                .timeout(10000)
                .into(binding.picture)

            card.setOnClickListener {
                onInteractionListener.clickedOnCard(anime)
            }

        }
    }
}

class AnimeDiffCallback : DiffUtil.ItemCallback<Anime>() {
    override fun areItemsTheSame(oldItem: Anime, newItem: Anime): Boolean {
        return oldItem.data.id == newItem.data.id
    }

    override fun areContentsTheSame(oldItem: Anime, newItem: Anime): Boolean {
        return oldItem == newItem
    }
}