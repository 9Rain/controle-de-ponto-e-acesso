package com.dio.controledepontoeacesso.controller;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.RelationshipNotFoundException;
import com.dio.controledepontoeacesso.model.Movimentacao;
import com.dio.controledepontoeacesso.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movements")
public class MovimentacaoController {
    @Autowired
    MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<Movimentacao> createMovimentacao(@Valid @RequestBody Movimentacao movement){
        var userId = Optional.ofNullable(movement.getMovimentacaoId().getIdUsuario());

        if(userId.isEmpty() || userId.get() <= 0) return ResponseEntity.badRequest().build();

        try {
            var res = movimentacaoService.saveMovimentacao(movement);
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
    public List<Movimentacao> getMovimentacaoList(){
        return movimentacaoService.findAll();
    }

    @GetMapping("/{movementId}")
    public ResponseEntity<Movimentacao> getMovimentacaoByID(@PathVariable("movementId") Long movementId) {
        return movimentacaoService.getById(movementId)
                .map(movement -> ResponseEntity.ok().body(movement))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Movimentacao> updateDateType(@Valid @RequestBody Movimentacao movement){
        var movementId = Optional.ofNullable(movement.getMovimentacaoId().getIdMovimento());

        if(movementId.isEmpty() || movementId.get() <= 0) return ResponseEntity.badRequest().build();

        var userId = Optional.ofNullable(movement.getMovimentacaoId().getIdUsuario());

        if(userId.isEmpty() || userId.get() <= 0) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok().body(movimentacaoService.updateMovimentacao(movement));
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

    @DeleteMapping("/{movementId}")
    public ResponseEntity deleteByID(@PathVariable("movementId") Long movementId) {
        if(movementId <= 0) return ResponseEntity.badRequest().build();

        try {
            movimentacaoService.deleteMovimentacao(movementId);
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
