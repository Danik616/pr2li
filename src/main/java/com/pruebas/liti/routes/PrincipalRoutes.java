package com.pruebas.liti.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class PrincipalRoutes {
    
    @Bean
    public RouterFunction<ServerResponse> routerInit(PrincipalHandler principalHandler){
        return RouterFunctions.route(GET("/listar"), principalHandler::listarUsuario)
            .andRoute(POST("/guardar"), principalHandler::guardarUsuario)
            .andRoute(POST("/login"), principalHandler::iniciarSesión);
    }
}
