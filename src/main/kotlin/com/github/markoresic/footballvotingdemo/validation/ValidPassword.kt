package com.github.markoresic.footballvotingdemo.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@MustBeDocumented
@Constraint(validatedBy = [PasswordConstraintValidator::class])
annotation class ValidPassword(
    val message: String = "Invalid password",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)