package com.example.news

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

object DataValidator {
    private val valuePattern = Regex("(.*[a-zA-Z0-9._-])")

    fun validateLoginSignIn(
        login: TextInputLayout,
        accountDao: AccountDao,
        context: Context
    ): Boolean {
        return if (login.toString().isEmpty()) {
            showError(login, context.getString(R.string.ERROR_MESSAGE_FIELD_EMPTY))
            false
        } else if (!login.toString().matches(valuePattern)) {
            showError(login, context.getString(R.string.ERROR_MESSAGE_FIELD_INVALID))
            false
        } else if (!accountDao.isLoginExist(login.editText?.text.toString())) {
            Toast.makeText(
                context,
                context.getString(R.string.ERROR_MESSAGE_ACCOUNT_DOES_NOT_EXISTS),
                Toast.LENGTH_SHORT
            ).show()
            false
        } else {
            login.error = ""
            true
        }
    }

    fun validateLoginSignUp(
        login: TextInputLayout,
        accountDao: AccountDao,
        context: Context
    ): Boolean {
        return if (login.editText?.text.toString().isEmpty()) {
            showError(login, context.getString(R.string.ERROR_MESSAGE_FIELD_EMPTY))
            false
        } else if (!login.editText?.text.toString().matches(valuePattern)) {
            showError(login, context.getString(R.string.ERROR_MESSAGE_FIELD_INVALID))
            false
        } else if (accountDao.isLoginExist(login.editText?.text.toString())) {
            showError(login, context.getString(R.string.ERROR_MESSAGE_LOGIN_TAKEN))
            false
        } else {
            login.error = ""
            true
        }
    }

    fun validateEmail(email: TextInputLayout, accountDao: AccountDao, context: Context): Boolean {
        return if (email.editText?.text.toString().isEmpty()) {
            showError(email, context.getString(R.string.ERROR_MESSAGE_FIELD_EMPTY))
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.editText?.text.toString()).matches()) {
            showError(email, context.getString(R.string.ERROR_MESSAGE_EMAIL_INVALID))
            false
        } else if (accountDao.isEmailExist(email.editText?.text.toString())) {
            showError(email, context.getString(R.string.ERROR_MESSAGE_EMAIL_EXISTS))
            false
        } else {
            email.error = ""
            true
        }
    }

    fun validatePassword(password: TextInputLayout, context: Context): Boolean {
        return if (password.editText?.text.toString().isEmpty()) {
            showError(password, context.getString(R.string.ERROR_MESSAGE_FIELD_EMPTY))
            false
        } else if (!password.editText?.text.toString().matches(valuePattern)) {
            showError(password, context.getString(R.string.ERROR_MESSAGE_FIELD_INVALID))
            false
        } else {
            password.error = ""
            true
        }
    }


    fun validRepeatedPassword(
        password: TextInputLayout,
        repeatedPassword: TextInputLayout,
        context: Context
    ): Boolean {
        return if (repeatedPassword.editText?.text.toString().isEmpty()) {
            showError(repeatedPassword, context.getString(R.string.ERROR_MESSAGE_FIELD_EMPTY))
            false
        } else if (!repeatedPassword.editText?.text.toString().matches(valuePattern)) {
            showError(repeatedPassword, context.getString(R.string.ERROR_MESSAGE_FIELD_INVALID))
            false
        } else if (repeatedPassword.editText?.text.toString() != password.editText?.text.toString()) {
            showError(
                repeatedPassword,
                context.getString(R.string.ERROR_MESSAGE_PASSWORD_DIFFERENT)
            )
            false
        } else {
            repeatedPassword.error = ""
            true
        }
    }

    fun validatePassword(
        password: TextInputLayout,
        login: TextInputLayout,
        accountDao: AccountDao,
        context: Context
    ): Boolean {
        return if (password.editText?.text.toString().isEmpty()) {
            showError(password, context.getString(R.string.ERROR_MESSAGE_FIELD_EMPTY))
            false
        } else if (!password.editText?.text.toString().matches(valuePattern)) {
            showError(password, context.getString(R.string.ERROR_MESSAGE_FIELD_INVALID))
            false
        } else if (accountDao.findByLogin(login.editText?.text.toString()).password.toString() != password.editText?.text.toString()) {
            Toast.makeText(
                context,
                context.getString(R.string.ERROR_MESSAGE_INVALID_LOGIN_OR_PASSWORD),
                Toast.LENGTH_SHORT
            ).show()
            false
        } else {
            password.error = ""
            true
        }
    }

    private fun showError(textInput: TextInputLayout, errorMessage: String) {
        textInput.error = errorMessage
        textInput.requestFocus()
    }
}
