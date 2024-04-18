package com.tinnova.api.repository;

import com.tinnova.api.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    @Query("SELECT v FROM Veiculo v WHERE " +
            "((:marca IS NULL AND :ano IS NULL AND :cor IS NULL) OR " +
            "(:marca IS NOT NULL AND v.marca = :marca) OR " +
            "(:ano IS NOT NULL AND v.ano = :ano) OR " +
            "(:cor IS NOT NULL AND v.cor = :cor))")
    List<Veiculo> findByMarcaOrAnoOrCor(
            @Param("marca") String marca,
            @Param("ano") Integer ano,
            @Param("cor") String cor);

}

