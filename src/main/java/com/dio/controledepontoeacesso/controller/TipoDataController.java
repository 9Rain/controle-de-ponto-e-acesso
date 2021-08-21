package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.model.TipoData;
import com.dio.controledepontoeacesso.service.TipoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/date-types")
public class TipoDataController {
    @Autowired
    TipoDataService tipoDataService;

    @PostMapping
    public TipoData createTipoData(@Valid @RequestBody TipoData dateType){
        return tipoDataService.saveTipoData(dateType);
    }

    @GetMapping
    public List<TipoData> getTipoDataList(){
        return tipoDataService.findAll();
    }

    @GetMapping("/{dateTypeId}")
    public ResponseEntity<TipoData> getTipoDataByID(@PathVariable("dateTypeId") Long dateTypeId) {
        return tipoDataService.getById(dateTypeId)
                .map(dateType -> ResponseEntity.ok().body(dateType))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<TipoData> updateDateType(@Valid @RequestBody TipoData dateType){
        var dateTypeId = Optional.ofNullable(dateType.getId());

        if(dateTypeId.isEmpty() || dateTypeId.get() <= 0) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok().body(tipoDataService.updateTipoData(dateType));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{dateTypeId}")
    public ResponseEntity deleteByID(@PathVariable("dateTypeId") Long dateTypeId) {
        if(dateTypeId <= 0) return ResponseEntity.badRequest().build();

        try {
            tipoDataService.deleteTipoData(dateTypeId);
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
