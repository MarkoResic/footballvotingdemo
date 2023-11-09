package com.github.markoresic.footballvotingdemo.model.player

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date

@Document(collection = "players")
data class Player(
    @Id
    val idPlayer: String?,
    val name: String,
    val dob: Date,
    val imageUrl: String,
    val teamName: String,
    val position: String,
    val jerseyNumber: String
)