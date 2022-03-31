package com.mystat

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.util.Patterns
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.mystat.user.User
import com.mystat.utils.LoginState
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.internal.ContextUtils.getActivity


class LoginViewModel : ViewModel() {
    private val _loggingState = MutableStateFlow<LoginState>(LoginState.EMPTY)
    val loggingState: StateFlow<LoginState> = _loggingState

    private val auth = Firebase.auth

    fun registerUser(name: String, email: String, password: String) {

        _loggingState.value = LoginState.LOADING
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            if (name.isBlank()) {
                _loggingState.value = LoginState.EMPTY_NAME_ERROR()
            }

            if (email.isBlank()) {
                _loggingState.value = LoginState.EMPTY_EMAIL_ERROR()
            }

            if (password.isBlank()) {
                _loggingState.value = LoginState.EMPTY_PASSWORD_ERROR()
            }
        } else {
            when {
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    _loggingState.value = LoginState.INCORRECT_EMAIL_ERROR()
                }
                password.length < 6 -> {
                    _loggingState.value = LoginState.INCORRECT_PASSWORD_ERROR()
                }
                else -> {
                    _loggingState.value = LoginState.SUCCESS
                    userFirebaseAuth(name, email, password)
                }
            }
        }
    }


    private fun userFirebaseAuth(name: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = User(uid = FirebaseAuth.getInstance().currentUser?.uid?:"emptyUid", name = name, email = email)
                    FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().currentUser?.uid!!)
                        .setValue(user).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Timber.d("${user.name} registered successfully")
                            }
                        }
                }
            }
            .addOnFailureListener { task ->
                Timber.d("failure, ${task.message}")
            }
    }

    fun userLogin(email: String, password: String){
        _loggingState.value = LoginState.LOADING
        if (email.isBlank() || password.isBlank()) {
            if (email.isBlank()) {
                _loggingState.value = LoginState.EMPTY_EMAIL_ERROR()
            }
            if (password.isBlank()) {
                _loggingState.value = LoginState.EMPTY_PASSWORD_ERROR()
            }
        }else{
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                _loggingState.value = LoginState.INCORRECT_EMAIL_ERROR()
            }else{
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        val user = FirebaseAuth.getInstance().currentUser
                        if (user?.isEmailVerified == true){
                            _loggingState.value = LoginState.SUCCESS
                            Timber.d("success log in")
                        }else{
                            _loggingState.value = LoginState.NEED_VERIFY_EMAIL_ERROR()
                            user?.sendEmailVerification()
                            Timber.d("check your email to verify your account")
                        }

                    }else{
                        _loggingState.value = LoginState.WRONG_CREDENTIALS_ERROR()
                        Timber.d("wrong credentials")
                    }
                }
            }
        }
    }

    fun resetPassword(email: String){
        _loggingState.value = LoginState.LOADING
        if (email.isBlank()){
            _loggingState.value = LoginState.EMPTY_EMAIL_ERROR()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            _loggingState.value = LoginState.INCORRECT_EMAIL_ERROR()
        } else{
            auth.sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful){
                    _loggingState.value = LoginState.CHECK_YOUR_EMAIL_SUCCESS()
                } else{
                    _loggingState.value = LoginState.ERROR()
                }
            }
        }
    }

    fun getClient(activity: Activity): GoogleSignInClient {
        val gso =  GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("1015968467062-uiqjste3c2hcb8n1br5ngm4d4e5a75sh.apps.googleusercontent.com")
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(activity, gso)

    }

    fun signInWithGoogle(activity: Activity, launcher: ActivityResultLauncher<Intent>){
        val signInClient = getClient(activity)
        launcher.launch(signInClient.signInIntent)
    }

    fun firebaseAuthWithGoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful){
                _loggingState.value = LoginState.SUCCESS
            } else{
                _loggingState.value = LoginState.ERROR()
            }
        }
    }

    fun checkAuthState(){
        if (auth.currentUser != null){
            _loggingState.value = LoginState.SUCCESS
        }
    }

    @SuppressLint("RestrictedApi")
    fun setUpActionBar(context: Context){
        val ab = (getActivity(context) as AppCompatActivity).supportActionBar
        ab?.show()
        viewModelScope.launch(Dispatchers.IO) {
            val bitMap = Picasso.get().load(auth.currentUser?.photoUrl).get()
            val drawable = BitmapDrawable(getActivity(context)?.resources, bitMap)
            withContext(Dispatchers.Main){
                ab?.setDisplayHomeAsUpEnabled(true)
                ab?.setHomeAsUpIndicator(drawable)
                ab?.title = auth.currentUser?.displayName
            }

        }
    }

    @SuppressLint("RestrictedApi")
    fun clearActionBar(context: Context){
        val ab = (getActivity(context) as AppCompatActivity).supportActionBar
        ab?.hide()
    }
}



