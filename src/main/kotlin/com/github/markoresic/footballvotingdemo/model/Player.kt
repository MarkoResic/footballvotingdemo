package com.github.markoresic.footballvotingdemo.model

import java.sql.Date

data class Player(
    val idPlayer: Int, var name: String, var dob: Date,
    var imageUrl: String, var teamName: String,
    var position: String, var jerseyNumber: String
)