package com.github.markoresic.footballvotingdemo.auth

import com.github.markoresic.footballvotingdemo.config.JwtService
import com.github.markoresic.footballvotingdemo.model.user.Role
import com.github.markoresic.footballvotingdemo.model.user.User
import com.github.markoresic.footballvotingdemo.service.UserService
import com.github.markoresic.footballvotingdemo.token.RefreshToken
import com.github.markoresic.footballvotingdemo.token.RefreshTokenRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class AuthenticationService(
    private val userService: UserService,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager
) {

    fun register(request: RegisterRequest): AuthenticationResponse {
        val user = User(
            null,
            request.email,
            passwordEncoder().encode(request.password),
            request.nickname,
            Role.USER
        )
        val savedUser = try {
            userService.createUser(user)
        } catch (e: Exception) {
            throw RuntimeException("User with e-mail already exists!")
        }

        val accessToken = jwtService.generateAccessToken(savedUser)
        val refreshTokenDocument = RefreshToken(
            null,
            savedUser.idUser!!
        )
        val savedRefreshTokenDocument = refreshTokenRepository.save(refreshTokenDocument)
        val refreshToken = jwtService.generateRefreshToken(savedUser, savedRefreshTokenDocument.id!!)

        return AuthenticationResponse(savedUser.idUser, accessToken, refreshToken)
    }

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password)
        )
        val user = userService.getUser(request.email)

        val accessToken = jwtService.generateAccessToken(user)
        val refreshTokenDocument = RefreshToken(
            null,
            user.idUser!!
        )
        deleteAllRefreshTokens(user)
        val savedRefreshTokenDocument = refreshTokenRepository.save(refreshTokenDocument)
        val refreshToken = jwtService.generateRefreshToken(user, savedRefreshTokenDocument.id!!)

        return AuthenticationResponse(user.idUser, accessToken, refreshToken)
    }

    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(11)
    }

    fun deleteAllRefreshTokens(user: User) {
        val refreshTokens = refreshTokenRepository.findAllByUserId(user.idUser!!)
        if (refreshTokens.isEmpty()) {
            return
        }
        refreshTokenRepository.deleteAll(refreshTokens)
    }

    fun logout(refreshToken: String): Boolean {
        if (!jwtService.isTokenExpired(refreshToken)) {
            val refreshTokenId = jwtService.getTokenIdFromRefreshToken(refreshToken)
            if (refreshTokenId != null) {
                if (refreshTokenRepository.existsById(refreshTokenId)) {
                    refreshTokenRepository.deleteById(refreshTokenId)
                    return true
                }
            }
        }
        throw BadCredentialsException("Invalid token!")
    }

    fun refreshAccessToken(refreshToken: String): AuthenticationResponse {
        if (!jwtService.isTokenExpired(refreshToken)) {
            val refreshTokenId = jwtService.getTokenIdFromRefreshToken(refreshToken)
            if (refreshTokenId != null) {
                if (refreshTokenRepository.existsById(refreshTokenId)) {
                    val user = userService.getUser(jwtService.extractUsername(refreshToken))
                    val newAccessToken = jwtService.generateAccessToken(user)
                    return AuthenticationResponse(user.idUser!!, newAccessToken, refreshToken)
                }
            }
        }
        throw BadCredentialsException("Invalid token!")
    }
}