package com.dio.controledepontoeacesso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class TipoData {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 400)
    private String descricao;

    @JsonIgnore
    @Getter
    @Setter
    @OneToMany(mappedBy = "tipoData", cascade = CascadeType.ALL)
    private List<Calendario> calendario;

    public TipoData(long id, String descricao, List<Calendario> calendario) {
        this.id = id;
        this.setDescricao(descricao);
        this.calendario = calendario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.trim();
    }
}
