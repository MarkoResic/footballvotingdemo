package com.github.markoresic.footballvotingdemo.auth

data class RegisterRequest(
    val email: String,
    val password: String,
    val nickname: String
)