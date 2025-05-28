package com.example.seguro.repository;

import com.example.seguro.model.Apolice;
import com.example.seguro.model.TipoSeguro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApoliceRepository extends JpaRepository<Apolice, Long> {

    Optional<Apolice> findByNumeroApolice(String numeroApolice);

    List<Apolice> findByTipoSeguro(TipoSeguro tipoSeguro);

    List<Apolice> findByNomeSeguradoContainingIgnoreCase(String nomeSegurado);

    List<Apolice> findByDocumentoSegurado(String documentoSegurado);

    @Query("SELECT a FROM Apolice a WHERE a.dataInicioVigencia <= :data AND a.dataFimVigencia >= :data AND a.ativo = true")
    List<Apolice> findApolicesVigentesNaData(@Param("data") LocalDate data);

    @Query(value = "SELECT * FROM APOLICES a WHERE a.ATIVO = 1 AND " +
            "TO_CHAR(a.DATA_FIM_VIGENCIA, 'YYYY-MM-DD') BETWEEN TO_CHAR(SYSDATE, 'YYYY-MM-DD') AND " +
            "TO_CHAR(SYSDATE + 30, 'YYYY-MM-DD')", nativeQuery = true)
    List<Apolice> findApolicesAVencer30Dias();
} 