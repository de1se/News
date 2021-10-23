package com.example.news

import android.content.Context
import android.util.Patterns
import android.widget.Toast

object DataValidator {
    fun isValidInput(context: Context, accountInformation: AccountData, repeatedPassword: String): Boolean {
        if (!isValidLogin(context, accountInformation.login)) {
            return false
        }

        if (!isValidEmail(context, accountInformation.email)) {
            return false
        }

        if (!isValidPassword(context, accountInformation.password, repeatedPassword)) {
            return false
        }

        return true
    }
    
    private fun isValidLogin(context: Context, login: String): Boolean {
        return if (login.isEmpty()) {
            showToast(context.getString(R.string.ERROR_MESSAGE_LOGIN_EMPTY), context)
            false
        } else {
            true
        }
    }

    private fun isValidEmail(context: Context, email: String): Boolean {
        if (email.isEmpty()) {
            showToast(context.getString(R.string.ERROR_MESSAGE_EMAIL_EMPTY), context)
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast(context.getString(R.string.ERROR_MESSAGE_EMAIL_INVALID), context)
            return false
        }

        return true
    }

    private fun isValidPassword(context: Context, password: String, repeatedPassword: String): Boolean {
        if (password.isEmpty()) {
            showToast(context.getString(R.string.ERROR_MESSAGE_PASSWORD_EMPTY), context)
            return false
        }

        if (repeatedPassword.isEmpty()) {
            showToast(context.getString(R.string.ERROR_MESSAGE_REPEATED_PASSWORD_EMPTY), context)
            return false
        }

        if (!password.equals(repeatedPassword)) {
            showToast(context.getString(R.string.ERROR_MESSAGE_PASSWORD_DIFFERENT), context)
            return false
        }

        return true
    }

    fun showToast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}