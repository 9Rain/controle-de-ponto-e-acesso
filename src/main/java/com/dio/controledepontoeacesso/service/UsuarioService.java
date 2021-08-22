package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.UsuarioDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.mapper.UsuarioMapper;
import com.dio.controledepontoeacesso.repository.UsuarioRepository;
import com.dio.controledepontoeacesso.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioMapper usuarioMapper;

    public UsuarioDTO saveUsuario(UsuarioDTO usuario){
        return usuarioMapper.toUsuarioDTO(
                usuarioRepository.save(
                        usuarioMapper.toUsuario(usuario)
                )
        );
    }

    public List<UsuarioDTO> findAll() {
        return usuarioMapper.toUsuarioDTOs(usuarioRepository.findAll());
    }

    public UsuarioDTO getById(Long idUsuario) throws NotFoundException {
        return usuarioRepository.findById(idUsuario)
                .map(usuarioMapper::toUsuarioDTO)
                .orElseThrow(() -> new NotFoundException(UsuarioResponse.ENTITY_NOT_FOUND));
    }

    public UsuarioDTO updateUsuario(UsuarioDTO usuario) throws NotFoundException {
        var usuarioToBeUpdated = usuarioRepository.findById(usuario.getId());

        if(usuarioToBeUpdated.isEmpty()) {
            throw new NotFoundException(UsuarioResponse.ENTITY_NOT_FOUND);
        }

        return usuarioMapper.toUsuarioDTO(
                usuarioRepository.save(
                        usuarioMapper.toUsuario(usuario)
                )
        );
    }

    public void deleteUsuario(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }
}
