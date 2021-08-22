package com.dio.controledepontoeacesso.mapper;

import com.dio.controledepontoeacesso.dto.NivelAcessoDTO;
import com.dio.controledepontoeacesso.model.NivelAcesso;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface NivelAcessoMapper {
    NivelAcessoDTO toNivelAcessoDTO(NivelAcesso accessLevel);

    List<NivelAcessoDTO> toNivelAcessoDTOs(List<NivelAcesso> accessLevels);

    NivelAcesso toNivelAcesso(NivelAcessoDTO accessLevelDTO);
}
