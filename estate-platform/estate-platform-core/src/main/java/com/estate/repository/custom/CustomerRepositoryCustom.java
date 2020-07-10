package com.estate.repository.custom;

import com.estate.dto.CustomerDTO;
import com.estate.entity.CustomerEntity;
import com.estate.paging.Pageable;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<?> findAll(CustomerDTO customerDTO, Pageable pageable);
    Long getTotalItems(CustomerDTO customerDTO);
    List<CustomerEntity> getCustomerNotContractAndByUserId(String userName);
    List<CustomerEntity> getCustomerNotContractAndByUserIdIsManager();
    CustomerEntity existCustomerInContract(Long idCustomer);

}
