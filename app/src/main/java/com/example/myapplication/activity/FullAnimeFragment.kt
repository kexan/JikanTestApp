package com.example.myapplication.activity

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentFullAnimeBinding
import com.example.myapplication.viewmodel.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class FullAnimeFragment : Fragment() {
    private val animeViewModel: AnimeViewModel by viewModels(ownerProducer = ::requireParentFragment)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFullAnimeBinding.inflate(inflater, container, false)
        val anime = animeViewModel.currentAnime
        val imageUrl = anime.data.images.jpg.largeImgUrl
        binding.apply {
            titleName.text = anime.data.title
            originalName.text = anime.data.titleJapanese
            if (anime.data.year != null) {
                year.text = "Год выпуска: " + anime.data.year.toString()
            } else {
                year.text = "Год не указан"
            }

            episodesCount.text = "Количество эпизодов: " + anime.data.episodes.toString()
            synopsis.text = anime.data.synopsis
            synopsis.movementMethod = ScrollingMovementMethod()

            score.text = anime.data.score.toString()
            Glide.with(binding.picture)
                .load(imageUrl)
                .fitCenter()
                .timeout(10000)
                .into(binding.picture)
        }
        return binding.root
    }
}