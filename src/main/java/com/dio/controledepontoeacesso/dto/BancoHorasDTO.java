package com.dio.controledepontoeacesso.dto;

import com.dio.controledepontoeacesso.model.Movimentacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class BancoHorasDTO {
    @ApiModelProperty(
            value = "Additional hour's id",
            name = "id",
            dataType = "Long",
            readOnly = true)
    private Long id;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataTrabalhada;

    @NotNull
    private BigDecimal quantidadeHoras;

    @NotNull
    private BigDecimal saldoHoras;

    @NotNull
    @Getter
    @Setter
    private Movimentacao movimentacao;
}
