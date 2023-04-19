package com.nastirlex.cinema.presentation.main.collections.collection_change

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.FragmentCollectionChangeBinding

class CollectionChangeFragment : Fragment() {
    private lateinit var binding: FragmentCollectionChangeBinding

    private val collectionChangeViewModel by lazy {
        CollectionChangeViewModel(
            requireActivity().application,
            args.collectionId
        )
    }

    private val args: CollectionChangeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionChangeBinding.inflate(inflater, container, false)

        setFragmentResultListener("change_icon") { requestKey, bundle ->
            val result = bundle.getInt("bundleKey")
            binding.collectionChangeIconImageView.setImageResource(result)
            setupOnSaveButtonClick(result)
        }

        setupOnSaveButtonClick(args.collectionIcon)

        setupCollectionName()
        setupOnBackButtonClick()
        setupOnSelectIconButtonClick()
        setupOnDeleteButtonClick()

        return binding.root
    }

    private fun setupOnBackButtonClick() {
        binding.collectionChangeBackImageButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupOnSelectIconButtonClick() {
        binding.collectionChangeSelectIconButton.setOnClickListener {
            findNavController().navigate(R.id.action_collectionChangeFragment_to_collectionIconSelectFragment2)
        }
    }

    private fun setupOnSaveButtonClick(icon: Int) {
        binding.collectionChangeSaveButton.setOnClickListener {
            if (binding.collectionChangeNameEditText.text.toString().isBlank()) {
                Toast.makeText(requireContext(), "Name must be filled", Toast.LENGTH_SHORT).show()
            } else {
                collectionChangeViewModel.updateCollection(
                    name = binding.collectionChangeNameEditText.text.toString(),
                    icon = icon
                )
                findNavController().navigateUp()
            }
        }
    }

    private fun setupOnDeleteButtonClick() {
        binding.collectionChangeDeleteButton.setOnClickListener {
            collectionChangeViewModel.deleteCollection(args.collectionId)
            Navigation.findNavController(
                requireActivity(),
                R.id.activity_main_fragment_nav_host
            ).navigate(R.id.mainFragment)
        }
    }

    private fun setupCollectionName() {
        binding.collectionChangeNameEditText.setText(args.collectionName)
    }


}