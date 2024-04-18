package com.tinnova.api.service;

import com.tinnova.api.dto.VeiculoDto;
import com.tinnova.api.entity.Veiculo;
import com.tinnova.api.exception.NotFoundException;
import com.tinnova.api.repository.VeiculoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class VeiculoService{

    private final VeiculoRepository repository;

    public VeiculoService(VeiculoRepository repository) {
        this.repository = repository;
    }

    public List<Veiculo> findAll() {
        return repository.findAll();
    }

    public List<Veiculo> findByMarcaOrAnoOrCor(String marca, Integer ano, String cor) {
        return repository.findByMarcaOrAnoOrCor(marca, ano, cor);
    }

    public Veiculo findById(Long id) {
        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Veiculo nao encontrado!"));

        return veiculo;
    }

    public Veiculo save(Veiculo veiculo){
        veiculo.setCreated(LocalDateTime.now());
        return repository.save(veiculo);
    }

    public Veiculo update(Long id, VeiculoDto veiculoUpdate){
        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Veiculo nao encontrado!"));

        veiculo.setVeiculo(veiculoUpdate.veiculo());
        veiculo.setMarca(veiculoUpdate.marca());
        veiculo.setCor(veiculoUpdate.cor());
        veiculo.setAno(veiculoUpdate.ano());
        veiculo.setDescricao(veiculoUpdate.descricao());
        veiculo.setVendido(veiculoUpdate.vendido());
        veiculo.setUpdated(LocalDateTime.now());

        Veiculo veiculoSalvo = repository.save(veiculo);

        return veiculoSalvo;
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Veiculo nao encontrado!"));
        repository.deleteById(id);
    }

    public Veiculo updatePatchVeiculo(Long id, Map<String, Object> updates) {
        Veiculo veiculo = repository.findById(id)
                .orElseThrow(() ->new NotFoundException("Veiculo nao encontrado!"));

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Veiculo.class, key);
            if(field != null){
                field.setAccessible(true);
                ReflectionUtils.setField(field, veiculo, value);
            }
        });
        veiculo.setUpdated(LocalDateTime.now());
        return repository.save(veiculo);
    }
}
