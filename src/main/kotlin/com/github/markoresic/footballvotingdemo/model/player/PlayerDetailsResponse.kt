package com.github.markoresic.footballvotingdemo.model.player

import java.util.Date

data class PlayerDetailsResponse(
    val name: String,
    val dob: Date,
    val imageUrl: String,
    val teamName: String,
    val position: String,
    val jerseyNumber: String
)