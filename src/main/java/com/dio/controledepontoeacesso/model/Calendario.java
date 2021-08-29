package com.dio.controledepontoeacesso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Audited
@Table(name = "tb_calendario")
public class Calendario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(
            value = "Calendar's id",
            name = "id",
            dataType = "Long",
            required = true)
    private Long id;

    @Column(name = "descricao")
    @ApiModelProperty(
            value = "Calendar's description",
            name = "descricao",
            dataType = "String",
            readOnly = true)
    private String descricao;

    @Column(name = "data_especial")
    @ApiModelProperty(
            value = "Calendar's special date",
            name = "dataEspecial",
            dataType = "LocalDateTime",
            readOnly = true)
    private LocalDateTime dataEspecial;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_data_id")
    @JsonIgnore
    private TipoData tipoData;
}
