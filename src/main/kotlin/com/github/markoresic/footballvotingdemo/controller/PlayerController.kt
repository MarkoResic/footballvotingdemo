package com.github.markoresic.footballvotingdemo.controller

import com.github.markoresic.footballvotingdemo.model.player.PlayerDetails
import com.github.markoresic.footballvotingdemo.model.player.PlayerListItemResponse
import com.github.markoresic.footballvotingdemo.service.PlayerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/players")
class PlayerController(private val playerService: PlayerService) {

    @GetMapping("/{id}")
    fun getPlayerDetails(@PathVariable id: String): PlayerDetails =
        playerService.getPlayerDetails(id)

    @GetMapping("/search/{searchTerm}")
    fun getPlayersBySearchTerm(@PathVariable searchTerm: String): List<PlayerListItemResponse> =
        playerService.getPlayersBySearchTerm(searchTerm)

    @PostMapping("/management")
    @ResponseStatus(HttpStatus.CREATED)
    fun createPlayer(@RequestBody playerDetails: PlayerDetails) =
        playerService.createPlayer(playerDetails)
}