package com.dio.controledepontoeacesso.mapper;

import com.dio.controledepontoeacesso.dto.LocalidadeDTO;
import com.dio.controledepontoeacesso.model.Localidade;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface LocalidadeMapper {
    LocalidadeDTO toLocalidadeDTO(Localidade local);

    List<LocalidadeDTO> toLocalidadeDTOs(List<Localidade> locals);

    Localidade toLocalidade(LocalidadeDTO localDTO);
}
