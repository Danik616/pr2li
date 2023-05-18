package com.pruebas.liti.security.jwt;

import org.springframework.stereotype.Component;

@Component
public class JwtRepository {

    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public JwtRepository() {
    }

    

    
}
