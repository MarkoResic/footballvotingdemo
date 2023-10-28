package com.github.markoresic.footballvotingdemo.controller

import com.github.markoresic.footballvotingdemo.model.user.UserResponse
import com.github.markoresic.footballvotingdemo.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun getUser(): UserResponse {
        val authentication = SecurityContextHolder.getContext().authentication
        val email: String = authentication.name
        return userService.getUser(email)
    }
}