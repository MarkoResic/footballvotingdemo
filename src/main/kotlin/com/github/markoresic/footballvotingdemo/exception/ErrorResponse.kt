package com.github.markoresic.footballvotingdemo.exception

data class ErrorResponse(
    var status: Int? = null,
    var message: String? = null,
    var stackTrace: String? = null
)
