package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.RelationshipNotFoundException;
import com.dio.controledepontoeacesso.model.BancoHoras;
import com.dio.controledepontoeacesso.repository.BancoHorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoHorasService {
    BancoHorasRepository bancoHorasRepository;
    UsuarioService usuarioService;
    MovimentacaoService movimentacaoService;

    @Autowired
    public BancoHorasService(BancoHorasRepository bancoHorasRepository, UsuarioService usuarioService,
                             MovimentacaoService movimentacaoService) {
        this.bancoHorasRepository = bancoHorasRepository;
        this.usuarioService = usuarioService;
        this.movimentacaoService = movimentacaoService;
    }

    public BancoHoras saveBancoHoras(BancoHoras bancoHoras) throws RelationshipNotFoundException {
        var relatedMovement = movimentacaoService.getById(bancoHoras.getBancoHorasId().getIdMovimentacao());

        if(relatedMovement.isEmpty()) {
            throw new RelationshipNotFoundException();
        }

        var relatedUsuario = usuarioService.getById(bancoHoras.getBancoHorasId().getIdUsuario());

        if(relatedUsuario.isEmpty()) {
            throw new RelationshipNotFoundException();
        }

        return bancoHorasRepository.save(bancoHoras);
    }

    public List<BancoHoras> findAll() {
        return bancoHorasRepository.findAll();
    }

    public Optional<BancoHoras> getById(Long idBancoHoras) {
        return bancoHorasRepository.findById(idBancoHoras);
    }

    public BancoHoras updateBancoHoras(BancoHoras bancoHoras) throws NoSuchElementException, RelationshipNotFoundException {
        var bancoHorasToBeUpdated = this.getById(bancoHoras.getBancoHorasId().getIdBancoHoras());

        if(bancoHorasToBeUpdated.isEmpty()) {
            throw new NoSuchElementException();
        }

        return bancoHorasRepository.save(bancoHoras);
    }

    public void deleteBancoHoras(Long idBancoHoras) {
        bancoHorasRepository.deleteById(idBancoHoras);
    }
}
