package com.pruebas.liti.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.pruebas.liti.Repository.IRolProofRepository;
import com.pruebas.liti.Repository.IUserProofRepository;
import com.pruebas.liti.Repository.RolUsuarioRepository;
import com.pruebas.liti.entity.RolEntityProof;



import reactor.core.publisher.Mono;
@Service
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
            Mono<List<RolEntityProof>> rolesMono = 
                        rolUsuarioRepository.findByUserId(account.getId())
                            .flatMap(rolUsuario -> rolRepository.findById(rolUsuario.getRolId()))
                            .collectList();
                            
                    return rolesMono.zipWith(Mono.just(account), (roles, acc) -> {
                        List<GrantedAuthority> authorities = roles.stream()
                            .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                            .collect(Collectors.toList());
                        
                        return User.builder()
                            .username(acc.getEmail())
                            .password(acc.getPassword())
                            .authorities(authorities)
                            .build();
                    });
        }).switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found with email: " + email)))
        .onErrorResume(Exception.class, e -> {
            System.out.println("Error");
            return Mono.error(new UsernameNotFoundException("User not found with email: " + email));
        });
    }
    
}
