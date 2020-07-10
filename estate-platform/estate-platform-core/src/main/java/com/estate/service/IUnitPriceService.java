package com.estate.service;

import com.estate.dto.UnitPriceDto;

import java.util.List;

public interface IUnitPriceService {
    UnitPriceDto insert(UnitPriceDto unitPriceDto);

    List<UnitPriceDto> getAllUnitPriceByIdBuilding(Long idBuilding, UnitPriceDto dto);

    int getTotalItems(Long idBuilding);

    UnitPriceDto findLastUnitPrice(Long idBuilding);
}
