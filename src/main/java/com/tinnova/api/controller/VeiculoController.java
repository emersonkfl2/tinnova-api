package com.tinnova.api.controller;

import com.tinnova.api.dto.VeiculoDto;
import com.tinnova.api.entity.Veiculo;
import com.tinnova.api.mapper.VeiculoMapper;
import com.tinnova.api.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/veiculos", produces = MediaType.APPLICATION_JSON_VALUE)
public class VeiculoController {

    private final VeiculoService service;

    private final VeiculoMapper mapper;

    public VeiculoController(VeiculoService service, VeiculoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDto>> findAll() {
        List<Veiculo> veiculos = service.findAll();

        return new ResponseEntity<>(veiculos.stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<VeiculoDto>> findVeiculos(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) String cor) {

        List<Veiculo> veiculos = service.findByMarcaOrAnoOrCor(marca, ano, cor);
        List<VeiculoDto> veiculosDto = veiculos.stream()
                .map(mapper::entityToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(veiculosDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDto> findById(@PathVariable Long id) {
        Veiculo veiculo = service.findById(id);

        return new ResponseEntity<>(mapper.entityToDto(veiculo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VeiculoDto> saveVeiculo(@Valid @RequestBody VeiculoDto dto) {
        try {
            Veiculo veiculo = service.save(mapper.dtoToEntity(dto));
            return new ResponseEntity<>(mapper.entityToDto(veiculo), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDto> updateVeiculo(@PathVariable Long id, @Valid @RequestBody VeiculoDto dto) {
        Veiculo veiculo = service.update(id, dto);
        return new ResponseEntity<>(mapper.entityToDto(veiculo), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VeiculoDto> updatePatchVeiculo(@PathVariable Long id,
                                                         @RequestBody Map<String, Object> updates) {
        Veiculo updatedVeiculo = service.updatePatchVeiculo(id, updates);

        return new ResponseEntity<>(mapper.entityToDto(updatedVeiculo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
