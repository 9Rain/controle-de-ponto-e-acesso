package com.dio.controledepontoeacesso.mapper;

import com.dio.controledepontoeacesso.dto.TipoDataDTO;
import com.dio.controledepontoeacesso.model.TipoData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface TipoDataMapper {
    TipoDataDTO toTipoDataDTO(TipoData dateType);

    List<TipoDataDTO> toTipoDataDTOs(List<TipoData> dateTypes);

    TipoData toTipoData(TipoDataDTO dateTypeDTO);
}
