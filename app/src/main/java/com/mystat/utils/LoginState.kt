package com.mystat.utils

sealed class LoginState{
    object SUCCESS: LoginState()
    object LOADING: LoginState()
    object EMPTY: LoginState()
    data class ERROR(val message: String = "Try again! Something wrong happened!"): LoginState()
    data class EMPTY_NAME_ERROR(val message: String = "Name is required"): LoginState()
    data class EMPTY_EMAIL_ERROR(val message: String = "Email is required"): LoginState()
    data class INCORRECT_EMAIL_ERROR(val message: String = "Email is incorrect"): LoginState()
    data class WRONG_CREDENTIALS_ERROR(val message: String = "Credentials is incorrect"): LoginState()
    data class NEED_VERIFY_EMAIL_ERROR(val message: String = "You need to verify your email, we send you the letter"): LoginState()
    data class EMPTY_PASSWORD_ERROR(val message: String = "Password is required"): LoginState()
    data class INCORRECT_PASSWORD_ERROR(val message: String = "Password must be bigger than 6 characters"): LoginState()
    data class CHECK_YOUR_EMAIL_SUCCESS(val message: String = "Check your email to reset the password"): LoginState()
}
