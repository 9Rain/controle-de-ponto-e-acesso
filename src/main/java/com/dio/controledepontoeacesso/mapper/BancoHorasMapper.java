package com.dio.controledepontoeacesso.mapper;

import com.dio.controledepontoeacesso.dto.BancoHorasDTO;
import com.dio.controledepontoeacesso.model.BancoHoras;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface BancoHorasMapper {
    BancoHorasDTO toBancoHorasDTO(BancoHoras bancoHoras);

    List<BancoHorasDTO> toBancoHorasDTOs(List<BancoHoras> bancoHoras);

    BancoHoras toBancoHoras(BancoHorasDTO bancoHorasDTO);
}
