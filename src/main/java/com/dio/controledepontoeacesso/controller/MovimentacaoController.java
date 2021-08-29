package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.dto.BancoHorasDTO;
import com.dio.controledepontoeacesso.dto.LocalidadeDTO;
import com.dio.controledepontoeacesso.dto.MovimentacaoDTO;
import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.exception.RelationshipNotFoundException;
import com.dio.controledepontoeacesso.model.Movimentacao;
import com.dio.controledepontoeacesso.response.LocalidadeResponse;
import com.dio.controledepontoeacesso.response.MovimentacaoResponse;
import com.dio.controledepontoeacesso.response.NivelAcessoResponse;
import com.dio.controledepontoeacesso.service.BancoHorasService;
import com.dio.controledepontoeacesso.service.MovimentacaoService;
import com.dio.controledepontoeacesso.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movements")
public class MovimentacaoController {
    @Autowired
    MovimentacaoService movimentacaoService;

    @Autowired
    BancoHorasService bancoHorasService;

    @PostMapping
    public ResponseEntity<MovimentacaoDTO> createMovimentacao(@Valid @RequestBody MovimentacaoDTO movement){
        var calendarioId = Optional.ofNullable(movement.getCalendario().getId());

        if(calendarioId.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, MovimentacaoResponse.CALENDARIO_IS_REQUIRED, new NullPointerException());

        var ocorrenciaId = Optional.ofNullable(movement.getOcorrencia().getId());

        if(ocorrenciaId.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, MovimentacaoResponse.OCORRENCIA_IS_REQUIRED, new NullPointerException());

        var usuarioId = Optional.ofNullable(movement.getUsuario().getId());

        if(usuarioId.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, MovimentacaoResponse.USUARIO_IS_REQUIRED, new NullPointerException());

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(movimentacaoService.saveMovimentacao(movement));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, MovimentacaoResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoDTO>> getMovimentacaoList(){
        try {
            return ResponseEntity.ok(movimentacaoService.findAll());
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, MovimentacaoResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping("/{movementId}")
    public ResponseEntity<MovimentacaoDTO> getMovimentacaoByID(@PathVariable("movementId") Long movementId) {
        try {
            return ResponseEntity.ok(movimentacaoService.getById(movementId));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, MovimentacaoResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @PutMapping("/{movementId}")
    public ResponseEntity<MovimentacaoDTO> updateMovimentacao(@PathVariable("movementId") Long movementId,
                                                          @Valid @RequestBody MovimentacaoDTO movement){
        var calendarioId = Optional.ofNullable(movement.getCalendario().getId());

        if(calendarioId.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, MovimentacaoResponse.CALENDARIO_IS_REQUIRED, new NullPointerException());

        var ocorrenciaId = Optional.ofNullable(movement.getOcorrencia().getId());

        if(ocorrenciaId.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, MovimentacaoResponse.OCORRENCIA_IS_REQUIRED, new NullPointerException());

        var usuarioId = Optional.ofNullable(movement.getUsuario().getId());

        if(usuarioId.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, MovimentacaoResponse.USUARIO_IS_REQUIRED, new NullPointerException());

        try {
            movement.setId(movementId);
            return ResponseEntity.ok(movimentacaoService.updateMovimentacao(movement));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, MovimentacaoResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @DeleteMapping("/{movementId}")
    public ResponseEntity deleteMovimentacaoByID(@PathVariable("movementId") Long movementId) {
        try {
            movimentacaoService.deleteMovimentacao(movementId);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, MovimentacaoResponse.ENTITY_NOT_FOUND, e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, MovimentacaoResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping("/{movimentacaoId}/additional-hours")
    public ResponseEntity<List<BancoHorasDTO>> listMovimentacaoBancoHoras(@PathVariable("movimentacaoId") Long movimentacaoId) {
        try {
            return ResponseEntity.ok(bancoHorasService.findByMovimentacaoId(movimentacaoId));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, MovimentacaoResponse.INTERNAL_SERVER_ERROR, e);
        }
    }
}
