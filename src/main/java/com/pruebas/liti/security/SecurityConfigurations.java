package com.pruebas.liti.security;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import com.pruebas.liti.security.jwt.JwtFilter;
import com.pruebas.liti.security.repository.SecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfigurations {

    private final SecurityContextRepository securityContextRepository;

    public SecurityConfigurations(SecurityContextRepository securityContextRepository) {
        this.securityContextRepository = securityContextRepository;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, JwtFilter jwtFilter) {
        http
                .authorizeExchange()
                .pathMatchers("/admin/**").hasRole("ADMIN")
                .pathMatchers("/**").permitAll()
                //.anyExchange().authenticated()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                //.addFilterAfter(jwtFilter, SecurityWebFiltersOrder.FIRST)
                //.securityContextRepository(securityContextRepository)
                .logout(logout -> logout
                        .logoutUrl("/logout"))
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .cors();

        return http.build();
    }

}
