package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.model.CategoriaUsuario;
import com.dio.controledepontoeacesso.service.CategoriaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-categories")
public class CategoriaUsuarioController {
    @Autowired
    CategoriaUsuarioService categoriaUsuarioService;

    @PostMapping
    public CategoriaUsuario createCategoriaUsuario(@Valid @RequestBody CategoriaUsuario categoriaUsuario){
        return categoriaUsuarioService.saveCategoriaUsuario(categoriaUsuario);
    }

    @GetMapping
    public List<CategoriaUsuario> getCategoriaUsuarioList(){
        return categoriaUsuarioService.findAll();
    }

    @GetMapping("/{idCategoriaUsuario}")
    public ResponseEntity<CategoriaUsuario> getCategoriaUsuarioByID(@PathVariable("idCategoriaUsuario") Long idCategoriaUsuario) {
        return categoriaUsuarioService.getById(idCategoriaUsuario)
                .map(categoriaUsuario -> ResponseEntity.ok().body(categoriaUsuario))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<CategoriaUsuario> updateCategoriaUsuario(@Valid @RequestBody CategoriaUsuario categoriaUsuario){
        var res = Optional.of(categoriaUsuario.getId());

        if(res.isEmpty() || res.get() <= 0) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok().body(categoriaUsuarioService.updateCategoriaUsuario(categoriaUsuario));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idCategoriaUsuario}")
    public ResponseEntity deleteByID(@PathVariable("idCategoriaUsuario") Long idCategoriaUsuario) {
        if(idCategoriaUsuario <= 0) return ResponseEntity.badRequest().build();

        try {
            categoriaUsuarioService.deleteCategoriaUsuario(idCategoriaUsuario);
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
