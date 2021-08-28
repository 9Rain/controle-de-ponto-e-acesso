package com.dio.controledepontoeacesso.repository;

import com.dio.controledepontoeacesso.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Transactional
    @Modifying
    @Query("DELETE Usuario U WHERE U.jornadaTrabalho.id = :JornadaTrabalhoId")
    void deleteByJornadaTrabalhoId(@Param("JornadaTrabalhoId") Long JornadaTrabalhoId);

    @Query(value = "SELECT U FROM Usuario U WHERE U.jornadaTrabalho.id = :jornadaTrabalhoId")
    List<Usuario> findByJornadaTrabalhoId(@Param("jornadaTrabalhoId") Long jornadaTrabalhoId);
}
