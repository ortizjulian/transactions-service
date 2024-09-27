package com.emazon.transactions.infrastructure.configuration;

import com.emazon.transactions.infrastructure.output.security.jwt.JwtAuthenticationFilter;
import com.emazon.transactions.utils.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigFilter {
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/supply/next/**").hasAuthority(SecurityConstants.ROLE_CLIENT)
                                .requestMatchers(HttpMethod.POST,"/supply/**").hasAuthority(SecurityConstants.ROLE_WAREHOUSE_ASSISTANT)
                                .requestMatchers("/sale/**").hasAuthority(SecurityConstants.ROLE_CLIENT)
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}