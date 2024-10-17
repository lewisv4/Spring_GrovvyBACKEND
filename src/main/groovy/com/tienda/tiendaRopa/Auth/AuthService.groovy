package com.tienda.tiendaRopa.Auth

import com.tienda.tiendaRopa.Jwt.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import com.tienda.tiendaRopa.User.User
import com.tienda.tiendaRopa.User.Role
import com.tienda.tiendaRopa.User.UserRepository

@Service
class AuthService {

    private final UserRepository userRepository
    private final JwtService jwtService
    private final PasswordEncoder passwordEncoder
    private final AuthenticationManager authenticationManager

    // Constructor generado por @RequiredArgsConstructor ya no es necesario si usamos final
    AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository
        this.jwtService = jwtService
        this.passwordEncoder = passwordEncoder
        this.authenticationManager = authenticationManager
    }

    AuthResponse login(LoginRequest request) {
        try {
            println ("login: " + request);

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username, request.password)
            )
            UserDetails user = userRepository.findByUsername(request.username)
                    .orElseThrow { new RuntimeException("User not found") }
            String token = jwtService.generateToken(user)
            println("Generated token: $token")
            return new AuthResponse(token: token)
        } catch (Exception e) {
            println("Login failed: ${e.message}")
            e.printStackTrace();
            println ("login: " + request);
            throw e
        }
    }



    AuthResponse register(RegisterRequest request) {
        println("Registering user: ${request.username}")
        try {
            // Check if user already exists
            if (userRepository.findByUsername(request.username).isPresent()) {
                println("User ${request.username} already exists")
                throw new RuntimeException("User already exists")
            }

            User user = new User(
                    username: request.username,
                    password: passwordEncoder.encode(request.password),
                    firstname: request.firstname,
                    lastname: request.lastname,
                    country: request.country,
                    role: Role.USER
            )

            println("Saving user: $user")
            user = userRepository.save(user)
            println("User saved with ID: ${user.id}")

            String token = jwtService.generateToken(user)
            println("Generated token for ${user.username}: $token")

            return new AuthResponse(token: token)
        } catch (Exception e) {
            println("Registration failed: ${e.message}")
            e.printStackTrace()
            throw e
        }
    }
}
