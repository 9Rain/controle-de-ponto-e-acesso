package com.dio.controledepontoeacesso.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
public class BancoHoras {
    @EqualsAndHashCode
    @Embeddable
    @Getter
    @Setter
    public class BancoHorasId implements Serializable {
        @Getter
        @Setter
        @NotNull
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long idBancoHoras;

        @Getter
        @Setter
        @NotNull
        private long idMovimentacao;

        @Getter
        @Setter
        @NotNull
        private long idUsuario;
    }
    @EmbeddedId
    private BancoHorasId bancoHorasId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataTrabalhada;

    private BigDecimal quantidadeHoras;
    private BigDecimal saldoHoras;
}
