package com.github.markoresic.footballvotingdemo.model

import java.time.LocalDateTime

data class Vote(
    val idVote: Int, var playerId: Int, var userId: Int, var votedDateTime: LocalDateTime
)