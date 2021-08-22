package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.dto.JornadaTrabalhoDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.response.JornadaTrabalhoResponse;
import com.dio.controledepontoeacesso.service.JornadaTrabalhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/working-days")
public class JornadaTrabalhoController {
    @Autowired
    JornadaTrabalhoService jornadaTrabalhoService;

    @PostMapping
    public ResponseEntity<JornadaTrabalhoDTO> createJornada(@Valid @RequestBody JornadaTrabalhoDTO jornadaTrabalho){
        return ResponseEntity.status(HttpStatus.CREATED).body(jornadaTrabalhoService.saveJornada(jornadaTrabalho));
    }

    @GetMapping
    public ResponseEntity<List<JornadaTrabalhoDTO>> getJornadaList(){
        return ResponseEntity.ok(jornadaTrabalhoService.findAll());
    }

    @GetMapping("/{idJornada}")
    public ResponseEntity<JornadaTrabalhoDTO> getJornadaByID(@PathVariable("idJornada") Long idJornada) {
        try {
            return ResponseEntity.ok(jornadaTrabalhoService.getById(idJornada));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, JornadaTrabalhoResponse.ENTITY_NOT_FOUND, e);
        }
    }

    @PutMapping("/{idJornada}")
    public ResponseEntity<JornadaTrabalhoDTO> updateJornada(@PathVariable("idJornada") Long idJornada,
                                                         @Valid @RequestBody JornadaTrabalhoDTO jornadaTrabalho){
        try {
            jornadaTrabalho.setId(idJornada);
            return ResponseEntity.ok(jornadaTrabalhoService.updateJornada(jornadaTrabalho));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, JornadaTrabalhoResponse.ENTITY_NOT_FOUND, e);
        }
    }

    @DeleteMapping("/{idJornada}")
    public ResponseEntity deleteByID(@PathVariable("idJornada") Long idJornada) {
        try {
            jornadaTrabalhoService.deleteJornada(idJornada);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, JornadaTrabalhoResponse.ENTITY_NOT_FOUND, e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, JornadaTrabalhoResponse.INTERNAL_SERVER_ERROR, e);
        }
    }
}
