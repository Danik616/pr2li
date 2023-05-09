package com.pruebas.liti.helpers;

import java.io.Serializable;

public class RolUsuarioCompositeKey implements Serializable {
    private Long rolId;
    private Long usuarioId;

    public RolUsuarioCompositeKey() {
    }

    public RolUsuarioCompositeKey(Long rolId, Long usuarioId) {
        this.rolId = rolId;
        this.usuarioId = usuarioId;
    }
    
    public Long getRolId() {
        return rolId;
    }
    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }
    public Long getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    
}
