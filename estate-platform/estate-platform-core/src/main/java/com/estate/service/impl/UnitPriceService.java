package com.estate.service.impl;

import com.estate.converter.UnitPriceConverter;
import com.estate.dto.MailRequest;
import com.estate.dto.UnitPriceDto;
import com.estate.entity.ContractEntity;
import com.estate.entity.CustomerEntity;
import com.estate.entity.UnitPriceEntity;
import com.estate.paging.PageRequest;
import com.estate.repository.CustomerRepository;
import com.estate.repository.UnitPriceRepository;
import com.estate.repository.custom.ContractRepositoryCustom;
import com.estate.repository.custom.UnitPriceRepositoryCustom;
import com.estate.service.IMailService;
import com.estate.service.IUnitPriceService;
import com.estate.utils.FormatDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UnitPriceService implements IUnitPriceService {

    @Autowired
    private UnitPriceRepository unitPriceRepository;

    @Autowired
    private UnitPriceRepositoryCustom unitPriceRepositoryCustom;

    @Autowired
    private UnitPriceConverter unitPriceConverter;

    @Autowired
    private ContractRepositoryCustom contractRepositoryCustom;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private IMailService mailService;

    @Override
    public UnitPriceDto insert(UnitPriceDto unitPriceDto) {
        UnitPriceEntity unitPriceEntity = new UnitPriceEntity();
        unitPriceEntity.setIdBuilding(unitPriceDto.getIdBuilding());
        unitPriceEntity.setEffectiveDate(unitPriceDto.getEffectiveDate());
        unitPriceEntity.setRoomPrice(unitPriceDto.getRoomPrice());
        unitPriceEntity.setWaterPrice(unitPriceDto.getWaterPrice());
        unitPriceEntity.setElectricityPrice(unitPriceDto.getElectricityPrice());
        if (this.findLastUnitPrice(unitPriceDto.getIdBuilding()) != null) {
            ContractEntity contractEntity = contractRepositoryCustom.findCustomerContractByBuildingId(unitPriceDto.getIdBuilding());
            if (contractEntity != null) {
                CustomerEntity customerEntity = customerRepository.findOne(contractEntity.getIdCustomer());
                MailRequest mailRequest = new MailRequest();
                mailRequest.setName("Building");
                mailRequest.setSubject("Building");
                mailRequest.setTo(customerEntity.getEmail());
                mailRequest.setFrom("hoanganhtien25041997@gmail.com");
                Map<String, Object> mapModel = new HashMap<>();
                mapModel.put("name", customerEntity.getName());
                mapModel.put("message", unitPriceDto.getNoteUnitPrice());
                mailService.sendMail(mailRequest, mapModel,"email-create-unitprice.ftl");
            }
        }
        unitPriceRepository.save(unitPriceEntity);
        return unitPriceDto;
    }

    @Override
    public List<UnitPriceDto> getAllUnitPriceByIdBuilding(Long idBuilding, UnitPriceDto dto) {
        com.estate.paging.Pageable pageableCustom = new PageRequest(dto.getPage(),
                dto.getMaxPageItems());
        List<UnitPriceEntity> entities = unitPriceRepositoryCustom.findAllUnitPriceByBuildingId(idBuilding, pageableCustom);
        List<UnitPriceDto> unitPriceDtos = new ArrayList<>();
        for (UnitPriceEntity item : entities) {
            UnitPriceDto unitPriceDto = unitPriceConverter.convertToDto(item);
            unitPriceDtos.add(unitPriceDto);
        }

        return unitPriceDtos;
    }

    @Override
    public int getTotalItems(Long idBuilding) {
        int totalItem = 0;
        totalItem = unitPriceRepositoryCustom.getTotalItems(idBuilding).intValue();
        return totalItem;
    }

    @Override
    public UnitPriceDto findLastUnitPrice(Long idBuilding) {
        UnitPriceEntity unitPriceEntity = unitPriceRepositoryCustom.findLastUnitPrice(idBuilding);
        if (unitPriceEntity != null) {
            return unitPriceConverter.convertToDto(unitPriceEntity);
        }
        return null;
    }

    private String handleMailUnitPriceElecttric(UnitPriceDto unitPriceDto) {
        String message = "";
        UnitPriceDto unitPriceDtoOld = this.findLastUnitPrice(unitPriceDto.getIdBuilding());
        if (unitPriceDto.getElectricityPrice().doubleValue() != unitPriceDtoOld.getElectricityPrice().doubleValue()) {
            message += "Xin Lỗi Tiền Điện Đổi Từ '" + unitPriceDtoOld.getElectricityPrice() + "' Thành '" + unitPriceDto.getElectricityPrice() + "' Trên 1 Số Điện \r\n";
        }
        return message;
    }

    private String handleMailUnitPriceWatter(UnitPriceDto unitPriceDto) {
        String message = "";
        UnitPriceDto unitPriceDtoOld = this.findLastUnitPrice(unitPriceDto.getIdBuilding());
        if (unitPriceDto.getWaterPrice().doubleValue() != unitPriceDtoOld.getWaterPrice().doubleValue()) {
            message += "Xin Lỗi Tiền Nước Đổi Từ '" + unitPriceDtoOld.getWaterPrice() + "' Thành '" + unitPriceDto.getWaterPrice() + "' Trên 1 Khối Nước \r\n ";

        }
        return message;
    }

    private String handleMailUnitPriceRoom(UnitPriceDto unitPriceDto) {
        String message = "";
        UnitPriceDto unitPriceDtoOld = this.findLastUnitPrice(unitPriceDto.getIdBuilding());
        if (unitPriceDto.getRoomPrice().doubleValue() != unitPriceDtoOld.getRoomPrice().doubleValue()) {
            message += "Xin Lỗi Tiền Phòng Đổi Từ '" + unitPriceDtoOld.getRoomPrice() + "' Thành '" + unitPriceDto.getRoomPrice() + "' \r\n";
        }
        return message;
    }
}
