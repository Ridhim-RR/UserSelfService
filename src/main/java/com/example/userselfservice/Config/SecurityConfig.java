//package com.example.userselfservice.Config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//    @Configuration
//    public class SecurityConfig {
//        @Bean
//        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http
//                    .authorizeHttpRequests((requests) -> requests
//                            .anyRequest().permitAll() // Allow all requests without authentication
//                    )
//                    .cors(AbstractHttpConfigurer::disable);  // Disable CORS protection
//                    http.csrf(AbstractHttpConfigurer::disable); // Disable CSRF protection
//
//            return http.build();
//        }
//    }
