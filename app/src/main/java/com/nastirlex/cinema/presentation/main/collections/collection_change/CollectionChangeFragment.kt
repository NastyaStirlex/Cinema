package com.nastirlex.cinema.presentation.main.collections.collection_change

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.FragmentCollectionChangeBinding

class CollectionChangeFragment : Fragment() {
    private lateinit var binding: FragmentCollectionChangeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionChangeBinding.inflate(inflater, container, false)

        return binding.root
    }

}