package com.syseva.syseva.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions((frameOptionsConfig -> frameOptionsConfig.disable())))
                .authorizeHttpRequests(authz -> authz.anyRequest().permitAll());
        return http.build();
    }
}
