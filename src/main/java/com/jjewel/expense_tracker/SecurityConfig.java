package com.jjewel.expense_tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private Environment environment;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/public/**").permitAll() // Allow access to /public/**
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Allow only access by admin roles
                        .requestMatchers("/user/**").hasRole("USER") // Allow only access by user roles
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin((Customizer.withDefaults()))
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    @Profile("dev")
    public UserDetailsService inMemoryUserDetailsManager() {
        UserDetails user1 = User
                .withUsername(environment.getProperty("TEST_USER_NAME"))
                .password(environment.getProperty("TEST_USER_PASSWORD"))
                .roles("USER").build();

        UserDetails user2 = User
                .withUsername(environment.getProperty("ADMIN_USER_NAME"))
                .password(environment.getProperty("ADMIN_USER_PASSWORD"))
                .roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
