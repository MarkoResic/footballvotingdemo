package com.github.markoresic.footballvotingdemo.model.vote

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "votes")
data class Vote(
    @Id
    val idVote: String?,
    val playerId: String,
    val userId: String,
    val votedDateTime: LocalDateTime
)