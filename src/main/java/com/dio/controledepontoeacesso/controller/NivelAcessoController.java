package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.model.NivelAcesso;
import com.dio.controledepontoeacesso.service.NivelAcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/access-levels")
public class NivelAcessoController {
    @Autowired
    NivelAcessoService nivelAcessoService;

    @PostMapping
    public NivelAcesso createNivelAcesso(@Valid @RequestBody NivelAcesso accessLevel){
        return nivelAcessoService.saveNivelAcesso(accessLevel);
    }

    @GetMapping
    public List<NivelAcesso> getNivelAcessoList(){
        return nivelAcessoService.findAll();
    }

    @GetMapping("/{accessLevelId}")
    public ResponseEntity<NivelAcesso> getNivelAcessoByID(@PathVariable("accessLevelId") Long accessLevelId) {
        return nivelAcessoService.getById(accessLevelId)
                .map(accessLevel -> ResponseEntity.ok().body(accessLevel))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<NivelAcesso> updateDateType(@Valid @RequestBody NivelAcesso accessLevel){
        var res = Optional.ofNullable(accessLevel.getId());

        if(res.isEmpty() || res.get() <= 0) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok().body(nivelAcessoService.updateNivelAcesso(accessLevel));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{accessLevelId}")
    public ResponseEntity deleteByID(@PathVariable("accessLevelId") Long accessLevelId) {
        if(accessLevelId <= 0) return ResponseEntity.badRequest().build();

        try {
            nivelAcessoService.deleteNivelAcesso(accessLevelId);
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
