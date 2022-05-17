package com.usuarioapi.controller.handler;

import com.usuarioapi.documento.Usuario;
import com.usuarioapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UsuarioHandler {

    private final UsuarioService service;

    public Mono<ServerResponse> buscarTodos(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.buscarTodos(), Usuario.class);
    }

    public Mono<ServerResponse> buscarPorId(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.buscarUsuarioPorId(id), Usuario.class);
    }

    public Mono<ServerResponse> cadastrarUsuario(ServerRequest request) {
        final Mono<Usuario> usuarioMono = request.bodyToMono(Usuario.class);
        return ServerResponse
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(usuarioMono.flatMap(service::criarUsuario), Usuario.class));
    }

}
