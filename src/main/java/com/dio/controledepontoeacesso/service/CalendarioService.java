package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.CalendarioDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.mapper.CalendarioMapper;
import com.dio.controledepontoeacesso.repository.CalendarioRepository;
import com.dio.controledepontoeacesso.response.CalendarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarioService {
    @Autowired
    CalendarioRepository calendarioRepository;

    @Autowired
    CalendarioMapper calendarioMapper;


    public CalendarioDTO saveCalendario(CalendarioDTO calendario) {
        return calendarioMapper.toCalendarioDTO(
                calendarioRepository.save(
                        calendarioMapper.toCalendario(calendario)
                )
        );
    }

    public List<CalendarioDTO> findAll() {
        return calendarioMapper.toCalendarioDTOs(calendarioRepository.findAll());
    }

    public CalendarioDTO getById(Long idCalendario) throws NotFoundException {
        return calendarioRepository.findById(idCalendario)
                .map(calendarioMapper::toCalendarioDTO)
                .orElseThrow(() -> new NotFoundException(CalendarioResponse.ENTITY_NOT_FOUND));
    }

    public CalendarioDTO updateCalendario(CalendarioDTO calendario) throws NotFoundException {
        var calendarioToBeUpdated = calendarioRepository.findById(calendario.getId());

        if(calendarioToBeUpdated.isEmpty()) {
            throw new NotFoundException(CalendarioResponse.ENTITY_NOT_FOUND);
        }

        return calendarioMapper.toCalendarioDTO(
                calendarioRepository.save(
                        calendarioMapper.toCalendario(calendario)
                )
        );
    }

    public void deleteCalendario(Long idCalendario) {
        calendarioRepository.deleteById(idCalendario);
    }
}
