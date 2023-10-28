package com.github.markoresic.footballvotingdemo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "votes")
data class Vote(
    @Id
    val idVote: String?,
    val playerId: Int,
    val userId: Int,
    val votedDateTime: LocalDateTime
)