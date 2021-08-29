package com.dio.controledepontoeacesso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(
            value = "Additional hour's id",
            name = "id",
            dataType = "Long",
            required = true)
    private Long id;

    @Column(name = "data_trabalhada")
    @ApiModelProperty(
            value = "Additional hour's worked date",
            name = "dataTrabalhada",
            dataType = "LocalDateTime",
            readOnly = true)
    private LocalDateTime dataTrabalhada;

    @ApiModelProperty(
            value = "Additional hour's quantity of hours",
            name = "quantidadeHoras",
            dataType = "BigDecimal",
            readOnly = true)
    @Column(name = "quantidade_horas")
    private BigDecimal quantidadeHoras;

    @ApiModelProperty(
            value = "Additional hour's accumulated worked hours",
            name = "saldoHoras",
            dataType = "BigDecimal",
            readOnly = true)
    @Column(name = "saldo_horas")
    private BigDecimal saldoHoras;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movimentacao_id")
    @JsonIgnore
    private Movimentacao movimentacao;
}
