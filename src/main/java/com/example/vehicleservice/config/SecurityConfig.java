package com.example.vehicleservice.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.vehicleservice.security.CustomUserDetailService;
import com.example.vehicleservice.security.JwtAuthenticationEntryPoint;
import com.example.vehicleservice.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//	    private final JwtAuthenticationEntryPoint authenticationEntryPoint;
//	    private final CustomUserDetailService userDetailService;
//	    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//
//	    public SecurityConfig(JwtAuthenticationEntryPoint authenticationEntryPoint,
//	                          CustomUserDetailService userDetailService,
//	                          JwtAuthenticationFilter jwtAuthenticationFilter) {
//	        this.authenticationEntryPoint = authenticationEntryPoint;
//	        this.userDetailService = userDetailService;
//	        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//	    }
        @Autowired
	    private JwtAuthenticationEntryPoint authenticationEntryPoint;
        
        @Autowired
	    private CustomUserDetailService userDetailService;
        
        @Autowired
	    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/auth/**").permitAll()
                    .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
