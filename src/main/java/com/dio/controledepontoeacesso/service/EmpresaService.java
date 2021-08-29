package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.EmpresaDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.mapper.EmpresaMapper;
import com.dio.controledepontoeacesso.repository.BancoHorasRepository;
import com.dio.controledepontoeacesso.repository.EmpresaRepository;
import com.dio.controledepontoeacesso.repository.MovimentacaoRepository;
import com.dio.controledepontoeacesso.repository.UsuarioRepository;
import com.dio.controledepontoeacesso.response.EmpresaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    BancoHorasRepository bancoHorasRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    EmpresaMapper empresaMapper;

    public EmpresaDTO saveEmpresa(EmpresaDTO empresa){
        return empresaMapper.toEmpresaDTO(
                empresaRepository.save(
                        empresaMapper.toEmpresa(empresa)
                )
        );
    }

    public List<EmpresaDTO> findAll() {
        return empresaMapper.toEmpresaDTOs(empresaRepository.findAll());
    }

    public EmpresaDTO getById(Long idEmpresa) throws NotFoundException {
        return empresaRepository.findById(idEmpresa)
                .map(empresaMapper::toEmpresaDTO)
                .orElseThrow(() -> new NotFoundException(EmpresaResponse.ENTITY_NOT_FOUND));
    }

    public EmpresaDTO updateEmpresa(EmpresaDTO empresa) throws NotFoundException {
        var empresaToBeUpdated = empresaRepository.findById(empresa.getId());

        if(empresaToBeUpdated.isEmpty()) {
            throw new NotFoundException(EmpresaResponse.ENTITY_NOT_FOUND);
        }

        return empresaMapper.toEmpresaDTO(
                empresaRepository.save(
                        empresaMapper.toEmpresa(empresa)
                )
        );
    }

    public void deleteEmpresa(Long idEmpresa) {
        bancoHorasRepository.deleteByEmpresaId(idEmpresa);
        movimentacaoRepository.deleteByEmpresaId(idEmpresa);
        usuarioRepository.deleteByEmpresaId(idEmpresa);
        empresaRepository.deleteById(idEmpresa);
    }
}
