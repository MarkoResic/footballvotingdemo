package com.github.markoresic.footballvotingdemo.exception

import io.jsonwebtoken.ExpiredJwtException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalErrorHandler {

    @ExceptionHandler(ExpiredJwtException::class)
    fun handleExpiredJwt(e: ExpiredJwtException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.message)
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.NOT_FOUND.value(), e.message)
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(e: RuntimeException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.message)
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(BadCredentialsException::class)
    fun handleRuntimeException(e: BadCredentialsException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.UNAUTHORIZED.value(), e.message)
        return ResponseEntity(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleInvalidArgument(e: MethodArgumentNotValidException): ResponseEntity<Map<String, String?>> {
        val errorMap = HashMap<String, String?>()
        e.bindingResult.fieldErrors.forEach { error ->
            errorMap[error.field] = error.defaultMessage
        }
        return ResponseEntity(errorMap, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(DuplicateEmailException::class)
    fun handleDuplicateEmailException(e: DuplicateEmailException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(HttpStatus.CONFLICT.value(), e.message)
        return ResponseEntity(errorResponse, HttpStatus.CONFLICT)
    }
}