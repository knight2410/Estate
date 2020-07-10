package com.estate.service;

import com.estate.dto.ConsumeDto;
import com.estate.dto.ContractDto;
import com.estate.dto.DetailContract;

import java.util.List;

public interface IContractService {
    ContractDto findOneById(Long id);

    List<ContractDto> finAllContract(ContractDto model, boolean isManager);

    List<ContractDto> finAllContractDownload(ContractDto model, boolean isManager);

    int getTotalItems(ContractDto model, boolean isManager);

    ContractDto save(ConsumeDto consumeDto);

    ContractDto invoiceProcessing(ConsumeDto consumeDto);

    List<DetailContract> findAllDetailContract(Long idContract,DetailContract detailContract);

    int getTotalItemsContract(Long idContract, DetailContract detailContract);

    List<DetailContract> findAllDetailContractByIdBuilding(Long idBuilding,DetailContract detailContract);

    int getTotalItemsContractByIdBuilding(Long idBuilding, DetailContract detailContract);

    List<DetailContract> findAllDetailContractByIdCustomer(Long idCustomer,DetailContract detailContract);

    int getTotalItemsContractByIdCustomer(Long idCustomer, DetailContract detailContract);

    void sendMail(ConsumeDto consumeDto);

    void updateConsume(ConsumeDto consumeDto);

    void deleteContract(long[] idContract);


}
