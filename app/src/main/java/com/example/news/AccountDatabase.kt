package com.example.news

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Account::class], version = 1)
abstract class AccountDatabase: RoomDatabase() {
    abstract fun accountDao(): AccountDao
}