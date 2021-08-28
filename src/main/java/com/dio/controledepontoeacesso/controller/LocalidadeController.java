package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.dto.LocalidadeDTO;
import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.exception.RelationshipNotFoundException;
import com.dio.controledepontoeacesso.model.Localidade;
import com.dio.controledepontoeacesso.response.CalendarioResponse;
import com.dio.controledepontoeacesso.response.LocalidadeResponse;
import com.dio.controledepontoeacesso.response.TipoDataResponse;
import com.dio.controledepontoeacesso.service.LocalidadeService;
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
@RequestMapping("/locals")
public class LocalidadeController {
    @Autowired
    LocalidadeService localidadeService;

    @PostMapping
    public ResponseEntity<LocalidadeDTO> createLocalidade(@Valid @RequestBody LocalidadeDTO localidade){
        var nivelAcessoId = Optional.ofNullable(localidade.getNivelAcesso().getId());

        if(nivelAcessoId.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, LocalidadeResponse.NIVEL_ACESSO_IS_REQUIRED, new NullPointerException());

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(localidadeService.saveLocalidade(localidade));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, LocalidadeResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping
    public ResponseEntity<List<LocalidadeDTO>> getLocalidadeList(){
        try {
            return ResponseEntity.ok(localidadeService.findAll());
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, LocalidadeResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping("/{idLocalidade}")
    public ResponseEntity<LocalidadeDTO> getLocalidadeByID(@PathVariable("idLocalidade") Long idLocalidade) {
        try {
            return ResponseEntity.ok(localidadeService.getById(idLocalidade));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, LocalidadeResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @PutMapping("/{idLocalidade}")
    public ResponseEntity<LocalidadeDTO> updateLocalidade(@PathVariable("idLocalidade") Long idLocalidade,
                                                       @Valid @RequestBody LocalidadeDTO localidade){
        var nivelAcessoId = Optional.ofNullable(localidade.getNivelAcesso().getId());

        if(nivelAcessoId.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, LocalidadeResponse.NIVEL_ACESSO_IS_REQUIRED, new NullPointerException());

        try {
            localidade.setId(idLocalidade);
            return ResponseEntity.ok(localidadeService.updateLocalidade(localidade));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, LocalidadeResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @DeleteMapping("/{idLocalidade}")
    public ResponseEntity deleteByID(@PathVariable("idLocalidade") Long idLocalidade) {
        try {
            localidadeService.deleteLocalidade(idLocalidade);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, LocalidadeResponse.ENTITY_NOT_FOUND, e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, LocalidadeResponse.INTERNAL_SERVER_ERROR, e);
        }
    }
}
