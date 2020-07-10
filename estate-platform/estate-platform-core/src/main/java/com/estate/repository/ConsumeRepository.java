package com.estate.repository;

import com.estate.entity.ConsumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumeRepository extends JpaRepository<ConsumeEntity,Long> {
    List<ConsumeEntity> findByIdContract(long contractId);
}
