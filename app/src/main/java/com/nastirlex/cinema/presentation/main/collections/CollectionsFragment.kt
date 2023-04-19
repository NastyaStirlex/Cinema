package com.nastirlex.cinema.presentation.main.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.nastirlex.cinema.R
import com.nastirlex.cinema.database.Collection
import com.nastirlex.cinema.databinding.FragmentCollectionsBinding
import com.nastirlex.cinema.presentation.main.MainFragmentDirections
import com.nastirlex.cinema.presentation.main.collections.adapter.CollectionsListAdapter

class CollectionsFragment : Fragment() {
    private lateinit var binding: FragmentCollectionsBinding

    private val collectionsViewModel by lazy { CollectionsViewModel(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionsBinding.inflate(inflater, container, false)

        binding.addButton.setOnClickListener {
            val icon = R.drawable.ic_heart
            setFragmentResult("icon", bundleOf("bundleKey" to icon))

            val action = MainFragmentDirections.actionMainFragmentToCollectionsCreateNavGraph()
            Navigation.findNavController(
                requireActivity(),
                R.id.activity_main_fragment_nav_host
            ).navigate(action)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        collectionsViewModel.getCollections()

        setupCollectionsObserver()
    }


    private fun setupCollectionsObserver() {
        val collectionsObserver: Observer<List<Collection>> = Observer {
            setupCollectionsRecyclerView(collections = it)
        }

        collectionsViewModel.collections.observe(viewLifecycleOwner, collectionsObserver)
    }

    private fun setupCollectionsRecyclerView(collections: List<Collection>) {
        binding.collectionsRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        binding.collectionsRecyclerView.adapter =
            CollectionsListAdapter(collections = collections) { collection ->
                val action = MainFragmentDirections.actionMainFragmentToCollectionInfoNavGraph(
                    collectionId = collection.id,
                    collectionName = collection.name,
                    collectionIcon = collection.icon
                )
                Navigation.findNavController(
                    requireActivity(),
                    R.id.activity_main_fragment_nav_host
                ).navigate(action)
            }
    }

}