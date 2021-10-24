package com.example.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.news.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityMainBinding
    lateinit var accountDao: AccountDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        val navigationController = activityBinding.fragmentContainerView

        val database = Room.databaseBuilder(applicationContext, AccountDatabase::class.java, "account_database").fallbackToDestructiveMigration().allowMainThreadQueries().build()
        accountDao = database.accountDao()
    }
}