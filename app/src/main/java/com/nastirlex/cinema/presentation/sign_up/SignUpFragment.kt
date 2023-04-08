package com.nastirlex.cinema.presentation.sign_up

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
import com.nastirlex.cinema.data.dto.RegisterBodyDto
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.data.repositoryImpl.AuthRepositoryImpl
import com.nastirlex.cinema.databinding.FragmentSignUpBinding
import com.nastirlex.cinema.utils.Event
import com.nastirlex.cinema.utils.Status

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding

    private val authRepositoryImpl by lazy { AuthRepositoryImpl() }
    private val signUpViewModel by lazy {
        SignUpViewModel(authRepositoryImpl)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        val signUpScreenStateObserver = Observer<Event<TokenDto>> { state ->
            when (state.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
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

        signUpViewModel.signUpScreenState.observe(viewLifecycleOwner, signUpScreenStateObserver)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnRegisterButtonClick()
        setupOnToLoginButtonClick()
    }

    private fun setupOnRegisterButtonClick() {
        binding.registerButton.setOnClickListener {
            signUpViewModel.onClickRegister(
                RegisterBodyDto(
                    email = binding.emailEditText.text.toString(),
                    password = binding.passwordEditText.text.toString(),
                    firstName = binding.nameEditText.text.toString(),
                    lastName = binding.surnameEditText.text.toString()
                )
            )
        }
    }

    private fun setupOnToLoginButtonClick() {
        binding.toLoginButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}