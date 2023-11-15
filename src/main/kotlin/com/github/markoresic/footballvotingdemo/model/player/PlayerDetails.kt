package com.github.markoresic.footballvotingdemo.model.player

import java.util.*

data class PlayerDetails(
    val fullName: String,
    val dob: Date,
    val imageUrl: String,
    val teamName: String,
    val position: String,
    val jerseyNumber: String,
    var numberOfVotes: Int = 0
)