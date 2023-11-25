package com.github.markoresic.footballvotingdemo.controller

import com.github.markoresic.footballvotingdemo.auth.AuthenticationRequest
import com.github.markoresic.footballvotingdemo.auth.AuthenticationResponse
import com.github.markoresic.footballvotingdemo.auth.AuthenticationService
import com.github.markoresic.footballvotingdemo.auth.RegisterRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
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

    @PostMapping("/logout")
    fun logout(
        @RequestBody refreshToken: String
    ): ResponseEntity<Unit> {
        if (authenticationService.logout(refreshToken)) {
            return ResponseEntity.status(HttpStatus.OK).build()
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }

    @PostMapping("/refresh-token")
    fun refreshAccessToken(
        @RequestBody refreshToken: String
    ): ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok(authenticationService.refreshAccessToken(refreshToken))
    }
}