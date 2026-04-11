package com.project.recipes.repository

import com.project.recipes.model.User

interface UserRepository {

    fun saveUser(user: User)
    fun getUser(): User
    fun getUserByEmail(email: String): User?
    fun login(email: String, password: String): Boolean
}