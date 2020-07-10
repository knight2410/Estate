package com.estate.repository;

import com.estate.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
    boolean existsByIdCustomer(Long idCustomer);
    boolean existsByIdBuilding(Long idBuilding);
}
