package com.dio.controledepontoeacesso.model;

import lombok.*;
import org.hibernate.envers.Audited;

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
@Audited
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal tolerancia;

    private LocalDateTime inicioJornada;

    private LocalDateTime finalJornada;

    @ManyToOne(optional = false)
    private JornadaTrabalho jornadaTrabalho;

    @ManyToOne(optional = false)
    private CategoriaUsuario categoriaUsuario;

    @ManyToOne(optional = false)
    private Empresa empresa;

    @ManyToOne(optional = false)
    private NivelAcesso nivelAcesso;
}
