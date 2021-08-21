package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.model.JornadaTrabalho;
import com.dio.controledepontoeacesso.service.JornadaTrabalhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/working-days")
public class JornadaTrabalhoController {
    @Autowired
    JornadaTrabalhoService jornadaTrabalhoService;

    @PostMapping
    public JornadaTrabalho createJornada(@Valid @RequestBody JornadaTrabalho jornadaTrabalho){
        return jornadaTrabalhoService.saveJornada(jornadaTrabalho);
    }

    @GetMapping
    public List<JornadaTrabalho> getJornadaList(){
        return jornadaTrabalhoService.findAll();
    }

    @GetMapping("/{idJornada}")
    public ResponseEntity<JornadaTrabalho> getJornadaByID(@PathVariable("idJornada") Long idJornada) {
        return jornadaTrabalhoService.getById(idJornada)
                .map(jornada -> ResponseEntity.ok().body(jornada))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<JornadaTrabalho> updateJornada(@Valid @RequestBody JornadaTrabalho jornadaTrabalho){
        var res = Optional.of(jornadaTrabalho.getId());

        if(res.isEmpty() || res.get() <= 0) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok().body(jornadaTrabalhoService.updateJornada(jornadaTrabalho));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idJornada}")
    public ResponseEntity deleteByID(@PathVariable("idJornada") Long idJornada) {
        if(idJornada <= 0) return ResponseEntity.badRequest().build();

        try {
            jornadaTrabalhoService.deleteJornada(idJornada);
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
