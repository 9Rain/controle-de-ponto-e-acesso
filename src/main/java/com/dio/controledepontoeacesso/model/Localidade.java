package com.dio.controledepontoeacesso.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;


@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Audited
public class Localidade {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "NIVEL_ACESSO_ID", referencedColumnName = "ID")
    private NivelAcesso nivelAcesso;

    @NotNull
    @NotBlank
    private String descricao;

    @Transient
    private long nivelAcessoId;

    public Localidade(long id, NivelAcesso nivelAcesso, String descricao, long nivelAcessoId) {
        this.id = id;
        this.nivelAcesso = nivelAcesso;
        this.setDescricao(descricao);
        this.setNivelAcessoId(nivelAcesso.getId());
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.trim();
    }


    public long getNivelAcessoId() {
        var nivelAcesso = Optional.ofNullable(this.getNivelAcesso());
        if(nivelAcesso.isPresent()) this.setNivelAcessoId(nivelAcesso.get().getId());

        return nivelAcessoId;
    }

    public void setNivelAcessoId(long nivelAcessoId) {
        this.nivelAcessoId = nivelAcessoId;
    }
}
