package com.estate.service.impl;

import com.estate.converter.ContractConverter;
import com.estate.dto.ConsumeDto;
import com.estate.dto.ContractDto;
import com.estate.dto.DetailContract;
import com.estate.dto.MailRequest;
import com.estate.entity.*;
import com.estate.paging.PageRequest;
import com.estate.repository.*;
import com.estate.repository.custom.ContractRepositoryCustom;
import com.estate.service.IContractService;
import com.estate.service.IMailService;
import com.estate.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
public class ContractService implements IContractService {

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private ContractConverter contractConverter;
    @Autowired
    private ConsumeRepository consumeRepository;

    @Autowired
    private ContractRepositoryCustom contractRepositoryCustom;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IMailService mailService;

    @Autowired
    private UnitPriceRepository unitPriceRepository;

    @Override
    public ContractDto findOneById(Long id) {
        return contractConverter.convertToDto(contractRepository.findOne(id));
    }

    @Override
    public List<ContractDto> finAllContract(ContractDto model, boolean isManager) {
        com.estate.paging.Pageable pageableCustom = new PageRequest(model.getPage(),
                model.getMaxPageItems());
        List<TestEntity> contractCustomEntitiess = new ArrayList<>();
        contractCustomEntitiess = contractRepositoryCustom.finAll(model, pageableCustom, isManager);
        List<ContractDto> results = new ArrayList<>();
        for (TestEntity item : contractCustomEntitiess) {
            ContractDto contractDto = new ContractDto();
            contractDto.setCreatedBy(userRepository.findOneByUserName(item.getCreateByContract()).getFullName());
            contractDto.setId(item.getId().longValue());
            contractDto.setNameBuilding(item.getNameBuilding());
            contractDto.setNameCustomer(item.getNameCustomer());
            contractDto.setCreatedDateConsumer(item.getCreatedDateConsumer());
            contractDto.setCreatedDate((Timestamp) item.getCreatedDate());
            contractDto.setAmountPaid(item.getAmountPaid());
            if (item.getCreatedDateConsumer() != null) {
                contractDto.setMonthCreatedDateConsumer(item.getCreatedDateConsumer().getMonth() + 1);
            }
            contractDto.setPowerNumber(item.getPowerNumber());
            contractDto.setWaterNumber(item.getWaterNumber());
            contractDto.setElectricityPrice(item.getElectricityPrice());
            contractDto.setRoomPrice(item.getRoomPrice());
            contractDto.setWaterPrice(item.getWaterPrice());
            contractDto.setEffectiveDate(item.getEffectiveDate());
            contractDto.setAmountPayable(item.getAmountPayable());
            contractDto.setPercentDaysStay(item.getPercentDaysStay());
            results.add(contractDto);
        }
        return results;
    }

    @Override
    public List<ContractDto> finAllContractDownload(ContractDto model, boolean isManager) {
        List<TestEntity> contractCustomEntitiess = new ArrayList<>();
        contractCustomEntitiess = contractRepositoryCustom.findAllDownload(model, isManager);
        List<ContractDto> results = new ArrayList<>();
        for (TestEntity item : contractCustomEntitiess) {
            ContractDto contractDto = new ContractDto();
            contractDto.setCreatedBy(userRepository.findOneByUserName(item.getCreateByContract()).getFullName());
            contractDto.setId(item.getId().longValue());
            contractDto.setNameBuilding(item.getNameBuilding());
            contractDto.setNameCustomer(item.getNameCustomer());
            contractDto.setCreatedDateConsumer(item.getCreatedDateConsumer());
            contractDto.setCreatedDate((Timestamp) item.getCreatedDate());
            contractDto.setAmountPaid(item.getAmountPaid());
            if (item.getCreatedDateConsumer() != null) {
                contractDto.setMonthCreatedDateConsumer(item.getCreatedDateConsumer().getMonth() + 1);
            }
            contractDto.setPowerNumber(item.getPowerNumber());
            contractDto.setWaterNumber(item.getWaterNumber());
            contractDto.setElectricityPrice(item.getElectricityPrice());
            contractDto.setRoomPrice(item.getRoomPrice());
            contractDto.setWaterPrice(item.getWaterPrice());
            contractDto.setEffectiveDate(item.getEffectiveDate());
            contractDto.setAmountPayable(item.getAmountPayable());
            contractDto.setPercentDaysStay(item.getPercentDaysStay());
            results.add(contractDto);
        }
        return results;
    }

    @Override
    public int getTotalItems(ContractDto model, boolean isManager) {
        int totalItem = 0;
        totalItem = contractRepositoryCustom.getTotalItems(model, isManager).intValue();
        return totalItem;
    }

