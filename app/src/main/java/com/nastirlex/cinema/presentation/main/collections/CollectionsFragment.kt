package com.nastirlex.cinema.presentation.main.collections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.FragmentCollectionsBinding
import com.nastirlex.cinema.presentation.main.collections.adapter.CollectionsListAdapter
import com.nastirlex.cinema.utils.SpacesItemDecoration
import com.nastirlex.cinema.utils.dpToPixel

class CollectionsFragment : Fragment() {
    private lateinit var binding: FragmentCollectionsBinding

//    private val action =
//        CollectionsFragmentDirections.actionCollectionsFragmentToCollectionInfoActivity("")

    // navController?.navigate(action)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionsBinding.inflate(inflater, container, false)

        //setSupportActionBar(binding.myToolbar)

        binding.collectionsRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        binding.collectionsRecyclerView.adapter = CollectionsListAdapter()
        binding.collectionsRecyclerView.addItemDecoration(
            SpacesItemDecoration(
                topFirst = this.requireContext().dpToPixel(24f).toInt(),
                leftFirst = this.requireContext().dpToPixel(16f).toInt(),
                bottomFirst = this.requireContext().dpToPixel(32f).toInt(),
                left = this.requireContext().dpToPixel(
                    22f
                ).toInt(),
                right = this.requireContext().dpToPixel(
                    22f
                ).toInt(),
                bottom = this.requireContext().dpToPixel(20f).toInt()
            )
        )

        binding.addButton.setOnClickListener {
            val navController = Navigation.findNavController(binding.root)
            navController.navigate(R.id.action_collectionsFragment_to_collectionCreateFragment)
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navController = Navigation.findNavController(binding.root)
        super.onViewCreated(view, savedInstanceState)
    }

}