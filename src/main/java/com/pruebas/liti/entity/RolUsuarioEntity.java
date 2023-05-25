package com.pruebas.liti.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;



@Table("rol_usuario")
public class RolUsuarioEntity {
    @Id
    private long id;

    private long rolId;
    
    
    private String usuarioId;

    
    public RolUsuarioEntity(long rolId, String usuarioId) {
        this.rolId = rolId;
        this.usuarioId = usuarioId;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public long getRolId() {
        return rolId;
    }


    public void setRolId(long rolId) {
        this.rolId = rolId;
    }


    public String getUsuarioId() {
        return usuarioId;
    }


    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    
    
}

