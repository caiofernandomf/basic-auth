package io.linuxtips.basicauth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final UserAuthenticationService userAuthenticationService;

    public SecurityConfig(UserAuthenticationService userAuthenticationService){
        this.userAuthenticationService=userAuthenticationService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        String idForEncode = "bcrypt";
        return new DelegatingPasswordEncoder(idForEncode,Map.of(idForEncode,new BCryptPasswordEncoder()));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize)-> authorize
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/student/find/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/users/**")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .anyRequest().authenticated()).httpBasic(Customizer.withDefaults()).build();

    }

}
