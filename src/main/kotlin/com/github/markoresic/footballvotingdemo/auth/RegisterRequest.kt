package com.github.markoresic.footballvotingdemo.auth

import com.github.markoresic.footballvotingdemo.model.user.Role

data class RegisterRequest(
    val email: String,
    val password: String,
    val nickname: String,
    val role: Role
) {
}