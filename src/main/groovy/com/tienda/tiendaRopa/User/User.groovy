package com.tienda.tiendaRopa.User

import jakarta.persistence.Basic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails




@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "app_user", uniqueConstraints = @UniqueConstraint(columnNames = ["username"]))
class User implements UserDetails {

    @Id
    @GeneratedValue
    Long id

    @Basic
    @Column(nullable = false)
    String username

    @Column(nullable = false)
    String lastname

    String firstname
    String country
    String password

    @Enumerated(EnumType.STRING)
    Role role

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return [new SimpleGrantedAuthority(role.name())] // Usamos una lista de Groovy
    }

    @Override
    boolean isAccountNonExpired() {
        return true
    }

    @Override
    boolean isAccountNonLocked() {
        return true
    }

    @Override
    boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    boolean isEnabled() {
        return true
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getUsername() {
        return username
    }

    void setUsername(String username) {
        this.username = username
    }

    String getLastname() {
        return lastname
    }

    void setLastname(String lastname) {
        this.lastname = lastname
    }

    String getFirstname() {
        return firstname
    }

    void setFirstname(String firstname) {
        this.firstname = firstname
    }

    String getCountry() {
        return country
    }

    void setCountry(String country) {
        this.country = country
    }

    String getPassword() {
        return password
    }

    void setPassword(String password) {
        this.password = password
    }

    Role getRole() {
        return role
    }

    void setRole(Role role) {
        this.role = role
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", country='" + country + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}