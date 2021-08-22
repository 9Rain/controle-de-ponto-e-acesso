package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.dto.CalendarioDTO;
import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.exception.RelationshipNotFoundException;
import com.dio.controledepontoeacesso.model.Calendario;
import com.dio.controledepontoeacesso.response.CalendarioResponse;
import com.dio.controledepontoeacesso.response.TipoDataResponse;
import com.dio.controledepontoeacesso.service.CalendarioService;
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
@RequestMapping("/calendars")
public class CalendarioController {
    @Autowired
    CalendarioService calendarioService;

    @PostMapping
    public ResponseEntity<CalendarioDTO> createCalendario(@Valid @RequestBody CalendarioDTO calendar){
        return ResponseEntity.status(HttpStatus.CREATED).body(calendarioService.saveCalendario(calendar));
    }

    @GetMapping
    public ResponseEntity<List<CalendarioDTO>> getCalendarioList(){
        return ResponseEntity.ok(calendarioService.findAll());
    }

    @GetMapping("/{calendarId}")
    public ResponseEntity<CalendarioDTO> getCalendarioByID(@PathVariable("calendarId") Long calendarId) {
        try {
            return ResponseEntity.ok(calendarioService.getById(calendarId));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, CalendarioResponse.ENTITY_NOT_FOUND, e);
        }
    }

    @PutMapping("/{calendarId}")
    public ResponseEntity<CalendarioDTO> updateDateType(@PathVariable("calendarId") Long calendarId,
                                                     @Valid @RequestBody CalendarioDTO calendar){
        try {
            calendar.setId(calendarId);
            return ResponseEntity.ok(calendarioService.updateCalendario(calendar));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, CalendarioResponse.ENTITY_NOT_FOUND, e);
        }
    }

    @DeleteMapping("/{calendarId}")
    public ResponseEntity deleteByID(@PathVariable("calendarId") Long calendarId) {
        try {
            calendarioService.deleteCalendario(calendarId);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, CalendarioResponse.ENTITY_NOT_FOUND, e);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, CalendarioResponse.INTERNAL_SERVER_ERROR, e);
        }
    }
}
