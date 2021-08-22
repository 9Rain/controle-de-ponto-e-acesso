package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.exception.RelationshipNotFoundException;
import com.dio.controledepontoeacesso.mapper.TipoDataMapper;
import com.dio.controledepontoeacesso.model.Calendario;
import com.dio.controledepontoeacesso.repository.CalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalendarioService {
    @Autowired
    CalendarioRepository calendarioRepository;

    @Autowired
    TipoDataService tipoDataService;

    @Autowired
    TipoDataMapper tipoDataMapper;


    public Calendario saveCalendario(Calendario calendario) {
        var relatedTipoData = tipoDataService.getById(calendario.getTipoDataId());

//        if(relatedTipoData.isEmpty()) {
//            throw new RelationshipNotFoundException();
//        }

        calendario.setTipoData(tipoDataMapper.toTipoData(relatedTipoData));
//        calendario.setTipoData(relatedTipoData.get());

        return calendarioRepository.save(calendario);
    }

    public List<Calendario> findAll() {
        return calendarioRepository.findAll();
    }

    public Optional<Calendario> getById(Long idCalendario) {
        return calendarioRepository.findById(idCalendario);
    }

    public Calendario updateCalendario(Calendario calendario) throws NoSuchElementException {
        var calendarioToBeUpdated = this.getById(calendario.getId());

        if(calendarioToBeUpdated.isEmpty()) {
            throw new NoSuchElementException();
        }

        var relatedTipoData = tipoDataService.getById(calendario.getTipoDataId());

//        if(relatedTipoData.isEmpty()) {
//            throw new RelationshipNotFoundException();
//        }

//        calendario.setTipoData(relatedTipoData.get());
        calendario.setTipoData(tipoDataMapper.toTipoData(relatedTipoData));

        return calendarioRepository.save(calendario);
    }

    public void deleteCalendario(Long idCalendario) {
        calendarioRepository.deleteById(idCalendario);
    }
}
