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
@Table(name = "tb_localidade")
public class Localidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(
            value = "Working day's id",
            name = "id",
            dataType = "Long",
            required = true)
    private long id;

    @Column(name = "descricao")
    @ApiModelProperty(
            value = "Local's description",
            name = "id",
            dataType = "String",
            readOnly = true)
    private String descricao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "nivel_acesso_id")
    @ApiModelProperty(
            name = "nivelAcesso",
            dataType = "NivelAcesso",
            required = true)
    private NivelAcesso nivelAcesso;
}
