package com.example.news

import android.content.Context

class AccountDataManager(context: Context) {
    private val dataManager = DataManager(context)

    fun registerAccount(data: AccountData) {
        dataManager.writeData(data)
        logInAccount()
    }

    fun logInAccount() {
        dataManager.writeState(true)
    }

    fun logOutAccount() {
        dataManager.writeState(false)
    }

    fun isValidData(login: String, password: String): Boolean {
        val userData = dataManager.readData()
        return userData.login.equals(login) && userData.password.equals(password)
    }

    fun checkIsLogged(): Boolean {
        return dataManager.readState()
    }
}