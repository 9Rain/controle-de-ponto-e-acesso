package com.dio.controledepontoeacesso.mapper;

import com.dio.controledepontoeacesso.dto.CategoriaUsuarioDTO;
import com.dio.controledepontoeacesso.model.CategoriaUsuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface CategoriaUsuarioMapper {
    CategoriaUsuarioDTO toCategoriaUsuarioDTO(CategoriaUsuario userCategory);

    List<CategoriaUsuarioDTO> toCategoriaUsuarioDTOs(List<CategoriaUsuario> userCategorys);

    CategoriaUsuario toCategoriaUsuario(CategoriaUsuarioDTO userCategoryDTO);
}
