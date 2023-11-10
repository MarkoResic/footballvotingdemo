package com.github.markoresic.footballvotingdemo.exception

import io.jsonwebtoken.ExpiredJwtException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
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

    @ExceptionHandler(UserNotCreatedException::class)
    fun handleUserNotCreated(e: UserNotCreatedException): ResponseEntity<String> {
        return ResponseEntity("User with e-mail already exists!", HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleInvalidArgument(e: MethodArgumentNotValidException): ResponseEntity<Map<String, String?>> {
        val errorMap = HashMap<String, String?>()
        e.bindingResult.fieldErrors.forEach { error ->
            errorMap[error.field] = error.defaultMessage
        }
        return ResponseEntity(errorMap, HttpStatus.BAD_REQUEST)
    }
}