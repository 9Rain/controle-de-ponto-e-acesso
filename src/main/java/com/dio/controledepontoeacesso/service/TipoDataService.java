package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.TipoDataDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.mapper.TipoDataMapper;
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
        var tipoDataToBeUpdated = tipoDataRepository.findById(tipoData.getId());

        if(tipoDataToBeUpdated.isEmpty()) {
            throw new NotFoundException(TipoDataResponse.ENTITY_NOT_FOUND);
        }

        return tipoDataMapper.toTipoDataDTO(
                tipoDataRepository.save(
                        tipoDataMapper.toTipoData(tipoData)
                )
        );
    }

    public void deleteTipoData(Long idTipoData) {
        tipoDataRepository.deleteById(idTipoData);
    }
}
