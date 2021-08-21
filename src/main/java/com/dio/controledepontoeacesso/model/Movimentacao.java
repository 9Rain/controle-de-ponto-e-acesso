package com.dio.controledepontoeacesso.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
public class Movimentacao {
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public class MovimentacaoId implements Serializable {
        @Getter
        @Setter
        @NotNull
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long idMovimento;

        @Getter
        @Setter
        @NotNull
        private long idUsuario;
    }
    @Getter
    @Setter
    @Id
    @EmbeddedId
    private MovimentacaoId movimentacaoId;

    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataEntrada;

    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataSaida;

    @Getter
    @Setter
    private BigDecimal periodo;

    @Getter
    @Setter
    @ManyToOne
    private Ocorrencia ocorrencia;

    @Getter
    @Setter
    @ManyToOne
    private Calendario calendario;

    @Transient
    private Long ocorrenciaId;

    @Transient
    private Long calendarioId;

    public Long getOcorrenciaId() {
        var ocorrencia = Optional.ofNullable(this.getOcorrencia());
        if(ocorrencia.isPresent()) this.setOcorrenciaId(ocorrencia.get().getId());

        return ocorrenciaId;
    }

    public void setOcorrenciaId(Long ocorrenciaId) {
        this.ocorrenciaId = ocorrenciaId;
    }

    public Long getCalendarioId() {
        var calendario = Optional.ofNullable(this.getCalendario());
        if(calendario.isPresent()) this.setCalendarioId(calendario.get().getId());

        return calendarioId;
    }

    public void setCalendarioId(Long calendarioId) {
        this.calendarioId = calendarioId;
    }
}
