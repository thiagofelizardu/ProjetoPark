package com.estudos.demo_park_api.web.dto.mapper;

import com.estudos.demo_park_api.model.entity.Usuario;
import com.estudos.demo_park_api.web.dto.UsuarioCreateDto;
import com.estudos.demo_park_api.web.dto.UsuarioResponseDto;
import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.stream.Collectors;

public class UsuarioMapper {
    public static Usuario toUsuario(UsuarioCreateDto createDto) {
        return new ModelMapper().map(createDto, Usuario.class);
    }

    public static UsuarioResponseDto toDto(Usuario usuario) {
        String role = usuario.getRole().name().substring("ROLE_".length());
        PropertyMap<Usuario, UsuarioResponseDto> props = new PropertyMap<Usuario, UsuarioResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(props);
        return modelMapper.map(usuario, UsuarioResponseDto.class);
    }

    public static Page<UsuarioResponseDto> toPageDto(Page<Usuario> usuarios) {
        return usuarios.map(UsuarioMapper::toDto);
    }


}
