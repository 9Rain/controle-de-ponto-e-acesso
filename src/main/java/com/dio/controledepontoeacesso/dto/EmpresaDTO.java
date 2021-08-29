package com.dio.controledepontoeacesso.dto;

import com.dio.controledepontoeacesso.annotation.UF;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class EmpresaDTO {
    @Getter
    @Setter
    @ApiModelProperty(
            value = "Company's id",
            name = "id",
            dataType = "Long",
            readOnly = true)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 400)
    private String descricao;

    @NotNull
    @CNPJ
    private String cnpj;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String endereco;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String bairro;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String cidade;

    @NotNull
    @NotBlank
    @UF
    private String estado;

    @NotNull
    @NotBlank
    @Size(min = 14, max = 15)
    // @Pattern(regexp = "^([(][0-9]{2}[)])[\\t\\n\\x0b\\r\\f]([9]{1})?([0-9]{4})[-]([0-9]{4})$")
    private String telefone;

    public EmpresaDTO(Long id, String descricao, String cnpj, String endereco, String bairro, String cidade, String estado, String telefone) {
        this.id = id;
        this.setDescricao(descricao);
        this.setCnpj(cnpj);
        this.setEndereco(endereco);
        this.setBairro(bairro);
        this.setCidade(cidade);
        this.setEstado(estado);
        this.setTelefone(telefone);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.trim();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj.trim();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco.trim();
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro.trim();
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade.trim();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado.trim();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone.trim();
    }
}
