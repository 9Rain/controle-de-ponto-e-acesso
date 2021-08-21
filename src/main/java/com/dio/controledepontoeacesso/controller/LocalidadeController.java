package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.RelationshipNotFoundException;
import com.dio.controledepontoeacesso.model.Localidade;
import com.dio.controledepontoeacesso.service.LocalidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locals")
public class LocalidadeController {
    @Autowired
    LocalidadeService localidadeService;

    @PostMapping
    public ResponseEntity<Localidade> createLocalidade(@Valid @RequestBody Localidade localidade){
        var accessLevelId = Optional.ofNullable(localidade.getNivelAcessoId());

        if(accessLevelId.isEmpty() || accessLevelId.get() <= 0) return ResponseEntity.badRequest().build();

        try {
            var res = localidadeService.saveLocalidade(localidade);
            return ResponseEntity.ok().body(res);
        } catch (RelationshipNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public List<Localidade> getLocalidadeList(){
        return localidadeService.findAll();
    }

    @GetMapping("/{idLocalidade}")
    public ResponseEntity<Localidade> getLocalidadeByID(@PathVariable("idLocalidade") Long idLocalidade) {
        return localidadeService.getById(idLocalidade)
                .map(local -> ResponseEntity.ok().body(local))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Localidade> updateLocalidade(@Valid @RequestBody Localidade localidade){
        var localId = Optional.ofNullable(localidade.getId());

        if(localId.isEmpty() || localId.get() <= 0) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok().body(localidadeService.updateLocalidade(localidade));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (RelationshipNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{idLocalidade}")
    public ResponseEntity deleteByID(@PathVariable("idLocalidade") Long idLocalidade) {
        if(idLocalidade <= 0) return ResponseEntity.badRequest().build();

        try {
            localidadeService.deleteLocalidade(idLocalidade);
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
