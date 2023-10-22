package com.github.markoresic.footballvotingdemo.model

data class User(
    val idUser: Int, var email: String, var nickname: String, var isAdmin: Boolean
)