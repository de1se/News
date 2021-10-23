package com.example.news

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user_database ORDER BY id ASC")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM user_database WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user_database WHERE login LIKE :login LIMIT 1")
    fun findByLogin(login: String): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewUser(user: User)

    @Update
    fun updateUserData(user: User)

    @Delete
    fun deleteUser(user: User)
}