package com.tinnova.api.controller;

import com.tinnova.api.dto.VeiculoDto;
import com.tinnova.api.entity.Veiculo;
import com.tinnova.api.mapper.VeiculoMapper;
import com.tinnova.api.repository.VeiculoRepository;
import com.tinnova.api.service.VeiculoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VeiculoControllerTest {

    @Mock
    private VeiculoService service;

    @Mock
    private VeiculoMapper mapper;

    @Mock
    private VeiculoRepository repository;

    @InjectMocks
    private VeiculoController controller;

    Veiculo veiculo1 = new Veiculo(1L, "Fiesta", "Ford", "Azul", 2015,
            "Descricao 1", false, LocalDateTime.now(), LocalDateTime.now()
    );
    Veiculo veiculo2 = new Veiculo(2L, "Civic", "Honda", "Preto", 2018,
            "Descricao 2", true, LocalDateTime.now(), LocalDateTime.now()
    );

    VeiculoDto dto1 = new VeiculoDto("Fiesta", "Ford", "Azul", 2015,
            "Descricao 1", false, LocalDateTime.now(), LocalDateTime.now()
    );
    VeiculoDto dto2 = new VeiculoDto("Civic", "Honda", "Preto", 2018,
            "Descricao 2", true, LocalDateTime.now(), LocalDateTime.now()
    );

    @Test
    public void findAll_shouldReturnAllVeiculos() {
        List<Veiculo> mockVeiculos = Arrays.asList(veiculo1, veiculo2);

        when(service.findAll()).thenReturn(mockVeiculos);

        ResponseEntity<List<VeiculoDto>> response = controller.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockVeiculos.size(), response.getBody().size());
    }

    @Test
    public void findVeiculos_ShouldReturnVeiculosBasedOnSearchParams() {
        List<Veiculo> mockVeiculos = Arrays.asList(veiculo1, veiculo2);

        when(service.findByMarcaOrAnoOrCor(anyString(), any(), anyString())).thenReturn(mockVeiculos);

        ResponseEntity<List<VeiculoDto>> response = controller.findVeiculos("Fiesta", 2015, "Azul");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void findById_ShouldReturnVeiculoDto() {
        long veiculoId = 1L;
        when(service.findById(veiculoId)).thenReturn(veiculo1);
        when(mapper.entityToDto(veiculo1)).thenReturn(dto1);

        ResponseEntity<VeiculoDto> response = controller.findById(veiculoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Fiesta", response.getBody().veiculo());
    }

    @Test
    public void saveVeiculo_ShouldReturnCreatedVeiculo() {
        when(mapper.dtoToEntity(dto1)).thenReturn(veiculo1);
        when(service.save(any(Veiculo.class))).thenReturn(veiculo1);
        when(mapper.entityToDto(veiculo1)).thenReturn(dto1);

        ResponseEntity<VeiculoDto> response = controller.saveVeiculo(dto1);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(dto1, response.getBody());
    }

    @Test
    public void updateVeiculo_ShouldReturnUpdatedVeiculoDto() {
        Veiculo existingVeiculo = veiculo1;
        VeiculoDto updateDto = dto1;

        when(service.update(eq(existingVeiculo.getId()), any(VeiculoDto.class))).thenReturn(existingVeiculo);
        when(mapper.entityToDto(existingVeiculo)).thenReturn(updateDto);

        ResponseEntity<VeiculoDto> response = controller.updateVeiculo(existingVeiculo.getId(), updateDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(updateDto, response.getBody());
    }

    @Test
    public void deleteById_ShouldReturnNoContentStatus() {
        long veiculoId = 1L;
        doNothing().when(service).delete(veiculoId);

        ResponseEntity<Void> response = controller.deleteById(veiculoId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}
