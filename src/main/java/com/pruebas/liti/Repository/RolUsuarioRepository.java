package com.pruebas.liti.Repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.pruebas.liti.entity.RolUsuarioEntity;

import reactor.core.publisher.Flux;


public interface RolUsuarioRepository extends R2dbcRepository<RolUsuarioEntity, Long>{
    
    @Query("SELECT * FROM rol_usuario WHERE usuario_id = :id")
    public Flux<RolUsuarioEntity> findByUserId(String id);
}
