package com.pruebas.liti.dto;

public class UserDto {
    private String email;
    private String password;
    private long role;

    public UserDto(String email, String password, long role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }

    
}
