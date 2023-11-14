package com.kunikhin.kata313.configs;

import com.kunikhin.kata313.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserService userService;
    private final SuccessUserHandler successUserHandler;

    @Autowired
    public SecurityConfig(UserService userService, SuccessUserHandler successUserHandler) {
        this.userService = userService;
        this.successUserHandler = successUserHandler;
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                                .requestMatchers("/user/**").hasAnyAuthority("ADMIN", "USER")
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin.successHandler(successUserHandler).permitAll()
                )
                .logout(logout ->logout.permitAll()
                );
        return httpSecurity.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
