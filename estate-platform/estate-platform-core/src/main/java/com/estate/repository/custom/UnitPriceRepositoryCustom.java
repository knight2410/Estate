package com.estate.repository.custom;

import com.estate.entity.UnitPriceEntity;
import com.estate.paging.Pageable;

import java.math.BigInteger;
import java.util.List;

public interface UnitPriceRepositoryCustom {
    UnitPriceEntity findByIdBuildingAndEffectiveDate(Long idBuilding);

    List<UnitPriceEntity> findAllUnitPriceByBuildingId(Long idBuilding, Pageable pageable);

    BigInteger getTotalItems(Long idBuilding);

    UnitPriceEntity findLastUnitPrice(Long idBuilding);

}
