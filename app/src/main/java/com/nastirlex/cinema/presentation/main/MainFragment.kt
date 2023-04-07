package com.nastirlex.cinema.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.nastirlex.cinema.databinding.FragmentMainBinding
import com.nastirlex.cinema.presentation.main.adapters.ForYouListAdapter
import com.nastirlex.cinema.presentation.main.adapters.FreshListAdapter
import com.nastirlex.cinema.presentation.main.adapters.TrendListAdapter

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        // TODO: add padding (decoration) for each recuclerView
        binding.trendRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.trendRecyclerView.adapter = TrendListAdapter()

        binding.freshRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.freshRecyclerView.adapter = FreshListAdapter()

        binding.forYouRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        binding.forYouRecyclerView.adapter = ForYouListAdapter()

        return binding.root
    }
}