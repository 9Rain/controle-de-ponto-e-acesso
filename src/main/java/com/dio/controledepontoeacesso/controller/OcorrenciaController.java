package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.model.Ocorrencia;
import com.dio.controledepontoeacesso.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/occurrences")
public class OcorrenciaController {
    @Autowired
    OcorrenciaService ocorrenciaService;

    @PostMapping
    public Ocorrencia createOcorrencia(@Valid @RequestBody Ocorrencia occurrence){
        return ocorrenciaService.saveOcorrencia(occurrence);
    }

    @GetMapping
    public List<Ocorrencia> getOcorrenciaList(){
        return ocorrenciaService.findAll();
    }

    @GetMapping("/{occurrenceId}")
    public ResponseEntity<Ocorrencia> getOcorrenciaByID(@PathVariable("occurrenceId") Long occurrenceId) {
        return ocorrenciaService.getById(occurrenceId)
                .map(occurrence -> ResponseEntity.ok().body(occurrence))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Ocorrencia> updateDateType(@Valid @RequestBody Ocorrencia occurrence){
        var res = Optional.of(occurrence.getId());

        if(res.isEmpty() || res.get() <= 0) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok().body(ocorrenciaService.updateOcorrencia(occurrence));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{occurrenceId}")
    public ResponseEntity deleteByID(@PathVariable("occurrenceId") Long occurrenceId) {
        if(occurrenceId <= 0) return ResponseEntity.badRequest().build();

        try {
            ocorrenciaService.deleteOcorrencia(occurrenceId);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
