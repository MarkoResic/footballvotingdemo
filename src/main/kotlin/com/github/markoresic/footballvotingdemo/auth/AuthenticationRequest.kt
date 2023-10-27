package com.github.markoresic.footballvotingdemo.auth

data class AuthenticationRequest(
    val email: String,
    val password: String
) {
}