package com.example.news

import android.content.Context

class AccountDataManager(context: Context) {
    private val dataManager = DataManager(context)

    fun logInAccount(data: Account) {
        dataManager.writeData(data)
        dataManager.writeState(true)
    }

    fun logOutAccount() {
        dataManager.writeState(false)
    }

    fun checkIsLogged(): Boolean {
        return dataManager.readState()
    }
}