package com.dio.controledepontoeacesso.mapper;

import com.dio.controledepontoeacesso.dto.UsuarioDTO;
import com.dio.controledepontoeacesso.model.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface UsuarioMapper {
    UsuarioDTO toUsuarioDTO(Usuario user);

    List<UsuarioDTO> toUsuarioDTOs(List<Usuario> users);

    Usuario toUsuario(UsuarioDTO userDTO);
}
