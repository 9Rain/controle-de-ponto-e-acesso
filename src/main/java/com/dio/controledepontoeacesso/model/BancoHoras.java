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
public class BancoHoras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataTrabalhada;

    private BigDecimal quantidadeHoras;

    private BigDecimal saldoHoras;

    @ManyToOne(optional = false)
    private Movimentacao movimentacao;
}
