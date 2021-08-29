package com.dio.controledepontoeacesso.repository;

import com.dio.controledepontoeacesso.model.BancoHoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BancoHorasRepository extends JpaRepository<BancoHoras, Long> {
    @Transactional
    @Modifying
    @Query("DELETE BancoHoras BH WHERE BH.movimentacao.id = :movimentacaoId")
    void deleteByMovimentacaoId(@Param("movimentacaoId") Long movimentacaoId);

    @Transactional
    @Modifying
    @Query("DELETE BancoHoras BH WHERE BH.movimentacao IN (SELECT M FROM Movimentacao M WHERE M.calendario.id = :calendarioId)")
    void deleteByCalendarioId(@Param("calendarioId") Long calendarioId);

    @Transactional
    @Modifying
    @Query("DELETE BancoHoras BH WHERE BH.movimentacao IN (SELECT M FROM Movimentacao M JOIN Calendario C ON M.calendario.id = C.id WHERE C.tipoData.id = :tipoDataId)")
    void deleteByTipoDataId(@Param("tipoDataId") Long tipoDataId);

    @Transactional
    @Modifying
    @Query("DELETE BancoHoras BH WHERE BH.movimentacao IN (SELECT M FROM Movimentacao M WHERE M.ocorrencia.id = :ocorrenciaId)")
    void deleteByOcorrenciaId(Long ocorrenciaId);

    @Transactional
    @Modifying
    @Query("DELETE BancoHoras BH WHERE BH.movimentacao IN (SELECT M FROM Movimentacao M WHERE M.usuario.id = :usuarioId)")
    void deleteByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Transactional
    @Modifying
    @Query("DELETE BancoHoras BH WHERE BH.movimentacao IN (SELECT M FROM Movimentacao M JOIN Usuario U ON M.usuario.id = U.id WHERE U.jornadaTrabalho.id = :jornadaTrabalhoId)")
    void deleteByJornadaTrabalhoId(@Param("jornadaTrabalhoId") Long jornadaTrabalhoId);

    @Transactional
    @Modifying
    @Query("DELETE BancoHoras BH WHERE BH.movimentacao IN (SELECT M FROM Movimentacao M JOIN Usuario U ON M.usuario.id = U.id WHERE U.categoriaUsuario.id = :categoriaUsuarioId)")
    void deleteByCategoriaUsuarioId(@Param("categoriaUsuarioId") Long categoriaUsuarioId);

    @Transactional
    @Modifying
    @Query("DELETE BancoHoras BH WHERE BH.movimentacao IN (SELECT M FROM Movimentacao M JOIN Usuario U ON M.usuario.id = U.id WHERE U.empresa.id = :empresaId)")
    void deleteByEmpresaId(@Param("empresaId") Long empresaId);

    @Transactional
    @Modifying
    @Query("DELETE BancoHoras BH WHERE BH.movimentacao IN (SELECT M FROM Movimentacao M JOIN Usuario U ON M.usuario.id = U.id WHERE U.nivelAcesso.id = :nivelAcessoId)")
    void deleteByNivelAcessoId(@Param("nivelAcessoId") Long nivelAcessoId);

    @Query(value = "SELECT BH FROM BancoHoras BH WHERE BH.movimentacao.id = :movimentacaoId")
    List<BancoHoras> findByMovimentacaoId(@Param("movimentacaoId") Long movimentacaoId);
}
