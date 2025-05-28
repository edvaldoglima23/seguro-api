package com.example.seguro.config;

import com.example.seguro.model.Apolice;
import com.example.seguro.model.TipoSeguro;
import com.example.seguro.repository.ApoliceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ApoliceRepository apoliceRepository;

    @Override
    public void run(String... args) {
        // Criar alguns dados de exemplo para demonstração
        if (apoliceRepository.count() == 0) {
            criarDadosExemplo();
        }
    }

    private void criarDadosExemplo() {
        // Apólice 1 - Auto
        Apolice apolice1 = new Apolice();
        apolice1.setNumeroApolice("AP001-2023");
        apolice1.setTipoSeguro(TipoSeguro.AUTO);
        apolice1.setNomeSegurado("João da Silva");
        apolice1.setDocumentoSegurado("123.456.789-00");
        apolice1.setDataInicioVigencia(LocalDate.now().minusMonths(3));
        apolice1.setDataFimVigencia(LocalDate.now().plusMonths(9));
        apolice1.setValorPremio(new BigDecimal("1500.00"));
        apolice1.setValorCobertura(new BigDecimal("50000.00"));
        apolice1.setAtivo(true);
        apoliceRepository.save(apolice1);
        
        // Apólice 2 - Vida
        Apolice apolice2 = new Apolice();
        apolice2.setNumeroApolice("AP002-2023");
        apolice2.setTipoSeguro(TipoSeguro.VIDA);
        apolice2.setNomeSegurado("Maria Oliveira");
        apolice2.setDocumentoSegurado("987.654.321-00");
        apolice2.setDataInicioVigencia(LocalDate.now().minusMonths(6));
        apolice2.setDataFimVigencia(LocalDate.now().plusMonths(6));
        apolice2.setValorPremio(new BigDecimal("800.00"));
        apolice2.setValorCobertura(new BigDecimal("150000.00"));
        apolice2.setAtivo(true);
        apoliceRepository.save(apolice2);
        
        // Apólice 3 - Residencial
        Apolice apolice3 = new Apolice();
        apolice3.setNumeroApolice("AP003-2023");
        apolice3.setTipoSeguro(TipoSeguro.RESIDENCIAL);
        apolice3.setNomeSegurado("Carlos Santos");
        apolice3.setDocumentoSegurado("456.789.123-00");
        apolice3.setDataInicioVigencia(LocalDate.now().minusMonths(1));
        apolice3.setDataFimVigencia(LocalDate.now().plusMonths(11));
        apolice3.setValorPremio(new BigDecimal("500.00"));
        apolice3.setValorCobertura(new BigDecimal("200000.00"));
        apolice3.setAtivo(true);
        apoliceRepository.save(apolice3);
        
        // Apólice 4 - Empresarial
        Apolice apolice4 = new Apolice();
        apolice4.setNumeroApolice("AP004-2023");
        apolice4.setTipoSeguro(TipoSeguro.EMPRESARIAL);
        apolice4.setNomeSegurado("Empresa XYZ Ltda");
        apolice4.setDocumentoSegurado("12.345.678/0001-90");
        apolice4.setDataInicioVigencia(LocalDate.now().minusMonths(8));
        apolice4.setDataFimVigencia(LocalDate.now().plusMonths(4));
        apolice4.setValorPremio(new BigDecimal("5000.00"));
        apolice4.setValorCobertura(new BigDecimal("1000000.00"));
        apolice4.setAtivo(true);
        apoliceRepository.save(apolice4);
        
        // Apólice 5 - Saúde
        Apolice apolice5 = new Apolice();
        apolice5.setNumeroApolice("AP005-2023");
        apolice5.setTipoSeguro(TipoSeguro.SAUDE);
        apolice5.setNomeSegurado("Ana Pereira");
        apolice5.setDocumentoSegurado("321.654.987-00");
        apolice5.setDataInicioVigencia(LocalDate.now().minusMonths(2));
        apolice5.setDataFimVigencia(LocalDate.now().plusMonths(10));
        apolice5.setValorPremio(new BigDecimal("300.00"));
        apolice5.setValorCobertura(new BigDecimal("50000.00"));
        apolice5.setAtivo(true);
        apoliceRepository.save(apolice5);
        
        // Apólice 6 - Viagem (A vencer em breve)
        Apolice apolice6 = new Apolice();
        apolice6.setNumeroApolice("AP006-2023");
        apolice6.setTipoSeguro(TipoSeguro.VIAGEM);
        apolice6.setNomeSegurado("Roberto Almeida");
        apolice6.setDocumentoSegurado("111.222.333-44");
        apolice6.setDataInicioVigencia(LocalDate.now().minusMonths(11));
        apolice6.setDataFimVigencia(LocalDate.now().plusDays(20)); // Vai vencer em breve
        apolice6.setValorPremio(new BigDecimal("200.00"));
        apolice6.setValorCobertura(new BigDecimal("30000.00"));
        apolice6.setAtivo(true);
        apoliceRepository.save(apolice6);
        
        // Apólice 7 - Auto (Cancelada)
        Apolice apolice7 = new Apolice();
        apolice7.setNumeroApolice("AP007-2023");
        apolice7.setTipoSeguro(TipoSeguro.AUTO);
        apolice7.setNomeSegurado("Fernando Costa");
        apolice7.setDocumentoSegurado("555.666.777-88");
        apolice7.setDataInicioVigencia(LocalDate.now().minusMonths(5));
        apolice7.setDataFimVigencia(LocalDate.now().plusMonths(7));
        apolice7.setValorPremio(new BigDecimal("1200.00"));
        apolice7.setValorCobertura(new BigDecimal("45000.00"));
        apolice7.setAtivo(false); // Cancelada
        apoliceRepository.save(apolice7);
    }
} 