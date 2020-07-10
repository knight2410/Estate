package com.estate.converter;

import com.estate.dto.DistrictDTO;
import com.estate.entity.DistrictEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistrictConverter {
    @Autowired
    private ModelMapper modelMapper ;

    public DistrictDTO convertToDto(DistrictEntity entity) {
        DistrictDTO result = modelMapper.map(entity, DistrictDTO.class);
        return result;
    }

    public DistrictEntity convertToEntity(DistrictDTO dto) {
        DistrictEntity result = modelMapper.map(dto, DistrictEntity.class);
        return result;
    }
}
