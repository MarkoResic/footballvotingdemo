package com.github.markoresic.footballvotingdemo.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService(
    @Value("\${application.security.jwt.secret-key}")
    private val secretKey: String,
    @Value("\${application.security.jwt.expiration}")
    private val jwtExpiration: Long,
    @Value("\${application.security.jwt.refresh-token.expiration}")
    private val refreshExpiration: Long

) {
    fun extractUsername(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    fun generateAccessToken(userDetails: UserDetails): String {
        return generateAccessToken(HashMap(), userDetails)
    }

    fun generateAccessToken(
        extraClaims: Map<String, Any?>,
        userDetails: UserDetails
    ): String {
        return buildAccessToken(extraClaims, userDetails, jwtExpiration)
    }

    fun generateRefreshToken(userDetails: UserDetails, documentRefreshTokenId: String): String {
        return buildRefreshToken(documentRefreshTokenId, userDetails, refreshExpiration)
    }

    private fun buildAccessToken(
        extraClaims: Map<String, Any?>,
        userDetails: UserDetails,
        expiration: Long
    ): String {
        return Jwts
            .builder()
            .claims(extraClaims)
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), Jwts.SIG.HS256)
            .compact()
    }

    private fun buildRefreshToken(
        documentRefreshTokenId: String,
        userDetails: UserDetails,
        expiration: Long
    ): String {
        return Jwts
            .builder()
            .claim("tokenId", documentRefreshTokenId)
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), Jwts.SIG.HS256)
            .compact()
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts
            .parser()
            .verifyWith(getSignInKey())
            .build()
            .parseSignedClaims(token)
            .payload
    }

    private fun getSignInKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun getTokenIdFromRefreshToken(token: String): String? {
        return extractClaim(token) { obj: Claims -> obj["tokenId"] }?.toString()
    }
}