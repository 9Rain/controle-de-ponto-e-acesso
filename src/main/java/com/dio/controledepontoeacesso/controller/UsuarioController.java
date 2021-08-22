package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.dto.UsuarioDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.response.UsuarioResponse;
import com.dio.controledepontoeacesso.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> createUsuario(@Valid @RequestBody UsuarioDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(user));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getUsuarioList(){
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UsuarioDTO> getUsuarioByID(@PathVariable("userId") Long userId) {
        try {
            return ResponseEntity.ok(usuarioService.getById(userId));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, UsuarioResponse.ENTITY_NOT_FOUND, e);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable("userId") Long userId,
                                                      @Valid @RequestBody UsuarioDTO user){
        try {
            user.setId(userId);
            return ResponseEntity.ok(usuarioService.updateUsuario(user));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, UsuarioResponse.ENTITY_NOT_FOUND, e);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUsuarioByID(@PathVariable("userId") Long userId) {
        try {
            usuarioService.deleteUsuario(userId);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, UsuarioResponse.ENTITY_NOT_FOUND, e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, UsuarioResponse.INTERNAL_SERVER_ERROR, e);
        }
    }
}
