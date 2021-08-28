package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.NivelAcessoDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.mapper.NivelAcessoMapper;
import com.dio.controledepontoeacesso.repository.LocalidadeRepository;
import com.dio.controledepontoeacesso.repository.NivelAcessoRepository;
import com.dio.controledepontoeacesso.response.NivelAcessoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NivelAcessoService {
    @Autowired
    NivelAcessoRepository nivelAcessoRepository;

    @Autowired
    LocalidadeRepository localidadeRepository;

    @Autowired
    NivelAcessoMapper nivelAcessoMapper;

    public NivelAcessoDTO saveNivelAcesso(NivelAcessoDTO nivelAcesso){
        return nivelAcessoMapper.toNivelAcessoDTO(
            nivelAcessoRepository.save(
                nivelAcessoMapper.toNivelAcesso(nivelAcesso)
            )
        );
    }

    public List<NivelAcessoDTO> findAll() {
        return nivelAcessoMapper.toNivelAcessoDTOs(nivelAcessoRepository.findAll());
    }

    public NivelAcessoDTO getById(Long idNivelAcesso) throws NotFoundException {
        return nivelAcessoRepository.findById(idNivelAcesso)
            .map(nivelAcessoMapper::toNivelAcessoDTO)
            .orElseThrow(() -> new NotFoundException(NivelAcessoResponse.ENTITY_NOT_FOUND));
    }

    public NivelAcessoDTO updateNivelAcesso(NivelAcessoDTO nivelAcesso) throws NotFoundException {
        var nivelAcessoToBeUpdated = nivelAcessoRepository.findById(nivelAcesso.getId());

        if(nivelAcessoToBeUpdated.isEmpty()) {
            throw new NotFoundException(NivelAcessoResponse.ENTITY_NOT_FOUND);
        }

        return nivelAcessoMapper.toNivelAcessoDTO(
            nivelAcessoRepository.save(
                nivelAcessoMapper.toNivelAcesso(nivelAcesso)
            )
        );
    }

    public void deleteNivelAcesso(Long idNivelAcesso) {
        localidadeRepository.deleteByNivelAcessoId(idNivelAcesso);
        nivelAcessoRepository.deleteById(idNivelAcesso);
    }
}
