package com.nastirlex.cinema.presentation.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ForwardingPlayer
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.CollectionDto
import com.nastirlex.cinema.data.repositoryImpl.CollectionsRepositoryImpl
import com.nastirlex.cinema.data.repositoryImpl.EpisodesRepositoryImpl
import com.nastirlex.cinema.data.repositoryImpl.MovieRepositoryImpl
import com.nastirlex.cinema.databinding.FragmentEpisodeBinding

class EpisodeFragment : Fragment() {
    private lateinit var binding: FragmentEpisodeBinding
    private val args: EpisodeFragmentArgs by navArgs()

    private val movieRepositoryImpl by lazy { MovieRepositoryImpl() }
    private val collectionsRepositoryImpl by lazy { CollectionsRepositoryImpl() }
    private val episodesRepositoryImpl by lazy { EpisodesRepositoryImpl() }

    private val episodeViewModel by lazy {
        EpisodeViewModel(
            args.movieId,
            args.episodeId,
            movieRepositoryImpl,
            collectionsRepositoryImpl,
            episodesRepositoryImpl
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setupPlayer()
        setupEpisodeName()
        setupFilmName()

        setupDescription()
        setupFilmPoster()
        setupYears()
        setupCollectionsObserver()
        setupOnAddToCollectionButtonClick()
        setupOnVideoClick()
        setupOnBackButtonClick()
        setupEpisodeTimeObserver()
    }

    private fun setupEpisodeName() {
        binding.episodeTitleTextView.text = args.episodeName
    }

    private fun setupFilmName() {
        binding.episodeFilmNameTextView.text = args.movieName
    }

    private fun setupPlayer() {

        val exoPlayer = ExoPlayer.Builder(requireContext()).build().apply {
            setMediaItem(com.google.android.exoplayer2.MediaItem.fromUri(args.filePath))
            playWhenReady = false
            prepare()
        }

        var forwardingPlayer: ForwardingPlayer = object : ForwardingPlayer(exoPlayer) {
            override fun isCommandAvailable(command: Int): Boolean {
                if (command == COMMAND_GET_AUDIO_ATTRIBUTES) {
                    return false
                }
                return super.isCommandAvailable(command)
            }

        }

        binding.episodeStyledPlayerView.apply {
            player = forwardingPlayer
        }
    }

    private fun setupOnVideoClick() {
        binding.episodeStyledPlayerView.setOnClickListener {
            binding.episodeStyledPlayerView.player?.apply {
                if (isPlaying) stop() else prepare()
            }
        }
    }

    private fun setupDescription() {
        val descriptionObserver = Observer<String> {
            binding.descriptionTextView.text = it
        }

        episodeViewModel.description.observe(viewLifecycleOwner, descriptionObserver)
    }

    private fun setupFilmPoster() {
        //val filmPosterObserver = Observer<List<MovieDto>> {
            Glide.with(binding.root).load(args.moviePoster).into(binding.episodeFilmImageView)
        //}

        //episodeViewModel.viewed.observe(viewLifecycleOwner, filmPosterObserver)
    }

    private fun setupYears() {
        val yearsObserver = Observer<String> {
            binding.yearsTextView.text = it
        }

        episodeViewModel.years.observe(viewLifecycleOwner, yearsObserver)
    }

    private fun setupCollectionsObserver() {
        val collectionsObserver = Observer<List<CollectionDto>> {
            setupCollectionsSpinner(it)
        }

        episodeViewModel.collections.observe(viewLifecycleOwner, collectionsObserver)
    }

    private fun setupCollectionsSpinner(collections: List<CollectionDto>) {
        binding.collectionsSpinner.visibility = View.INVISIBLE

        val collectionsArray = collections.map { it.name }.toTypedArray()

        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.item_collections_spinner,
            collectionsArray
        )

        adapter.setDropDownViewResource(R.layout.item_dropdown_spinner)

        binding.collectionsSpinner.adapter = adapter

        binding.collectionsSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    binding.collectionsSpinner.visibility = View.INVISIBLE
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    binding.collectionsSpinner.visibility = View.INVISIBLE
                }
            }

    }

    private fun setupOnAddToCollectionButtonClick() {
        binding.addImageButton.setOnClickListener {
            binding.collectionsSpinner.performClick()
        }
    }

    private fun setupOnBackButtonClick() {
        binding.collectionChangeBackImageButton.setOnClickListener {
            episodeViewModel.saveEpisodeTime(
                binding.episodeStyledPlayerView.player?.currentPosition?.div(1000)?.toInt()
            )
            binding.episodeStyledPlayerView.player?.release()
            findNavController().navigateUp()
        }
    }

    private fun setupEpisodeTimeObserver() {
        val episodeTimeObserver = Observer<Int> {
            binding.episodeStyledPlayerView.player?.seekTo((it * 1000).toLong())
            binding.episodeStyledPlayerView.player?.play()
        }

        episodeViewModel.episodeTime.observe(viewLifecycleOwner, episodeTimeObserver)
    }

}