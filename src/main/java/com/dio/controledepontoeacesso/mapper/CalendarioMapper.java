package com.dio.controledepontoeacesso.mapper;

import com.dio.controledepontoeacesso.dto.CalendarioDTO;
import com.dio.controledepontoeacesso.model.Calendario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface CalendarioMapper {
    CalendarioDTO toCalendarioDTO(Calendario calendario);

    List<CalendarioDTO> toCalendarioDTOs(List<Calendario> calendarios);

    Calendario toCalendario(CalendarioDTO calendarioDTO);
}
