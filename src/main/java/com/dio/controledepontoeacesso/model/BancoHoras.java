package com.dio.controledepontoeacesso.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "tb_banco_horas")
public class BancoHoras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_trabalhada")
    private LocalDateTime dataTrabalhada;

    @Column(name = "quantidade_horas")
    private BigDecimal quantidadeHoras;

    @Column(name = "saldo_horas")
    private BigDecimal saldoHoras;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movimentacao_id")
    private Movimentacao movimentacao;
}
