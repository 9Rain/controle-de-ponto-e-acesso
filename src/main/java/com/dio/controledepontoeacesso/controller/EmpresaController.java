package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.dto.EmpresaDTO;
import com.dio.controledepontoeacesso.dto.UsuarioDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.response.EmpresaResponse;
import com.dio.controledepontoeacesso.service.EmpresaService;
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
@RequestMapping("/companies")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<EmpresaDTO> createEmpresa(@Valid @RequestBody EmpresaDTO empresa){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.saveEmpresa(empresa));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, EmpresaResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> getEmpresaList(){
        try {
            return ResponseEntity.ok(empresaService.findAll());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, EmpresaResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping("/{idEmpresa}")
    public ResponseEntity<EmpresaDTO> getEmpresaByID(@PathVariable("idEmpresa") Long idEmpresa) {
        try {
            return ResponseEntity.ok(empresaService.getById(idEmpresa));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, EmpresaResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @PutMapping("/{idEmpresa}")
    public ResponseEntity<EmpresaDTO> updateEmpresa(@PathVariable("idEmpresa") Long idEmpresa,
                                                 @Valid @RequestBody EmpresaDTO empresa){
        try {
            empresa.setId(idEmpresa);
            return ResponseEntity.ok(empresaService.updateEmpresa(empresa));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, EmpresaResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @DeleteMapping("/{idEmpresa}")
    public ResponseEntity deleteByID(@PathVariable("idEmpresa") Long idEmpresa) {
        try {
            empresaService.deleteEmpresa(idEmpresa);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, EmpresaResponse.ENTITY_NOT_FOUND, e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, EmpresaResponse.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping("/{companyId}/users")
    public ResponseEntity<List<UsuarioDTO>> listEmpresaUsuarios(@PathVariable("companyId") Long companyId) {
        try {
            return ResponseEntity.ok(usuarioService.findByEmpresaId(companyId));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, EmpresaResponse.INTERNAL_SERVER_ERROR, e);
        }
    }
}
