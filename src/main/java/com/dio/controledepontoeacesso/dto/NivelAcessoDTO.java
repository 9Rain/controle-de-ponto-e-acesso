package com.dio.controledepontoeacesso.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NivelAcessoDTO {
    @Getter
    @Setter
    private long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 400)
    private String descricao;

    public NivelAcessoDTO(long id, String descricao) {
        this.id = id;
        this.setDescricao(descricao);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.trim();
    }
}
