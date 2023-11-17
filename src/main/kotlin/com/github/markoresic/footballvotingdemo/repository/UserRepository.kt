package com.github.markoresic.footballvotingdemo.repository

import com.github.markoresic.footballvotingdemo.model.user.User
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface UserRepository : MongoRepository<User, String> {
    fun findByEmail(email: String): Optional<User>
    fun existsByEmail(email: String): Boolean
}