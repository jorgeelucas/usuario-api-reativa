package com.usuarioapi.service;

import com.usuarioapi.documento.Usuario;
import com.usuarioapi.repositorio.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Mono<Usuario> criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Flux<Usuario> buscarTodos() {
        final var fluxDeUsuariosEncontrados = usuarioRepository.findAll();
        return fluxDeUsuariosEncontrados;
    }

    public Mono<Usuario> buscarUsuarioPorId(String id) {
        return usuarioRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado!")));
    }

    public Mono<Void> deletarUsuarioPorId(String id) {
        return usuarioRepository.deleteById(id);
    }



}
