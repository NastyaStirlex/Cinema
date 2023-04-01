package com.nastirlex.cinema.presentation.main.collections

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nastirlex.cinema.databinding.FragmentCollectionsBinding
import com.nastirlex.cinema.utils.SpacesItemDecoration
import com.nastirlex.cinema.utils.dpToPixel

class CollectionsFragment : Fragment() {
    private lateinit var _binding: FragmentCollectionsBinding

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionsBinding.inflate(inflater, container, false)

        _binding.collectionsRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        _binding.collectionsRecyclerView.adapter = CollectionsListAdapter()
        _binding.collectionsRecyclerView.addItemDecoration(
            SpacesItemDecoration(
                topFirst = this.requireContext().dpToPixel(24f).toInt(),
                leftFirst = this.requireContext().dpToPixel(16f).toInt(),
                bottomFirst = this.requireContext().dpToPixel(32f).toInt(),
                left = this.requireContext().dpToPixel(
                    22f
                ).toInt(),
                right = this.requireContext().dpToPixel(
                    22f
                ).toInt(),
                bottom = this.requireContext().dpToPixel(20f).toInt()
            )
        )

        return binding.root
    }


}