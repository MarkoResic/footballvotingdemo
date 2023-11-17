package com.github.markoresic.footballvotingdemo.controller

import com.github.markoresic.footballvotingdemo.model.user.UserResponse
import com.github.markoresic.footballvotingdemo.service.UserService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getUser(): UserResponse {
        val authentication = SecurityContextHolder.getContext().authentication
        val email: String = authentication.name
        return userService.getUserResponse(email)
    }
}