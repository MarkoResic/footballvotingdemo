package com.github.markoresic.footballvotingdemo.repository

import com.github.markoresic.footballvotingdemo.model.Vote
import org.springframework.data.mongodb.repository.MongoRepository

interface VoteRepository : MongoRepository<Vote, String> {
}