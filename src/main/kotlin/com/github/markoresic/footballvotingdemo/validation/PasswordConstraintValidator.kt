package com.github.markoresic.footballvotingdemo.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class PasswordConstraintValidator : ConstraintValidator<ValidPassword, String> {
    override fun isValid(password: String?, context: ConstraintValidatorContext?): Boolean {

        return !password.isNullOrBlank()
                && password.length in 8..30
                && stringContainsSpecialChar(password)
    }

    private fun stringContainsSpecialChar(s: String): Boolean {
        val special = s.find { c: Char -> !c.isLetterOrDigit() }
        return special != null
    }
}