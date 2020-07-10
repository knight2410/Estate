package com.estate.repository;

import com.estate.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {
    DistrictEntity findOneByCode(String code);
    DistrictEntity findOneByName(String name);
}
