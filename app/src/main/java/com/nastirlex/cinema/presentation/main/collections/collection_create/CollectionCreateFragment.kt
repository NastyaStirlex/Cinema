package com.nastirlex.cinema.presentation.main.collections.collection_create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.FragmentCollectionCreateBinding

class CollectionCreateFragment : Fragment() {
    private lateinit var binding: FragmentCollectionCreateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionCreateBinding.inflate(inflater, container, false)

        return binding.root
    }

}