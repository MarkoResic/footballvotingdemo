package com.github.markoresic.footballvotingdemo.token

import org.springframework.data.mongodb.repository.MongoRepository

interface RefreshTokenRepository: MongoRepository<RefreshToken, String> {
    fun findAllByUserId(userId: String): List<RefreshToken>
}