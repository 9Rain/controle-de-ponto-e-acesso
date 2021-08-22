package com.dio.controledepontoeacesso.repository;

import com.dio.controledepontoeacesso.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
