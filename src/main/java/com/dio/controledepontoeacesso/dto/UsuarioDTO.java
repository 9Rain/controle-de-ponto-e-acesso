package com.dio.controledepontoeacesso.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class UsuarioDTO {
    @Getter
    @Setter
    private Long id;

    @NotBlank
    @NotNull
    private String nome;

    @Getter
    @Setter
    @NotNull
    private BigDecimal tolerancia;

    @Getter
    @Setter
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime inicioJornada;

    @Getter
    @Setter
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime finalJornada;

    public UsuarioDTO(Long id, String nome, BigDecimal tolerancia, LocalDateTime inicioJornada, LocalDateTime finalJornada) {
        this.id = id;
        this.setNome(nome);
        this.tolerancia = tolerancia;
        this.inicioJornada = inicioJornada;
        this.finalJornada = finalJornada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }
}
