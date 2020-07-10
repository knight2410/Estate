package com.estate.controller.admin.api;

import com.estate.dto.UnitPriceDto;
import com.estate.service.IUnitPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin/unit-price")
public class UnitPriceAPI {

    @Autowired
    private IUnitPriceService unitPriceService;
    @PostMapping
    public ResponseEntity<UnitPriceDto> createCustomer(@RequestBody UnitPriceDto unitPriceDto){
        return ResponseEntity.ok(unitPriceService.insert(unitPriceDto));
    }
}