    @Override
    public ContractDto save(ConsumeDto consumeDto) {
        ContractEntity contractEntity = new ContractEntity();
        contractEntity.setIdBuilding(consumeDto.getIdBuilding());
        contractEntity.setIdCustomer(consumeDto.getIdCustomer());
        contractEntity.setIdUser(SecurityUtils.getPrincipal().getId());
        contractEntity.setNote(consumeDto.getNoteContract());
        contractEntity.setNameBuilding(buildingRepository.findOne(consumeDto.getIdBuilding()).getName());
        CustomerEntity customerEntity = customerRepository.findOne(consumeDto.getIdCustomer());
        contractEntity.setNameCustomer(customerEntity.getName());
        contractEntity.setNameUser(userRepository.findOne(SecurityUtils.getPrincipal().getId()).getFullName());
        // save contract
        contractEntity = contractRepository.save(contractEntity);

        ConsumeEntity consumeEntity = new ConsumeEntity();
        consumeEntity.setIdBuilding(consumeDto.getIdBuilding());
        consumeEntity.setIdUnitPrice(consumeDto.getIdUnitPrice());
        consumeEntity.setIdContract(contractEntity.getId());
        consumeEntity.setPowerNumber(consumeDto.getPowerNumber());
        consumeEntity.setWaterNumber(consumeDto.getWaterNumber());
        consumeEntity.setPercentDaysStay((double) 0);
        //save consume
        consumeEntity = consumeRepository.save(consumeEntity);

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setIdConsume(consumeEntity.getId());
        paymentEntity.setAmountPaid(consumeDto.getAmountPaid());
        paymentEntity.setAmountPayable(consumeDto.getAmountPaid());
        //save payment
        paymentEntity = paymentRepository.save(paymentEntity);

        MailRequest mailRequest = new MailRequest();
        mailRequest.setName("Building");
        mailRequest.setSubject("Building");
        mailRequest.setTo(customerEntity.getEmail());
        mailRequest.setFrom("hoanganhtien25041997@gmail.com");
        Map<String, Object> mapModel = new HashMap<>();
        mapModel.put("message", consumeDto.getNoteContract());
        mapModel.put("name", customerEntity.getName());
        mailService.sendMail(mailRequest, mapModel, "email-create-contract.ftl");
        return contractConverter.convertToDto(contractEntity);
    }

    @Override
    public ContractDto invoiceProcessing(ConsumeDto consumeDto) {

        ConsumeEntity consumeEntity = new ConsumeEntity();
        consumeEntity.setIdBuilding(consumeDto.getIdBuilding());
        consumeEntity.setIdContract(consumeDto.getContractId());
        consumeEntity.setIdUnitPrice(consumeDto.getIdUnitPrice());
        consumeEntity.setPowerNumber(consumeDto.getPowerNumberNew());
        consumeEntity.setWaterNumber(consumeDto.getWaterNumberNew());
        consumeEntity.setPercentDaysStay(consumeDto.getPercentDaysStay());
        consumeEntity.setNote(consumeDto.getNoteContract());
        //save consume
        consumeEntity = consumeRepository.save(consumeEntity);
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setAmountPayable(consumeDto.getAmountPayable());
        paymentEntity.setIdConsume(consumeEntity.getId());
        //save payment
        paymentRepository.save(paymentEntity);
        CustomerEntity customerEntity = customerRepository.findOne(consumeDto.getIdCustomer());
        MailRequest mailRequest = new MailRequest();
        mailRequest.setName("Building");
        mailRequest.setSubject("Building");
        mailRequest.setTo(customerEntity.getEmail());
        mailRequest.setFrom("hoanganhtien25041997@gmail.com");
        Map<String, Object> mapModel = new HashMap<>();
        mapModel.put("message", consumeDto.getNoteContract());
        mapModel.put("name", customerEntity.getName());
        mailService.sendMail(mailRequest, mapModel, "email-create-contract-invoice-processing.ftl");
        return contractConverter.convertToDto(contractRepository.findOne(consumeDto.getContractId()));
    }

    @Override
    public List<DetailContract> findAllDetailContract(Long idContract, DetailContract detailContract) {
        com.estate.paging.Pageable pageableCustom = new PageRequest(detailContract.getPage(),
                detailContract.getMaxPageItems());
        List<DetailContract> detailContracts = contractRepositoryCustom.findDetailContract(idContract, pageableCustom, detailContract);
        return detailContracts;
    }

    @Override
    public int getTotalItemsContract(Long idContract, DetailContract detailContract) {
        int totalItem = 0;
        totalItem = contractRepositoryCustom.getTotalItemsDetailContract(idContract, detailContract).intValue();
        return totalItem;
    }

    @Override
    public List<DetailContract> findAllDetailContractByIdBuilding(Long idBuilding, DetailContract detailContract) {
        com.estate.paging.Pageable pageableCustom = new PageRequest(detailContract.getPage(),
                detailContract.getMaxPageItems());
        List<ContractEntity> contractEntities = contractRepositoryCustom.findContractByIdBuilding(idBuilding, detailContract, pageableCustom);
        List<DetailContract> detailContracts = new ArrayList<>();
        for (ContractEntity item : contractEntities) {
            DetailContract detail = new DetailContract();
            detail.setId(item.getId());
            detail.setIdBuilding(item.getIdBuilding());
            detail.setIdCustomer(item.getIdCustomer());
            detail.setIdUser(item.getIdUser());
            detail.setIsDelete(item.getIsDelete());
            detail.setNameUser(item.getNameUser());
            detail.setNameCustomer(item.getNameCustomer());
            detail.setCreatedDate((Timestamp) item.getCreatedDate());
            detail.setNameBuilding(item.getNameBuilding());
            detailContracts.add(detail);
        }
        return detailContracts;
    }

