package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.dto.EmpresaDTO;
import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.model.Empresa;
import com.dio.controledepontoeacesso.response.EmpresaResponse;
import com.dio.controledepontoeacesso.response.TipoDataResponse;
import com.dio.controledepontoeacesso.service.EmpresaService;
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
@RequestMapping("/companies")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<EmpresaDTO> createEmpresa(@Valid @RequestBody EmpresaDTO empresa){
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.saveEmpresa(empresa));
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> getEmpresaList(){
        return ResponseEntity.ok(empresaService.findAll());
    }

    @GetMapping("/{idEmpresa}")
    public ResponseEntity<EmpresaDTO> getEmpresaByID(@PathVariable("idEmpresa") Long idEmpresa) {
        try {
            return ResponseEntity.ok(empresaService.getById(idEmpresa));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, EmpresaResponse.ENTITY_NOT_FOUND, e);
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
                    HttpStatus.NOT_FOUND, EmpresaResponse.ENTITY_NOT_FOUND, e);
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
}
