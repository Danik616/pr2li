package com.pruebas.liti.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.pruebas.liti.ExceptionsAndResponses.AuthResponse;
import com.pruebas.liti.Repository.IRolProofRepository;
import com.pruebas.liti.Repository.IUserProofRepository;
import com.pruebas.liti.Repository.RolUsuarioRepository;
import com.pruebas.liti.dto.UserDto;
import com.pruebas.liti.dto.UserFindByIDDTO;
import com.pruebas.liti.dto.UserLoginDto;
import com.pruebas.liti.entity.RolUsuarioEntity;
import com.pruebas.liti.entity.UserEntityProof;
import com.pruebas.liti.security.jwt.JwtRepository;
import com.pruebas.liti.services.JwtUtil;
import com.pruebas.liti.services.UserServices;

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
    private UserServices userServices;

    @Autowired
    public JwtUtil jwtUtil;

    @Autowired
    private final JwtRepository jwt;

    public PrincipalHandler(JwtRepository jwt) {
        this.jwt = jwt;
    }

    // private Mono<ServerResponse> response406 =
    // ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build();
    private Mono<ServerResponse> response401 = ServerResponse.status(HttpStatus.UNAUTHORIZED).build();

    public Mono<ServerResponse> iniciarSesion(ServerRequest serverRequest) {
        Mono<UserLoginDto> loginRequest = serverRequest.bodyToMono(UserLoginDto.class);

        return loginRequest
                .flatMap(form -> {
                    return userServices.findByUsername(form.getUsername().toUpperCase())
                            .flatMap(userDetails -> {
                                return userRepository.validatePassword(userDetails.getUsername(), form.getPassword())
                                        .flatMap(valid -> {
                                            if (valid == 1) {
                                                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                                        userDetails.getUsername(), userDetails.getPassword(),
                                                        userDetails.getAuthorities());
                                                SecurityContext securityContext = SecurityContextHolder
                                                        .createEmptyContext();
                                                String authJwt = userDetails.getUsername();
                                                return jwtUtil.generateToken(authJwt).flatMap(token -> {
                                                    securityContext.setAuthentication(authentication);
                                                    jwt.setJwt(token);
                                                    return ServerResponse.ok()
                                                            .bodyValue(
                                                                    new AuthResponse(true, token, "Login successful"));
                                                });
                                            } else {
                                                return response401;
                                            }
                                        });
                            }).switchIfEmpty(response401);
                })
                .onErrorResume(Exception.class,
                        e -> ServerResponse.status(444).bodyValue("Hay un error en el servidor" + e))
                .switchIfEmpty(response401);
    }

    public Mono<ServerResponse> listarUsuario(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userRepository.findAll(), UserEntityProof.class);
    }

    public Mono<ServerResponse> listarUsuarioPorID(ServerRequest serverRequest) {
        Mono<UserFindByIDDTO> userDto = serverRequest.bodyToMono(UserFindByIDDTO.class);
        return userDto.flatMap(usuarioDto -> {
            String username = usuarioDto.getUsername().toUpperCase();
            System.out.println(username);
            return userRepository.findByIdUser(username).flatMap(user -> ServerResponse.ok().bodyValue(user));
        });

    }

    public Mono<ServerResponse> guardarUsuario(ServerRequest serverRequest) {

        Mono<UserDto> userDto = serverRequest.bodyToMono(UserDto.class);

        return userDto.flatMap(usuarioDto -> {
            if (!isValidEmail(usuarioDto.getEmail().trim())) {
                return ServerResponse.badRequest().bodyValue("El email es inválido");
            }

            Mono<Boolean> existsByEmailMono = existsByEmail(usuarioDto.getEmail().trim());

            return existsByEmailMono.flatMap(exists -> {
                if (exists) {
                    return ServerResponse.badRequest().bodyValue("El correo ya está en uso");
                }

                Mono<Boolean> existsByUserMono = existsByUser(usuarioDto.getUsuarioId().trim());

                return existsByUserMono.flatMap(existsUsername -> {
                    if (existsUsername) {
                        return ServerResponse.badRequest().bodyValue("Este usuario ya esta en uso");
                    }

                    String primerNombre, segundoNombre, primerApellido, segundoApellido;
                    String[] nombres = usuarioDto.getNombres().split(" ");
                    String[] apellidos = usuarioDto.getApellidos().split(" ");

                    primerNombre = nombres[0];
                    segundoNombre = nombres.length > 1 ? nombres[1] : "";
                    segundoNombre = segundoNombre != null ? segundoNombre.toUpperCase() : "";
                    primerApellido = apellidos[0];
                    segundoApellido = apellidos.length > 1 ? nombres[1] : "";
                    segundoApellido = segundoApellido != null ? segundoApellido.toUpperCase() : "";

                    // Guardar el objeto UserEntityProof sin la relación con el rol
                    UserEntityProof user = new UserEntityProof(usuarioDto.getUsuarioId().trim().toUpperCase(),
                            usuarioDto.getLocalidadId(), usuarioDto.getTpDocumentoId(),
                            usuarioDto.getUsuarioIdentificacion(), usuarioDto.getPassword(), usuarioDto.getUsuarioEstado(),
                            primerNombre.toUpperCase(), segundoNombre, primerApellido.toUpperCase(), segundoApellido,
                            usuarioDto.getEmail().trim(), usuarioDto.getPerfilId());

                    Mono<UserEntityProof> savedUserMono = userRepository.save(user);

                    // Guardar la relación entre el usuario y el rol en la tabla intermedia
                    Mono<RolUsuarioEntity> savedRolUsuarioMono = savedUserMono
                            .flatMap(savedUser -> rolRepository.findById(usuarioDto.getRole())
                                    .flatMap(rol -> {
                                        System.out.println(savedUser.toString());
                                        System.out.println(rol.getId());
                                        RolUsuarioEntity rolUsuarioEntity = new RolUsuarioEntity(rol.getId(),
                                                savedUser.getUsuarioId());
                                        return rolUsuarioRepository.save(rolUsuarioEntity);
                                    }));

                    // Combinar los resultados y retornar una respuesta HTTP
                    return savedRolUsuarioMono
                            .flatMap(savedRolUsuario -> ServerResponse.accepted().build())
                            .onErrorResume(Exception.class, (e) -> ServerResponse.status(HttpStatus.BAD_REQUEST)
                                    .bodyValue("Se guardo el usuario, pero hay un error en el hilo" + e));
                });
            });
        });
    }

    public Mono<ServerResponse> logout(ServerRequest serverRequest){
        jwt.setJwt(null);
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

    public Mono<Boolean> existsByEmail(String email) {
        return userRepository.countByEmail(email)
                .map(count -> count > 0)
                .defaultIfEmpty(false);
    }

    public Mono<Boolean> existsByUser(String username) {
        return userRepository.countByUsername(username)
                .map(count -> count > 0)
                .defaultIfEmpty(false);
    }

}
