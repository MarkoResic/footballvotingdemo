package com.github.markoresic.footballvotingdemo.service

import com.github.markoresic.footballvotingdemo.extension.toUserResponse
import com.github.markoresic.footballvotingdemo.model.user.User
import com.github.markoresic.footballvotingdemo.model.user.UserResponse
import com.github.markoresic.footballvotingdemo.repository.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository) {

    fun getUser(email: String): User =
        userRepository.findByEmail(email).orElseThrow { UsernameNotFoundException("User not found") }

    fun getUserResponse(email: String): UserResponse {
        return getUser(email).toUserResponse()
    }

    fun getUserId(email: String): String {
        val user = userRepository.findByEmail(email).orElseThrow { UsernameNotFoundException("User not found") }
        return user.idUser!!
    }

    fun createUser(user: User): User =
        userRepository.save(user)

}
