package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.RelationshipNotFoundException;
import com.dio.controledepontoeacesso.model.BancoHoras;
import com.dio.controledepontoeacesso.service.BancoHorasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/additional-hours")
public class BancoHorasController {
    @Autowired
    BancoHorasService bancoHorasService;

    @PostMapping
    public ResponseEntity<BancoHoras> createBancoHoras(@Valid @RequestBody BancoHoras additionalHours){
        var movementId = Optional.ofNullable(additionalHours.getBancoHorasId().getIdMovimentacao());

        if(movementId.isEmpty() || movementId.get() <= 0) return ResponseEntity.badRequest().build();

        var userId = Optional.ofNullable(additionalHours.getBancoHorasId().getIdUsuario());

        if(userId.isEmpty() || userId.get() <= 0) return ResponseEntity.badRequest().build();

        try {
            var res = bancoHorasService.saveBancoHoras(additionalHours);
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
    public List<BancoHoras> getBancoHorasList(){
        return bancoHorasService.findAll();
    }

    @GetMapping("/{additionalHoursId}")
    public ResponseEntity<BancoHoras> getBancoHorasByID(@PathVariable("additionalHoursId") Long additionalHoursId) {
        return bancoHorasService.getById(additionalHoursId)
                .map(additionalHours -> ResponseEntity.ok().body(additionalHours))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<BancoHoras> updateDateType(@Valid @RequestBody BancoHoras additionalHours){
        var additionalHoursId = Optional.ofNullable(additionalHours.getBancoHorasId().getIdBancoHoras());

        if(additionalHoursId.isEmpty() || additionalHoursId.get() <= 0) return ResponseEntity.badRequest().build();

        var movementId = Optional.ofNullable(additionalHours.getBancoHorasId().getIdMovimentacao());

        if(movementId.isEmpty() || movementId.get() <= 0) return ResponseEntity.badRequest().build();

        var userId = Optional.ofNullable(additionalHours.getBancoHorasId().getIdUsuario());

        if(userId.isEmpty() || userId.get() <= 0) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok().body(bancoHorasService.updateBancoHoras(additionalHours));
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

    @DeleteMapping("/{additionalHoursId}")
    public ResponseEntity deleteByID(@PathVariable("additionalHoursId") Long additionalHoursId) {
        if(additionalHoursId <= 0) return ResponseEntity.badRequest().build();

        try {
            bancoHorasService.deleteBancoHoras(additionalHoursId);
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
