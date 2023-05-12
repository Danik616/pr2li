package com.pruebas.liti.security;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;

//import com.pruebas.liti.services.UserServices;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfigurations {

    // @Autowired
    // private UserServices userServices;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
         http
            .authorizeExchange()
                .pathMatchers("/admin/**").hasRole("ADMIN")
                .pathMatchers("/login").permitAll()
                .anyExchange().authenticated()
                .and()
            .httpBasic().disable()
            .formLogin().disable()
            .logout(logout -> logout
                .logoutUrl("/logout"))
            .csrf().disable().cors().disable()
            .securityContextRepository(new WebSessionServerSecurityContextRepository());

        return http.build();
    }
}
