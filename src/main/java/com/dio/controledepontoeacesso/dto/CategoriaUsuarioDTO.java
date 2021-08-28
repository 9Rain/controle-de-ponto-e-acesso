package com.dio.controledepontoeacesso.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class CategoriaUsuarioDTO {
    @Getter
    @Setter
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 400)
    private String descricao;

    public CategoriaUsuarioDTO(Long id, String descricao) {
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
