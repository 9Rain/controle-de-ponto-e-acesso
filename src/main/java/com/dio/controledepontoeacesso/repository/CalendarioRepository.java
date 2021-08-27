package com.dio.controledepontoeacesso.repository;

import com.dio.controledepontoeacesso.model.Calendario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Long> {
    @Transactional
    @Modifying
    @Query("DELETE Calendario C WHERE C.tipoData.id = :tipoDataId")
    void deleteByTipoDataId(@Param("tipoDataId") Long tipoDataId);

    @Query(value = "SELECT C FROM Calendario C WHERE C.tipoData.id = :tipoDataId")
    List<Calendario> findByTipoDataId(@Param("tipoDataId") Long tipoDataId);
}
