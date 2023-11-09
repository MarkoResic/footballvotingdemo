package com.github.markoresic.footballvotingdemo.auth

import com.github.markoresic.footballvotingdemo.config.JwtService
import com.github.markoresic.footballvotingdemo.model.user.User
import com.github.markoresic.footballvotingdemo.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager
) {

    fun register(request: RegisterRequest): AuthenticationResponse {
        val savedUser = userRepository.save(User(null, request.email, passwordEncoder.encode(request.password), request.nickname, request.role))
        val jwtToken = jwtService.generateToken(savedUser)
        return AuthenticationResponse(jwtToken)
    }

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse{
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password))
        val user = userRepository.findByEmail(request.email).orElseThrow()
        val jwtToken = jwtService.generateToken(user)
        return AuthenticationResponse(jwtToken)
    }
}