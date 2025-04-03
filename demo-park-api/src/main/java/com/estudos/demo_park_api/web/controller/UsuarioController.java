package com.estudos.demo_park_api.web.controller;

import com.estudos.demo_park_api.model.entity.Usuario;
import com.estudos.demo_park_api.model.service.UsuarioService;
import com.estudos.demo_park_api.web.dto.UsuarioCreateDto;
import com.estudos.demo_park_api.web.dto.UsuarioSenhaDto;
import com.estudos.demo_park_api.web.dto.mapper.UsuarioMapper;
import com.estudos.demo_park_api.web.dto.UsuarioResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/createUser")
    public ResponseEntity<UsuarioResponseDto> createUser(@Valid @RequestBody UsuarioCreateDto createDto) {
        Usuario novoUsuario = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(novoUsuario));
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
        Usuario userId = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(userId));
    }
    @PatchMapping("{id}") // O @PatchMapping é utilizado para atualizações parciais.
    // Como queremos atualizar apenas a senha, essa abordagem é apropriada.
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UsuarioSenhaDto dto) {
        usuarioService.editarSenha(id, dto.getSenhaAtual(),dto.getNovaSenha(),dto.getConfirmaSenha());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping()
    public ResponseEntity<Page<UsuarioResponseDto>> getAllUser(Pageable usuario) {
        Page<Usuario> userId = usuarioService.buscarTodosUsuarios(usuario);
        return ResponseEntity.ok(UsuarioMapper.toPageDto(userId));
    }

}
