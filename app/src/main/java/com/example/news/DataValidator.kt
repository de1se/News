package com.example.news

import android.content.Context
import android.util.Patterns
import android.widget.Toast

object DataValidator {
    private val valuePattern = Regex("(.*[a-zA-Z0-9._-])")

    fun isValidInput(context: Context, accountDao: AccountDao, accountInformation: Account, repeatedPassword: String): Boolean {
        if (!isValidLogin(context, accountInformation.login)) {
            return false
        }

        if (accountDao.isLoginExist(accountInformation.login)) {
            showToast(context.getString(R.string.ERROR_MESSAGE_LOGIN_EXISTS), context)
            return false
        }

        if (!isValidEmail(context, accountDao, accountInformation.email)){
            return false
        }

        if (!isValidPassword(context, accountInformation.password, repeatedPassword)) {
            return false
        }

        return true
    }

    fun isValidInput(context: Context, accountDao: AccountDao, login: String?, password: String?): Boolean {
        if (!isValidLogin(context, login)) {
            return false
        }

        if (!isExist(accountDao, login)) {
            showToast(context.getString(R.string.ERROR_MESSAGE_ACCOUNT_DOES_NOT_EXISTS), context)
            return false
        }

        if (!isValidPassword(context, accountDao, login, password)){
            return false
        }

        return true
    }
    
    private fun isValidLogin(context: Context, login: String?): Boolean {
        if (login!!.isEmpty()) {
            showToast(context.getString(R.string.ERROR_MESSAGE_LOGIN_EMPTY), context)
            return false
        }

        if (!login.matches(valuePattern)){
            showToast(context.getString(R.string.ERROR_MESSAGE_LOGIN_INVALID), context)
            return false
        }

        return true
    }

    private fun isValidEmail(context: Context, accountDao: AccountDao, email: String?): Boolean {
        if (email!!.isEmpty()) {
            showToast(context.getString(R.string.ERROR_MESSAGE_EMAIL_EMPTY), context)
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast(context.getString(R.string.ERROR_MESSAGE_EMAIL_INVALID), context)
            return false
        }

        if (accountDao.isEmailExist(email)) {
            showToast(context.getString(R.string.ERROR_MESSAGE_EMAIL_EXISTS), context)
            return false
        }

        return true
    }

    private fun isValidPassword(context: Context, password: String?, repeatedPassword: String): Boolean {
        if (password!!.isEmpty()) {
            showToast(context.getString(R.string.ERROR_MESSAGE_PASSWORD_EMPTY), context)
            return false
        }

        if (!password.matches(valuePattern)) {
            showToast(context.getString(R.string.ERROR_MESSAGE_PASSWORD_INVALID), context)
            return false
        }

        if (repeatedPassword.isEmpty()) {
            showToast(context.getString(R.string.ERROR_MESSAGE_REPEATED_PASSWORD_EMPTY), context)
            return false
        }

        if (!repeatedPassword.matches(valuePattern)) {
            showToast(context.getString(R.string.ERROR_MESSAGE_PASSWORD_INVALID), context)
            return false
        }

        if (!password.equals(repeatedPassword)) {
            showToast(context.getString(R.string.ERROR_MESSAGE_PASSWORD_DIFFERENT), context)
            return false
        }

        return true
    }

    private fun isValidPassword(context: Context, accountDao: AccountDao, login: String?, password: String?): Boolean {
        if (password!!.isEmpty()) {
            showToast(context.getString(R.string.ERROR_MESSAGE_PASSWORD_EMPTY), context)
            return false
        }

        if (!password.matches(valuePattern)) {
            showToast(context.getString(R.string.ERROR_MESSAGE_PASSWORD_INVALID), context)
            return false
        }

        if(!accountDao.findByLogin(login!!).password.equals(password)) {
            showToast("Неправильный логин или пароль!", context)
            return false
        }

        return true
    }

    private fun isExist(accountDao: AccountDao, login: String?): Boolean {
        return accountDao.isLoginExist(login)
    }

    private fun showToast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
