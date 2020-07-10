package com.estate.service;

import com.estate.dto.CustomerDTO;
import com.estate.dto.CustomerServiceDTO;

public interface ICustomerService_Service {
    CustomerDTO insert(CustomerServiceDTO customerServiceDTO, long customerId);
}
