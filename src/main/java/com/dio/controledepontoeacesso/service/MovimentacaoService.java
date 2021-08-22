package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.MovimentacaoDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.mapper.MovimentacaoMapper;
import com.dio.controledepontoeacesso.repository.MovimentacaoRepository;
import com.dio.controledepontoeacesso.response.MovimentacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoService {
    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    MovimentacaoMapper movimentacaoMapper;

    public MovimentacaoDTO saveMovimentacao(MovimentacaoDTO movimentacao){
        return movimentacaoMapper.toMovimentacaoDTO(
                movimentacaoRepository.save(
                        movimentacaoMapper.toMovimentacao(movimentacao)
                )
        );
    }

    public List<MovimentacaoDTO> findAll() {
        return movimentacaoMapper.toMovimentacaoDTOs(movimentacaoRepository.findAll());
    }

    public MovimentacaoDTO getById(Long idMovimentacao) throws NotFoundException {
        return movimentacaoRepository.findById(idMovimentacao)
                .map(movimentacaoMapper::toMovimentacaoDTO)
                .orElseThrow(() -> new NotFoundException(MovimentacaoResponse.ENTITY_NOT_FOUND));
    }

    public MovimentacaoDTO updateMovimentacao(MovimentacaoDTO movimentacao) throws NotFoundException {
        var movimentacaoToBeUpdated = movimentacaoRepository.findById(movimentacao.getMovimentacaoId().getIdMovimento());

        if(movimentacaoToBeUpdated.isEmpty()) {
            throw new NotFoundException(MovimentacaoResponse.ENTITY_NOT_FOUND);
        }

        return movimentacaoMapper.toMovimentacaoDTO(
                movimentacaoRepository.save(
                        movimentacaoMapper.toMovimentacao(movimentacao)
                )
        );
    }

    public void deleteMovimentacao(Long idMovimentacao) {
        movimentacaoRepository.deleteById(idMovimentacao);
    }
}
