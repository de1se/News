package com.example.news

import androidx.room.*
import androidx.room.OnConflictStrategy

@Dao
interface AccountDao {
    @Query("SELECT EXISTS (SELECT 1 FROM account_database WHERE login = :login)")
    fun isLoginExist(login: String?): Boolean

    @Query("SELECT EXISTS (SELECT 1 FROM account_database WHERE email = :email)")
    fun isEmailExist(email: String?): Boolean

    @Query("SELECT * FROM account_database ORDER BY id ASC")
    fun getAllAccounts(): List<Account>

    @Query("SELECT * FROM account_database WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Account>

    @Query("SELECT * FROM account_database WHERE login LIKE :login LIMIT 1")
    fun findByLogin(login: String): Account

    @Insert
    fun insertNewAccount(user: Account)

    @Update
    fun updateAccountData(user: Account)

    @Delete
    fun deleteAccount(user: Account)
}