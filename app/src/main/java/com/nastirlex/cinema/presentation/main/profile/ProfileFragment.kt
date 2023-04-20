package com.nastirlex.cinema.presentation.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    private val profileViewModel by lazy { ProfileViewModel(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        setupNameObserver()
        setupEmailObserver()
        avatarEmailObserver()

        binding.logoutButton.setOnClickListener {
            Navigation.findNavController(
                requireActivity(),
                R.id.activity_main_fragment_nav_host
            ).navigate(R.id.action_mainFragment_to_authorization_nav_graph)
        }

        return binding.root
    }

    private fun setupNameObserver() {
        val nameObserver: Observer<String> = Observer {
            binding.profileNameTextView.text = it
        }

        profileViewModel.name.observe(viewLifecycleOwner, nameObserver)
    }

    private fun setupEmailObserver() {
        val emailObserver: Observer<String> = Observer {
            binding.profileEmailTextView.text = it
        }

        profileViewModel.email.observe(viewLifecycleOwner, emailObserver)
    }

    private fun avatarEmailObserver() {
        val avatarObserver: Observer<String?> = Observer {
            if (it != null)
                Glide
                    .with(binding.root)
                    .load(it).into(binding.avatarImageView)
            else {}
        }

        profileViewModel.avatar.observe(viewLifecycleOwner, avatarObserver)
    }
}