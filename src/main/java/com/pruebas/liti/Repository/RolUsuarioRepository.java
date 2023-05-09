package com.pruebas.liti.Repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.pruebas.liti.entity.RolUsuarioEntity;
import com.pruebas.liti.helpers.RolUsuarioCompositeKey;

public interface RolUsuarioRepository extends R2dbcRepository<RolUsuarioEntity, RolUsuarioCompositeKey>{
    
}
