package com.mystat

import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.collect
import timber.log.Timber

import com.mystat.databinding.FragmentLoginBinding
import com.mystat.utils.LoginState
import kotlinx.coroutines.launch


class LoggingFragment : Fragment(R.layout.fragment_login), View.OnClickListener {

    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel: LoginViewModel by viewModels()
    lateinit var launcher: ActivityResultLauncher<Intent>
    private val auth = FirebaseAuth.getInstance()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (auth.currentUser == null){
            viewModel.clearActionBar(requireContext())
        }
        Timber.d("current user = ${auth.currentUser?.displayName}")
        viewModel.checkAuthState()
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null){
                    viewModel.firebaseAuthWithGoogle(account.idToken)
                }
            }catch (apiException: ApiException){
                Timber.d("api exception = ${apiException.message}")
            }
        }
        binding.btnLogin.setOnClickListener(this)
        binding.tvForgotPassword.setOnClickListener(this)
        binding.tvRegister.setOnClickListener(this)
        binding.btnSignInGoogle.setOnClickListener(this)

        observe()

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_forgot_password ->{
                Timber.d("to forgot pass")
                findNavController().navigate(R.id.action_loggingFragment_to_forgotPassFragment)
            }
            R.id.tv_register ->{
                findNavController().navigate(R.id.action_loggingFragment_to_registrationFragment)
                Timber.d("tv register")
            }
            R.id.btn_login ->{
                viewModel.userLogin(binding.editTextLogin.text.toString(), binding.editTextPassword.text.toString())
            }
            R.id.btn_signIn_google -> {
                Timber.d("test")
                viewModel.signInWithGoogle(requireActivity(), launcher)
            }
        }
    }

    fun observe() {
        lifecycleScope.launch {
            viewModel.loggingState.collect { loginState: LoginState ->
                when (loginState) {
                    LoginState.LOADING -> isShowProgress(true)
                    is LoginState.EMPTY_EMAIL_ERROR -> {
                        binding.editTextLogin.error = loginState.message
                        isShowProgress(false)
                    }
                    is LoginState.EMPTY_PASSWORD_ERROR -> {
                        binding.editTextPassword.error = loginState.message
                        isShowProgress(false)
                    }
                    is LoginState.INCORRECT_EMAIL_ERROR -> {
                        binding.editTextLogin.error = loginState.message
                        isShowProgress(false)
                    }
                    LoginState.SUCCESS -> {
                        makeSnackBar("You have successfully logged in")
                        findNavController().navigate(R.id.action_loggingFragment_to_mainFragment2)
                        isShowProgress(false)
                    }
                    is LoginState.WRONG_CREDENTIALS_ERROR -> {
                        isShowProgress(false)
                        makeSnackBar(message = loginState.message)
                    }
                    is LoginState.NEED_VERIFY_EMAIL_ERROR -> {
                        isShowProgress(false)
                        makeSnackBar(message = loginState.message)
                    }
                    LoginState.EMPTY -> {
                        isShowProgress(false)
                    }

                }
            }

        }
    }

    private fun isShowProgress(isShow: Boolean) {
        binding.progressBar.isVisible = isShow
        binding.btnLogin.isEnabled = !isShow
    }

    private fun makeSnackBar(message: String){
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }
}