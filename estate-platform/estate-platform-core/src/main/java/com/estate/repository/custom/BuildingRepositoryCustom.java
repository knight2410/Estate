package com.estate.repository.custom;

import com.estate.builder.BuildingBuilder;
import com.estate.dto.DetailContract;
import com.estate.entity.BuildingEntity;
import com.estate.entity.ContractEntity;
import com.estate.entity.TestEntity;
import com.estate.paging.Pageable;

import java.math.BigInteger;
import java.util.List;

public interface BuildingRepositoryCustom {
    List<BuildingEntity> findAll(BuildingBuilder buildingBuilder, Pageable pageable,boolean priority);
    BigInteger getTotalItems(BuildingBuilder buildingBuilder, boolean priority);
    List<TestEntity> test();
    BuildingEntity existBuildingInContract(Long idBuilding);

}
