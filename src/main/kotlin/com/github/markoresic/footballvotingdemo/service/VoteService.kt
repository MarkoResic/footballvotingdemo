package com.github.markoresic.footballvotingdemo.service

import com.github.markoresic.footballvotingdemo.model.vote.Vote
import com.github.markoresic.footballvotingdemo.model.vote.VoteCreateRequest
import com.github.markoresic.footballvotingdemo.repository.VoteRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Service
class VoteService(private val voteRepository: VoteRepository) {

    fun createVote(voteCreateRequest: VoteCreateRequest): Optional<Vote> {
        val votesToday =
            voteRepository.findByUserIdAndVotedDateTimeIsAfter(
                voteCreateRequest.userId,
                LocalDate.now().atStartOfDay()
            )
        if (votesToday.isNotEmpty()) {
            return Optional.empty()
        }

        val vote = Vote(
            null,
            voteCreateRequest.playerId,
            voteCreateRequest.userId,
            LocalDateTime.now()
        )
        return Optional.of(voteRepository.insert(vote))
    }
}