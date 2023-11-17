package com.github.markoresic.footballvotingdemo.controller

import com.github.markoresic.footballvotingdemo.model.player.PlayerDetails
import com.github.markoresic.footballvotingdemo.model.player.PlayerListItemResponse
import com.github.markoresic.footballvotingdemo.model.player.PlayerVotesListItemResponse
import com.github.markoresic.footballvotingdemo.service.PlayerService
import com.github.markoresic.footballvotingdemo.service.VoteService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/players")
class PlayerController(
    private val playerService: PlayerService,
    private val voteService: VoteService
) {

    @GetMapping
    fun getPlayerDetails(@RequestParam id: String): PlayerDetails {
        val votesCount = voteService.getVotesCountByPlayer(id)
        val playerDetails = playerService.getPlayerDetails(id)
        playerDetails.votes = votesCount
        return playerDetails
    }

    @GetMapping("/search")
    fun getPlayersBySearchTerm(@RequestParam searchTerm: String): List<PlayerListItemResponse> =
        playerService.getPlayersBySearchTerm(searchTerm)

    @GetMapping("/top/today")
    fun getTopPlayersByVotesForToday(): List<PlayerVotesListItemResponse> {
        val votes = voteService.getVotesForToday()
        return votes
            .groupingBy { it.playerId }
            .eachCount()
            .toList()
            .sortedByDescending { it.second }
            .take(10)
            .map { keyValuePair ->
                PlayerVotesListItemResponse(
                    playerService.getPlayerDetails(keyValuePair.first).name,
                    keyValuePair.second
                )
            }
    }

    @GetMapping("/top/all-time")
    fun getTopPlayersByVotes(): List<PlayerVotesListItemResponse> {
        val votes = voteService.getVotes()
        return votes
            .groupingBy { it.playerId }
            .eachCount()
            .toList()
            .sortedByDescending { it.second }
            .take(10)
            .map { keyValuePair ->
                PlayerVotesListItemResponse(
                    playerService.getPlayerDetails(keyValuePair.first).fullName,
                    keyValuePair.second
                )
            }
    }

    @PostMapping("/management")
    @ResponseStatus(HttpStatus.CREATED)
    fun createPlayer(@Valid @RequestBody playerDetails: PlayerDetails) {
        if (playerService.playerExistsByTeamNameAndJerseyNumber(playerDetails.teamName, playerDetails.jerseyNumber)) {
            throw RuntimeException("Player already exists!")
        }
        playerService.createPlayer(playerDetails)
    }
}