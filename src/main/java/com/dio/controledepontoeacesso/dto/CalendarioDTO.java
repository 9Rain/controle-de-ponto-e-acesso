package com.dio.controledepontoeacesso.dto;

import com.dio.controledepontoeacesso.model.TipoData;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class CalendarioDTO {
    @Getter
    @Setter
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 400)
    private String descricao;

    @NotNull
    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataEspecial;

    @NotNull
    @Getter
    @Setter
    private TipoData tipoData;

    public CalendarioDTO(Long id, TipoData tipoData, String descricao, LocalDateTime dataEspecial) {
        this.id = id;
        this.setDescricao(descricao);
        this.dataEspecial = dataEspecial;
        this.tipoData = tipoData;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.trim();
    }
}
