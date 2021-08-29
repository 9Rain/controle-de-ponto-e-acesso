package com.dio.controledepontoeacesso.dto;

import com.dio.controledepontoeacesso.model.NivelAcesso;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class LocalidadeDTO {
    @Getter
    @Setter
    @ApiModelProperty(
            value = "Local's id",
            name = "id",
            dataType = "Long",
            readOnly = true)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 400)
    private String descricao;

    @NotNull
    @Getter
    @Setter
    private NivelAcesso nivelAcesso;

    public LocalidadeDTO(long id, String descricao, NivelAcesso nivelAcesso) {
        this.id = id;
        this.setDescricao(descricao);
        this.nivelAcesso = nivelAcesso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.trim();
    }
}
