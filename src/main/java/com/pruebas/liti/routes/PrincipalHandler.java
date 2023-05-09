package com.pruebas.liti.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.pruebas.liti.Repository.IRolProofRepository;
import com.pruebas.liti.Repository.IUserProofRepository;
import com.pruebas.liti.Repository.RolUsuarioRepository;
import com.pruebas.liti.dto.UserDto;
import com.pruebas.liti.entity.RolUsuarioEntity;
import com.pruebas.liti.entity.UserEntityProof;

import reactor.core.publisher.Mono;

@Component
public class PrincipalHandler {

    @Autowired
    private IRolProofRepository rolRepository;

    @Autowired
    private IUserProofRepository userRepository;

    @Autowired
    private RolUsuarioRepository rolUsuarioRepository;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    private Mono<ServerResponse> response406 = ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build();

    public Mono<ServerResponse> listarUsuario(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRepository.findAll(), UserEntityProof.class);
    }

    public Mono<ServerResponse> guardarUsuario(ServerRequest serverRequest){
        Mono<UserDto> userDto = serverRequest.bodyToMono(UserDto.class);
    
        return userDto.flatMap(usuarioDto -> {
            if (!isValidEmail(usuarioDto.getEmail())) {
                return ServerResponse.badRequest().bodyValue("El email es inválido");
            }
    
            String encryptedPassword = encryptPassword(usuarioDto.getPassword());
    
            // Guardar el objeto UserEntityProof sin la relación con el rol
            UserEntityProof user = new UserEntityProof(usuarioDto.getEmail(), encryptedPassword);
    
            Mono<UserEntityProof> savedUserMono = userRepository.save(user);
    
            // Guardar la relación entre el usuario y el rol en la tabla intermedia
            Mono<RolUsuarioEntity> savedRolUsuarioMono = savedUserMono
                .flatMap(savedUser -> rolRepository.findById(usuarioDto.getRole())
                .flatMap(rol -> {
                    RolUsuarioEntity rolUsuarioEntity = new RolUsuarioEntity(rol.getId(), savedUser.getId());
                    return rolUsuarioRepository.save(rolUsuarioEntity);
                }));
    
            // Combinar los resultados y retornar una respuesta HTTP
            return savedRolUsuarioMono
                .flatMap(savedRolUsuario -> ServerResponse.accepted().build())
                .switchIfEmpty(ServerResponse.status(HttpStatus.BAD_REQUEST).build());
        });
    }

    public Mono<ServerResponse> iniciarSesión(ServerRequest serverRequest){
        return ServerResponse.ok().build();
    }
    

    private boolean isValidEmail(String email) {
        String patron = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        // Explicación del patrón:
        // ^ -> Inicio de línea
        // [\\w.-]+ -> Uno o más caracteres alfanuméricos, punto o guión
        // @ -> Símbolo arroba
        // [\\w.-]+ -> Uno o más caracteres alfanuméricos, punto o guión (dominio)
        // \\.[a-zA-Z]{2,} -> Punto y dos o más letras (extensión)
        // $ -> Fin de línea

        return email.matches(patron);
    }

    private String encryptPassword(String password) {
        password = passwordEncoder.encode(password);
        return password;
    }
}
