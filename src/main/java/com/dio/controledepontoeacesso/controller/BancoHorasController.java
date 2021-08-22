package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.dto.BancoHorasDTO;
import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.exception.RelationshipNotFoundException;
import com.dio.controledepontoeacesso.model.BancoHoras;
import com.dio.controledepontoeacesso.response.BancoHorasResponse;
import com.dio.controledepontoeacesso.service.BancoHorasService;
import com.dio.controledepontoeacesso.service.BancoHorasService;
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
@RequestMapping("/additional-hours")
public class BancoHorasController {
    @Autowired
    BancoHorasService bancoHorasService;

    @PostMapping
    public ResponseEntity<BancoHorasDTO> createBancoHoras(@Valid @RequestBody BancoHorasDTO additionalHour){
        return ResponseEntity.status(HttpStatus.CREATED).body(bancoHorasService.saveBancoHoras(additionalHour));
    }

    @GetMapping
    public ResponseEntity<List<BancoHorasDTO>> getBancoHorasList(){
        return ResponseEntity.ok(bancoHorasService.findAll());
    }

    @GetMapping("/{additionalHourId}")
    public ResponseEntity<BancoHorasDTO> getBancoHorasByID(@PathVariable("additionalHourId") Long additionalHourId) {
        try {
            return ResponseEntity.ok(bancoHorasService.getById(additionalHourId));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, BancoHorasResponse.ENTITY_NOT_FOUND, e);
        }
    }

    @PutMapping("/{additionalHourId}")
    public ResponseEntity<BancoHorasDTO> updateBancoHoras(@PathVariable("additionalHourId") Long additionalHourId,
                                                    @Valid @RequestBody BancoHorasDTO additionalHour){
        try {
            var embbededAdditionalHourId = additionalHour.getBancoHorasId();
            embbededAdditionalHourId.setIdBancoHoras(additionalHourId);
            additionalHour.setBancoHorasId(embbededAdditionalHourId);
            return ResponseEntity.ok(bancoHorasService.updateBancoHoras(additionalHour));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, BancoHorasResponse.ENTITY_NOT_FOUND, e);
        }
    }

    @DeleteMapping("/{additionalHourId}")
    public ResponseEntity deleteBancoHorasByID(@PathVariable("additionalHourId") Long additionalHourId) {
        try {
            bancoHorasService.deleteBancoHoras(additionalHourId);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, BancoHorasResponse.ENTITY_NOT_FOUND, e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, BancoHorasResponse.INTERNAL_SERVER_ERROR, e);
        }
    }
}
