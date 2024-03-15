package ru.shutov.rateuteacher.config;

import jakarta.servlet.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.shutov.rateuteacher.enums.Role;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/profile/admin")
                                .hasAnyRole(Role.ROLE_ADMIN.getRole())
                                .requestMatchers("/profile/**")
                                .hasAnyRole(Role.ROLE_MODERATOR.getRole(), Role.ROLE_ADMIN.getRole())
                                .anyRequest().permitAll())
                .formLogin(form ->
                        form.loginPage("/auth/login")
                                .loginProcessingUrl("/perform_login")
                                .defaultSuccessUrl("/profile", true)
                                .failureUrl("/auth/login?error"))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
