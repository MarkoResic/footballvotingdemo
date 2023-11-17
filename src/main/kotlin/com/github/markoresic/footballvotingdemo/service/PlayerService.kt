package com.github.markoresic.footballvotingdemo.service

import com.github.markoresic.footballvotingdemo.extension.toPlayerDetails
import com.github.markoresic.footballvotingdemo.extension.toPlayerListItemResponse
import com.github.markoresic.footballvotingdemo.model.player.Player
import com.github.markoresic.footballvotingdemo.model.player.PlayerDetails
import com.github.markoresic.footballvotingdemo.model.player.PlayerListItemResponse
import com.github.markoresic.footballvotingdemo.repository.PlayerRepository
import org.springframework.stereotype.Service

@Service
class PlayerService(private val playerRepository: PlayerRepository) {

    fun playerExistsById(id: String): Boolean = playerRepository.existsById(id)

    fun playerExistsByTeamNameAndJerseyNumber(teamName: String, jerseyNumber: String): Boolean =
        playerRepository.existsByTeamNameAndJerseyNumber(teamName, jerseyNumber)

    fun getPlayerDetails(id: String): PlayerDetails {
        val player = playerRepository.findById(id).orElseThrow()
        return player.toPlayerDetails()
    }

    fun getPlayersBySearchTerm(searchTerm: String): List<PlayerListItemResponse> {
        val players = playerRepository.findByNameContainingIgnoreCase(searchTerm)
        return players.map { player -> player.toPlayerListItemResponse() }
    }

    fun createPlayer(playerDetails: PlayerDetails) {
        val player = Player(
            null,
            playerDetails.fullName,
            playerDetails.dob,
            playerDetails.imageUrl,
            playerDetails.teamName,
            playerDetails.position,
            playerDetails.jerseyNumber
        )
        playerRepository.insert(player)
    }
}