package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.CategoriaUsuarioDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.mapper.CategoriaUsuarioMapper;
import com.dio.controledepontoeacesso.repository.BancoHorasRepository;
import com.dio.controledepontoeacesso.repository.CategoriaUsuarioRepository;
import com.dio.controledepontoeacesso.repository.MovimentacaoRepository;
import com.dio.controledepontoeacesso.repository.UsuarioRepository;
import com.dio.controledepontoeacesso.response.CategoriaUsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaUsuarioService {
    @Autowired
    CategoriaUsuarioRepository categoriaUsuarioRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    BancoHorasRepository bancoHorasRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    CategoriaUsuarioMapper categoriaUsuarioMapper;

    public CategoriaUsuarioDTO saveCategoriaUsuario(CategoriaUsuarioDTO categoriaUsuario){
        return categoriaUsuarioMapper.toCategoriaUsuarioDTO(
                categoriaUsuarioRepository.save(
                        categoriaUsuarioMapper.toCategoriaUsuario(categoriaUsuario)
                )
        );
    }

    public List<CategoriaUsuarioDTO> findAll() {
        return categoriaUsuarioMapper.toCategoriaUsuarioDTOs(categoriaUsuarioRepository.findAll());
    }

    public CategoriaUsuarioDTO getById(Long idCategoriaUsuario) throws NotFoundException {
        return categoriaUsuarioRepository.findById(idCategoriaUsuario)
                .map(categoriaUsuarioMapper::toCategoriaUsuarioDTO)
                .orElseThrow(() -> new NotFoundException(CategoriaUsuarioResponse.ENTITY_NOT_FOUND));
    }

    public CategoriaUsuarioDTO updateCategoriaUsuario(CategoriaUsuarioDTO categoriaUsuario) throws NotFoundException {
        var categoriaUsuarioToBeUpdated = categoriaUsuarioRepository.findById(categoriaUsuario.getId());

        if(categoriaUsuarioToBeUpdated.isEmpty()) {
            throw new NotFoundException(CategoriaUsuarioResponse.ENTITY_NOT_FOUND);
        }

        return categoriaUsuarioMapper.toCategoriaUsuarioDTO(
                categoriaUsuarioRepository.save(
                        categoriaUsuarioMapper.toCategoriaUsuario(categoriaUsuario)
                )
        );
    }

    public void deleteCategoriaUsuario(Long idCategoriaUsuario) {
        bancoHorasRepository.deleteByCategoriaUsuarioId(idCategoriaUsuario);
        movimentacaoRepository.deleteByCategoriaUsuarioId(idCategoriaUsuario);
        usuarioRepository.deleteByCategoriaUsuarioId(idCategoriaUsuario);
        categoriaUsuarioRepository.deleteById(idCategoriaUsuario);
    }
}
