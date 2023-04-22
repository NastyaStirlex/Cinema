package com.nastirlex.cinema.presentation.main.compilation

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.data.repositoryImpl.MovieRepositoryImpl
import com.nastirlex.cinema.databinding.FragmentCompilationBinding
import com.nastirlex.cinema.utils.CompilationSpacesItemDecoration

class CompilationFragment : Fragment() {

    private lateinit var binding: FragmentCompilationBinding


    private val compilationViewModel by lazy {
        CompilationViewModel(
            requireActivity().application
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompilationBinding.inflate(inflater, container, false)

        compilationViewModel.getCompilation()
        setupCompilationObserver()

        return binding.root
    }

    private fun setupCompilationObserver() {
        val compilationObserver = Observer<List<MovieDto>> {
            setupCompilationSwipeCards(it)
        }

        compilationViewModel.compilation.observe(viewLifecycleOwner, compilationObserver)
    }

    private fun setupCompilationSwipeCards(compilation: List<MovieDto>) {
        val compilationArray = compilation.toMutableList()
        val adapter = CompilationAdapter(compilationArray)

        binding.swipe.background = resources.getDrawable(R.drawable.transparent_background)

        binding.swipe.adapter = adapter

        binding.swipe.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {
            override fun removeFirstObjectInAdapter() {
                compilationArray.removeAt(0)
                adapter.notifyDataSetChanged()
            }

            override fun onLeftCardExit(p0: Any?) {
                val movie = p0 as MovieDto
                compilationViewModel.deleteFilmFromFavourites(movie.movieId)
                compilationViewModel.deleteFilmFromCompilation(movie.movieId)
            }

            override fun onRightCardExit(p0: Any?) {
                val movie = p0 as MovieDto

                compilationViewModel.addFilmToFavourites(
                    poster = movie.poster,
                    name = movie.name,
                    description = movie.description,
                    id = movie.movieId
                )
                compilationViewModel.deleteFilmFromCompilation(movie.movieId)
            }

            override fun onAdapterAboutToEmpty(p0: Int) {}

            override fun onScroll(p0: Float) {}
        })

        binding.swipe.setOnItemClickListener { p0, _ ->
            Toast.makeText(
                requireContext(),
                "data is $p0 " + compilationArray[p0].name,
                Toast.LENGTH_SHORT
            )
                .show()
        }

        binding.like.setOnClickListener {
            binding.swipe.topCardListener.selectRight()
        }

        binding.dislike.setOnClickListener {
            binding.swipe.topCardListener.selectLeft()
        }

    }

}