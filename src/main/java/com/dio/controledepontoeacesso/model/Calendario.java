package com.dio.controledepontoeacesso.model;

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
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_especial")
    private LocalDateTime dataEspecial;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_data_id")
    private TipoData tipoData;
}
