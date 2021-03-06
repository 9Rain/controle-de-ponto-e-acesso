package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.OcorrenciaDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.mapper.OcorrenciaMapper;
import com.dio.controledepontoeacesso.repository.BancoHorasRepository;
import com.dio.controledepontoeacesso.repository.MovimentacaoRepository;
import com.dio.controledepontoeacesso.repository.OcorrenciaRepository;
import com.dio.controledepontoeacesso.response.OcorrenciaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OcorrenciaService {
    @Autowired
    OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    BancoHorasRepository bancoHorasRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    OcorrenciaMapper ocorrenciaMapper;

    public OcorrenciaDTO saveOcorrencia(OcorrenciaDTO ocorrencia){
        return ocorrenciaMapper.toOcorrenciaDTO(
                ocorrenciaRepository.save(
                        ocorrenciaMapper.toOcorrencia(ocorrencia)
                )
        );
    }

    public List<OcorrenciaDTO> findAll() {
        return ocorrenciaMapper.toOcorrenciaDTOs(ocorrenciaRepository.findAll());
    }

    public OcorrenciaDTO getById(Long idOcorrencia) throws NotFoundException {
        return ocorrenciaRepository.findById(idOcorrencia)
                .map(ocorrenciaMapper::toOcorrenciaDTO)
                .orElseThrow(() -> new NotFoundException(OcorrenciaResponse.ENTITY_NOT_FOUND));
    }

    public OcorrenciaDTO updateOcorrencia(OcorrenciaDTO ocorrencia) throws NotFoundException {
        var ocorrenciaToBeUpdated = ocorrenciaRepository.findById(ocorrencia.getId());

        if(ocorrenciaToBeUpdated.isEmpty()) {
            throw new NotFoundException(OcorrenciaResponse.ENTITY_NOT_FOUND);
        }

        return ocorrenciaMapper.toOcorrenciaDTO(
                ocorrenciaRepository.save(
                        ocorrenciaMapper.toOcorrencia(ocorrencia)
                )
        );
    }

    public void deleteOcorrencia(Long idOcorrencia) {
        bancoHorasRepository.deleteByOcorrenciaId(idOcorrencia);
        movimentacaoRepository.deleteByOcorrenciaId(idOcorrencia);
        ocorrenciaRepository.deleteById(idOcorrencia);
    }
}
