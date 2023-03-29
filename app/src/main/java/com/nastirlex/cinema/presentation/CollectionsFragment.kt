package com.nastirlex.cinema.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nastirlex.cinema.databinding.FragmentCollectionsBinding

class CollectionsFragment : Fragment() {
    private lateinit var _binding: FragmentCollectionsBinding

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollectionsBinding.inflate(inflater, container, false)

        val root = binding.root

        return root
    }
}