package com.nastirlex.cinema.presentation.sign_in

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nastirlex.cinema.R
import androidx.fragment.app.viewModels
import com.nastirlex.cinema.data.dto.LoginBodyDto
import com.nastirlex.cinema.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private val signInViewModel by viewModels<SignInViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        val root = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onLoginButtonClick()
    }

    private fun onLoginButtonClick() {
        binding.loginButton.setOnClickListener {
//            signInViewModel.onClickLogin(
//                LoginBodyDto(
//                    email = binding.emailEditText.text.toString(),
//                    password = binding.passwordEditText.text.toString()
//                )
//            )
        }
    }

    private fun onToRegisterButtonClick() {
        binding.signupButton.setOnClickListener {

        }
    }
}