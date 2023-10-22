package com.github.markoresic.footballvotingdemo.repository

import com.github.markoresic.footballvotingdemo.model.user.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, Int> {
    fun findByEmail(email: String): User?
}