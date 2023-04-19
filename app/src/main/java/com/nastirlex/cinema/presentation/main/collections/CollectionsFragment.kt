package com.nastirlex.cinema.presentation.main.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.CollectionDto
import com.nastirlex.cinema.data.repositoryImpl.CollectionsRepositoryImpl
import com.nastirlex.cinema.databinding.FragmentCollectionsBinding
import com.nastirlex.cinema.presentation.main.MainFragmentDirections
import com.nastirlex.cinema.presentation.main.collections.adapter.CollectionsListAdapter
import com.nastirlex.cinema.utils.CollectionsListSpacesItemDecoration
import com.nastirlex.cinema.utils.dpToPixel

class CollectionsFragment : Fragment() {
    private lateinit var binding: FragmentCollectionsBinding

    private val collectionsRepositoryImpl by lazy { CollectionsRepositoryImpl() }
    private val collectionsViewModel by lazy { CollectionsViewModel(collectionsRepositoryImpl) }

//    private val action =
//        CollectionsFragmentDirections.actionCollectionsFragmentToCollectionInfoActivity("")

    // navController?.navigate(action)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionsBinding.inflate(inflater, container, false)

        binding.addButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToCollectionsCreateNavGraph(icon = R.drawable.ic_heart)
            Navigation.findNavController(
                requireActivity(),
                R.id.activity_main_fragment_nav_host
            ).navigate(action)
        }

        getCollections()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        getCollections()
    }

    private fun getCollections() {
        val collectionsObserver: Observer<List<CollectionDto>> = Observer {
            setupCollectionsRecyclerView(collections = it)
        }

        collectionsViewModel.collections.observe(viewLifecycleOwner, collectionsObserver)
    }

    private fun setupCollectionsRecyclerView(collections: List<CollectionDto>) {
        binding.collectionsRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        binding.collectionsRecyclerView.addItemDecoration(
            CollectionsListSpacesItemDecoration(
                topFirst = this.requireContext().dpToPixel(24f).toInt(),
                leftFirst = this.requireContext().dpToPixel(16f).toInt(),
                bottomFirst = this.requireContext().dpToPixel(16f).toInt(),
                left = this.requireContext().dpToPixel(
                    22f
                ).toInt(),
                right = this.requireContext().dpToPixel(
                    22f
                ).toInt(),
                bottom = this.requireContext().dpToPixel(10f).toInt()
            )
        )

        binding.collectionsRecyclerView.adapter = CollectionsListAdapter(collections = collections) { collection ->
            val action = MainFragmentDirections.actionMainFragmentToCollectionInfoNavGraph(
                collectionId = collection.collectionId,
                collectionName = collection.name
            )
            Navigation.findNavController(
                requireActivity(),
                R.id.activity_main_fragment_nav_host
            ).navigate(action)
        }
    }

}