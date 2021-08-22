package com.dio.controledepontoeacesso.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class OcorrenciaDTO {
    @Getter
    @Setter
    private long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String nome;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 400)
    private String descricao;

    public OcorrenciaDTO(long id, String nome, String descricao) {
        this.id = id;
        this.setNome(nome);
        this.setDescricao(descricao);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.trim();
    }
}
