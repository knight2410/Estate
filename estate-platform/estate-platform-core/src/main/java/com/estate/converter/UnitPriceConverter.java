package com.estate.converter;

import com.estate.dto.UnitPriceDto;
import com.estate.entity.UnitPriceEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitPriceConverter {
    @Autowired
    private ModelMapper modelMapper ;

    public UnitPriceDto convertToDto(UnitPriceEntity entity) {
        UnitPriceDto result = modelMapper.map(entity, UnitPriceDto.class);
        return result;
    }

    public UnitPriceEntity convertToEntity(UnitPriceDto dto) {
        UnitPriceEntity result = modelMapper.map(dto, UnitPriceEntity.class);
        return result;
    }
}
