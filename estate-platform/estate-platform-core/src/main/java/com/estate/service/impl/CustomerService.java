package com.estate.service.impl;

import com.estate.converter.CustomerConverter;
import com.estate.converter.CustomerServiceConverter;
import com.estate.dto.CustomerDTO;
import com.estate.dto.MailRequest;
import com.estate.entity.CustomerEntity;
import com.estate.entity.UserEntity;
import com.estate.paging.PageRequest;
import com.estate.repository.ContractRepository;
import com.estate.repository.CustomerRepository;
import com.estate.repository.UserRepository;
import com.estate.service.ICustomerService;
import com.estate.service.IMailService;
import com.estate.service.IRoleService;
import com.estate.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private CustomerServiceConverter customerServiceConverter;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMailService mailService;


    @Autowired
    private ContractRepository contractRepository;
    private Long userId;
    private boolean isManager;

    @Override
    public List<CustomerDTO> searchCustomersAssignment(CustomerDTO modelSearch) {
        getUserAndRole();
        com.estate.paging.Pageable pageableCustom = new PageRequest(modelSearch.getPage(), modelSearch.getMaxPageItems());
        List<?> customerEntities = null;
        List<CustomerDTO> result = new ArrayList<>();
        if (!isManager) {
            modelSearch.setStaffName(userRepository.findOne(userId).getUserName());
        }
        customerEntities = customerRepository.findAll(modelSearch, pageableCustom);
        for (Object item : customerEntities) {
            CustomerEntity customerEntity = new CustomerEntity();
            try {
                customerEntity = (CustomerEntity) item;
            } catch (Exception e) {
                customerEntity = (CustomerEntity) ((Object[]) item)[0];
            }
            CustomerDTO customerDTO = customerConverter.convertToDto(customerEntity);
            customerDTO.setContact(contractRepository.existsByIdCustomer(customerDTO.getId()));
            if (customerRepository.existCustomerInContract(((CustomerEntity) item).getId()) != null) {
                customerDTO.setExistCustomerInContract(true);
            }
            result.add(customerDTO);
        }

        return result;
    }

    @Override
    public CustomerDTO findOneById(long id) {
        CustomerEntity customerEntity = customerRepository.findOne(id);
        return customerConverter.convertToDto(customerEntity);
    }

    @Override
    public CustomerDTO findCustomerById(long id) {
        CustomerEntity entity = customerRepository.findOne(id);
        CustomerDTO dto = customerConverter.convertToDto(entity);
        return dto;
    }

    @Override
    public CustomerDTO insert(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
        customerRepository.save(customerEntity);

        MailRequest mailRequest = new MailRequest();
        mailRequest.setName("Building");
        mailRequest.setSubject("Building");
        mailRequest.setTo(customerEntity.getEmail());
        mailRequest.setFrom("hoanganhtien25041997@gmail.com");
        Map<String, Object> mapModel = new HashMap<>();
        mapModel.put("message", customerDTO.getNote());
        mapModel.put("name", customerEntity.getName());
        mailService.sendMail(mailRequest, mapModel, "email-create-contract.ftl");
        return customerDTO;
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO, long id) {
        CustomerEntity oldCustomer = customerRepository.findOne(id);
        oldCustomer.setName(customerDTO.getName());
        oldCustomer.setDateMail(customerDTO.getDateMail());
        oldCustomer.setEmail(customerDTO.getEmail());
        oldCustomer.setNeed(customerDTO.getNeed());
        oldCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        oldCustomer.setPassword(customerDTO.getPassword());
        oldCustomer = customerRepository.save(oldCustomer);
        return customerConverter.convertToDto(oldCustomer);
    }

    @Override
    public CustomerDTO updateStatus(CustomerDTO customerDTO, long id) {
        CustomerEntity oldCustomer = customerRepository.findOne(id);
        oldCustomer.setNote(customerDTO.getNote());
        oldCustomer.setStatus(customerDTO.getStatus());
        oldCustomer = customerRepository.save(oldCustomer);
        return customerConverter.convertToDto(oldCustomer);
    }


    @Override
    public int getTotalItemsAssignment(CustomerDTO modelSearch) {
        int totalItem = 0;
        getUserAndRole();
        if (!isManager) {
            modelSearch.setStaffName(userRepository.findOne(userId).getUserName());
        }
        totalItem = customerRepository.getTotalItems(modelSearch).intValue();
        return totalItem;
    }

    void getUserAndRole() {
        userId = SecurityUtils.getPrincipal().getId();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        isManager = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("MANAGER"));
    }


    @Override
    public void deleteCustomer(long[] ids) {
        for (long item : ids) {
            customerRepository.delete(item);
        }
    }

    @Override
    public void assignCustomerToStaff(Long[] usersId, long id) {
        CustomerEntity customer = customerRepository.findOne(id);
        List<UserEntity> userEntities = new ArrayList<>();
        for (Long item : usersId) {
            userEntities.add(userRepository.findOne(item));
        }
        customer.setUsers(userEntities);
        customerRepository.save(customer);
    }

    @Override
    public Map<Long, String> getCustomerNotContractAndByUserId(Long userId) {
        List<CustomerEntity> customerEntities = customerRepository.getCustomerNotContractAndByUserId(userRepository.findOne(userId).getUserName());
        Map<Long, String> stringMap = new HashMap<>();
        for (CustomerEntity item : customerEntities) {
            stringMap.put(item.getId(), item.getName());
        }
        return stringMap;
    }

    @Override
    public Map<Long, String> getCustomerNotContractAndByUserIdIsManager() {
        List<CustomerEntity> customerEntities = customerRepository.getCustomerNotContractAndByUserIdIsManager();
        Map<Long, String> stringMap = new HashMap<>();
        for (CustomerEntity item : customerEntities) {
            stringMap.put(item.getId(), item.getName());
        }
        return stringMap;
    }


}

   