package com.github.markoresic.footballvotingdemo.config

import com.github.markoresic.footballvotingdemo.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException


@Configuration
class ApplicationConfig(val userRepository: UserRepository) {

    @Bean
    fun userDetailsService(): UserDetailsService {
        return UserDetailsService { email: String ->
            userRepository.findByEmail(email).orElseThrow { UsernameNotFoundException("User not found") }
        }
    }

}