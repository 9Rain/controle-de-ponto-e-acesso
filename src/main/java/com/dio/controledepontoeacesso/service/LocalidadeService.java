package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.LocalidadeDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.mapper.LocalidadeMapper;
import com.dio.controledepontoeacesso.repository.LocalidadeRepository;
import com.dio.controledepontoeacesso.response.LocalidadeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadeService {
    @Autowired
    LocalidadeRepository localidadeRepository;

    @Autowired
    LocalidadeMapper localidadeMapper;

    public LocalidadeDTO saveLocalidade(LocalidadeDTO localidade) {
        return localidadeMapper.toLocalidadeDTO(
                localidadeRepository.save(
                        localidadeMapper.toLocalidade(localidade)
                )
        );
    }

    public List<LocalidadeDTO> findAll() {
        return localidadeMapper.toLocalidadeDTOs(localidadeRepository.findAll());
    }

    public LocalidadeDTO getById(Long idLocalidade) throws NotFoundException {
        return localidadeRepository.findById(idLocalidade)
                .map(localidadeMapper::toLocalidadeDTO)
                .orElseThrow(() -> new NotFoundException(LocalidadeResponse.ENTITY_NOT_FOUND));
    }

    public LocalidadeDTO updateLocalidade(LocalidadeDTO localidade) throws NotFoundException {
        var localidadeToBeUpdated = localidadeRepository.findById(localidade.getId());

        if(localidadeToBeUpdated.isEmpty()) {
            throw new NotFoundException(LocalidadeResponse.ENTITY_NOT_FOUND);
        }

        return localidadeMapper.toLocalidadeDTO(
                localidadeRepository.save(
                        localidadeMapper.toLocalidade(localidade)
                )
        );
    }

    public void deleteLocalidade(Long idLocalidade) {
        localidadeRepository.deleteById(idLocalidade);
    }
}
