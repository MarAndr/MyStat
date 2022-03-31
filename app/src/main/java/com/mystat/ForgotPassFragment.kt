package com.mystat

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.collect

import com.mystat.databinding.FragmentForgotPassBinding
import com.mystat.utils.LoginState
import kotlinx.coroutines.launch


class ForgotPassFragment : Fragment(R.layout.fragment_forgot_pass) {

    private val binding: FragmentForgotPassBinding by viewBinding(FragmentForgotPassBinding::bind)
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var auth: FirebaseAuth


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding.button.setOnClickListener {
            resetPassword()
        }
        observe()

    }

    private fun resetPassword() {
        viewModel.resetPassword(binding.etResetPassword.text.toString())
    }

    private fun observe(){
        lifecycleScope.launch {
        viewModel.loggingState.collect { loginState ->
            when(loginState){
                is LoginState.CHECK_YOUR_EMAIL_SUCCESS -> {
                makeSnackBar(loginState.message)
                isShowProgress(false)
                }
                is LoginState.INCORRECT_EMAIL_ERROR -> {
                    makeSnackBar(loginState.message)
                    isShowProgress(false)
                }
                is LoginState.EMPTY_EMAIL_ERROR -> {
                    makeSnackBar(loginState.message)
                    isShowProgress(false)
                }
                is LoginState.ERROR -> {
                    makeSnackBar(loginState.message)
                    isShowProgress(false)
                }
                is LoginState.LOADING -> isShowProgress(true)
                else -> isShowProgress(false)
            }
        }

        }
    }

    private fun isShowProgress(isShow: Boolean) {
        binding.progressBar.isVisible = isShow
        binding.button.isEnabled = !isShow
    }

    private fun makeSnackBar(message: String){
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }
}