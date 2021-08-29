package com.dio.controledepontoeacesso.dto;

import com.dio.controledepontoeacesso.model.Calendario;
import com.dio.controledepontoeacesso.model.Ocorrencia;
import com.dio.controledepontoeacesso.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class MovimentacaoDTO {
    private Long id;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataEntrada;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataSaida;

    @NotNull
    private BigDecimal periodo;

    @NotNull
    @Getter
    @Setter
    private Calendario calendario;

    @NotNull
    @Getter
    @Setter
    private Ocorrencia ocorrencia;

    @NotNull
    @Getter
    @Setter
    private Usuario usuario;
}
