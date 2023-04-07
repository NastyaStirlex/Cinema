package com.nastirlex.cinema.presentation.main.collections.collection_icon_select

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.FragmentCollectionIconSelectBinding
import com.nastirlex.cinema.utils.CollectionItemListSpacesItemDecoration
import com.nastirlex.cinema.utils.dpToPixel

class CollectionIconSelectFragment : Fragment() {
    private lateinit var binding: FragmentCollectionIconSelectBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionIconSelectBinding.inflate(inflater, container, false)

        binding.iconsRecyclerView.layoutManager = GridLayoutManager(this.requireContext(), 4)

        /* pass item dato to activity / fragment when clicked */
        binding.iconsRecyclerView.adapter = CollectionIconListAdapter { position ->
            Toast.makeText(
                this.context,
                "item $position clicked",
                Toast.LENGTH_LONG
            ).show()
        }

        binding.iconsRecyclerView.addItemDecoration(
            CollectionItemListSpacesItemDecoration(
                bottom = this.requireContext().dpToPixel(
                    16f
                ).toInt(),
                start = this.requireContext().dpToPixel(5f).toInt(),
                end = this.requireContext().dpToPixel(5f).toInt()
            )
        )

        return binding.root
    }
}