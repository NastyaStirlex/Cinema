package com.nastirlex.cinema.presentation.sign_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.data.repositoryImpl.AuthRepositoryImpl
import com.nastirlex.cinema.data.utils.Resource
import com.nastirlex.cinema.databinding.FragmentSignInBinding
import com.nastirlex.cinema.presentation.main.Event
import com.nastirlex.cinema.presentation.main.Status
import com.nastirlex.cinema.utils.PurchaseConfirmationDialogFragment

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding


    private val signInViewModel by lazy {
        SignInViewModel(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        setupOnLoginButtonClick()
        setupOnToRegisterButtonClick()

        val signInScreenStateObserver = Observer<Resource<TokenDto>> { state ->
            when (state) {
                is Resource.Success -> {
                    binding.loginProgressBar.visibility = View.GONE
                    Navigation.findNavController(
                        requireActivity(),
                        R.id.activity_main_fragment_nav_host
                    ).navigate(R.id.mainFragment)
                }

                is Resource.Error -> {
                    binding.loginProgressBar.visibility = View.GONE
                    PurchaseConfirmationDialogFragment(state.message!!).show(
                        childFragmentManager, PurchaseConfirmationDialogFragment.TAG)
                    signInViewModel.signInScreenState.value = Resource.Default()
                }

                is Resource.Loading -> {
                    binding.loginProgressBar.visibility = View.VISIBLE
                }

                else -> {}
            }
        }

        signInViewModel.signInScreenState.observe(viewLifecycleOwner, signInScreenStateObserver)

        return binding.root
    }

    private fun setupOnLoginButtonClick() {
        binding.loginButton.setOnClickListener {
            signInViewModel.onClickLogin(
                email = binding.emailEditText.text.toString(),
                password = binding.passwordEditText.text.toString()
            )
        }
    }

    private fun setupOnToRegisterButtonClick() {
        binding.toRegisterButton.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }
}