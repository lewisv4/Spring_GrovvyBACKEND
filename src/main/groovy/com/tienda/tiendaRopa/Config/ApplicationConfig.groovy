package com.tienda.tiendaRopa.Config

import com.tienda.tiendaRopa.User.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@RequiredArgsConstructor
class ApplicationConfig {

    private final UserRepository userRepository // Eliminar @Autowired

    @Autowired
    ApplicationConfig(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        println("AuthenticationManager")
        return config.authenticationManager
    }

    @Bean
    UserDetailsService userDetailService() {
        return { username ->
            userRepository.findByUsername(username)
                    .orElseThrow { new UsernameNotFoundException("User not found $username") }
        } as UserDetailsService // Cast a closure como UserDetailsService
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        println("AuthenticationManager")
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider()
        authenticationProvider.setUserDetailsService(userDetailService())
        authenticationProvider.setPasswordEncoder(passwordEncoder())
        return authenticationProvider
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        println("passwordEncoder")
        return new BCryptPasswordEncoder()
    }


}

