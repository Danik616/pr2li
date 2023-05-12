package com.pruebas.liti.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import com.pruebas.liti.services.UserServices;


import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    public UserServices userService;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        
            String username = authentication.getPrincipal().toString();
            String password = authentication.getCredentials().toString();

            System.out.println("This is the username: "+username+" and this is the pwd: "+password);
            // Get user details from user service based on username
            // Check if user details exist and if the provided password matches
            return userService.findByUsername(username).flatMap(userDetails -> {
                if (userDetails != null) {
                    return Mono.just(userDetails)
                            .filter(userDetail -> passwordEncoder.matches(password, userDetail.getPassword()))
                            .map(userDetail -> new UsernamePasswordAuthenticationToken(userDetail.getUsername(), userDetail.getPassword(), userDetail.getAuthorities()));
                } else {
                    return Mono.empty();
                }
            });
    }
}