    @Override
    public int getTotalItemsContractByIdBuilding(Long idBuilding, DetailContract detailContract) {
        int totalItem = 0;
        totalItem = contractRepositoryCustom.getTotalFindContractByIdBuilding(idBuilding, detailContract).intValue();
        return totalItem;
    }

    @Override
    public List<DetailContract> findAllDetailContractByIdCustomer(Long idCustomer, DetailContract detailContract) {
        com.estate.paging.Pageable pageableCustom = new PageRequest(detailContract.getPage(),
                detailContract.getMaxPageItems());
        List<ContractEntity> contractEntities = contractRepositoryCustom.findContractByIdCustomer(idCustomer, detailContract, pageableCustom);
        List<DetailContract> detailContracts = new ArrayList<>();
        for (ContractEntity item : contractEntities) {
            DetailContract detail = new DetailContract();
            detail.setId(item.getId());
            detail.setIdBuilding(item.getIdBuilding());
            detail.setIdCustomer(item.getIdCustomer());
            detail.setIdUser(item.getIdUser());
            detail.setIsDelete(item.getIsDelete());
            detail.setNameUser(item.getNameUser());
            detail.setNameCustomer(item.getNameCustomer());
            detail.setCreatedDate((Timestamp) item.getCreatedDate());
            detail.setNameBuilding(item.getNameBuilding());
            detailContracts.add(detail);
        }
        return detailContracts;
    }

    @Override
    public int getTotalItemsContractByIdCustomer(Long idCustomer, DetailContract detailContract) {
        int totalItem = 0;
        totalItem = contractRepositoryCustom.getTotalFindContractByIdCustomer(idCustomer, detailContract).intValue();
        return totalItem;
    }

    @Override
    public void sendMail(ConsumeDto consumeDto) {
        ConsumeEntity consumeEntity = consumeRepository.findOne(consumeDto.getId());
        consumeEntity.setNote(consumeDto.getNote());
        consumeRepository.save(consumeEntity);
        CustomerEntity customerEntity = customerRepository.findOne(consumeDto.getIdCustomer());
        MailRequest mailRequest = new MailRequest();
        mailRequest.setName("Building");
        mailRequest.setSubject("Building");
        mailRequest.setTo(customerEntity.getEmail());
        mailRequest.setFrom("hoanganhtien25041997@gmail.com");
        Map<String, Object> mapModel = new HashMap<>();
        mapModel.put("message", consumeDto.getNote());
        mapModel.put("name", customerEntity.getName());
        mailService.sendMail(mailRequest, mapModel, "email-create-contract-invoice-processing.ftl");
    }

    @Override
    public void updateConsume(ConsumeDto consumeDto) {
        ConsumeEntity consumeEntity = consumeRepository.findOne(consumeDto.getId());
        consumeEntity.setNote(consumeDto.getNote());
        consumeEntity.setPercentDaysStay(consumeDto.getDebt());
        consumeRepository.save(consumeEntity);
        PaymentEntity paymentEntity = paymentRepository.findOneByIdConsume(consumeDto.getId());
        paymentEntity.setAmountPaid(consumeDto.getAmountPaid());
        paymentRepository.save(paymentEntity);
        CustomerEntity customerEntity = customerRepository.findOne(consumeDto.getIdCustomer());
        MailRequest mailRequest = new MailRequest();
        mailRequest.setName("Building");
        mailRequest.setSubject("Building");
        mailRequest.setTo(customerEntity.getEmail());
        mailRequest.setFrom("hoanganhtien25041997@gmail.com");
        Map<String, Object> mapModel = new HashMap<>();
        mapModel.put("message", consumeDto.getNote());
        mapModel.put("name", customerEntity.getName());
        mailService.sendMail(mailRequest, mapModel, "email-create-contract-invoice-processing.ftl");
    }

    @Override
    public void deleteContract(long[] idContract) {
        for (long item : idContract) {
            ContractEntity entity = contractRepository.findOne(item);
            entity.setIsDelete(new Date());
        }
    }

    private void activeProcessed1(List<ContractDto> contractDtos) {
        for (ContractDto item : contractDtos) {
            List<ConsumeEntity> consumeEntities = consumeRepository.findByIdContract(item.getId());
            if (consumeEntities.get(consumeEntities.size() - 1).getCreatedDate().getMonth() == new Date().getMonth() + 1) {
                item.setActive(true);
            } else {
                item.setActive(false);
            }
        }
    }
}
