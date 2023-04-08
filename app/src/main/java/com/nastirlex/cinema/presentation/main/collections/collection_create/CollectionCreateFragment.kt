package com.nastirlex.cinema.presentation.main.collections.collection_create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.FragmentCollectionCreateBinding

class CollectionCreateFragment : Fragment() {
    private lateinit var binding: FragmentCollectionCreateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionCreateBinding.inflate(inflater, container, false)

        binding.backImageButton.setOnClickListener {
            Navigation.findNavController(
                requireActivity(),
                R.id.activity_main_fragment_nav_host
            ).navigateUp()
        }

        binding.selectIconButton.setOnClickListener {
            Navigation.findNavController(
                requireActivity(),
                R.id.activity_main_fragment_nav_host
            ).navigate(R.id.action_collectionCreateFragment_to_collectionIconSelectFragment)
        }

        return binding.root
    }

}