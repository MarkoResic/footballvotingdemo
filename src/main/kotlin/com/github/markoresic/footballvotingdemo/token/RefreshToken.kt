package com.github.markoresic.footballvotingdemo.token

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "refreshTokens")
data class RefreshToken(
    @Id
    val id: String?,
    val userId: String
)