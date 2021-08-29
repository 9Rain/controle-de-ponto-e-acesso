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
@Table(name = "tb_movimentacao")
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_entrada")
    private LocalDateTime dataEntrada;

    @Column(name = "data_saida")
    private LocalDateTime dataSaida;

    @Column(name = "periodo")
    private BigDecimal periodo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "calendario_id")
    private Calendario calendario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ocorrencia_id")
    private Ocorrencia ocorrencia;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
