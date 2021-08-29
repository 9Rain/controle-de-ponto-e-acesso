package com.dio.controledepontoeacesso.model;

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
    private long id;

    @Column(name = "descricao")
    private String descricao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "nivel_acesso_id")
    private NivelAcesso nivelAcesso;
}
