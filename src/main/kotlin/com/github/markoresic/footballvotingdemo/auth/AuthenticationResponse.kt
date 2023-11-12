package com.github.markoresic.footballvotingdemo.auth

data class AuthenticationResponse(
    val userId: String,
    val accessToken: String,
    val refreshToken: String
)