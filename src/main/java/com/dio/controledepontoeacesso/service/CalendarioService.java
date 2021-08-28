package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.CalendarioDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.mapper.CalendarioMapper;
import com.dio.controledepontoeacesso.repository.BancoHorasRepository;
import com.dio.controledepontoeacesso.repository.CalendarioRepository;
import com.dio.controledepontoeacesso.repository.MovimentacaoRepository;
import com.dio.controledepontoeacesso.repository.TipoDataRepository;
import com.dio.controledepontoeacesso.response.CalendarioResponse;
import com.dio.controledepontoeacesso.response.TipoDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarioService {
    @Autowired
    CalendarioRepository calendarioRepository;

    @Autowired
    TipoDataRepository tipoDataRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    BancoHorasRepository bancoHorasRepository;

    @Autowired
    CalendarioMapper calendarioMapper;


    public CalendarioDTO saveCalendario(CalendarioDTO calendario) throws NotFoundException {
        return tipoDataRepository.findById(calendario.getTipoData().getId())
            .map((tipoData) -> {
                calendario.setTipoData(tipoData);

                return calendarioMapper.toCalendarioDTO(
                    calendarioRepository.save(
                        calendarioMapper.toCalendario(calendario)
                    )
                );
            })
            .orElseThrow(() -> new NotFoundException(CalendarioResponse.TIPO_DATA_NOT_FOUND));
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
        return calendarioRepository.findById(calendario.getId())
            .map((calendarioToBeUpdated) ->
                tipoDataRepository.findById(calendario.getTipoData().getId())
                    .map((tipoData) -> {
                        calendario.setTipoData(tipoData);

                        return calendarioMapper.toCalendarioDTO(
                                calendarioRepository.save(
                                        calendarioMapper.toCalendario(calendario)
                                )
                        );
                    })
                    .orElseThrow(() -> new NotFoundException(CalendarioResponse.TIPO_DATA_NOT_FOUND))
            )
            .orElseThrow(() -> new NotFoundException(CalendarioResponse.ENTITY_NOT_FOUND));
    }

    public void deleteCalendario(Long idCalendario) {
        bancoHorasRepository.deleteByCalendarioId(idCalendario);
        movimentacaoRepository.deleteByCalendarioId(idCalendario);
        calendarioRepository.deleteById(idCalendario);
    }

    public List<CalendarioDTO> findByTipoDataId(Long dateTypeId) throws NotFoundException {
        return tipoDataRepository.findById(dateTypeId)
            .map((tipoData) -> calendarioMapper.toCalendarioDTOs(calendarioRepository.findByTipoDataId(dateTypeId)))
            .orElseThrow(() -> new NotFoundException(CalendarioResponse.TIPO_DATA_NOT_FOUND));
    }
}
