package com.github.markoresic.footballvotingdemo.service

import com.github.markoresic.footballvotingdemo.extension.toPlayerDetailsResponse
import com.github.markoresic.footballvotingdemo.extension.toPlayerListItemResponse
import com.github.markoresic.footballvotingdemo.model.player.PlayerDetailsResponse
import com.github.markoresic.footballvotingdemo.model.player.PlayerListItemResponse
import com.github.markoresic.footballvotingdemo.repository.PlayerRepository
import org.springframework.stereotype.Service

@Service
class PlayerService(private val playerRepository: PlayerRepository) {

    fun getPlayerDetails(id: String): PlayerDetailsResponse {
        val player = playerRepository.findById(id).orElseThrow()
        return player.toPlayerDetailsResponse()
    }

    fun getPlayersBySearchTerm(searchTerm: String): List<PlayerListItemResponse> {
        val players = playerRepository.findByNameContainingIgnoreCase(searchTerm)
        return players.map { player -> player.toPlayerListItemResponse() }
    }
}