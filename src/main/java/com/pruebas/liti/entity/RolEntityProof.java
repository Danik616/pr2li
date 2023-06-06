package com.pruebas.liti.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("roles")
public class RolEntityProof {
    @Id
    private long rolId;

    private String rolNombre;


    public RolEntityProof() {
    }

    public long getId() {
        return rolId;
    }

    public void setId(long id) {
        this.rolId = id;
    }

    public String getNombre() {
        return rolNombre;
    }

    public void setNombre(String nombre) {
        this.rolNombre = nombre;
    }

}
