package com.github.markoresic.footballvotingdemo.repository

import com.github.markoresic.footballvotingdemo.model.player.Player
import org.springframework.data.mongodb.repository.MongoRepository

interface PlayerRepository : MongoRepository<Player, String> {
    fun findByNameContainingIgnoreCase(searchTerm: String): List<Player>

    fun existsByTeamNameAndJerseyNumber(teamName: String, jerseyNumber: String): Boolean
}