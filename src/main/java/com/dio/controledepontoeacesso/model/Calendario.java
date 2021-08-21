package com.dio.controledepontoeacesso.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Audited
public class Calendario {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "TIPO_DATA_ID", referencedColumnName = "ID")
    private TipoData tipoData;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 400)
    private String descricao;

    @NotNull
    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataEspecial;

    @Transient
    private Long tipoDataId;

    public Calendario(Long id, TipoData tipoData, String descricao, LocalDateTime dataEspecial, Long tipoDataId) {
        this.id = id;
        this.tipoData = tipoData;
        this.setDescricao(descricao);
        this.dataEspecial = dataEspecial;
        this.setTipoDataId(tipoDataId);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.trim();
    }

    public Long getTipoDataId() {
        var tipoData = Optional.ofNullable(this.getTipoData());
        if(tipoData.isPresent()) this.setTipoDataId(tipoData.get().getId());

        return tipoDataId;
    }

    public void setTipoDataId(Long tipoDataId) {
        this.tipoDataId = tipoDataId;
    }
}
