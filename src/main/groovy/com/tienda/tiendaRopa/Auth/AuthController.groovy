package com.tienda.tiendaRopa.Auth

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    private AuthService authService

    @PostMapping("demo")
    String welcome() {
        println("entroa demo")
        return "Welcome from secure endpoint"
    }


    @PostMapping("test")
    String test() {
        println("entroa test")

        return "Welcome from secure endpoint"
    }

    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        println(request)
        ResponseEntity.ok(authService.login(request))
    }

    @PostMapping("/register")
    ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        println(registerRequest)
        AuthResponse response = authService.register(registerRequest)
        ResponseEntity.ok(response)
    }
}