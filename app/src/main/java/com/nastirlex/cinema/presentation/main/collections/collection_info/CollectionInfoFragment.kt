package com.nastirlex.cinema.presentation.main.collections.collection_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.FragmentCollectionInfoBinding
import com.nastirlex.cinema.utils.CollectionFilmsItemListSpacesItemDecoration
import com.nastirlex.cinema.utils.dpToPixel

class CollectionInfoFragment : Fragment() {
    private lateinit var binding: FragmentCollectionInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionInfoBinding.inflate(inflater, container, false)

        binding.collectionFilmsRecyclerView.layoutManager =
            LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.collectionFilmsRecyclerView.adapter = CollectionFilmsListAdapter()
        binding.collectionFilmsRecyclerView.addItemDecoration(
            CollectionFilmsItemListSpacesItemDecoration(bottom = this.requireContext().dpToPixel(16f).toInt(), start = 0, end = 0)
        )


        binding.backImageButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }
}