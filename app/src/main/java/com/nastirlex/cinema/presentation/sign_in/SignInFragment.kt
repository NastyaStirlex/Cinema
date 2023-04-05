package com.nastirlex.cinema.presentation.sign_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.data.repositoryImpl.AuthRepositoryImpl
import com.nastirlex.cinema.databinding.FragmentSignInBinding
import com.nastirlex.cinema.utils.Event
import com.nastirlex.cinema.utils.Status

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding

    private val authRepositoryImpl by lazy { AuthRepositoryImpl() }
    private val signInViewModel by lazy {
        SignInViewModel(authRepositoryImpl)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        val signInScreenStateObserver = Observer<Event<TokenDto>> { state ->
            when (state.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), state.error!!, Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {
                    Toast.makeText(requireContext(), "Loading...", Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }

        signInViewModel.signInScreenState.observe(viewLifecycleOwner, signInScreenStateObserver)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnLoginButtonClick()
    }

    private fun setupOnLoginButtonClick() {
        binding.loginButton.setOnClickListener {
            signInViewModel.onClickLogin(
                LoginBodyDto(
                    email = binding.emailEditText.text.toString(),
                    password = binding.passwordEditText.text.toString()
                )
            )
        }
    }

    private fun onToRegisterButtonClick() {
        binding.signupButton.setOnClickListener {

        }
    }
}