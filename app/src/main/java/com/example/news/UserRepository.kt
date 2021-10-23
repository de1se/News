package com.example.news

class UserRepository(private val userDao: UserDao) {

    val getAllUsers: List<User> = userDao.getAllUsers()

    suspend fun insertNewUser(user: User) {
        userDao.insertNewUser(user)
    }
}