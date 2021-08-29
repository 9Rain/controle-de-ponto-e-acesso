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
@Table(name = "tb_tipo_data")
public class TipoData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(
            value = "Date type's id",
            name = "id",
            dataType = "Long",
            required = true)
    private Long id;

    @Column(name = "descricao")
    @ApiModelProperty(
            value = "Date type's description",
            name = "descricao",
            dataType = "String",
            readOnly = true)
    private String descricao;
}
