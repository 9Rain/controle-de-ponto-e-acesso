package com.dio.controledepontoeacesso.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Audited
@Table(name = "tb_ocorrencia")
public class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(
            value = "Occurence's id",
            name = "id",
            dataType = "Long",
            required = true)
    private Long id;

    @Column(name = "nome")
    @ApiModelProperty(
            value = "Occurence's name",
            name = "nome",
            dataType = "String",
            readOnly = true)
    private String nome;

    @Column(name = "descricao")
    @ApiModelProperty(
            value = "Occurence's description",
            name = "descricao",
            dataType = "String",
            readOnly = true)
    private String descricao;
}
