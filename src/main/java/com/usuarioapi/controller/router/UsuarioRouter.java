package com.usuarioapi.controller.router;

import com.usuarioapi.controller.handler.UsuarioHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class UsuarioRouter {

    @Bean
    public RouterFunction<ServerResponse> router(UsuarioHandler handler) {

        return route()
                .path("/v1/usuarios", b1 -> b1
                        .nest(accept(APPLICATION_JSON), b2 -> b2
                                .GET("/{id}", handler::buscarPorId)
                                .GET("", handler::buscarTodos)
                                .POST("", handler::cadastrarUsuario)))
                .build();

//        return RouterFunctions.route(GET("/v1/usuarios"), handler::buscarTodos)
//                .andRoute(GET("/v1/usuarios/{id}"), handler::buscarPorId)
//                .andRoute(POST("/v1/usuarios"), handler::cadastrarUsuario);
    }

}
