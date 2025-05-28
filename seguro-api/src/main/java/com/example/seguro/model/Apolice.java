package com.example.seguro.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "APOLICES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Apolice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "apolice_seq")
    @SequenceGenerator(name = "apolice_seq", sequenceName = "APOLICE_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank(message = "O número da apólice é obrigatório")
    @Column(name = "NUMERO_APOLICE", unique = true, nullable = false)
    private String numeroApolice;

    @NotNull(message = "O tipo de seguro é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_SEGURO", nullable = false)
    private TipoSeguro tipoSeguro;

    @NotBlank(message = "O nome do segurado é obrigatório")
    @Column(name = "NOME_SEGURADO", nullable = false)
    private String nomeSegurado;

    @NotBlank(message = "O CPF/CNPJ do segurado é obrigatório")
    @Column(name = "DOCUMENTO_SEGURADO", nullable = false)
    private String documentoSegurado;

    @NotNull(message = "A data de início da vigência é obrigatória")
    @Column(name = "DATA_INICIO_VIGENCIA", nullable = false)
    private LocalDate dataInicioVigencia;

    @NotNull(message = "A data de fim da vigência é obrigatória")
    @Column(name = "DATA_FIM_VIGENCIA", nullable = false)
    private LocalDate dataFimVigencia;

    @NotNull(message = "O valor do prêmio é obrigatório")
    @Positive(message = "O valor do prêmio deve ser positivo")
    @Column(name = "VALOR_PREMIO", nullable = false)
    private BigDecimal valorPremio;

    @NotNull(message = "O valor da cobertura é obrigatório")
    @Positive(message = "O valor da cobertura deve ser positivo")
    @Column(name = "VALOR_COBERTURA", nullable = false)
    private BigDecimal valorCobertura;

    @Column(name = "ATIVO")
    private boolean ativo = true;

    @CreatedDate
    @Column(name = "DATA_CRIACAO", updatable = false)
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @Column(name = "DATA_ATUALIZACAO")
    private LocalDateTime dataAtualizacao;
} 