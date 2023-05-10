package com.pruebas.liti.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import com.pruebas.liti.Repository.IUserProofRepository;

import reactor.core.publisher.Mono;

public class UserServices implements IUserServices{

    @Autowired
    public IUserProofRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }
    
}
