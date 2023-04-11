package com.nastirlex.cinema.presentation.main.collections.collection_create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.repositoryImpl.CollectionsRepositoryImpl
import com.nastirlex.cinema.databinding.FragmentCollectionCreateBinding
import com.nastirlex.cinema.presentation.main.Event
import com.nastirlex.cinema.presentation.main.Status

class CollectionCreateFragment : Fragment() {
    private lateinit var binding: FragmentCollectionCreateBinding

    private val collectionsRepositoryImpl by lazy { CollectionsRepositoryImpl() }
    private val collectionCreateViewModel by lazy {
        CollectionCreateViewModel(
            collectionsRepositoryImpl
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionCreateBinding.inflate(inflater, container, false)

        binding.backImageButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.selectIconButton.setOnClickListener {
            findNavController().navigate(R.id.action_collectionCreateFragment_to_collectionIconSelectFragment)
        }





        return binding.root
    }

    override fun onStart() {
        super.onStart()
        setupScreenStateObserver()
        setupOnCollectionCreateButton()
        val iconObserver = Observer<Int> {
            binding.collectonIconImageView.setImageResource(it)
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }

        collectionCreateViewModel.icon.observe(viewLifecycleOwner, iconObserver)
    }

    private fun setupScreenStateObserver() {
        val collectionCreateScreenStateObserver = Observer<Event<Any>> {
            when (it.status) {
                Status.SUCCESS -> {
                    Navigation.findNavController(
                        requireActivity(),
                        R.id.activity_main_fragment_nav_host
                    ).navigate(R.id.mainFragment)
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.error!!, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }

        collectionCreateViewModel.collectionCreateScreenState.observe(
            viewLifecycleOwner,
            collectionCreateScreenStateObserver
        )
    }

    private fun setupOnCollectionCreateButton() {
        binding.collectionCreateButton.setOnClickListener {
            if (binding.collectionCreateNameEditText.text.toString().isBlank()) {
                Toast.makeText(requireContext(), "Name must be filled", Toast.LENGTH_SHORT).show()
            } else {
                collectionCreateViewModel.createCollection(binding.collectionCreateNameEditText.text.toString())
            }
        }
    }

}