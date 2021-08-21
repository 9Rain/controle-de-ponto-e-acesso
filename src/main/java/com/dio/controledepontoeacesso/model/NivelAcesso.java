package com.dio.controledepontoeacesso.model;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Audited
public class NivelAcesso {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 400)
    private String descricao;

    @Getter
    @Setter
    @OneToMany(mappedBy = "nivelAcesso", cascade = CascadeType.ALL)
    private List<Localidade> localidades;

    public NivelAcesso(long id, String descricao, List<Localidade> localidades) {
        this.id = id;
        this.setDescricao(descricao);
        this.localidades = localidades;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.trim();
    }
}
