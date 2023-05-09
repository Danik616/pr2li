package com.pruebas.liti.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("roles")
public class RolEntityProof {
    @Id
    private long id;

    private String nombre;

    @MappedCollection(idColumn = "rol_id", keyColumn = "user_id")
    private List<UserEntityProof> users;

    public RolEntityProof(String nombre, List<UserEntityProof> users) {
        this.nombre = nombre;
        this.users = users;
    }

    public RolEntityProof() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<UserEntityProof> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntityProof> users) {
        this.users = users;
    }

    
}
