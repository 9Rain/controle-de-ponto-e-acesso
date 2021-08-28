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
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataEntrada;

    private LocalDateTime dataSaida;

    private BigDecimal periodo;

    @ManyToOne(optional = false)
    private Calendario calendario;
}
