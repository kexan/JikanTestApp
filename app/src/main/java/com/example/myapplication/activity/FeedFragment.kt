package com.example.myapplication.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.myapplication.R
import com.example.myapplication.adapter.AnimeAdapter
import com.example.myapplication.adapter.OnInteractionListener
import com.example.myapplication.databinding.FragmentFeedBinding
import com.example.myapplication.dto.Anime
import com.example.myapplication.viewmodel.AnimeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FeedFragment : Fragment() {
    private val animeViewModel: AnimeViewModel by viewModels(ownerProducer = ::requireParentFragment)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)

        val adapter = AnimeAdapter(object : OnInteractionListener {
            override fun clickedOnCard(anime: Anime) {
                animeViewModel.holdAnime(anime)
                findNavController().navigate(
                    R.id.action_feedFragment_to_fullAnimeFragment
                )
            }

        })

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            animeViewModel.data.collectLatest {
                adapter.submitData(it)
            }
        }

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest {
                binding.swiperefresh.isRefreshing =
                    it.refresh is LoadState.Loading ||
                            it.prepend is LoadState.Loading ||
                            it.append is LoadState.Loading
            }
        }

        binding.swiperefresh.setOnRefreshListener {
            adapter.refresh()
        }


        binding.list.adapter = adapter

        return binding.root
    }
}