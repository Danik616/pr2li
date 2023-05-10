package com.pruebas.liti.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.pruebas.liti.Repository.IRolProofRepository;
import com.pruebas.liti.Repository.IUserProofRepository;
import com.pruebas.liti.Repository.RolUsuarioRepository;
import com.pruebas.liti.entity.RolEntityProof;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserServices implements IUserServices{

    @Autowired
    public IUserProofRepository userRepository;

    @Autowired
    public IRolProofRepository rolRepository;

    @Autowired
    public RolUsuarioRepository rolUsuarioRepository;

    @Override
    public Mono<UserDetails> findByUsername(String email) {
        return userRepository.findByEmail(email).flatMap( account -> {
            Flux<RolEntityProof> userBuilderMono = 
                rolUsuarioRepository.findByUserId(account.getId())
                    .flatMap(rolUsuario -> rolRepository.findById(rolUsuario.getRolId()));

                    return userBuilderMono.collectList().map(roles -> {
                        List<GrantedAuthority> authorities = roles.stream()
                                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                                .collect(Collectors.toList());
            
                        return User.builder()
                                .username(account.getEmail())
                                .password(account.getPassword())
                                .authorities(authorities)
                                .build();
                    });
        });
    }
    
}
