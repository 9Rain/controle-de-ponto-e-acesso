package com.dio.controledepontoeacesso.mapper;

import com.dio.controledepontoeacesso.dto.MovimentacaoDTO;
import com.dio.controledepontoeacesso.model.Movimentacao;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface MovimentacaoMapper {
    MovimentacaoDTO toMovimentacaoDTO(Movimentacao movement);

    List<MovimentacaoDTO> toMovimentacaoDTOs(List<Movimentacao> movements);

    Movimentacao toMovimentacao(MovimentacaoDTO movementDTO);
}
