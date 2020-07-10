package com.estate.controller.admin.api;

import com.estate.dto.CustomerServiceDTO;
import com.estate.dto.CustomerDTO;
import com.estate.service.impl.CustomerService;
import com.estate.service.impl.CustomerService_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "apiCustomerOfAdmin")
@RequestMapping(value = "/api/admin/customer")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerService_Service customerService_service;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.insert(customerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable("id") long id){
        return ResponseEntity.ok(customerService.update(customerDTO,id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestBody long[] ids) {
        if (ids.length > 0) {
            customerService.deleteCustomer(ids);
        }
        return ResponseEntity.ok("success");
    }

    @PostMapping("/{id}/users")
    public ResponseEntity<Void> assignCustomerToStaff(@RequestBody Long[] usersId, @PathVariable("id") long id) {
        customerService.assignCustomerToStaff(usersId, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/customer_service")
    public ResponseEntity<CustomerDTO> updateCareDetail(@RequestBody CustomerServiceDTO customerServiceDTO, @PathVariable("id") long id) {
        return ResponseEntity.ok(customerService_service.insert(customerServiceDTO, id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CustomerDTO> updateStatus(@RequestBody CustomerDTO customerDTO, @PathVariable("id") long id) {
        return ResponseEntity.ok(customerService.updateStatus(customerDTO,id));
    }
}
