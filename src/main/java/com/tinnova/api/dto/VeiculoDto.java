package com.tinnova.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tinnova.api.utils.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public record VeiculoDto(
        String veiculo,
        String marca,
        String cor,
        Integer ano,
        String descricao,
        boolean vendido,
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime created,
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime updated
) {
        @Override
        public String veiculo() {
                return veiculo;
        }

        @Override
        public String marca() {
                return marca;
        }

        @Override
        public String cor() {
                return cor;
        }

        @Override
        public Integer ano() {
                return ano;
        }

        @Override
        public String descricao() {
                return descricao;
        }

        @Override
        public boolean vendido() {
                return vendido;
        }

        @Override
        public LocalDateTime created() {
                return created;
        }

        @Override
        public LocalDateTime updated() {
                return updated;
        }
}
