package com.nastirlex.cinema.presentation.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.RegisterBodyDto
import com.nastirlex.cinema.data.dto.TokenDto
import com.nastirlex.cinema.data.repositoryImpl.AuthRepositoryImpl
import com.nastirlex.cinema.data.utils.Resource
import com.nastirlex.cinema.databinding.FragmentSignUpBinding
import com.nastirlex.cinema.presentation.main.Event
import com.nastirlex.cinema.presentation.main.Status
import com.nastirlex.cinema.utils.PurchaseConfirmationDialogFragment

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding

    private val signUpViewModel by lazy {
        SignUpViewModel(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        val signUpScreenStateObserver = Observer<Resource<TokenDto>> { state ->
            when (state) {
                is Resource.Success -> {
                    binding.registerProgressBar.visibility = View.GONE
                    Navigation.findNavController(
                        requireActivity(),
                        R.id.activity_main_fragment_nav_host
                    ).navigate(R.id.mainFragment)
                }

                is Resource.Error -> {
                    binding.registerProgressBar.visibility = View.GONE
                    PurchaseConfirmationDialogFragment(state.message!!).show(
                        childFragmentManager, PurchaseConfirmationDialogFragment.TAG
                    )
                }

                is Resource.Loading -> {
                    binding.registerProgressBar.visibility = View.VISIBLE
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
                firstName = binding.nameEditText.text.toString(),
                lastName = binding.surnameEditText.text.toString(),
                email = binding.emailEditText.text.toString(),
                password = binding.passwordEditText.text.toString(),
                repeatPassword = binding.repeatPasswordEditText.text.toString()
            )
        }
    }

    private fun setupOnToLoginButtonClick() {
        binding.toLoginButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}