package com.dio.controledepontoeacesso.mapper;

import com.dio.controledepontoeacesso.dto.OcorrenciaDTO;
import com.dio.controledepontoeacesso.model.Ocorrencia;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface OcorrenciaMapper {
    OcorrenciaDTO toOcorrenciaDTO(Ocorrencia occurence);

    List<OcorrenciaDTO> toOcorrenciaDTOs(List<Ocorrencia> occurences);

    Ocorrencia toOcorrencia(OcorrenciaDTO occurenceDTO);
}
