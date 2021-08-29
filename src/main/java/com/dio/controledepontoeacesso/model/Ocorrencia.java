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
@Table(name = "tb_ocorrencia")
public class Ocorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;
}
