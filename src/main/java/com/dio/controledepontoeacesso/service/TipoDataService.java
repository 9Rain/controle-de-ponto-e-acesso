package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.TipoDataDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.mapper.TipoDataMapper;
import com.dio.controledepontoeacesso.repository.BancoHorasRepository;
import com.dio.controledepontoeacesso.repository.CalendarioRepository;
import com.dio.controledepontoeacesso.repository.MovimentacaoRepository;
import com.dio.controledepontoeacesso.repository.TipoDataRepository;
import com.dio.controledepontoeacesso.response.TipoDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDataService {
    @Autowired
    TipoDataRepository tipoDataRepository;

    @Autowired
    CalendarioRepository calendarioRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    BancoHorasRepository bancoHorasRepository;

    @Autowired
    TipoDataMapper tipoDataMapper;

    public TipoDataDTO saveTipoData(TipoDataDTO tipoData){
        return tipoDataMapper.toTipoDataDTO(
                tipoDataRepository.save(
                        tipoDataMapper.toTipoData(tipoData)
                )
        );
    }

    public List<TipoDataDTO> findAll() {
        return tipoDataMapper.toTipoDataDTOs(tipoDataRepository.findAll());
    }

    public TipoDataDTO getById(Long idTipoData) throws NotFoundException {
        return tipoDataRepository.findById(idTipoData)
                .map(tipoDataMapper::toTipoDataDTO)
                .orElseThrow(() -> new NotFoundException(TipoDataResponse.ENTITY_NOT_FOUND));
    }

    public TipoDataDTO updateTipoData(TipoDataDTO tipoData) throws NotFoundException {
        return tipoDataRepository.findById(tipoData.getId())
            .map((tipoDataToBeUpdated) ->
                tipoDataMapper.toTipoDataDTO(
                    tipoDataRepository.save(
                            tipoDataMapper.toTipoData(tipoData)
                    )
                )
            )
            .orElseThrow(() -> new NotFoundException(TipoDataResponse.ENTITY_NOT_FOUND));
    }

    public void deleteTipoData(Long idTipoData) {
        bancoHorasRepository.deleteByTipoDataId(idTipoData);
        movimentacaoRepository.deleteByTipoDataId(idTipoData);
        calendarioRepository.deleteByTipoDataId(idTipoData);
        tipoDataRepository.deleteById(idTipoData);
    }
}
