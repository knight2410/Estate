package com.estate.converter;

import com.estate.dto.ContractDto;
import com.estate.entity.ContractEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractConverter {
    @Autowired
    private ModelMapper modelMapper ;

    public ContractDto convertToDto(ContractEntity entity) {
        ContractDto result = modelMapper.map(entity, ContractDto.class);
        return result;
    }

    public ContractEntity convertToEntity(ContractDto dto) {
        ContractEntity result = modelMapper.map(dto, ContractEntity.class);
        return result;
    }
}
