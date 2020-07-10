package com.estate.repository;

import com.estate.entity.CustomerEntity;
import com.estate.entity.UserEntity;
import com.estate.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>,CustomerRepositoryCustom {
    Page<CustomerEntity> findAll(Pageable pageable);
    // lấy tất cả khách hàng theo user
    Page<CustomerEntity> findByUsers_id(Pageable pageable,long userId);
    long countByUsers_id(long userId);

    CustomerEntity findOneByEmailAndPassword(String emal, String pass);

}
