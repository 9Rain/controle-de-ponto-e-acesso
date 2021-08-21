package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.model.NivelAcesso;
import com.dio.controledepontoeacesso.repository.NivelAcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NivelAcessoService {
    NivelAcessoRepository nivelAcessoRepository;

    @Autowired
    public NivelAcessoService(NivelAcessoRepository nivelAcessoRepository) {
        this.nivelAcessoRepository = nivelAcessoRepository;
    }

    public NivelAcesso saveNivelAcesso(NivelAcesso nivelAcesso){
        nivelAcesso.setLocalidades(null);
        return nivelAcessoRepository.save(nivelAcesso);
    }

    public List<NivelAcesso> findAll() {
        return nivelAcessoRepository.findAll();
    }

    public Optional<NivelAcesso> getById(Long idNivelAcesso) {
        return nivelAcessoRepository.findById(idNivelAcesso);
    }

    public NivelAcesso updateNivelAcesso(NivelAcesso nivelAcesso) throws NoSuchElementException {
        var nivelAcessoToBeUpdated = this.getById(nivelAcesso.getId());

        if(nivelAcessoToBeUpdated.isEmpty()) {
            throw new NoSuchElementException();
        }

        nivelAcesso.setLocalidades(nivelAcessoToBeUpdated.get().getLocalidades());

        return nivelAcessoRepository.save(nivelAcesso);
    }

    public void deleteNivelAcesso(Long idNivelAcesso) {
        nivelAcessoRepository.deleteById(idNivelAcesso);
    }
}
