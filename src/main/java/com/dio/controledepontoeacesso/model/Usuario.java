package com.dio.controledepontoeacesso.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Audited
public class Usuario {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    private CategoriaUsuario categoriaUsuario;

    @NotNull
    private String nome;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    private Empresa empresa;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    private NivelAcesso nivelAcesso;

    @Getter
    @Setter
    @NotNull
    @ManyToOne
    private JornadaTrabalho jornadaTrabalho;

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

    private Long categoriaUsuarioId;
    private Long empresaId;
    private Long nivelAcessoId;
    private Long jornadaTrabalhoId;
}
