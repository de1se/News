package com.example.news

import android.content.Context
import android.content.SharedPreferences

class DataManager(context: Context) {
    private val APPLICATION_PREFERENCES = "Login"
    private val APPLICATION_PREFERENCES_IS_LOGGED = "IsLogged"
    private var sharedPreferences =
        context.getSharedPreferences(APPLICATION_PREFERENCES, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun writeData(accountData: Account) {
        editor.putString(
            APPLICATION_PREFERENCES,
            "${accountData.login} ${accountData.email} ${accountData.password}"
        )
        editor.apply()
    }

    fun writeState(state: Boolean) {
        editor.putBoolean(APPLICATION_PREFERENCES_IS_LOGGED, state)
        editor.apply()
    }

    fun readState(): Boolean {
        return sharedPreferences.getBoolean(APPLICATION_PREFERENCES_IS_LOGGED, false)
    }
}