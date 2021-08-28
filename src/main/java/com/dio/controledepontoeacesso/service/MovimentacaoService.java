package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.MovimentacaoDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.mapper.MovimentacaoMapper;
import com.dio.controledepontoeacesso.repository.BancoHorasRepository;
import com.dio.controledepontoeacesso.repository.CalendarioRepository;
import com.dio.controledepontoeacesso.repository.MovimentacaoRepository;
import com.dio.controledepontoeacesso.repository.OcorrenciaRepository;
import com.dio.controledepontoeacesso.response.MovimentacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoService {
    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    CalendarioRepository calendarioRepository;

    @Autowired
    OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    BancoHorasRepository bancoHorasRepository;

    @Autowired
    MovimentacaoMapper movimentacaoMapper;

    public MovimentacaoDTO saveMovimentacao(MovimentacaoDTO movimentacao) throws NotFoundException {
        return calendarioRepository.findById(movimentacao.getCalendario().getId())
            .map((calendario) -> {
                movimentacao.setCalendario(calendario);

                return ocorrenciaRepository.findById(movimentacao.getOcorrencia().getId())
                    .map((ocorrencia) -> {
                        movimentacao.setOcorrencia(ocorrencia);

                        return movimentacaoMapper.toMovimentacaoDTO(
                                movimentacaoRepository.save(
                                        movimentacaoMapper.toMovimentacao(movimentacao)
                                )
                        );
                    })
                    .orElseThrow(() -> new NotFoundException(MovimentacaoResponse.OCORRENCIA_NOT_FOUND));
            })
            .orElseThrow(() -> new NotFoundException(MovimentacaoResponse.CALENDARIO_NOT_FOUND));
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
        return movimentacaoRepository.findById(movimentacao.getId())
            .map((movimentacaoToBeUpdated) ->
                calendarioRepository.findById(movimentacao.getCalendario().getId())
                    .map((calendario) -> {
                        movimentacao.setCalendario(calendario);

                        return ocorrenciaRepository.findById(movimentacao.getOcorrencia().getId())
                            .map((ocorrencia) -> {
                                movimentacao.setOcorrencia(ocorrencia);

                                return movimentacaoMapper.toMovimentacaoDTO(
                                    movimentacaoRepository.save(
                                        movimentacaoMapper.toMovimentacao(movimentacao)
                                    )
                                );
                            })
                            .orElseThrow(() -> new NotFoundException(MovimentacaoResponse.OCORRENCIA_NOT_FOUND));
                    })
                    .orElseThrow(() -> new NotFoundException(MovimentacaoResponse.CALENDARIO_NOT_FOUND))
            )
            .orElseThrow(() -> new NotFoundException(MovimentacaoResponse.ENTITY_NOT_FOUND));
    }

    public void deleteMovimentacao(Long idMovimentacao) {
        bancoHorasRepository.deleteByMovimentacaoId(idMovimentacao);
        movimentacaoRepository.deleteById(idMovimentacao);
    }

    public List<MovimentacaoDTO> findByCalendarioId(Long calendarId) throws NotFoundException {
        return calendarioRepository.findById(calendarId)
            .map((nivelAcesso) -> movimentacaoMapper.toMovimentacaoDTOs(movimentacaoRepository.findByCalendarioId(calendarId)))
            .orElseThrow(() -> new NotFoundException(MovimentacaoResponse.CALENDARIO_NOT_FOUND));
    }

    public List<MovimentacaoDTO> findByOcorrenciaId(Long occurenceId) throws NotFoundException {
        return ocorrenciaRepository.findById(occurenceId)
            .map((nivelAcesso) -> movimentacaoMapper.toMovimentacaoDTOs(movimentacaoRepository.findByOcorrenciaId(occurenceId)))
            .orElseThrow(() -> new NotFoundException(MovimentacaoResponse.OCORRENCIA_NOT_FOUND));
    }
}
