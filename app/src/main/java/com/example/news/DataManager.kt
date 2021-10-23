package com.example.news

import android.content.Context
import android.content.SharedPreferences

class DataManager(context: Context) {
    private val APPLICATION_PREFERENCES = "Login"
    private val APPLICATION_PREFERENCES_IS_LOGGED = "IsLogged"
    private var sharedPreferences = context.getSharedPreferences(APPLICATION_PREFERENCES, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun writeData(accountData: AccountData) {
        editor.putString(APPLICATION_PREFERENCES, "${accountData.login} ${accountData.email} ${accountData.password} ${accountData.repeatedPassword}")
        editor.apply()
    }

    fun readData(): AccountData {
        val tempString: List<String>? = sharedPreferences.getString(APPLICATION_PREFERENCES, "")?.split(" ")
        val accountData: AccountData = if (tempString!!.isNotEmpty()) {
            AccountData(
                tempString[0],
                tempString[1],
                tempString[2],
                tempString[3]
            )
        } else {
            AccountData("", "", "", "")
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