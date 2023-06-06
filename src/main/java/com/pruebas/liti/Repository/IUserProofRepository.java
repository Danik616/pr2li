package com.pruebas.liti.Repository;


import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;

import com.pruebas.liti.entity.UserEntityProof;
import reactor.core.publisher.Mono;

public interface IUserProofRepository extends R2dbcRepository<UserEntityProof, String> {
    @Query("SELECT * FROM usuarios WHERE USUARIO_CORREO =:email")
    public Mono<UserEntityProof> findByEmail(String email);

    @Query("SELECT COUNT(*) FROM usuarios WHERE USUARIO_CORREO = :email")
    Mono<Integer> countByEmail(@Param("email") String email);

    @Query("SELECT COUNT(*) FROM usuarios WHERE USUARIO_ID = :email")
    Mono<Integer> countByUsername(@Param("email") String email);

    @Query("Insert into USUARIOS (USUARIO_ID,LOCALIDAD_ID,TP_DOCUMENTO_ID, USUARIO_CLAVE, USUARIO_ESTADO, USUARIO_IDENTIFICACION,USUARIO_NOMBRE1,USUARIO_NOMBRE2,USUARIO_NOMBRE3,USUARIO_NOMBRE4,USUARIO_CORREO, PERFIL_ID) values (:usuarioId, :localidadId, :tpDocumentoId, :password, :usuarioEstado, :usuarioIdentificacion, :nombre1, :nombre2, :apellido1, :apellido2, :email, :perfilId)")
    Mono<UserEntityProof> insertUsername(@Param("email") String email, @Param("password") String password,
            @Param("localidadId") long localidadId, @Param("tpDocumentoId") String tpDocumentoId,
            @Param("usuarioIdentificacion") String usuarioIdentificacion, @Param("usuarioEstado") String usuarioEstado,
            @Param("usuarioId") String usuarioId, @Param("nombre1") String nombre1, @Param("nombre2") String nombre2,
            @Param("apellido1") String apellido1, @Param("apellido2") String apellido2, @Param("perfilId") long perfilId);

    @Query("SELECT * FROM USUARIOS WHERE USUARIO_ID = :user")
    Mono<UserEntityProof> findByIdUser(@Param("user") String user);

    @Query("SELECT count(*) from usuarios WHERE USUARIO_ID = :user AND TOOLKIT.DECRYPT (USUARIO_CLAVE) = :password")
    Mono<Integer> validatePassword(@Param("user") String user, @Param("password") String password);

}
