package com.github.markoresic.footballvotingdemo.service

import com.github.markoresic.footballvotingdemo.model.vote.Vote
import com.github.markoresic.footballvotingdemo.model.vote.VoteCreateRequest
import com.github.markoresic.footballvotingdemo.repository.VoteRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class VoteService(private val voteRepository: VoteRepository) {

    fun getVotesForToday(): List<Vote> =
        voteRepository.findByVotedDateTimeIsAfter(LocalDate.now().atStartOfDay())

    fun getVotes(): List<Vote> =
        voteRepository.findAll()

    fun getVotesCountByPlayer(id: String): Int =
        voteRepository.findByPlayerId(id).count()

    fun createVote(voteCreateRequest: VoteCreateRequest): Boolean {
        val votesToday =
            voteRepository.findByUserIdAndVotedDateTimeIsAfter(
                voteCreateRequest.userId,
                LocalDate.now().atStartOfDay()
            )
        if (votesToday.isNotEmpty()) {
            return false
        }

        val vote = Vote(
            null,
            voteCreateRequest.playerId,
            voteCreateRequest.userId,
            LocalDateTime.now()
        )
        voteRepository.insert(vote)
        return true
    }
}