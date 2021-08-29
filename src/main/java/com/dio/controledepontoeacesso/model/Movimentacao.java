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
@Table(name = "tb_movimentacao")
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(
            value = "Movement's id",
            name = "id",
            dataType = "Long",
            required = true)
    private Long id;

    @Column(name = "data_entrada")
    @ApiModelProperty(
            value = "Local's entry date",
            name = "dataEntrada",
            dataType = "LocalDateTime",
            readOnly = true)
    private LocalDateTime dataEntrada;

    @Column(name = "data_saida")
    @ApiModelProperty(
            value = "Local's departure date",
            name = "dataSaida",
            dataType = "LocalDateTime",
            readOnly = true)
    private LocalDateTime dataSaida;

    @Column(name = "periodo")
    @ApiModelProperty(
            value = "Local's period",
            name = "periodo",
            dataType = "BigDecimal",
            readOnly = true)
    private BigDecimal periodo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "calendario_id")
    @JsonIgnore
    private Calendario calendario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ocorrencia_id")
    @JsonIgnore
    private Ocorrencia ocorrencia;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;
}
