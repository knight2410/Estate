package com.estate.repository;

import com.estate.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
   List<RoleEntity> findOneByCode(String code);
}
