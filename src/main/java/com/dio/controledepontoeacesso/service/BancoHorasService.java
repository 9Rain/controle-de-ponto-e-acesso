package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.BancoHorasDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.mapper.BancoHorasMapper;
import com.dio.controledepontoeacesso.repository.BancoHorasRepository;
import com.dio.controledepontoeacesso.repository.MovimentacaoRepository;
import com.dio.controledepontoeacesso.response.BancoHorasResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoHorasService {
    @Autowired
    BancoHorasRepository bancoHorasRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    BancoHorasMapper bancoHorasMapper;

    public BancoHorasDTO saveBancoHoras(BancoHorasDTO bancoHoras) throws NotFoundException {
        return movimentacaoRepository.findById(bancoHoras.getMovimentacao().getId())
            .map((movimentacao) -> {
                bancoHoras.setMovimentacao(movimentacao);

                return bancoHorasMapper.toBancoHorasDTO(
                        bancoHorasRepository.save(
                                bancoHorasMapper.toBancoHoras(bancoHoras)
                        )
                );
            })
            .orElseThrow(() -> new NotFoundException(BancoHorasResponse.MOVIMENTACAO_NOT_FOUND));
    }

    public List<BancoHorasDTO> findAll() {
        return bancoHorasMapper.toBancoHorasDTOs(bancoHorasRepository.findAll());
    }

    public BancoHorasDTO getById(Long idBancoHoras) throws NotFoundException {
        return bancoHorasRepository.findById(idBancoHoras)
                .map(bancoHorasMapper::toBancoHorasDTO)
                .orElseThrow(() -> new NotFoundException(BancoHorasResponse.ENTITY_NOT_FOUND));
    }

    public BancoHorasDTO updateBancoHoras(BancoHorasDTO bancoHoras) throws NotFoundException {
        return bancoHorasRepository.findById(bancoHoras.getId())
            .map((bancoHorasToBeUpdated) ->
                movimentacaoRepository.findById(bancoHoras.getMovimentacao().getId())
                    .map((movimentacao) -> {
                        bancoHoras.setMovimentacao(movimentacao);

                        return bancoHorasMapper.toBancoHorasDTO(
                            bancoHorasRepository.save(
                                bancoHorasMapper.toBancoHoras(bancoHoras)
                            )
                        );
                    })
                    .orElseThrow(() -> new NotFoundException(BancoHorasResponse.MOVIMENTACAO_NOT_FOUND))
            )
            .orElseThrow(() -> new NotFoundException(BancoHorasResponse.ENTITY_NOT_FOUND));
    }

    public void deleteBancoHoras(Long idBancoHoras) {
        bancoHorasRepository.deleteById(idBancoHoras);
    }

    public List<BancoHorasDTO> findByMovimentacaoId(Long movimentacaoId) throws NotFoundException {
        return movimentacaoRepository.findById(movimentacaoId)
            .map((movimentacao) -> bancoHorasMapper.toBancoHorasDTOs(bancoHorasRepository.findByMovimentacaoId(movimentacaoId)))
            .orElseThrow(() -> new NotFoundException(BancoHorasResponse.MOVIMENTACAO_NOT_FOUND));
    }
}
