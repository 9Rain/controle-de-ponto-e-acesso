package com.dio.controledepontoeacesso.repository;

import com.dio.controledepontoeacesso.model.Localidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, Long> {
    @Transactional
    @Modifying
    @Query("DELETE Localidade L WHERE L.nivelAcesso.id = :nivelAcessoId")
    void deleteByNivelAcessoId(@Param("nivelAcessoId") Long nivelAcessoId);

    @Query(value = "SELECT L FROM Localidade L WHERE L.nivelAcesso.id = :nivelAcessoId")
    List<Localidade> findByNivelAcessoId(@Param("nivelAcessoId") Long nivelAcessoId);
}
