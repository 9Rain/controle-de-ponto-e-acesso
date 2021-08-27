package com.dio.controledepontoeacesso.dto;

import com.dio.controledepontoeacesso.model.Calendario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class TipoDataDTO {
    @Getter
    @Setter
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 400)
    private String descricao;

    public TipoDataDTO(Long id, String descricao) {
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
