package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.LocalidadeDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.mapper.LocalidadeMapper;
import com.dio.controledepontoeacesso.repository.LocalidadeRepository;
import com.dio.controledepontoeacesso.repository.NivelAcessoRepository;
import com.dio.controledepontoeacesso.response.LocalidadeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadeService {
    @Autowired
    LocalidadeRepository localidadeRepository;

    @Autowired
    NivelAcessoRepository nivelAcessoRepository;

    @Autowired
    LocalidadeMapper localidadeMapper;

    public LocalidadeDTO saveLocalidade(LocalidadeDTO localidade) throws NotFoundException {
        return nivelAcessoRepository.findById(localidade.getNivelAcesso().getId())
            .map((nivelAcesso) -> {
                localidade.setNivelAcesso(nivelAcesso);

                return localidadeMapper.toLocalidadeDTO(
                    localidadeRepository.save(
                        localidadeMapper.toLocalidade(localidade)
                    )
                );
            })
            .orElseThrow(() -> new NotFoundException(LocalidadeResponse.NIVEL_ACESSO_NOT_FOUND));
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
        return localidadeRepository.findById(localidade.getId())
            .map((localidadeToBeUpdated) -> 
                nivelAcessoRepository.findById(localidade.getNivelAcesso().getId())
                    .map((nivelAcesso) -> {
                        localidade.setNivelAcesso(nivelAcesso);

                        return localidadeMapper.toLocalidadeDTO(
                            localidadeRepository.save(
                                localidadeMapper.toLocalidade(localidade)
                            )
                        );
                    })
                    .orElseThrow(() -> new NotFoundException(LocalidadeResponse.NIVEL_ACESSO_NOT_FOUND))
            )
            .orElseThrow(() -> new NotFoundException(LocalidadeResponse.ENTITY_NOT_FOUND));
    }

    public void deleteLocalidade(Long idLocalidade) {
        localidadeRepository.deleteById(idLocalidade);
    }

    public List<LocalidadeDTO> findByNivelAcessoId(Long accessLevelId) throws NotFoundException {
        return nivelAcessoRepository.findById(accessLevelId)
            .map((nivelAcesso) -> localidadeMapper.toLocalidadeDTOs(localidadeRepository.findByNivelAcessoId(accessLevelId)))
            .orElseThrow(() -> new NotFoundException(LocalidadeResponse.NIVEL_ACESSO_NOT_FOUND));
    }
}
