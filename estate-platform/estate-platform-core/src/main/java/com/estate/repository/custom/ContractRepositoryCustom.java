package com.estate.repository.custom;

import com.estate.dto.ContractDto;
import com.estate.dto.DetailContract;
import com.estate.entity.ContractCustomEntity;
import com.estate.entity.ContractEntity;
import com.estate.entity.TestEntity;
import com.estate.paging.Pageable;

import java.math.BigInteger;
import java.util.List;

public interface ContractRepositoryCustom {

    List<TestEntity> finAll(ContractDto contractDto, Pageable pageable, boolean isManager);

    List<TestEntity> findAllDownload(ContractDto contractDto, boolean isManager);


    BigInteger getTotalItems(ContractDto contractDto, boolean isManager);

    ContractEntity findCustomerContractByBuildingId(Long idBuilding);

    List<DetailContract> findDetailContract(Long idContract, Pageable pageable, DetailContract detailContract);

    BigInteger getTotalItemsDetailContract(Long idContract,DetailContract detailContract);

    List<ContractEntity> findContractByIdBuilding(Long idBuilding, DetailContract detailContract, Pageable pageable);

    BigInteger getTotalFindContractByIdBuilding(Long idBuilding, DetailContract detailContract);

    List<ContractEntity> findContractByIdCustomer(Long idCustomer, DetailContract detailContract, Pageable pageable);

    BigInteger getTotalFindContractByIdCustomer(Long idCustomer, DetailContract detailContract);


}
