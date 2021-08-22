package com.dio.controledepontoeacesso.mapper;

import com.dio.controledepontoeacesso.dto.JornadaTrabalhoDTO;
import com.dio.controledepontoeacesso.model.JornadaTrabalho;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel="spring")
public interface JornadaTrabalhoMapper {
    JornadaTrabalhoDTO toJornadaTrabalhoDTO(JornadaTrabalho product);

    List<JornadaTrabalhoDTO> toJornadaTrabalhoDTOs(List<JornadaTrabalho> products);

    JornadaTrabalho toJornadaTrabalho(JornadaTrabalhoDTO productDTO);
}