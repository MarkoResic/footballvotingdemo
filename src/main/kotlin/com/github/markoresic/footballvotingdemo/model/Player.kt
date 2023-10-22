package com.github.markoresic.footballvotingdemo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.sql.Date

@Document(collection = "players")
data class Player(
    @Id
    val idPlayer: Int,
    val name: String,
    val dob: Date,
    val imageUrl: String,
    val teamName: String,
    val position: String,
    val jerseyNumber: String
)