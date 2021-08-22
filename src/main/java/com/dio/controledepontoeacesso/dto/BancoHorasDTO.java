package com.dio.controledepontoeacesso.dto;

import com.dio.controledepontoeacesso.model.BancoHoras;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class BancoHorasDTO {
    @Getter
    @Setter
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class BancoHorasId implements Serializable {
        private long idBancoHoras;

        @NotNull
        private long idMovimentacao;

        @NotNull
        private long idUsuario;
    }

    @NotNull
    @EmbeddedId
    private BancoHorasId bancoHorasId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataTrabalhada;

    @NotNull
    private BigDecimal quantidadeHoras;

    @NotNull
    private BigDecimal saldoHoras;
}
