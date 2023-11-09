package com.github.markoresic.footballvotingdemo.extension

import com.github.markoresic.footballvotingdemo.model.user.User
import com.github.markoresic.footballvotingdemo.model.user.UserResponse

fun User.toUserResponse() = UserResponse(
    email,
    nickname,
    role.name
)