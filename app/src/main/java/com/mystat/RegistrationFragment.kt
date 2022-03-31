package com.mystat

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import timber.log.Timber

import com.mystat.databinding.FragmentRegistrationBinding
import com.mystat.utils.LoginState
import kotlinx.coroutines.launch


class RegistrationFragment : Fragment(R.layout.fragment_registration), View.OnClickListener {

    private val binding: FragmentRegistrationBinding by viewBinding(FragmentRegistrationBinding::bind)
    private val viewModel: LoginViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRegister.setOnClickListener(this)
        observe()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_register -> {
                Timber.d("test")
                viewModel.registerUser(
                    binding.etName.text.toString(),
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
            }
        }
    }

    fun observe() {
        lifecycleScope.launch {
            viewModel.loggingState.collect { loginState: LoginState ->
                when (loginState) {
                    LoginState.LOADING -> isShowProgress(true)
                    is LoginState.EMPTY_NAME_ERROR -> {
                        binding.etName.error = loginState.message
                        isShowProgress(false)
                    }
                    is LoginState.EMPTY_EMAIL_ERROR -> {
                        binding.etEmail.error = loginState.message
                        isShowProgress(false)
                    }
                    is LoginState.EMPTY_PASSWORD_ERROR -> {
                        binding.etPassword.error = loginState.message
                        isShowProgress(false)
                    }
                    is LoginState.INCORRECT_PASSWORD_ERROR -> {
                        binding.etPassword.error = loginState.message
                        isShowProgress(false)
                    }
                    is LoginState.INCORRECT_EMAIL_ERROR -> {
                        binding.etEmail.error = loginState.message
                        isShowProgress(false)
                    }
                    LoginState.SUCCESS -> {
                        Snackbar.make(requireView(), "You have successfully registered", Snackbar.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_registrationFragment_to_loggingFragment)
                        isShowProgress(false)
                    }
                    LoginState.EMPTY -> {
                        isShowProgress(false)
                    }
                }
            }

        }
    }

    private fun isShowProgress(isShow: Boolean) {
        binding.progress.isVisible = isShow
        binding.buttonRegister.isEnabled = !isShow
    }
}