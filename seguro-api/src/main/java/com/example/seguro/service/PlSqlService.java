package com.example.seguro.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlSqlService {

    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall calcularRiscoApolice;
    private SimpleJdbcCall processarRenovacao;

    @PostConstruct
    public void init() {
        // Configurar chamadas para procedures PL/SQL
        calcularRiscoApolice = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_APOLICE")
                .withProcedureName("CALCULAR_RISCO_APOLICE")
                .withoutProcedureColumnMetaDataAccess();
                
        processarRenovacao = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_APOLICE")
                .withProcedureName("PROCESSAR_RENOVACAO")
                .withoutProcedureColumnMetaDataAccess();
    }

    /**
     * Calcula o risco da apólice utilizando uma procedure PL/SQL
     * 
     * @param numeroApolice o número da apólice
     * @param tipoSeguro o tipo de seguro
     * @return mapa com o resultado do cálculo
     */
    public Map<String, Object> calcularRiscoApolice(String numeroApolice, String tipoSeguro) {
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_numero_apolice", numeroApolice);
        inParams.put("p_tipo_seguro", tipoSeguro);
        
        SqlParameterSource parameterSource = new MapSqlParameterSource(inParams);
        return calcularRiscoApolice.execute(parameterSource);
    }
    
    /**
     * Processa a renovação de uma apólice utilizando uma procedure PL/SQL
     * 
     * @param numeroApolice o número da apólice
     * @param valorNovoCobertura o novo valor de cobertura
     * @param valorNovoPremio o novo valor do prêmio
     * @return mapa com o resultado da renovação
     */
    public Map<String, Object> processarRenovacao(String numeroApolice, BigDecimal valorNovoCobertura, BigDecimal valorNovoPremio) {
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_numero_apolice", numeroApolice);
        inParams.put("p_valor_novo_cobertura", valorNovoCobertura);
        inParams.put("p_valor_novo_premio", valorNovoPremio);
        
        SqlParameterSource parameterSource = new MapSqlParameterSource(inParams);
        return processarRenovacao.execute(parameterSource);
    }
    
    /**
     * Exemplo de script PL/SQL que seria executado no banco de dados Oracle:
     * 
     * CREATE OR REPLACE PACKAGE PKG_APOLICE AS
     *     PROCEDURE CALCULAR_RISCO_APOLICE(
     *         p_numero_apolice IN VARCHAR2,
     *         p_tipo_seguro IN VARCHAR2,
     *         p_risco OUT NUMBER,
     *         p_descricao_risco OUT VARCHAR2,
     *         p_status OUT VARCHAR2
     *     );
     *     
     *     PROCEDURE PROCESSAR_RENOVACAO(
     *         p_numero_apolice IN VARCHAR2,
     *         p_valor_novo_cobertura IN NUMBER,
     *         p_valor_novo_premio IN NUMBER,
     *         p_numero_nova_apolice OUT VARCHAR2,
     *         p_status OUT VARCHAR2
     *     );
     * END PKG_APOLICE;
     * /
     * 
     * CREATE OR REPLACE PACKAGE BODY PKG_APOLICE AS
     *     PROCEDURE CALCULAR_RISCO_APOLICE(
     *         p_numero_apolice IN VARCHAR2,
     *         p_tipo_seguro IN VARCHAR2,
     *         p_risco OUT NUMBER,
     *         p_descricao_risco OUT VARCHAR2,
     *         p_status OUT VARCHAR2
     *     ) IS
     *         v_count NUMBER;
     *     BEGIN
     *         SELECT COUNT(1) INTO v_count 
     *         FROM APOLICES 
     *         WHERE NUMERO_APOLICE = p_numero_apolice;
     *         
     *         IF v_count = 0 THEN
     *             p_status := 'ERRO';
     *             p_descricao_risco := 'Apólice não encontrada';
     *             p_risco := NULL;
     *             RETURN;
     *         END IF;
     *         
     *         -- Aqui seria implementada a lógica de cálculo de risco baseada no tipo de seguro
     *         IF p_tipo_seguro = 'AUTO' THEN
     *             p_risco := 0.8;
     *             p_descricao_risco := 'Risco Alto';
     *         ELSIF p_tipo_seguro = 'VIDA' THEN
     *             p_risco := 0.5;
     *             p_descricao_risco := 'Risco Médio';
     *         ELSE
     *             p_risco := 0.2;
     *             p_descricao_risco := 'Risco Baixo';
     *         END IF;
     *         
     *         p_status := 'SUCESSO';
     *     EXCEPTION
     *         WHEN OTHERS THEN
     *             p_status := 'ERRO';
     *             p_descricao_risco := 'Erro ao calcular risco: ' || SQLERRM;
     *             p_risco := NULL;
     *     END CALCULAR_RISCO_APOLICE;
     *     
     *     PROCEDURE PROCESSAR_RENOVACAO(
     *         p_numero_apolice IN VARCHAR2,
     *         p_valor_novo_cobertura IN NUMBER,
     *         p_valor_novo_premio IN NUMBER,
     *         p_numero_nova_apolice OUT VARCHAR2,
     *         p_status OUT VARCHAR2
     *     ) IS
     *         v_tipo_seguro VARCHAR2(20);
     *         v_nome_segurado VARCHAR2(100);
     *         v_documento_segurado VARCHAR2(20);
     *         v_data_fim_vigencia DATE;
     *     BEGIN
     *         -- Buscar dados da apólice original
     *         BEGIN
     *             SELECT TIPO_SEGURO, NOME_SEGURADO, DOCUMENTO_SEGURADO, DATA_FIM_VIGENCIA
     *             INTO v_tipo_seguro, v_nome_segurado, v_documento_segurado, v_data_fim_vigencia
     *             FROM APOLICES
     *             WHERE NUMERO_APOLICE = p_numero_apolice
     *             AND ATIVO = 1;
     *         EXCEPTION
     *             WHEN NO_DATA_FOUND THEN
     *                 p_status := 'ERRO';
     *                 p_numero_nova_apolice := NULL;
     *                 RETURN;
     *         END;
     *         
     *         -- Gerar novo número de apólice (exemplo simples)
     *         p_numero_nova_apolice := 'REN-' || p_numero_apolice;
     *         
     *         -- Inserir nova apólice renovada
     *         INSERT INTO APOLICES (
     *             NUMERO_APOLICE, TIPO_SEGURO, NOME_SEGURADO, DOCUMENTO_SEGURADO, 
     *             DATA_INICIO_VIGENCIA, DATA_FIM_VIGENCIA, VALOR_PREMIO, VALOR_COBERTURA, ATIVO
     *         ) VALUES (
     *             p_numero_nova_apolice, v_tipo_seguro, v_nome_segurado, v_documento_segurado,
     *             v_data_fim_vigencia + 1, ADD_MONTHS(v_data_fim_vigencia, 12), 
     *             p_valor_novo_premio, p_valor_novo_cobertura, 1
     *         );
     *         
     *         p_status := 'SUCESSO';
     *     EXCEPTION
     *         WHEN OTHERS THEN
     *             p_status := 'ERRO: ' || SQLERRM;
     *             p_numero_nova_apolice := NULL;
     *     END PROCESSAR_RENOVACAO;
     * END PKG_APOLICE;
     * /
     */
} 