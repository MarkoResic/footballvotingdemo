package com.github.markoresic.footballvotingdemo.extension

import com.github.markoresic.footballvotingdemo.model.player.Player
import com.github.markoresic.footballvotingdemo.model.player.PlayerDetailsResponse
import com.github.markoresic.footballvotingdemo.model.player.PlayerListItemResponse

fun Player.toPlayerDetailsResponse() = PlayerDetailsResponse(
    name,
    dob,
    imageUrl,
    teamName,
    position,
    jerseyNumber
)

fun Player.toPlayerListItemResponse() = PlayerListItemResponse(
    idPlayer,
    name,
    imageUrl,
    teamName
)