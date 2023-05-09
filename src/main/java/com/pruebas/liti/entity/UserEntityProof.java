package com.pruebas.liti.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("usuario")
public class UserEntityProof {

    @Id
    private long id;

    private String email;
    private String password;

    public UserEntityProof(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserEntityProof() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

}
