package com.example.seguro.dto;

import com.example.seguro.model.TipoSeguro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApoliceDTO {

    private Long id;

    @NotBlank(message = "O número da apólice é obrigatório")
    private String numeroApolice;

    @NotNull(message = "O tipo de seguro é obrigatório")
    private TipoSeguro tipoSeguro;

    @NotBlank(message = "O nome do segurado é obrigatório")
    private String nomeSegurado;

    @NotBlank(message = "O CPF/CNPJ do segurado é obrigatório")
    private String documentoSegurado;

    @NotNull(message = "A data de início da vigência é obrigatória")
    private LocalDate dataInicioVigencia;

    @NotNull(message = "A data de fim da vigência é obrigatória")
    private LocalDate dataFimVigencia;

    @NotNull(message = "O valor do prêmio é obrigatório")
    @Positive(message = "O valor do prêmio deve ser positivo")
    private BigDecimal valorPremio;

    @NotNull(message = "O valor da cobertura é obrigatório")
    @Positive(message = "O valor da cobertura deve ser positivo")
    private BigDecimal valorCobertura;

    private boolean ativo = true;
} 