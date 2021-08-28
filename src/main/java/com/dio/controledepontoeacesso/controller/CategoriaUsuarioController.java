package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.dto.CategoriaUsuarioDTO;
import com.dio.controledepontoeacesso.dto.UsuarioDTO;
import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.model.CategoriaUsuario;
import com.dio.controledepontoeacesso.response.CategoriaUsuarioResponse;
import com.dio.controledepontoeacesso.response.JornadaTrabalhoResponse;
import com.dio.controledepontoeacesso.response.OcorrenciaResponse;
import com.dio.controledepontoeacesso.response.Response;
import com.dio.controledepontoeacesso.service.CategoriaUsuarioService;
import com.dio.controledepontoeacesso.service.UsuarioService;
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
@RequestMapping("/user-categories")
public class CategoriaUsuarioController {
    @Autowired
    CategoriaUsuarioService categoriaUsuarioService;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<CategoriaUsuarioDTO> createCategoriaUsuario(@Valid @RequestBody CategoriaUsuarioDTO categoriaUsuario){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaUsuarioService.saveCategoriaUsuario(categoriaUsuario));
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, CategoriaUsuarioResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoriaUsuarioDTO>> getCategoriaUsuarioList(){
        try {
            return ResponseEntity.ok(categoriaUsuarioService.findAll());
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, CategoriaUsuarioResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping("/{idCategoriaUsuario}")
    public ResponseEntity<CategoriaUsuarioDTO> getCategoriaUsuarioByID(@PathVariable("idCategoriaUsuario") Long idCategoriaUsuario) {
        try {
            return ResponseEntity.ok(categoriaUsuarioService.getById(idCategoriaUsuario));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, CategoriaUsuarioResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @PutMapping("/{idCategoriaUsuario}")
    public ResponseEntity<CategoriaUsuarioDTO> updateCategoriaUsuario(@PathVariable("idCategoriaUsuario") Long idCategoriaUsuario,
                                                                   @Valid @RequestBody CategoriaUsuarioDTO categoriaUsuario){
        try {
            categoriaUsuario.setId(idCategoriaUsuario);
            return ResponseEntity.ok(categoriaUsuarioService.updateCategoriaUsuario(categoriaUsuario));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, CategoriaUsuarioResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @DeleteMapping("/{idCategoriaUsuario}")
    public ResponseEntity deleteByID(@PathVariable("idCategoriaUsuario") Long idCategoriaUsuario) {
        try {
            categoriaUsuarioService.deleteCategoriaUsuario(idCategoriaUsuario);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, CategoriaUsuarioResponse.ENTITY_NOT_FOUND, e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, CategoriaUsuarioResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping("/{userCategoryId}/users")
    public ResponseEntity<List<UsuarioDTO>> listCategoriaUsuarioUsuarios(@PathVariable("userCategoryId") Long userCategoryId) {
        try {
            return ResponseEntity.ok(usuarioService.findByCategoriaUsuarioId(userCategoryId));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, OcorrenciaResponse.INTERNAL_SERVER_ERROR, e);
        }
    }
}
