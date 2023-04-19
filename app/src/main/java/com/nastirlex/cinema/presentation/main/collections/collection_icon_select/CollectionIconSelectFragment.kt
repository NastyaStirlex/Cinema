package com.nastirlex.cinema.presentation.main.collections.collection_icon_select

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.nastirlex.cinema.data.repositoryImpl.CollectionsRepositoryImpl
import com.nastirlex.cinema.databinding.FragmentCollectionIconSelectBinding
import com.nastirlex.cinema.presentation.main.collections.collection_create.CollectionCreateViewModel
import com.nastirlex.cinema.utils.CollectionItemListSpacesItemDecoration
import com.nastirlex.cinema.utils.dpToPixel

class CollectionIconSelectFragment : Fragment() {
    private lateinit var binding: FragmentCollectionIconSelectBinding

    private val collectionsRepositoryImpl by lazy { CollectionsRepositoryImpl() }
    private val collectionCreateViewModel by lazy {
        CollectionCreateViewModel(
            requireActivity().application
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionIconSelectBinding.inflate(inflater, container, false)

        binding.backImageButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.iconsRecyclerView.layoutManager = GridLayoutManager(this.requireContext(), 4)

        /* pass item data to activity / fragment when clicked */
        binding.iconsRecyclerView.adapter = CollectionIconListAdapter { icon ->
            setFragmentResult("icon", bundleOf("bundleKey" to icon))

            findNavController().popBackStack()
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