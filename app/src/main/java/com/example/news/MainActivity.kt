package com.example.news

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.news.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityMainBinding
    private var backPressedTime: Long = 0
    private lateinit var backToast: Toast
    lateinit var accountDao: AccountDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        val database = Room.databaseBuilder(
            applicationContext,
            AccountDatabase::class.java,
            "account_database"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        accountDao = database.accountDao()
    }

    override fun onBackPressed() {
        if (backPressedTime + 1500 > System.currentTimeMillis()) {
            backToast.cancel()
            super.finish()
        } else {
            backToast =
                Toast.makeText(baseContext, "Нажмите кнопку назад ещё раз", Toast.LENGTH_SHORT)
            backToast.show()
        }

        backPressedTime = System.currentTimeMillis()
    }
}