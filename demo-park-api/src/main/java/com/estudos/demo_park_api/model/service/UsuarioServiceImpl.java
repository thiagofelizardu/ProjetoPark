package com.estudos.demo_park_api.model.service;

import com.estudos.demo_park_api.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioServiceImpl {

    Usuario salvar(Usuario usuario);

    Usuario buscarPorId(Long id);

    Usuario editarSenha(Long id, String password, String novaSenha, String confirmaSenha);

    Page<Usuario> buscarTodosUsuarios(Pageable pageable);
}
