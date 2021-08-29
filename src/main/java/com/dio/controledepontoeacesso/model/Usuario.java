package com.dio.controledepontoeacesso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(
            value = "User's id",
            name = "id",
            dataType = "Long",
            required = true)
    private Long id;

    @Column(name = "nome")
    @ApiModelProperty(
            value = "User's name",
            name = "nome",
            dataType = "String",
            readOnly = true)
    private String nome;

    @Column(name = "tolerancia")
    @ApiModelProperty(
            value = "User's tolerance",
            name = "tolerancia",
            dataType = "BigDecimal",
            readOnly = true)
    private BigDecimal tolerancia;

    @Column(name = "inicio_jornada")
    @ApiModelProperty(
            value = "User's start of office time",
            name = "inicioJornada",
            dataType = "LocalDateTime",
            readOnly = true)
    private LocalDateTime inicioJornada;

    @Column(name = "final_jornada")
    @ApiModelProperty(
            value = "User's end of office time",
            name = "finalJornada",
            dataType = "LocalDateTime",
            readOnly = true)
    private LocalDateTime finalJornada;

    @JoinColumn(name = "jornada_trabalho_id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private JornadaTrabalho jornadaTrabalho;

    @JoinColumn(name = "categoria_usuario_id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private CategoriaUsuario categoriaUsuario;

    @JoinColumn(name = "empresa_id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Empresa empresa;

    @JoinColumn(name = "nivel_acesso_id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private NivelAcesso nivelAcesso;
}
