package com.estate.service.impl;

import com.estate.converter.CustomerConverter;
import com.estate.converter.CustomerServiceConverter;
import com.estate.dto.CustomerServiceDTO;
import com.estate.dto.CustomerDTO;
import com.estate.entity.CustomerServiceEntity;
import com.estate.entity.CustomerEntity;
import com.estate.repository.CustomerRepository;
import com.estate.repository.CustomerServiceRepository;
import com.estate.service.ICustomerService_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService_Service implements ICustomerService_Service {

    @Autowired
    private CustomerServiceConverter customerServiceConverter;

    @Autowired
    private CustomerConverter customerConverter;


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceRepository customerServiceRepository;

    @Override
    public CustomerDTO insert(CustomerServiceDTO customerServiceDTO, long customerId) {
        CustomerEntity customerEntity = customerRepository.findOne(customerId);
        CustomerServiceEntity careDetailEntity = customerServiceConverter.convertToEntity(customerServiceDTO);
        careDetailEntity.setCustomer(customerEntity);
        customerServiceRepository.save(careDetailEntity);
        return customerConverter.convertToDto(customerEntity);
    }
}
