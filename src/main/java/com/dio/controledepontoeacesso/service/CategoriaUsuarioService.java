package com.dio.controledepontoeacesso.service;

import com.dio.controledepontoeacesso.exception.NoSuchElementException;
import com.dio.controledepontoeacesso.model.CategoriaUsuario;
import com.dio.controledepontoeacesso.repository.CategoriaUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaUsuarioService {
    CategoriaUsuarioRepository categoriaUsuarioRepository;

    @Autowired
    public CategoriaUsuarioService(CategoriaUsuarioRepository categoriaUsuarioRepository) {
        this.categoriaUsuarioRepository = categoriaUsuarioRepository;
    }

    public CategoriaUsuario saveCategoriaUsuario(CategoriaUsuario categoriaUsuario){
        return categoriaUsuarioRepository.save(categoriaUsuario);
    }

    public List<CategoriaUsuario> findAll() {
        return categoriaUsuarioRepository.findAll();
    }

    public Optional<CategoriaUsuario> getById(Long idCategoriaUsuario) {
        return categoriaUsuarioRepository.findById(idCategoriaUsuario);
    }

    public CategoriaUsuario updateCategoriaUsuario(CategoriaUsuario categoriaUsuario) throws NoSuchElementException {
        var categoriaUsuarioToBeUpdated = this.getById(categoriaUsuario.getId());

        if(categoriaUsuarioToBeUpdated.isEmpty()) {
            throw new NoSuchElementException();
        }

        return categoriaUsuarioRepository.save(categoriaUsuario);
    }

    public void deleteCategoriaUsuario(Long idCategoriaUsuario) {
        categoriaUsuarioRepository.deleteById(idCategoriaUsuario);
    }
}
