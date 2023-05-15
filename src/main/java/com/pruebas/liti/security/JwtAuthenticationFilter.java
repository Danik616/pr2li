package com.pruebas.liti.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import com.pruebas.liti.ExceptionsAndResponses.AuthenticationException;
import com.pruebas.liti.services.JwtUtil;

import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private final JwtUtil jwtTokenProvider;

    public JwtAuthenticationFilter(JwtUtil jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (exchange.getRequest().getURI().getPath().equals("/login")) {
            return chain.filter(exchange);
        }
        String token = extractTokenFromHeader(exchange.getRequest().getHeaders().getFirst(AUTHORIZATION_HEADER));
        if (token == null) {
            return Mono.error(new AuthenticationException("Invalid token")); // Invalid token
        }

        return jwtTokenProvider.validateToken(token)
                .flatMap(valid -> jwtTokenProvider.getAuthentication(token))
                .flatMap(authentication -> {
                    System.out.println("Este sera el auth " + authentication);

                    return chain.filter(exchange)
                            .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
                })
                .onErrorResume(AuthenticationException.class, e -> handleAuthenticationError(exchange, e))
                .switchIfEmpty(Mono.error(new AuthenticationException("Invalid token")));
    }

    private String extractTokenFromHeader(String header) {
        if (header != null && header.startsWith(BEARER_PREFIX)) {
            return header.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    private Mono<Void> handleAuthenticationError(ServerWebExchange exchange, AuthenticationException e) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
