package com.tienda.tiendaRopa.Auth

import groovy.transform.Canonical
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Canonical
public class AuthResponse {
    String token;
}
