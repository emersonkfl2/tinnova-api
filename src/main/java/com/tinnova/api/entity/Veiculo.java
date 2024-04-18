package com.tinnova.api.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "veiculo", schema = "veiculos_schema")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String veiculo;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private boolean vendido;

    @Column
    private LocalDateTime created;

    @Column
    private LocalDateTime updated;

    public Long getId(){
        return id;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public Veiculo() {
    }

    public Veiculo(Long id, String veiculo, String marca, String cor, Integer ano,
                   String descricao, boolean vendido, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.veiculo = veiculo;
        this.marca = marca;
        this.cor = cor;
        this.ano = ano;
        this.descricao = descricao;
        this.vendido = vendido;
        this.created = created;
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo1 = (Veiculo) o;
        return vendido == veiculo1.vendido && Objects.equals(id, veiculo1.id) && Objects.equals(veiculo, veiculo1.veiculo) && Objects.equals(marca, veiculo1.marca) && Objects.equals(cor, veiculo1.cor) && Objects.equals(ano, veiculo1.ano) && Objects.equals(descricao, veiculo1.descricao) && Objects.equals(created, veiculo1.created) && Objects.equals(updated, veiculo1.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, veiculo, marca, cor, ano, descricao, vendido, created, updated);
    }
}
