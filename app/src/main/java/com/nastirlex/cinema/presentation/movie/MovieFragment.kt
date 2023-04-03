package com.nastirlex.cinema.presentation.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.nastirlex.cinema.databinding.FragmentMovieBinding
import com.nastirlex.cinema.presentation.movie.adapter.EpisodesListAdapter
import com.nastirlex.cinema.presentation.movie.adapter.FramesListAdapter
import com.nastirlex.cinema.presentation.movie.adapter.TagsListAdapter
import com.nastirlex.cinema.utils.EpisodesListSpacesItemDecoration
import com.nastirlex.cinema.utils.FramesListSpacesItemDecoration
import com.nastirlex.cinema.utils.TagsListSpacesItemDecoration
import com.nastirlex.cinema.utils.dpToPixel

class MovieFragment : Fragment() {
    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)

        binding.tagsRecyclerView.layoutManager =
            FlexboxLayoutManager(requireContext(), FlexDirection.ROW, FlexWrap.WRAP)
        binding.tagsRecyclerView.adapter = TagsListAdapter()
        binding.tagsRecyclerView.addItemDecoration(
            TagsListSpacesItemDecoration(
                bottom = requireContext().dpToPixel(8f).toInt(),
                start = requireContext().dpToPixel(4f).toInt(),
                end = requireContext().dpToPixel(4f).toInt()
            )
        )


        binding.framesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.framesRecyclerView.adapter = FramesListAdapter()
        binding.framesRecyclerView.addItemDecoration(
            FramesListSpacesItemDecoration(
                bottom = requireContext().dpToPixel(16f).toInt(),
                start = requireContext().dpToPixel(16f).toInt()
            )
        )

        binding.episodesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.episodesRecyclerView.adapter = EpisodesListAdapter()
        binding.episodesRecyclerView.addItemDecoration(
            EpisodesListSpacesItemDecoration(
                bottom = requireContext().dpToPixel(16f).toInt(),
                start = requireContext().dpToPixel(16f).toInt()
            )
        )


        return binding.root
    }
}