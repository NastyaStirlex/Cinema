package com.nastirlex.cinema.presentation.main.collections.collection_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.nastirlex.cinema.R
import com.nastirlex.cinema.database.entity.Film
import com.nastirlex.cinema.databinding.FragmentCollectionInfoBinding
import com.nastirlex.cinema.utils.CollectionFilmsItemListSpacesItemDecoration
import com.nastirlex.cinema.utils.dpToPixel

class CollectionInfoFragment : Fragment() {
    private lateinit var binding: FragmentCollectionInfoBinding

    private val args: CollectionInfoFragmentArgs by navArgs()

    private val collectionInfoViewModel by lazy {
        CollectionInfoViewModel(requireActivity().application, args.collectionId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionInfoBinding.inflate(inflater, container, false)

        setFragmentResult("change_icon", bundleOf("bundleKey" to args.collectionIcon))

        binding.collectionInfoBackImageButton.setOnClickListener {
            findNavController().navigateUp()
        }

        setupCollectionFilmsObserver()
        setupCollectionName()
        setupOnEditButtonClick()

        return binding.root
    }

    private fun setupCollectionName() {
        binding.collectionInfoNameTextView.text = args.collectionName
    }

    private fun setupOnEditButtonClick() {
        binding.collectionInfoEditImageButton.setOnClickListener {
            val action = CollectionInfoFragmentDirections.actionCollectionInfoFragmentToCollectionChangeFragment(
                collectionName = args.collectionName,
                collectionId = args.collectionId,
                collectionIcon = args.collectionIcon
            )
            findNavController().navigate(action)
        }
    }

    private fun setupCollectionFilmsObserver() {
        val collectionFilmsObserver = Observer<List<Film>> {
            setupCollectionFilmsRecyclerView(it)
        }

        collectionInfoViewModel.collectionFilms.observe(viewLifecycleOwner, collectionFilmsObserver)
    }

    private fun setupCollectionFilmsRecyclerView(collectionFilms: List<Film>) {
        if (collectionFilms.isNotEmpty()) {
            binding.collectionInfoCollectionFilmsRecyclerView.layoutManager =
                LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)

            binding.collectionInfoCollectionFilmsRecyclerView.adapter =
                CollectionFilmsListAdapter(collectionFilms)

            binding.collectionInfoCollectionFilmsRecyclerView.addItemDecoration(
                CollectionFilmsItemListSpacesItemDecoration(
                    bottom = this.requireContext().dpToPixel(16f).toInt(), start = 0, end = 0
                )
            )
        }
    }
}