package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.dto.MovimentacaoDTO;
import com.dio.controledepontoeacesso.dto.OcorrenciaDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.response.OcorrenciaResponse;
import com.dio.controledepontoeacesso.service.MovimentacaoService;
import com.dio.controledepontoeacesso.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/occurrences")
public class OcorrenciaController {
    @Autowired
    OcorrenciaService ocorrenciaService;

    @Autowired
    MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<OcorrenciaDTO> createOcorrencia(@Valid @RequestBody OcorrenciaDTO occurrence){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(ocorrenciaService.saveOcorrencia(occurrence));
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, OcorrenciaResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping
    public ResponseEntity<List<OcorrenciaDTO>> getOcorrenciaList(){
        try {
            return ResponseEntity.ok(ocorrenciaService.findAll());
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, OcorrenciaResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping("/{occurrenceId}")
    public ResponseEntity<OcorrenciaDTO> getOcorrenciaByID(@PathVariable("occurrenceId") Long occurrenceId) {
        try {
            return ResponseEntity.ok(ocorrenciaService.getById(occurrenceId));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, OcorrenciaResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @PutMapping("/{occurrenceId}")
    public ResponseEntity<OcorrenciaDTO> updateOcorrencia(@PathVariable("occurrenceId") Long occurrenceId,
                                                          @Valid @RequestBody OcorrenciaDTO occurrence){
        try {
            occurrence.setId(occurrenceId);
            return ResponseEntity.ok(ocorrenciaService.updateOcorrencia(occurrence));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, OcorrenciaResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @DeleteMapping("/{occurrenceId}")
    public ResponseEntity deleteByID(@PathVariable("occurrenceId") Long occurrenceId) {
        try {
            ocorrenciaService.deleteOcorrencia(occurrenceId);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, OcorrenciaResponse.ENTITY_NOT_FOUND, e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, OcorrenciaResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping("/{occurrenceId}/movements")
    public ResponseEntity<List<MovimentacaoDTO>> listOcorrenciaMovimentacoes(@PathVariable("occurrenceId") Long occurrenceId) {
        try {
            return ResponseEntity.ok(movimentacaoService.findByOcorrenciaId(occurrenceId));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, OcorrenciaResponse.INTERNAL_SERVER_ERROR, e);
        }
    }
}
