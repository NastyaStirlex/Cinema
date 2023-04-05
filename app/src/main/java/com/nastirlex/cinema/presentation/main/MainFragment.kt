package com.nastirlex.cinema.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nastirlex.cinema.data.dto.CoverDto
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.data.repositoryImpl.MovieRepositoryImpl
import com.nastirlex.cinema.databinding.FragmentMainBinding
import com.nastirlex.cinema.utils.FreshListSpacesItemDecoration
import com.nastirlex.cinema.utils.dpToPixel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val movieRepositoryImpl by lazy { MovieRepositoryImpl() }
    private val mainViewModel by lazy { MainViewModel(movieRepositoryImpl) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        // TODO: add padding (decoration) for each recuclerView

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setupCover()
        getInTrend()
        getViewed()
        getFresh()
        getForYou()
    }

    private fun setupCover() {
        val coverObserver: Observer<CoverDto> = Observer {
            Glide.with(this).load(it.backgroundImage).into(binding.bannerImageView)
        }

        mainViewModel.cover.observe(viewLifecycleOwner, coverObserver)

    }

    private fun getInTrend() {
        val trendsObserver: Observer<List<MovieDto>> = Observer {
            setupTrendRecyclerView(trends = it)
        }

        mainViewModel.trends.observe(viewLifecycleOwner, trendsObserver)
    }

    private fun setupTrendRecyclerView(trends: List<MovieDto>) {
        binding.trendRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.trendRecyclerView.adapter = TrendListAdapter(trends = trends)
    }

    private fun getViewed() {
        val viewedObserver: Observer<List<MovieDto>> = Observer {
            setupViewedImageView(it.firstOrNull()?.poster)
        }

        mainViewModel.viewed.observe(viewLifecycleOwner, viewedObserver)
    }

    private fun setupViewedImageView(poster: String?) {
        if (poster == null) {
            binding.viewedGroup.visibility = View.GONE
        } else {
            Glide.with(this).load(poster).into(binding.viewedFilmImageView)
        }

    }

    private fun getFresh() {
        val freshObserver: Observer<List<MovieDto>> = Observer {
            setupFreshRecyclerView(fresh = it)
        }

        mainViewModel.fresh.observe(viewLifecycleOwner, freshObserver)
    }

    private fun setupFreshRecyclerView(fresh: List<MovieDto>) {
        binding.freshRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.freshRecyclerView.adapter = FreshListAdapter(fresh)
        binding.freshRecyclerView.addItemDecoration(
            FreshListSpacesItemDecoration(
                bottom = requireContext().dpToPixel(8f).toInt(),
                start = requireContext().dpToPixel(8f).toInt(),
                end = requireContext().dpToPixel(8f).toInt(),
            )
        )
    }

    private fun getForYou() {
        val forYouObserver: Observer<List<MovieDto>> = Observer {
            setupForYouRecyclerView(forYou = it)
        }

        mainViewModel.forYou.observe(viewLifecycleOwner, forYouObserver)
    }

    private fun setupForYouRecyclerView(forYou: List<MovieDto>) {
        binding.forYouRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.forYouRecyclerView.adapter = ForYouListAdapter(forYou)
    }
}