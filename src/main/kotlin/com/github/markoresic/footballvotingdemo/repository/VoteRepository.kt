package com.github.markoresic.footballvotingdemo.repository

import com.github.markoresic.footballvotingdemo.model.vote.Vote
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDateTime

interface VoteRepository : MongoRepository<Vote, String> {

    fun findByVotedDateTimeIsAfter(localDateTime: LocalDateTime): List<Vote>

    fun findByUserIdAndVotedDateTimeIsAfter(userId: String, localDateTime: LocalDateTime): List<Vote>

    fun findByPlayerId(playerId: String): List<Vote>
}