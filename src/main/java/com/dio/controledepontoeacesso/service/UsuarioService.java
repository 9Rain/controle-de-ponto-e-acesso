package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.dto.UsuarioDTO;
import com.dio.controledepontoeacesso.exception.NotFoundException;
import com.dio.controledepontoeacesso.mapper.UsuarioMapper;
import com.dio.controledepontoeacesso.repository.*;
import com.dio.controledepontoeacesso.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    JornadaTrabalhoRepository jornadaTrabalhoRepository;

    @Autowired
    CategoriaUsuarioRepository categoriaUsuarioRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    NivelAcessoRepository nivelAcessoRepository;

    @Autowired
    BancoHorasRepository bancoHorasRepository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    UsuarioMapper usuarioMapper;

    public UsuarioDTO saveUsuario(UsuarioDTO usuario) throws NotFoundException {
        return jornadaTrabalhoRepository.findById(usuario.getJornadaTrabalho().getId())
            .map((jornadaTrabalho) -> {
                usuario.setJornadaTrabalho(jornadaTrabalho);

                return categoriaUsuarioRepository.findById(usuario.getCategoriaUsuario().getId())
                    .map((categoriaUsuario) -> {
                        usuario.setCategoriaUsuario(categoriaUsuario);

                        return empresaRepository.findById(usuario.getEmpresa().getId())
                            .map((empresa) -> {
                                usuario.setEmpresa(empresa);

                                return nivelAcessoRepository.findById(usuario.getNivelAcesso().getId())
                                    .map((nivelAcesso) -> {
                                        usuario.setNivelAcesso(nivelAcesso);

                                        return usuarioMapper.toUsuarioDTO(
                                            usuarioRepository.save(
                                                usuarioMapper.toUsuario(usuario)
                                            )
                                        );
                                    })
                                    .orElseThrow(() -> new NotFoundException(UsuarioResponse.NIVEL_ACESSO_NOT_FOUND));
                            })
                            .orElseThrow(() -> new NotFoundException(UsuarioResponse.EMPRESA_NOT_FOUND));
                    })
                    .orElseThrow(() -> new NotFoundException(UsuarioResponse.CATEGORIA_USUARIO_NOT_FOUND));
            })
            .orElseThrow(() -> new NotFoundException(UsuarioResponse.JORNADA_TRABALHO_NOT_FOUND));
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
        return usuarioRepository.findById(usuario.getId())
            .map((usuarioToBeUpdated) ->
                jornadaTrabalhoRepository.findById(usuario.getJornadaTrabalho().getId())
                    .map((jornadaTrabalho) -> {
                        usuario.setJornadaTrabalho(jornadaTrabalho);

                        return categoriaUsuarioRepository.findById(usuario.getCategoriaUsuario().getId())
                            .map((categoriaUsuario) -> {
                                usuario.setCategoriaUsuario(categoriaUsuario);

                                return empresaRepository.findById(usuario.getEmpresa().getId())
                                    .map((empresa) -> {
                                        usuario.setEmpresa(empresa);

                                        return nivelAcessoRepository.findById(usuario.getNivelAcesso().getId())
                                            .map((nivelAcesso) -> {
                                                usuario.setNivelAcesso(nivelAcesso);

                                                return usuarioMapper.toUsuarioDTO(
                                                    usuarioRepository.save(
                                                        usuarioMapper.toUsuario(usuario)
                                                    )
                                                );
                                            })
                                            .orElseThrow(() -> new NotFoundException(UsuarioResponse.NIVEL_ACESSO_NOT_FOUND));
                                    })
                                    .orElseThrow(() -> new NotFoundException(UsuarioResponse.EMPRESA_NOT_FOUND));
                            })
                            .orElseThrow(() -> new NotFoundException(UsuarioResponse.CATEGORIA_USUARIO_NOT_FOUND));
                    })
                    .orElseThrow(() -> new NotFoundException(UsuarioResponse.JORNADA_TRABALHO_NOT_FOUND))
            )
            .orElseThrow(() -> new NotFoundException(UsuarioResponse.ENTITY_NOT_FOUND));
    }

    public void deleteUsuario(Long idUsuario) {
        bancoHorasRepository.deleteByUsuarioId(idUsuario);
        movimentacaoRepository.deleteByUsuarioId(idUsuario);
        usuarioRepository.deleteById(idUsuario);
    }

    public List<UsuarioDTO> findByJornadaTrabalhoId(Long workingDayId) throws NotFoundException {
        return jornadaTrabalhoRepository.findById(workingDayId)
            .map((jornadaTrabalho) -> usuarioMapper.toUsuarioDTOs(usuarioRepository.findByJornadaTrabalhoId(workingDayId)))
            .orElseThrow(() -> new NotFoundException(UsuarioResponse.JORNADA_TRABALHO_NOT_FOUND));
    }

    public List<UsuarioDTO> findByCategoriaUsuarioId(Long userCategoryId) throws NotFoundException {
        return categoriaUsuarioRepository.findById(userCategoryId)
            .map((categoriaUsuario) -> usuarioMapper.toUsuarioDTOs(usuarioRepository.findByCategoriaUsuarioId(userCategoryId)))
            .orElseThrow(() -> new NotFoundException(UsuarioResponse.CATEGORIA_USUARIO_NOT_FOUND));
    }

    public List<UsuarioDTO> findByEmpresaId(Long companyId) throws NotFoundException {
        return empresaRepository.findById(companyId)
            .map((empresa) -> usuarioMapper.toUsuarioDTOs(usuarioRepository.findByEmpresaId(companyId)))
            .orElseThrow(() -> new NotFoundException(UsuarioResponse.EMPRESA_NOT_FOUND));
    }

    public List<UsuarioDTO> findByNivelAcessoId(Long accessLevelId) throws NotFoundException {
        return nivelAcessoRepository.findById(accessLevelId)
            .map((nivelAcesso) -> usuarioMapper.toUsuarioDTOs(usuarioRepository.findByNivelAcessoId(accessLevelId)))
            .orElseThrow(() -> new NotFoundException(UsuarioResponse.NIVEL_ACESSO_NOT_FOUND));
    }
}
