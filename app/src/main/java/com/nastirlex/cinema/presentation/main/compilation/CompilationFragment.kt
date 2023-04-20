package com.nastirlex.cinema.presentation.main.compilation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.data.repositoryImpl.MovieRepositoryImpl
import com.nastirlex.cinema.databinding.FragmentCompilationBinding
import com.nastirlex.cinema.databinding.FragmentEpisodeBinding
import com.nastirlex.cinema.presentation.main.MainViewModel
import com.nastirlex.cinema.utils.CompilationSpacesItemDecoration

class CompilationFragment : Fragment() {

    private lateinit var binding: FragmentCompilationBinding

    private val movieRepositoryImpl by lazy { MovieRepositoryImpl() }
    private val compilationViewModel by lazy { CompilationViewModel(movieRepositoryImpl) }

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
            setupRecycler(it)
            //setupCompilationSwipeCards(it)
        }

        compilationViewModel.compilation.observe(viewLifecycleOwner, compilationObserver)
    }

    private fun setupRecycler(compilation: List<MovieDto>) {
        binding.recycler.addItemDecoration(CompilationSpacesItemDecoration())

        binding.recycler.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

        binding.recycler.adapter = CompilationAdapter(compilation)


    }

    private fun setupCompilationSwipeCards(compilation: List<MovieDto>) {
        val compilationArray = compilation.toMutableList()
        val adapter = CompilationAdapter(compilationArray)

//        binding.swipe.background = resources.getDrawable(R.drawable.transparent_background)
//
//        binding.swipe.adapter = adapter
//
//        binding.swipe.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {
//            override fun removeFirstObjectInAdapter() {
//                compilationArray.removeAt(0)
//                adapter.notifyDataSetChanged()
//            }
//
//            override fun onLeftCardExit(p0: Any?) {
//                val movie = p0 as MovieDto
//                compilationViewModel.deleteFilmFromCompilation(movie.movieId)
//            }
//
//            override fun onRightCardExit(p0: Any?) {
//                val movie = p0 as MovieDto
//                // TODO: request to db
//                Toast.makeText(requireContext(), "like", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onAdapterAboutToEmpty(p0: Int) {}
//
//            override fun onScroll(p0: Float) {}
//        })
//
//        binding.swipe.setOnItemClickListener { p0, _ ->
//            Toast.makeText(requireContext(), "data is $p0 " + compilationArray[p0].name, Toast.LENGTH_SHORT)
//                .show()
//        }
//
//        binding.like.setOnClickListener {
//            binding.swipe.topCardListener.selectRight()
//        }
//
//        binding.dislike.setOnClickListener {
//            binding.swipe.topCardListener.selectLeft()
//        }

    }

}