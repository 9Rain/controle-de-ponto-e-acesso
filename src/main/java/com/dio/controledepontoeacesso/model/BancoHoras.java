package com.dio.controledepontoeacesso.model;

import lombok.*;

import javax.persistence.*;
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
    @AllArgsConstructor
    @NoArgsConstructor
    public class BancoHorasId implements Serializable {
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long idBancoHoras;

        private long idMovimentacao;

        private long idUsuario;
    }
    @EmbeddedId
    private BancoHorasId bancoHorasId;

    private LocalDateTime dataTrabalhada;

    private BigDecimal quantidadeHoras;
    private BigDecimal saldoHoras;
}
