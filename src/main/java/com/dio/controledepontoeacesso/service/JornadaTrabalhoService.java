package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.JornadaTrabalhoDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.mapper.JornadaTrabalhoMapper;
import com.dio.controledepontoeacesso.repository.BancoHorasRepository;
import com.dio.controledepontoeacesso.repository.JornadaTrabalhoRepository;
import com.dio.controledepontoeacesso.repository.MovimentacaoRepository;
import com.dio.controledepontoeacesso.repository.UsuarioRepository;
import com.dio.controledepontoeacesso.response.JornadaTrabalhoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JornadaTrabalhoService {
    @Autowired
    JornadaTrabalhoRepository jornadaTrabalhoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    BancoHorasRepository bancoHorasRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    JornadaTrabalhoMapper jornadaTrabalhoMapper;

    public JornadaTrabalhoDTO saveJornada(JornadaTrabalhoDTO jornadaTrabalho){
        return jornadaTrabalhoMapper.toJornadaTrabalhoDTO(
                jornadaTrabalhoRepository.save(
                    jornadaTrabalhoMapper.toJornadaTrabalho(jornadaTrabalho)
                )
        );
    }

    public List<JornadaTrabalhoDTO> findAll() {
        return jornadaTrabalhoMapper.toJornadaTrabalhoDTOs(jornadaTrabalhoRepository.findAll());
    }

    public JornadaTrabalhoDTO getById(Long idJornada) throws NotFoundException {
        return jornadaTrabalhoRepository.findById(idJornada)
                .map(jornadaTrabalhoMapper::toJornadaTrabalhoDTO)
                .orElseThrow(() -> new NotFoundException(JornadaTrabalhoResponse.ENTITY_NOT_FOUND));
    }

    public JornadaTrabalhoDTO updateJornada(JornadaTrabalhoDTO jornadaTrabalho) throws NotFoundException {
        var jornadaTrabalhoToBeUpdated = jornadaTrabalhoRepository.findById(jornadaTrabalho.getId());

        if(jornadaTrabalhoToBeUpdated.isEmpty()) {
            throw new NotFoundException(JornadaTrabalhoResponse.ENTITY_NOT_FOUND);
        }

        return jornadaTrabalhoMapper.toJornadaTrabalhoDTO(
                jornadaTrabalhoRepository.save(
                        jornadaTrabalhoMapper.toJornadaTrabalho(jornadaTrabalho)
                )
        );
    }

    public void deleteJornada(Long idJornada) {
        bancoHorasRepository.deleteByJornadaTrabalhoId(idJornada);
        movimentacaoRepository.deleteByJornadaTrabalhoId(idJornada);
        usuarioRepository.deleteByJornadaTrabalhoId(idJornada);
        jornadaTrabalhoRepository.deleteById(idJornada);
    }
}
