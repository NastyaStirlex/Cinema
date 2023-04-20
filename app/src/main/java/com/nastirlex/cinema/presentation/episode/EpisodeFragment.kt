package com.nastirlex.cinema.presentation.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ForwardingPlayer
import com.nastirlex.cinema.R
import com.nastirlex.cinema.database.entity.Collection
import com.nastirlex.cinema.databinding.FragmentEpisodeBinding
import kotlinx.coroutines.Dispatchers


class EpisodeFragment : Fragment() {
    private lateinit var binding: FragmentEpisodeBinding
    private val args: EpisodeFragmentArgs by navArgs()

    private val episodeViewModel by lazy {
        EpisodeViewModel(
            requireActivity().application,
            args.movieId,
            args.episodeId
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false)

        episodeViewModel.getFavouritesId()
        episodeViewModel.isFavouriteFilm(args.movieId)

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

        setupOnDeleteFromFavouritesClick()
        setupOnAddToFavouritesClick()

        setupEpisodeTimeObserver()
        setupIsFavouriteFilmObserver()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        episodeViewModel.getFavouritesId()
        episodeViewModel.isFavouriteFilm(args.movieId)
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
        Glide.with(binding.root).load(args.moviePoster).into(binding.episodeFilmImageView)
    }

    private fun setupYears() {
        val yearsObserver = Observer<String> {
            binding.yearsTextView.text = it
        }

        episodeViewModel.years.observe(viewLifecycleOwner, yearsObserver)
    }

    private fun setupCollectionsObserver() {
        val collectionsObserver = Observer<List<Collection>> {
            setupCollectionsSpinner(it)
        }

        episodeViewModel.collections.observe(viewLifecycleOwner, collectionsObserver)
    }

    private fun setupCollectionsSpinner(collections: List<Collection>) {
        binding.collectionsSpinner.visibility = View.INVISIBLE

        val collectionsArray =
            arrayOf("выберите коллекцию...") + collections.map { it.name }.toTypedArray()

        val adapter = ArrayAdapter(
            requireActivity().application,
            R.layout.item_collections_spinner,
            collectionsArray
        )

        //val adapter = SpinnerAdapter(requireActivity().application, collectionsArray)
        //binding.collectionsSpinner.prompt = "dfghjkjhgfddfghjkjhg"

        adapter.setDropDownViewResource(R.layout.item_dropdown_spinner)

        binding.collectionsSpinner.adapter = adapter
        binding.collectionsSpinner.setSelection(0)

        binding.collectionsSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0) {
                        episodeViewModel.addFilmToCollection(
                            poster = args.moviePoster,
                            name = args.movieName,
                            description = args.description,
                            collectionId = collections[position - 1].id,
                            id = args.movieId
                        )
                    }



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

    private fun setupOnDeleteFromFavouritesClick() {

        binding.filledImageView.setOnClickListener {
            episodeViewModel.deleteFilmFromFavourites()
            binding.filledImageView.visibility = View.INVISIBLE
            binding.emptyImageButton.visibility = View.VISIBLE
        }

    }

    private fun setupOnAddToFavouritesClick() {
        binding.emptyImageButton.setOnClickListener {
            episodeViewModel.addFilmToFavourites(
                poster = args.moviePoster,
                name = args.movieName,
                description = args.description,
                id = args.movieId
            )
            binding.emptyImageButton.visibility = View.INVISIBLE
            binding.filledImageView.visibility = View.VISIBLE
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

    private fun setupIsFavouriteFilmObserver() {
        val isFavouriteObserver: Observer<Boolean> = Observer {
            when (it) {
                true -> {
                    binding.filledImageView.visibility = View.VISIBLE
                    binding.emptyImageButton.visibility = View.INVISIBLE
                }

                false -> {
                    binding.emptyImageButton.visibility = View.VISIBLE
                    binding.filledImageView.visibility = View.INVISIBLE
                }
            }
        }

        episodeViewModel.isFavourite.observe(viewLifecycleOwner, isFavouriteObserver)
    }


}