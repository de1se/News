package com.example.news

import android.content.Context
import android.content.SharedPreferences

class DataManager(private val customUserViewModel: UserViewModel, context: Context) {
    private val APPLICATION_PREFERENCES = "Login"
    private val APPLICATION_PREFERENCES_IS_LOGGED = "IsLogged"
    private var sharedPreferences = context.getSharedPreferences(APPLICATION_PREFERENCES, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun writeData(accountData: AccountData) {
        val newUserData = User(0, accountData.login, accountData.email, accountData.password)
        customUserViewModel.insertNewUser(newUserData)
        editor.putString(APPLICATION_PREFERENCES, "${accountData.login} ${accountData.email} ${accountData.password}")
        editor.apply()
    }

    fun readData(): AccountData {
        val temporaryString: List<String>? = sharedPreferences.getString(APPLICATION_PREFERENCES, "")?.split(" ")
        val accountData: AccountData = if (temporaryString!!.isNotEmpty()) {
            AccountData(
                temporaryString[0],
                temporaryString[1],
                temporaryString[2]
            )
        } else {
            AccountData("", "", "")
        }
        return accountData
    }

    fun writeState(state: Boolean) {
        editor.putBoolean(APPLICATION_PREFERENCES_IS_LOGGED, state)
        editor.apply()
    }

    fun readState() : Boolean {
        return sharedPreferences.getBoolean(APPLICATION_PREFERENCES_IS_LOGGED, false)
    }
}