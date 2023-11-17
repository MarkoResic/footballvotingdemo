package com.github.markoresic.footballvotingdemo.model.player

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import java.util.*

data class PlayerDetails(
    @field:NotBlank(message = "Name must not be blank")
    val name: String,
    @field:Past(message = "Date of birth must be in the past")
    val dob: Date,
    val imageUrl: String,
    @field:NotBlank(message = "Team name must not be blank")
    val teamName: String,
    @field:NotBlank(message = "Position must not be blank")
    val position: String,
    @field:NotBlank(message = "Jersey number must not be blank")
    val jerseyNumber: String,
    var votes: Int = 0
)