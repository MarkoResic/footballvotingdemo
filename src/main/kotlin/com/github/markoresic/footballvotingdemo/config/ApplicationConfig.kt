package com.github.markoresic.footballvotingdemo.config

import com.github.markoresic.footballvotingdemo.repository.UserRepository
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException


@Configuration
class ApplicationConfig {

    //Property must be initialized or be abstract
    val repository: UserRepository

    val userDetailsService = object : UserDetailsService {
        override fun loadUserByUsername(username: String): UserDetails {
            return repository.findByEmail(username) ?: throw UsernameNotFoundException("User not found")
        }
    }
}