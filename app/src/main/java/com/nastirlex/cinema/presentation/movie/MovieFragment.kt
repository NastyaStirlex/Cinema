package com.nastirlex.cinema.presentation.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.EpisodeDto
import com.nastirlex.cinema.data.repositoryImpl.MovieRepositoryImpl
import com.nastirlex.cinema.databinding.FragmentMovieBinding
import com.nastirlex.cinema.presentation.main.MainFragmentDirections
import com.nastirlex.cinema.presentation.movie.adapter.EpisodesListAdapter
import com.nastirlex.cinema.presentation.movie.adapter.FramesListAdapter
import com.nastirlex.cinema.presentation.movie.adapter.TagsListAdapter
import com.nastirlex.cinema.utils.EpisodesListSpacesItemDecoration
import com.nastirlex.cinema.utils.FramesListSpacesItemDecoration
import com.nastirlex.cinema.utils.TagsListSpacesItemDecoration
import com.nastirlex.cinema.utils.dpToPixel
import com.nastirlex.cinema.websocket.ChatWebSocketListener

class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding
    private val args: MovieFragmentArgs by navArgs()

    //private val chatWebSocketListener by lazy { ChatWebSocketListener() }
    private val movieViewModel by lazy {
        MovieViewModel(
            requireActivity().application,
            args.movieId,
            args.chatId,
            //chatWebSocketListener
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        setupOnBackButtonClick()
        setupFramesRecyclerView(args.frames)
        setupTagsRecyclerView(tags = args.tags)
        setupDescription(description = args.description)
        setupAge(args.age)
        setupPoster(args.poster)
        setupEpisodesObserver()
        setupFirstEpisodeObserver()
        setupCommentsButtonClick()
    }

    private fun setupOnBackButtonClick() {
        binding.movieBackImageButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupPoster(poster: String) {
        Glide.with(binding.root).load(poster).into(binding.movieBannerImageView)
    }

    private fun setupOnWatchButtonClick(
        preview: String,
        episodeId: String,
        episodeName: String,
        filePath: String
    ) {
        binding.movieWatchButton.setOnClickListener {
            val action = MovieFragmentDirections.actionMovieFragmentToEpisodeNavGraph(
                preview = preview,
                episodeId = episodeId,
                episodeName = episodeName,
                movieId = args.movieId,
                movieName = args.movieName,
                moviePoster = args.poster,
                filePath = filePath,
                description = args.description,
                chatId = args.chatId
            )
            findNavController().navigate(action)
        }
    }

    private fun setupDescription(description: String) {
        binding.movieDescriptionTextView.text = description
    }

    private fun setupAge(age: String) {
        when (age) {
            "18+" -> {
                binding.ageTextView.setTextColor(resources.getColor(R.color.vivid_red))
            }

            "16+" -> {
                binding.ageTextView.setTextColor(resources.getColor(R.color.chinese_orange))
            }

            "12+" -> {
                binding.ageTextView.setTextColor(resources.getColor(R.color.somon))
            }

            "6+" -> {
                binding.ageTextView.setTextColor(resources.getColor(R.color.purple_white))
            }

            "0+" -> {
                binding.ageTextView.setTextColor(resources.getColor(R.color.white))
            }
        }
        binding.ageTextView.text = age
    }

    private fun setupFramesRecyclerView(frames: Array<String>) {
        binding.framesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.framesRecyclerView.adapter = FramesListAdapter(frames = frames)

        binding.framesRecyclerView.addItemDecoration(
            FramesListSpacesItemDecoration(
                bottom = requireContext().dpToPixel(16f).toInt(),
                start = requireContext().dpToPixel(16f).toInt()
            )
        )
    }

    private fun setupTagsRecyclerView(tags: Array<String>) {
        binding.tagsRecyclerView.layoutManager =
            FlexboxLayoutManager(requireContext(), FlexDirection.ROW, FlexWrap.WRAP)

        binding.tagsRecyclerView.adapter = TagsListAdapter(tags = tags)

        binding.tagsRecyclerView.addItemDecoration(
            TagsListSpacesItemDecoration(
                bottom = requireContext().dpToPixel(8f).toInt(),
                start = requireContext().dpToPixel(4f).toInt(),
                end = requireContext().dpToPixel(4f).toInt()
            )
        )
    }

    private fun setupEpisodesObserver() {
        val episodesObserver = Observer<List<EpisodeDto>> {
            setupEpisodesRecyclerView(it)
        }

        movieViewModel.episodes.observe(viewLifecycleOwner, episodesObserver)
    }

    private fun setupEpisodesRecyclerView(episodes: List<EpisodeDto>) {
        if (episodes.isNotEmpty()) {
            binding.episodesRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            binding.episodesRecyclerView.adapter = EpisodesListAdapter(episodes) {
                val action = MovieFragmentDirections.actionMovieFragmentToEpisodeNavGraph(
                    preview = it.preview,
                    episodeId = it.episodeId,
                    episodeName = it.name,
                    movieId = args.movieId,
                    movieName = args.movieName,
                    moviePoster = args.poster,
                    filePath = it.filePath,
                    description = args.description,
                    chatId = args.chatId
                )

                Navigation.findNavController(
                    requireActivity(),
                    R.id.activity_main_fragment_nav_host
                ).navigate(action)
            }

            binding.episodesRecyclerView.addItemDecoration(
                EpisodesListSpacesItemDecoration(
                    bottom = requireContext().dpToPixel(16f).toInt(),
                    start = requireContext().dpToPixel(16f).toInt()
                )
            )
        } else {
            binding.movieEpisodesGroup.visibility = View.GONE
        }
    }

    private fun setupFirstEpisodeObserver() {
        val firstEpisodeObserver = Observer<EpisodeDto?> {
            if (it != null)
                setupOnWatchButtonClick(
                    it.preview,
                    it.episodeId,
                    it.name,
                    it.filePath
                )
        }

        movieViewModel.firstEpisode.observe(viewLifecycleOwner, firstEpisodeObserver)
    }

    private fun setupCommentsButtonClick() {
        binding.commentsImageButton.setOnClickListener {
            val action = MovieFragmentDirections.actionMovieFragmentToChatNavGraph(
                id = args.chatId
            )
            findNavController().navigate(action)
        }
    }


}