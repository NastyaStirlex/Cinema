package com.nastirlex.cinema.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.CoverDto
import com.nastirlex.cinema.data.dto.EpisodeDto
import com.nastirlex.cinema.data.dto.EpisodeViewDto
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.data.repositoryImpl.MovieRepositoryImpl
import com.nastirlex.cinema.databinding.FragmentMainContainerBinding
import com.nastirlex.cinema.presentation.main.adapters.ForYouListAdapter
import com.nastirlex.cinema.presentation.main.adapters.FreshListAdapter
import com.nastirlex.cinema.presentation.main.adapters.TrendListAdapter
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

        // TODO: add padding (decoration) for each recuclerView

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val navController = Navigation.findNavController(requireActivity(), R.id.activity_main_fragment_nav_host)
        //val mainGraph = navController.navInflater.inflate(R.navigation.app_nav_graph)
        //navController.graph = mainGraph
    }

    override fun onStart() {
        super.onStart()
        setupCover()
        getInTrend()
        getViewed()
        getFresh()
        getForYou()
        setupLastView()

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
        if (trends.isEmpty()) {
            binding.trendGroup.visibility = View.GONE
        } else {
            binding.trendRecyclerView.layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

            binding.trendRecyclerView.adapter = TrendListAdapter(trends = trends) { movie ->
                val action = MainFragmentDirections.actionMainFragmentToMovieNavGraph(
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

    private fun getViewed() {
        val viewedObserver: Observer<List<EpisodeViewDto>> = Observer {
            setupViewedImageView(it.firstOrNull())
        }

        mainViewModel.history.observe(viewLifecycleOwner, viewedObserver)
    }

    private fun setupViewedImageView(episodeView: EpisodeViewDto?) {
        if (episodeView == null) {
            binding.viewedGroup.visibility = View.GONE
        } else {
            Glide.with(this).load(episodeView.preview).into(binding.viewedPosterImageView)
            binding.viewedNameTextView.text = episodeView.episodeName
            setupViewedGroupClick(
                episodeView.preview,
                episodeView.episodeId,
                episodeView.episodeName,
                episodeView.movieId,
                episodeView.movieName,
                episodeView.filePath,
                episodeView.time
            )
        }
    }

    private fun setupViewedGroupClick(
        preview: String,
        episodeId: String,
        episodeName: String,
        movieId: String,
        movieName: String,
        filePath: String,
        position: Int
    ) {
        //getEpisodes(movieId)
        //val episodes = mainViewModel.episodes.value
        //Toast.makeText(requireContext(), episodes.size.toString(), Toast.LENGTH_LONG).show()

        binding.polygonImageView.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToEpisodeNavGraph(
                preview = preview,
                episodeId = episodeId,
                episodeName = episodeName,
                movieId = movieId,
                movieName = movieName,
                filePath = filePath,
                position = position
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
                poster = movie.poster,
                age = movie.age,
                frames = movie.imageUrls.map { it }.toTypedArray(),
                description = movie.description,
                tags = movie.tags.map {
                    it.tagName
                }.toTypedArray()
            )
            Toast.makeText(requireContext(), "", Toast.LENGTH_LONG).show()

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

            binding.forYouRecyclerView.adapter = ForYouListAdapter(forYou)
        }
    }

    private fun setupLastView() {

    }
}