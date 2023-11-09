package com.github.markoresic.footballvotingdemo.exception

import io.jsonwebtoken.ExpiredJwtException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalErrorHandler {

    @ExceptionHandler(ExpiredJwtException::class)
    fun handleExpiredJwt(e: ExpiredJwtException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.FORBIDDEN)
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)
}