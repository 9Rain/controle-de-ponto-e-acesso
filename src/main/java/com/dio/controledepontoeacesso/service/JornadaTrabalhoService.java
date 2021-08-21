package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.model.JornadaTrabalho;
import com.dio.controledepontoeacesso.repository.JornadaTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JornadaTrabalhoService {
    JornadaTrabalhoRepository jornadaTrabalhoRepository;

    @Autowired
    public JornadaTrabalhoService(JornadaTrabalhoRepository jornadaTrabalhoRepository) {
        this.jornadaTrabalhoRepository = jornadaTrabalhoRepository;
    }

    public JornadaTrabalho saveJornada(JornadaTrabalho jornadaTrabalho){
        return jornadaTrabalhoRepository.save(jornadaTrabalho);
    }

    public List<JornadaTrabalho> findAll() {
        return jornadaTrabalhoRepository.findAll();
    }

    public Optional<JornadaTrabalho> getById(Long idJornada) {
        return jornadaTrabalhoRepository.findById(idJornada);
    }

    public JornadaTrabalho updateJornada(JornadaTrabalho jornadaTrabalho) throws NoSuchElementException {
        var jornadaTrabalhoToBeUpdated = this.getById(jornadaTrabalho.getId());

        if(jornadaTrabalhoToBeUpdated.isEmpty()) {
            throw new NoSuchElementException();
        }

        return jornadaTrabalhoRepository.save(jornadaTrabalho);
    }

    public void deleteJornada(Long idJornada) {
        jornadaTrabalhoRepository.deleteById(idJornada);
    }
}
