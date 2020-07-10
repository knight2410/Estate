package com.estate.converter;

import com.estate.dto.CustomerServiceDTO;
import com.estate.entity.CustomerServiceEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceConverter {

    @Autowired
    private ModelMapper modelMapper ;

    public CustomerServiceDTO convertToDto(CustomerServiceEntity entity) {
        CustomerServiceDTO result = modelMapper.map(entity, CustomerServiceDTO.class);
        return result;
    }

    public CustomerServiceEntity convertToEntity(CustomerServiceDTO dto) {
        CustomerServiceEntity result = modelMapper.map(dto, CustomerServiceEntity.class);
        return result;
    }
}
