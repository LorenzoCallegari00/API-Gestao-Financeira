package com.lorenzo.GestaoFinanceira.repository;

import com.lorenzo.GestaoFinanceira.enums.Categoria;
import com.lorenzo.GestaoFinanceira.enums.TipoTransacao;
import com.lorenzo.GestaoFinanceira.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
    List<Transacao> findByTipo(TipoTransacao tipoTransacao);

    List<Transacao> findByCategoria(Categoria categoria);

    List<Transacao> findByDataBetween(LocalDate inicio, LocalDate fim);

    List<Transacao> findByDataBetweenAndTipo(LocalDate inicio, LocalDate fim, TipoTransacao tipoTransacao);

    @Query("SELECT SUM(t.valor) FROM Transacao t WHERE t.tipo = :tipo")
    BigDecimal calcularTotalPorTipo(@Param("tipo") TipoTransacao tipoTransacao);
}
