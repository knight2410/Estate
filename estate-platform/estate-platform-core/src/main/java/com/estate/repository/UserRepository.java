package com.estate.repository;

import com.estate.entity.BuildingEntity;
import com.estate.entity.RoleEntity;
import com.estate.entity.UserEntity;
import org.jboss.logging.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long>,UserRepositoryCustom {
    UserEntity findOneByUserName(String userName);
    Page<UserEntity> findByUserNameOrFullNameOrPhoneOrEmailContainingIgnoreCase(String searchValue1,String searchValue2,String searchValue3, String searchValue4, Pageable pageable);
    long countByUserNameOrFullNameOrPhoneOrEmailContainingIgnoreCase(String searchValue1,String searchValue2,String searchValue3, String searchValue4);
    long countByStatus(int status);
    boolean existsByUserNameOrEmail(String userName,String email);
    boolean existsByUserNameOrEmailAndIdIsNotIn(String userName, String email, long Id);
    List<UserEntity> findByBuildings_Id(long buildingId);
    boolean existsByIdAndBuildingsPrioritize_Id(long userId, long buildingId);
    boolean existsByIdAndBuildings_Id(long userId, long buildingId);
    boolean existsByIdAndCustomers_Id(long userId, long customerId);
    List<UserEntity> findByRoleList_id(long roleId);
    List<UserEntity> findByStatusAndRoleList_Code(Integer status, String role);
}
