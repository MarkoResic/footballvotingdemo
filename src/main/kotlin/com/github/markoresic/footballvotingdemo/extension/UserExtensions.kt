package com.github.markoresic.footballvotingdemo.extension

import com.github.markoresic.footballvotingdemo.model.user.User
import com.github.markoresic.footballvotingdemo.model.user.UserResponse

fun User.toUserView() = UserResponse(
    email,
    nickname,
    role.name
)