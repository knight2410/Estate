package com.estate.repository;

import com.estate.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity ,Long> {
   // List<PaymentEntity> findByIdContract(long idContract);
    PaymentEntity findOneByIdConsume(Long idConsume);
}
