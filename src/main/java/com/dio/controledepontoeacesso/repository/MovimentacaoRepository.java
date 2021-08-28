package com.dio.controledepontoeacesso.repository;

import com.dio.controledepontoeacesso.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    @Transactional
    @Modifying
    @Query("DELETE Movimentacao M WHERE M.calendario.id = :calendarioId")
    void deleteByCalendarioId(@Param("calendarioId") Long calendarioId);

    @Transactional
    @Modifying
    @Query("DELETE Movimentacao M WHERE M.ocorrencia.id = :ocorrenciaId")
    void deleteByOcorrenciaId(@Param("ocorrenciaId") Long ocorrenciaId);

    @Transactional
    @Modifying
    @Query("DELETE Movimentacao M WHERE M.calendario IN (SELECT C FROM Calendario C WHERE C.tipoData.id = :tipoDataId)")
    void deleteByTipoDataId(@Param("tipoDataId") Long tipoDataId);

    @Query(value = "SELECT M FROM Movimentacao M WHERE M.calendario.id = :calendarioId")
    List<Movimentacao> findByCalendarioId(@Param("calendarioId") Long calendarioId);

    @Query(value = "SELECT M FROM Movimentacao M WHERE M.ocorrencia.id = :ocorrenciaId")
    List<Movimentacao> findByOcorrenciaId(@Param("ocorrenciaId") Long ocorrenciaId);
}
