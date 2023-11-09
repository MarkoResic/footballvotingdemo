package com.github.markoresic.footballvotingdemo.model.vote

data class VoteCreateRequest(
    val playerId: String,
    val userId: String
)