package com.tienda.tiendaRopa.Demo

import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class DemoController {

    @PostMapping("demo")
    String welcome() {
        return "Welcome from secure endpoint"
    }
}
