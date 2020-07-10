package com.estate.repository;

import com.estate.entity.BuildingEntity;
import com.estate.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity,Long>, BuildingRepositoryCustom {
    // lấy tất cả tòa nhà theo user
    Page<BuildingEntity> findByStaffs_id(Pageable pageable,long userId);
    long countByStaffs_id(long userId);
    //lấy tòa nhà ưu tiên theo user
    Page<BuildingEntity> findByStaffsPrioritize_id(Pageable pageable,long userId);
    long countByStaffsPrioritize_id(long userId);
    //lấy tòa nhà ưu tiên theo user
    List<BuildingEntity> findByStaffsPrioritize_id(long userId);
    /*
     * check buildingId and customerId exists
     */
  //  boolean existsByIdAndCustomers_Id(long buildingId, long customerId);

}

