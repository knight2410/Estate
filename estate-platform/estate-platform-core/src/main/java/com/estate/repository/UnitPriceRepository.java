package com.estate.repository;

import com.estate.entity.UnitPriceEntity;
import com.estate.repository.custom.UnitPriceRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitPriceRepository extends JpaRepository<UnitPriceEntity,Long> {
    boolean existsByIdBuilding(Long idBuilding);
}
