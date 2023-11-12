package com.github.markoresic.footballvotingdemo.controller

import com.github.markoresic.footballvotingdemo.model.vote.VoteCreateRequest
import com.github.markoresic.footballvotingdemo.service.PlayerService
import com.github.markoresic.footballvotingdemo.service.UserService
import com.github.markoresic.footballvotingdemo.service.VoteService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException
import java.util.NoSuchElementException

@RestController
@RequestMapping("/api/votes")
class VoteController(
    private val voteService: VoteService,
    private val playerService: PlayerService,
    private val userService: UserService
) {

    @PostMapping("/{playerId}")
    fun createVote(@PathVariable playerId: String): ResponseEntity<Unit> {

        if (!playerService.playerExistsById(playerId)) {
            throw NoSuchElementException("Player not found!")
        }

        val authentication = SecurityContextHolder.getContext().authentication
        val email: String = authentication.name
        val userId = userService.getUserId(email)

        val voteCreateRequest = VoteCreateRequest(playerId, userId)
        return if (voteService.createVote(voteCreateRequest)) {
            ResponseEntity.status(HttpStatus.CREATED).build()
        } else {
            throw RuntimeException("You already voted today!")
        }
    }
}