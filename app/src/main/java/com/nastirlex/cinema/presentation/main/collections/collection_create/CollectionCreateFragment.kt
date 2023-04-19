package com.nastirlex.cinema.presentation.main.collections.collection_create

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.utils.Resource
import com.nastirlex.cinema.databinding.FragmentCollectionCreateBinding
import com.nastirlex.cinema.presentation.main.Event
import com.nastirlex.cinema.presentation.main.Status

class CollectionCreateFragment : Fragment() {
    private lateinit var binding: FragmentCollectionCreateBinding

    private val args: CollectionCreateFragmentArgs by navArgs()

    private val collectionCreateViewModel by lazy {
        CollectionCreateViewModel(
            application = requireActivity().application
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionCreateBinding.inflate(inflater, container, false)

        setFragmentResultListener("create_icon") { requestKey, bundle ->
            val result = bundle.getInt("bundleKey")
            binding.collectionCreateIconImageView.setImageResource(result)
            setupOnCollectionCreateButtonClick(result)
        }

        setupOnCollectionCreateButtonClick(args.icon)

        setupScreenStateObserver()


        binding.collectionCreateBackImageButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.collectionCreateSelectIconButton.setOnClickListener {
            findNavController().navigate(R.id.action_collectionCreateFragment_to_collectionIconSelectFragment)
        }

        return binding.root
    }

    private fun setupScreenStateObserver() {
        val collectionCreateScreenStateObserver = Observer<Resource<Any>> {
            when (it) {
                is Resource.Success -> {
                    findNavController().navigateUp()
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), it.message!!, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    //Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }

        collectionCreateViewModel.collectionCreateScreenState.observe(
            viewLifecycleOwner,
            collectionCreateScreenStateObserver
        )
    }

    private fun setupOnCollectionCreateButtonClick(icon: Int) {
        binding.collectionCreateSaveButton.setOnClickListener {
            if (binding.collectionCreateNameEditText.text.toString().isBlank()) {
                Toast.makeText(requireContext(), "Name must be filled", Toast.LENGTH_SHORT).show()
            } else {
                collectionCreateViewModel.createCollection(
                    name = binding.collectionCreateNameEditText.text.toString(),
                    icon = icon
                )
                //findNavController().navigateUp()
            }
        }
    }

}