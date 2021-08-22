package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.dto.MovimentacaoDTO;
import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.exception.RelationshipNotFoundException;
import com.dio.controledepontoeacesso.model.Movimentacao;
import com.dio.controledepontoeacesso.response.MovimentacaoResponse;
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

    @PostMapping
    public ResponseEntity<MovimentacaoDTO> createMovimentacao(@Valid @RequestBody MovimentacaoDTO movement){
        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacaoService.saveMovimentacao(movement));
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoDTO>> getMovimentacaoList(){
        return ResponseEntity.ok(movimentacaoService.findAll());
    }

    @GetMapping("/{movementId}")
    public ResponseEntity<MovimentacaoDTO> getMovimentacaoByID(@PathVariable("movementId") Long movementId) {
        try {
            return ResponseEntity.ok(movimentacaoService.getById(movementId));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, MovimentacaoResponse.ENTITY_NOT_FOUND, e);
        }
    }

    @PutMapping("/{movementId}")
    public ResponseEntity<MovimentacaoDTO> updateMovimentacao(@PathVariable("movementId") Long movementId,
                                                          @Valid @RequestBody MovimentacaoDTO movement){
        try {
            movement.setId(movementId);
            return ResponseEntity.ok(movimentacaoService.updateMovimentacao(movement));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, MovimentacaoResponse.ENTITY_NOT_FOUND, e);
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
}
