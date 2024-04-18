package com.tinnova.api.mapper;

import com.tinnova.api.dto.VeiculoDto;
import com.tinnova.api.entity.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {

    VeiculoDto entityToDto(Veiculo veiculo);

    Veiculo dtoToEntity(VeiculoDto dto);
}
