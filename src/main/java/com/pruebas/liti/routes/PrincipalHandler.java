package com.pruebas.liti.routes;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.pruebas.liti.Repository.IRolProofRepository;
import com.pruebas.liti.Repository.IUserProofRepository;
import com.pruebas.liti.dto.UserDto;
import com.pruebas.liti.entity.UserEntityProof;

import reactor.core.publisher.Mono;

@Component
public class PrincipalHandler {

    @Autowired
    private IRolProofRepository rolRepository;

    @Autowired
    private IUserProofRepository userRepository;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    private Mono<ServerResponse> response406 = ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build();

    public Mono<ServerResponse> listarUsuario(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRepository.findAll(), UserEntityProof.class);
    }

    public Mono<ServerResponse> guardarUsuario(ServerRequest serverRequest){
        Mono<UserDto> usuarioDto= serverRequest.bodyToMono(UserDto.class);

        return usuarioDto.flatMap(usuariod -> {
            if (!isValidEmail(usuariod.getEmail())) {
                return ServerResponse.badRequest().bodyValue("El email es inválido");
            }
            String encryptedPassword = encryptPassword(usuariod.getPassword());
            Mono<ServerResponse> role = rolRepository.findById(usuariod.getRole())
                    .flatMap(rol ->{
                        UserEntityProof user=new UserEntityProof(usuariod.getEmail(), encryptedPassword);
                        user.setRoles(Collections.singletonList(rol));
                        //user.incrementoOpcional();
                        return userRepository.save(user)
                                .flatMap(savedUser -> ServerResponse.accepted().build())
                                .switchIfEmpty(response406);
                    });
            return role.switchIfEmpty(ServerResponse.status(HttpStatus.BAD_REQUEST).build());
        });
            
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
