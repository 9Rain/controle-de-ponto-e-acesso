package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.RelationshipNotFoundException;
import com.dio.controledepontoeacesso.model.Calendario;
import com.dio.controledepontoeacesso.service.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/calendars")
public class CalendarioController {
    @Autowired
    CalendarioService calendarioService;

    @PostMapping
    public ResponseEntity<Calendario> createCalendario(@Valid @RequestBody Calendario calendar){
        var dateTypeId = Optional.ofNullable(calendar.getTipoDataId());

        if(dateTypeId.isEmpty() || dateTypeId.get() <= 0) return ResponseEntity.badRequest().build();

        try {
            var res = calendarioService.saveCalendario(calendar);
            return ResponseEntity.ok().body(res);
        } catch (RelationshipNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public List<Calendario> getCalendarioList(){
        return calendarioService.findAll();
    }

    @GetMapping("/{calendarId}")
    public ResponseEntity<Calendario> getCalendarioByID(@PathVariable("calendarId") Long calendarId) {
        return calendarioService.getById(calendarId)
                .map(calendar -> ResponseEntity.ok().body(calendar))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Calendario> updateDateType(@Valid @RequestBody Calendario calendar){
        var calendarId = Optional.ofNullable(calendar.getId());

        if(calendarId.isEmpty() || calendarId.get() <= 0) return ResponseEntity.badRequest().build();

        var dateTypeId = Optional.ofNullable(calendar.getTipoDataId());

        if(dateTypeId.isEmpty() || dateTypeId.get() <= 0) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok().body(calendarioService.updateCalendario(calendar));
        } catch (RelationshipNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{calendarId}")
    public ResponseEntity deleteByID(@PathVariable("calendarId") Long calendarId) {
        if(calendarId <= 0) return ResponseEntity.badRequest().build();

        try {
            calendarioService.deleteCalendario(calendarId);
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
