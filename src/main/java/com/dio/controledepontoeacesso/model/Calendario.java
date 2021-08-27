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
public class Calendario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private LocalDateTime dataEspecial;

    @ManyToOne(optional = false)
    private TipoData tipoData;
}
