package com.github.markoresic.footballvotingdemo.model.player

data class PlayerListItemResponse(
    val idPlayer: String?,
    val fullName: String,
    val imageUrl: String,
    val teamName: String
)