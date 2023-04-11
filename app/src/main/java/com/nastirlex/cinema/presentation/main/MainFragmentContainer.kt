package com.nastirlex.cinema.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.CoverDto
import com.nastirlex.cinema.data.dto.EpisodeViewDto
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.data.repositoryImpl.MovieRepositoryImpl
import com.nastirlex.cinema.data.utils.Resource
import com.nastirlex.cinema.databinding.FragmentMainContainerBinding
import com.nastirlex.cinema.presentation.main.adapters.ForYouListAdapter
import com.nastirlex.cinema.presentation.main.adapters.FreshListAdapter
import com.nastirlex.cinema.presentation.main.adapters.TrendListAdapter
import com.nastirlex.cinema.utils.ForYouListSpacesItemDecoration
import com.nastirlex.cinema.utils.FreshListSpacesItemDecoration
import com.nastirlex.cinema.utils.TrendListSpacesItemDecoration
import com.nastirlex.cinema.utils.dpToPixel

class MainFragmentContainer : Fragment() {
    private lateinit var binding: FragmentMainContainerBinding

    private val movieRepositoryImpl by lazy { MovieRepositoryImpl() }
    private val mainViewModel by lazy { MainViewModel(movieRepositoryImpl) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainContainerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setupMainScreenStateObserver()
        mainViewModel.getCover()
        getInTrend()
        getFresh()
        getForYou()
        getLastView()
    }

    override fun onResume() {
        super.onResume()
        binding.shimmer.startShimmerAnimation()
    }

    private fun setupMainScreenStateObserver() {
        val mainScreenStateObserver = Observer<Resource<CoverDto>> {
            when (it) {
                is Resource.Loading -> {
                    binding.shimmer.startShimmerAnimation()
                }
                is Resource.Success -> {
                    setupCover()
                }
                else -> {}
            }
        }

        mainViewModel.mainScreenState.observe(viewLifecycleOwner, mainScreenStateObserver)
    }

    private fun setupCover() {
        val coverObserver: Observer<CoverDto> = Observer {

            binding.shimmer.apply {
                stopShimmerAnimation()
                visibility = View.GONE
            }

            Glide
                .with(this)
                .load(it.backgroundImage)
                .into(binding.bannerImageView)

            binding.bannerImageView.visibility = View.VISIBLE

            binding.mainWatchButton.visibility = View.VISIBLE

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
        if (trends.isEmpty()) {
            binding.trendGroup.visibility = View.GONE
        } else {
            binding.trendRecyclerView.layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

            binding.trendRecyclerView.adapter = TrendListAdapter(trends = trends) { movie ->
                val action = MainFragmentDirections.actionMainFragmentToMovieNavGraph(
                    movieName = movie.name,
                    movieId = movie.movieId,
                    poster = movie.poster,
                    age = movie.age,
                    frames = movie.imageUrls.map { it }.toTypedArray(),
                    description = movie.description,
                    tags = movie.tags.map { it.tagName }.toTypedArray()
                )
                Navigation.findNavController(
                    requireActivity(),
                    R.id.activity_main_fragment_nav_host
                ).navigate(action)
            }

            binding.trendRecyclerView.addItemDecoration(
                TrendListSpacesItemDecoration(
                    startFirst = requireContext().dpToPixel(16f).toInt(),
                    bottom = requireContext().dpToPixel(8f).toInt(),
                    start = requireContext().dpToPixel(8f).toInt(),
                    end = requireContext().dpToPixel(8f).toInt(),
                )
            )
        }
    }

    private fun getHistory(moviePoster: String) {
        val historyObserver: Observer<List<EpisodeViewDto>> = Observer {
            setupViewed(moviePoster, it.last())
        }

        mainViewModel.history.observe(viewLifecycleOwner, historyObserver)
    }

    private fun getLastView() {
        val lastViewObserver = Observer<List<MovieDto>> {
            getHistory(it.last().poster)
        }

        mainViewModel.lastView.observe(viewLifecycleOwner, lastViewObserver)
    }

    private fun setupViewed(moviePoster: String, episodeView: EpisodeViewDto?) {
        if (episodeView == null) {
            binding.viewedGroup.visibility = View.GONE
        } else {
            Glide.with(this)
                .load(episodeView.preview)
                .into(binding.viewedPosterImageView)

            binding.viewedNameTextView.text = episodeView.episodeName

            setupViewedGroupClick(
                episodeView.preview,
                episodeView.episodeId,
                episodeView.episodeName,
                episodeView.movieId,
                episodeView.movieName,
                moviePoster,
                episodeView.filePath
            )
        }
    }

    private fun setupViewedGroupClick(
        preview: String,
        episodeId: String,
        episodeName: String,
        movieId: String,
        movieName: String,
        moviePoster: String,
        filePath: String
    ) {
        binding.polygonImageView.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToEpisodeNavGraph(
                preview = preview,
                episodeId = episodeId,
                episodeName = episodeName,
                movieId = movieId,
                movieName = movieName,
                moviePoster = moviePoster,
                filePath = filePath
            )

            Navigation.findNavController(
                requireActivity(),
                R.id.activity_main_fragment_nav_host
            ).navigate(action)
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

        binding.freshRecyclerView.adapter = FreshListAdapter(fresh) { movie ->
            val action = MainFragmentDirections.actionMainFragmentToMovieNavGraph(
                movieName = movie.name,
                movieId = movie.movieId,
                poster = movie.poster,
                age = movie.age,
                frames = movie.imageUrls.map { it }.toTypedArray(),
                description = movie.description,
                tags = movie.tags.map {
                    it.tagName
                }.toTypedArray()
            )
            Navigation.findNavController(
                requireActivity(),
                R.id.activity_main_fragment_nav_host
            ).navigate(action)
        }

        binding.freshRecyclerView.addItemDecoration(
            FreshListSpacesItemDecoration(
                startFirst = requireContext().dpToPixel(16f).toInt(),
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
        if (forYou.isEmpty()) {
            binding.forYouGroup.visibility = View.GONE
        } else {
            binding.forYouRecyclerView.layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

            binding.forYouRecyclerView.adapter = ForYouListAdapter(forYou) { movie ->
                val action = MainFragmentDirections.actionMainFragmentToMovieNavGraph(
                    movieName = movie.name,
                    movieId = movie.movieId,
                    poster = movie.poster,
                    age = movie.age,
                    frames = movie.imageUrls.map { it }.toTypedArray(),
                    description = movie.description,
                    tags = movie.tags.map {
                        it.tagName
                    }.toTypedArray()
                )
                Navigation.findNavController(
                    requireActivity(),
                    R.id.activity_main_fragment_nav_host
                ).navigate(action)
            }

            binding.forYouRecyclerView.addItemDecoration(
                ForYouListSpacesItemDecoration(
                    startFirst = requireContext().dpToPixel(16f).toInt(),
                    bottom = requireContext().dpToPixel(8f).toInt(),
                    start = requireContext().dpToPixel(8f).toInt(),
                    end = requireContext().dpToPixel(8f).toInt(),
                )
            )
        }
    }
}