package com.pruebas.liti.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;



@Table("rol_usuario")
public class RolUsuarioEntity {
    @Id
    private Long rolId;
    
    @Id
    private Long usuarioId;

    
    public RolUsuarioEntity(Long rolId, Long usuarioId) {
        this.rolId = rolId;
        this.usuarioId = usuarioId;
    }

    public Long getRolId() {
        return rolId;
    }
    
    public Long getUsuarioId() {
        return usuarioId;
    }
    
}

