package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.dto.NivelAcessoDTO;
import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.model.NivelAcesso;
import com.dio.controledepontoeacesso.response.NivelAcessoResponse;
import com.dio.controledepontoeacesso.response.OcorrenciaResponse;
import com.dio.controledepontoeacesso.service.NivelAcessoService;
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
@RequestMapping("/access-levels")
public class NivelAcessoController {
    @Autowired
    NivelAcessoService nivelAcessoService;

    @PostMapping
    public ResponseEntity<NivelAcessoDTO> createNivelAcesso(@Valid @RequestBody NivelAcessoDTO accessLevel){
        return ResponseEntity.status(HttpStatus.CREATED).body(nivelAcessoService.saveNivelAcesso(accessLevel));
    }

    @GetMapping
    public ResponseEntity<List<NivelAcessoDTO>> getNivelAcessoList(){
        return ResponseEntity.ok(nivelAcessoService.findAll());
    }

    @GetMapping("/{accessLevelId}")
    public ResponseEntity<NivelAcessoDTO> getNivelAcessoByID(@PathVariable("accessLevelId") Long accessLevelId) {
        try {
            return ResponseEntity.ok(nivelAcessoService.getById(accessLevelId));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, NivelAcessoResponse.ENTITY_NOT_FOUND, e);
        }
    }

    @PutMapping("/{accessLevelId}")
    public ResponseEntity<NivelAcessoDTO> updateNivelAcesso(@PathVariable("accessLevelId") Long accessLevelId,
                                                      @Valid @RequestBody NivelAcessoDTO accessLevel){
        try {
            accessLevel.setId(accessLevelId);
            return ResponseEntity.ok(nivelAcessoService.updateNivelAcesso(accessLevel));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, NivelAcessoResponse.ENTITY_NOT_FOUND, e);
        }
    }

    @DeleteMapping("/{accessLevelId}")
    public ResponseEntity deleteByID(@PathVariable("accessLevelId") Long accessLevelId) {
        if(accessLevelId <= 0) return ResponseEntity.badRequest().build();

        try {
            nivelAcessoService.deleteNivelAcesso(accessLevelId);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, NivelAcessoResponse.ENTITY_NOT_FOUND, e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, NivelAcessoResponse.INTERNAL_SERVER_ERROR, e);
        }
    }
}
