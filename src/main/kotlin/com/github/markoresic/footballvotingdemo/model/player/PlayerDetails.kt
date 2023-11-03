package com.github.markoresic.footballvotingdemo.model.player

import java.util.*

data class PlayerDetails(
    val name: String,
    val dob: Date,
    val imageUrl: String,
    val teamName: String,
    val position: String,
    val jerseyNumber: String,
    var votes: Int = 0
)