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
@Table(name = "tb_empresa")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(
            value = "Company's id",
            name = "id",
            dataType = "Long",
            required = true)
    private Long id;

    @Column(name = "descricao")
    @ApiModelProperty(
            value = "Company's description",
            name = "descricao",
            dataType = "String",
            readOnly = true)
    private String descricao;

    @Column(name = "cnpj")
    @ApiModelProperty(
            value = "Company's cnpj",
            name = "cnpj",
            dataType = "String",
            readOnly = true)
    private String cnpj;

    @Column(name = "endereco")
    @ApiModelProperty(
            value = "Company's address",
            name = "endereco",
            dataType = "String",
            readOnly = true)
    private String endereco;

    @Column(name = "bairro")
    @ApiModelProperty(
            value = "Neighborhood where company is located",
            name = "bairro",
            dataType = "String",
            readOnly = true)
    private String bairro;

    @Column(name = "cidade")
    @ApiModelProperty(
            value = "City where company is located",
            name = "cidade",
            dataType = "String",
            readOnly = true)
    private String cidade;

    @Column(name = "estado")
    @ApiModelProperty(
            value = "State where company is located",
            name = "estado",
            dataType = "String",
            readOnly = true)
    private String estado;

    @Column(name = "telefone")
    @ApiModelProperty(
            value = "Company's phone number",
            name = "telefone",
            dataType = "String",
            readOnly = true)
    private String telefone;
}
