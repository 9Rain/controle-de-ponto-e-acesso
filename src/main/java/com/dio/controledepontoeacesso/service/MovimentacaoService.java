package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.RelationshipNotFoundException;
import com.dio.controledepontoeacesso.model.Movimentacao;
import com.dio.controledepontoeacesso.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {
    MovimentacaoRepository movimentacaoRepository;
    UsuarioService usuarioService;
    CalendarioService calendarioService;
    OcorrenciaService ocorrenciaService;

    @Autowired
    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, UsuarioService usuarioService,
                               CalendarioService calendarioService, OcorrenciaService ocorrenciaService) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.usuarioService = usuarioService;
        this.calendarioService = calendarioService;
        this.ocorrenciaService = ocorrenciaService;
    }

    public Movimentacao saveMovimentacao(Movimentacao movimentacao) throws RelationshipNotFoundException {
        var relatedUsuario = usuarioService.getById(movimentacao.getMovimentacaoId().getIdUsuario());

        if(relatedUsuario.isEmpty()) {
            throw new RelationshipNotFoundException();
        }

        var relatedOcorrenciaId = Optional.ofNullable(movimentacao.getOcorrenciaId());

        if(relatedOcorrenciaId.isPresent()) {
            var relatedOcorrencia = ocorrenciaService.getById(relatedOcorrenciaId.get());

            if(relatedOcorrencia.isEmpty()) {
                throw new RelationshipNotFoundException();
            }

            movimentacao.setOcorrencia(relatedOcorrencia.get());
        }

        var relatedCalendarioId = Optional.ofNullable(movimentacao.getCalendarioId());

        if(relatedCalendarioId.isPresent()) {
            var relatedCalendario = calendarioService.getById(relatedCalendarioId.get());

            if(relatedCalendario.isEmpty()) {
                throw new RelationshipNotFoundException();
            }

            movimentacao.setCalendario(relatedCalendario.get());
        }

        return movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao> findAll() {
        return movimentacaoRepository.findAll();
    }

    public Optional<Movimentacao> getById(Long idMovimentacao) {
        return movimentacaoRepository.findById(idMovimentacao);
    }

    public Movimentacao updateMovimentacao(Movimentacao movimentacao) throws NoSuchElementException, RelationshipNotFoundException {
        var movimentacaoToBeUpdated = this.getById(movimentacao.getMovimentacaoId().getIdMovimento());

        if(movimentacaoToBeUpdated.isEmpty()) {
            throw new NoSuchElementException();
        }

        var relatedUsuario = usuarioService.getById(movimentacao.getMovimentacaoId().getIdUsuario());

        if(relatedUsuario.isEmpty()) {
            throw new RelationshipNotFoundException();
        }

        var relatedOcorrenciaId = Optional.ofNullable(movimentacao.getOcorrenciaId());

        if(relatedOcorrenciaId.isPresent()) {
            var relatedOcorrencia = ocorrenciaService.getById(relatedOcorrenciaId.get());

            if(relatedOcorrencia.isEmpty()) {
                throw new RelationshipNotFoundException();
            }

            movimentacao.setOcorrencia(relatedOcorrencia.get());
        }
        else movimentacao.setOcorrencia(null);

        var relatedCalendarioId = Optional.ofNullable(movimentacao.getCalendarioId());

        if(relatedCalendarioId.isPresent()) {
            var relatedCalendario = calendarioService.getById(relatedCalendarioId.get());

            if(relatedCalendario.isEmpty()) {
                throw new RelationshipNotFoundException();
            }

            movimentacao.setCalendario(relatedCalendario.get());
        }
        else movimentacao.setCalendario(null);

        return movimentacaoRepository.save(movimentacao);
    }

    public void deleteMovimentacao(Long idMovimentacao) {
        movimentacaoRepository.deleteById(idMovimentacao);
    }
}
