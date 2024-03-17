package ru.shutov.rateuteacher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.shutov.rateuteacher.enums.Role;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("user")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        UserDetails moderator = User.builder()
                .username("moderator")
                .password("moderator")
                .roles("MODERATOR")
                .build();
        return new InMemoryUserDetailsManager(user, admin, moderator);
    }

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
