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
@Table(name = "tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tolerancia")
    private BigDecimal tolerancia;

    @Column(name = "inicio_jornada")
    private LocalDateTime inicioJornada;

    @Column(name = "final_jornada")
    private LocalDateTime finalJornada;

    @JoinColumn(name = "jornada_trabalho_id")
    @ManyToOne(optional = false)
    private JornadaTrabalho jornadaTrabalho;

    @JoinColumn(name = "categoria_usuario_id")
    @ManyToOne(optional = false)
    private CategoriaUsuario categoriaUsuario;

    @JoinColumn(name = "empresa_id")
    @ManyToOne(optional = false)
    private Empresa empresa;

    @JoinColumn(name = "nivel_acesso_id")
    @ManyToOne(optional = false)
    private NivelAcesso nivelAcesso;
}
