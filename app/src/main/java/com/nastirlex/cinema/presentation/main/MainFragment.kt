package com.nastirlex.cinema.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.FragmentMainBinding
import com.nastirlex.cinema.utils.setupWithNavController

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

//    override fun onStart() {
//        super.onStart()
//        setupBottomNavigationBar()
//    }

    private fun setupBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.main_nav_graph,
            R.navigation.compilation_nav_graph,
            R.navigation.collections_nav_graph,
            R.navigation.profile_nav_graph
        )

        binding.fragmentMainBottomNavigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.fragment_main_nav_host_container,
            intent = requireActivity().intent
        )

    }
}