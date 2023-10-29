package com.github.markoresic.footballvotingdemo.controller

import com.github.markoresic.footballvotingdemo.model.player.PlayerDetailsResponse
import com.github.markoresic.footballvotingdemo.model.player.PlayerListItemResponse
import com.github.markoresic.footballvotingdemo.service.PlayerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.NoSuchElementException

@RestController
@RequestMapping("/api/players")
class PlayerController(private val playerService: PlayerService) {

    @GetMapping("/{id}")
    fun getPlayerDetails(@PathVariable id: String): ResponseEntity<PlayerDetailsResponse> {
        return try {
            ResponseEntity.ok(playerService.getPlayerDetails(id))
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/search/{searchTerm}")
    fun getPlayersBySearchTerm(@PathVariable searchTerm: String): List<PlayerListItemResponse> =
        playerService.getPlayersBySearchTerm(searchTerm)
}