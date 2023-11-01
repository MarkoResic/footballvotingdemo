package com.github.markoresic.footballvotingdemo.extension

import com.github.markoresic.footballvotingdemo.model.player.Player
import com.github.markoresic.footballvotingdemo.model.player.PlayerDetails
import com.github.markoresic.footballvotingdemo.model.player.PlayerListItemResponse

fun Player.toPlayerDetails() = PlayerDetails(
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