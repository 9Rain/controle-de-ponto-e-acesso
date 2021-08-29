package com.dio.controledepontoeacesso.dto;

import com.dio.controledepontoeacesso.model.CategoriaUsuario;
import com.dio.controledepontoeacesso.model.Empresa;
import com.dio.controledepontoeacesso.model.JornadaTrabalho;
import com.dio.controledepontoeacesso.model.NivelAcesso;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(
            value = "User's id",
            name = "id",
            dataType = "Long",
            readOnly = true)
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

    @Getter
    @Setter
    @NotNull
    private JornadaTrabalho jornadaTrabalho;

    @Getter
    @Setter
    @NotNull
    private CategoriaUsuario categoriaUsuario;

    @Getter
    @Setter
    @NotNull
    private Empresa empresa;

    @Getter
    @Setter
    @NotNull
    private NivelAcesso nivelAcesso;

    public UsuarioDTO(Long id, String nome, BigDecimal tolerancia, LocalDateTime inicioJornada,
                      LocalDateTime finalJornada, JornadaTrabalho jornadaTrabalho, CategoriaUsuario categoriaUsuario,
                      Empresa empresa, NivelAcesso nivelAcesso) {
        this.id = id;
        this.setNome(nome);
        this.tolerancia = tolerancia;
        this.inicioJornada = inicioJornada;
        this.finalJornada = finalJornada;
        this.jornadaTrabalho = jornadaTrabalho;
        this.categoriaUsuario = categoriaUsuario;
        this.empresa = empresa;
        this.nivelAcesso = nivelAcesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }
}
