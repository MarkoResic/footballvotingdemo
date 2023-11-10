package com.github.markoresic.footballvotingdemo.controller

import com.github.markoresic.footballvotingdemo.auth.AuthenticationRequest
import com.github.markoresic.footballvotingdemo.auth.AuthenticationResponse
import com.github.markoresic.footballvotingdemo.auth.AuthenticationService
import com.github.markoresic.footballvotingdemo.auth.RegisterRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {
    @PostMapping("/register")
    fun register(
        @Valid @RequestBody request: RegisterRequest
    ): ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok(authenticationService.register(request))
    }

    @PostMapping("/authenticate")
    fun authenticate(
        @RequestBody request: AuthenticationRequest
    ): ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok(authenticationService.authenticate(request))
    }
}