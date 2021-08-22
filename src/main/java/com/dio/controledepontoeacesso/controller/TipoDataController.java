package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.dto.TipoDataDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.response.TipoDataResponse;
import com.dio.controledepontoeacesso.service.TipoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/date-types")
public class TipoDataController {
    @Autowired
    TipoDataService tipoDataService;

    @PostMapping
    public ResponseEntity<TipoDataDTO> createTipoData(@Valid @RequestBody TipoDataDTO dateType){
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoDataService.saveTipoData(dateType));
    }

    @GetMapping
    public ResponseEntity<List<TipoDataDTO>> getTipoDataList(){
        return ResponseEntity.ok(tipoDataService.findAll());
    }

    @GetMapping("/{dateTypeId}")
    public ResponseEntity<TipoDataDTO> getTipoDataByID(@PathVariable("dateTypeId") Long dateTypeId) {
        try {
            return ResponseEntity.ok(tipoDataService.getById(dateTypeId));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, TipoDataResponse.ENTITY_NOT_FOUND, e);
        }
    }

    @PutMapping("/{dateTypeId}")
    public ResponseEntity<TipoDataDTO> updateDateType(@PathVariable("dateTypeId") Long dateTypeId,
                                                      @Valid @RequestBody TipoDataDTO dateType){
        try {
            dateType.setId(dateTypeId);
            return ResponseEntity.ok(tipoDataService.updateTipoData(dateType));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, TipoDataResponse.ENTITY_NOT_FOUND, e);
        }
    }

    @DeleteMapping("/{dateTypeId}")
    public ResponseEntity deleteByID(@PathVariable("dateTypeId") Long dateTypeId) {
        try {
            tipoDataService.deleteTipoData(dateTypeId);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, TipoDataResponse.ENTITY_NOT_FOUND, e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, TipoDataResponse.INTERNAL_SERVER_ERROR, e);
        }
    }
}
