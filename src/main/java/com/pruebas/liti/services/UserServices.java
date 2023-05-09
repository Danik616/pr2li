package com.pruebas.liti.services;

import org.springframework.security.core.userdetails.UserDetails;

import reactor.core.publisher.Mono;

public class UserServices implements IUserServices{

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }
    
}
