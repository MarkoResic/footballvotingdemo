package com.github.markoresic.footballvotingdemo.auth

import com.github.markoresic.footballvotingdemo.validation.ValidPassword
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class RegisterRequest(
    @field:NotBlank(message = "Email must not be blank")
    @field:Email(message = "Email must be valid")
    val email: String,
    @ValidPassword
    val password: String,
    @field:NotBlank(message = "Nickname must not be blank")
    val nickname: String
)