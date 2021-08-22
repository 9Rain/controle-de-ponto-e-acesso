package com.dio.controledepontoeacesso.mapper;

import com.dio.controledepontoeacesso.dto.EmpresaDTO;
import com.dio.controledepontoeacesso.model.Empresa;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface EmpresaMapper {
    EmpresaDTO toEmpresaDTO(Empresa company);

    List<EmpresaDTO> toEmpresaDTOs(List<Empresa> companies);

    Empresa toEmpresa(EmpresaDTO companyDTO);
}
