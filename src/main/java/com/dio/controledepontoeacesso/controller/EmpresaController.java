package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.model.Empresa;
import com.dio.controledepontoeacesso.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @PostMapping
    public Empresa createEmpresa(@Valid @RequestBody Empresa empresa){
        return empresaService.saveEmpresa(empresa);
    }

    @GetMapping
    public List<Empresa> getEmpresaList(){
        return empresaService.findAll();
    }

    @GetMapping("/{idEmpresa}")
    public ResponseEntity<Empresa> getEmpresaByID(@PathVariable("idEmpresa") Long idEmpresa) {
        return empresaService.getById(idEmpresa)
                .map(company -> ResponseEntity.ok().body(company))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Empresa> updateEmpresa(@Valid @RequestBody Empresa empresa){
        var res = Optional.of(empresa.getId());

        if(res.isEmpty() || res.get() <= 0) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok().body(empresaService.updateEmpresa(empresa));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idEmpresa}")
    public ResponseEntity deleteByID(@PathVariable("idEmpresa") Long idEmpresa) {
        if(idEmpresa <= 0) return ResponseEntity.badRequest().build();

        try {
            empresaService.deleteEmpresa(idEmpresa);
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
