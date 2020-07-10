package com.estate.repository.custom.impl;

import com.estate.dto.ContractDto;
import com.estate.dto.DetailContract;
import com.estate.entity.ContractCustomEntity;
import com.estate.entity.ContractEntity;
import com.estate.entity.TestEntity;
import com.estate.paging.Pageable;
import com.estate.repository.custom.ContractRepositoryCustom;
import com.estate.utils.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ContractRepositoryImpl implements ContractRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<TestEntity> finAll(ContractDto contractDto, Pageable pageable, boolean isManager) {
        StringBuilder sql = new StringBuilder(" select c.nameBuilding as nameBuilding,c.nameCustomer as nameCustomer,c.createdDate as createdDate,cs2.createdDate as createdDateConsumer,cs2.id as idConsumer,p.id as idPayment,p.amountPaid as amountPaid,c.id as id,cs2.powerNumber,cs2.waterNumber,up.electricityPrice,up.roomPrice,up.waterPrice,up.effectiveDate,p.amountPayable,cs2.percentDaysStay,c.createdBy  from contract c left join ");
        sql.append(" (select cs.idContract,MAX(cs.createdDate) as maxdate from consume cs group by cs.idContract ) cs on cs.idContract = c.id ");
        sql.append(" left join consume cs2 on cs2.idContract = cs.idContract and cs2.createdDate = maxdate ");
        sql.append(" join unit_price up on cs2.idUnitPrice = up.id ");
        sql.append(" left join payment p on p.idConsume = cs2.id ");

        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(contractDto.getNameBuilding())) {
            sql.append("AND LOWER(c.nameBuilding) LIKE '%" + contractDto.getNameBuilding() + "%'");
        }
        if (StringUtils.isNotBlank(contractDto.getNameCustomer())) {
            sql.append("AND LOWER(c.nameCustomer) LIKE '%" + contractDto.getNameCustomer() + "%'");
        }
        if (StringUtils.isNotBlank(contractDto.getStartDate())) {
            sql.append("AND  Date(c.createdDate) >= '" + contractDto.getStartDate() + "' ");
        }
        if (StringUtils.isNotBlank(contractDto.getEndDate())) {
            sql.append("AND Date(c.createdDate) <= '" + contractDto.getEndDate() + "' ");
        }
        if (!isManager) {
            sql.append(" AND c.createdBy = '" + SecurityUtils.getPrincipal().getUsername() + "' ");
        }
        if (StringUtils.isNotBlank(contractDto.getNameUser())) {
            sql.append(" AND c.createdBy = '" + contractDto.getNameUser() + "' ");
        }
        if (StringUtils.isNotBlank(contractDto.getStartDateUnitprice())) {
            sql.append("AND Date(up.effectiveDate) >= '" + contractDto.getStartDateUnitprice() + "'");
        }
        if (StringUtils.isNotBlank(contractDto.getEndDateUnitPrice())) {
            sql.append("AND Date(up.effectiveDate) <= '" + contractDto.getEndDateUnitPrice() + "'");
        }
        if (StringUtils.isNotBlank(contractDto.getStartDateConsume())) {
            sql.append("AND Date(cs2.createdDate) >= '" + contractDto.getStartDateConsume() + "'");
        }
        if (StringUtils.isNotBlank(contractDto.getEndDateConsume())) {
            sql.append("AND Date(cs2.createdDate) <= '" + contractDto.getEndDateConsume() + "'");
        }
        if (contractDto.getDeleteBuilding() != null) {
            if (contractDto.getDeleteBuilding() == 0) {
                sql.append("AND c.isDelete is not null ");
            }
            if (contractDto.getDeleteBuilding() == 1) {
                sql.append("AND c.isDelete is  null ");
            }
        }

        if (contractDto.getProcessed1() != null) {
            if (contractDto.getProcessed1() == 1) {
                sql.append(" AND Month(cs2.createdDate) = Month(now())");
            } else {
                sql.append(" AND Month(cs2.createdDate) < Month(now())");
            }
        }

        if (contractDto.getPaidMoney() != null) {
            if (contractDto.getPaidMoney() == 1) {

                sql.append(" AND p.amountPaid is not null ");
            } else {
                sql.append(" AND p.amountPaid is null ");
            }
        }

        sql.append("order by c.createdDate desc");
        Query query = entityManager.createNativeQuery(sql.toString()).setFirstResult(pageable.getOffset()).setMaxResults(pageable.getLimit());
        List<Object[]> rows = query.getResultList();

        List<TestEntity> result = new ArrayList<>();

        for (Object[] row : rows) {
            TestEntity testEntity = new TestEntity();
            testEntity.setNameBuilding(row[0] == null ? null : (String) row[0]);
            testEntity.setNameCustomer(row[1] == null ? null : (String) row[1]);
            testEntity.setCreatedDate(row[2] == null ? null : (Date) row[2]);
            testEntity.setCreatedDateConsumer(row[3] == null ? null : (Date) row[3]);
            testEntity.setIdConsumer(row[4] == null ? null : (BigInteger) row[4]);
            testEntity.setIdPayment(row[5] == null ? null : (BigInteger) row[5]);
            testEntity.setAmountPaid(row[6] == null ? null : (Double) row[6]);
            testEntity.setId(row[7] == null ? null : (BigInteger) row[7]);
            testEntity.setPowerNumber(row[8] == null ? null : (Double) row[8]);
            testEntity.setWaterNumber(row[9] == null ? null : (Double) row[9]);
            testEntity.setElectricityPrice(row[10] == null ? null : (Double) row[10]);
            testEntity.setRoomPrice(row[11] == null ? null : (Double) row[11]);
            testEntity.setWaterPrice(row[12] == null ? null : (Double) row[12]);
            testEntity.setEffectiveDate(row[13] == null ? null : (Date) row[13]);
            testEntity.setAmountPayable(row[14] == null ? null : (Double) row[14]);
            testEntity.setPercentDaysStay(row[15] == null ? null : (Double) row[15]);
            testEntity.setCreateByContract(row[16] == null ? null : (String) row[16]);
            result.add(testEntity);
        }
        return result;
    }

    @Override
    public List<TestEntity> findAllDownload(ContractDto contractDto, boolean isManager) {
        StringBuilder sql = new StringBuilder(" select c.nameBuilding as nameBuilding,c.nameCustomer as nameCustomer,c.createdDate as createdDate,cs2.createdDate as createdDateConsumer,cs2.id as idConsumer,p.id as idPayment,p.amountPaid as amountPaid,c.id as id,cs2.powerNumber,cs2.waterNumber,up.electricityPrice,up.roomPrice,up.waterPrice,up.effectiveDate,p.amountPayable,cs2.percentDaysStay,c.createdBy  from contract c left join ");
        sql.append(" (select cs.idContract,MAX(cs.createdDate) as maxdate from consume cs group by cs.idContract ) cs on cs.idContract = c.id ");
        sql.append(" left join consume cs2 on cs2.idContract = cs.idContract and cs2.createdDate = maxdate ");
        sql.append(" join unit_price up on cs2.idUnitPrice = up.id ");
        sql.append(" left join payment p on p.idConsume = cs2.id ");

        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(contractDto.getNameBuilding())) {
            sql.append("AND LOWER(c.nameBuilding) LIKE '%" + contractDto.getNameBuilding() + "%'");
        }
        if (StringUtils.isNotBlank(contractDto.getNameCustomer())) {
            sql.append("AND LOWER(c.nameCustomer) LIKE '%" + contractDto.getNameCustomer() + "%'");
        }
        if (StringUtils.isNotBlank(contractDto.getStartDate())) {
            sql.append("AND  Date(c.createdDate) >= '" + contractDto.getStartDate() + "' ");
        }
        if (StringUtils.isNotBlank(contractDto.getEndDate())) {
            sql.append("AND Date(c.createdDate) <= '" + contractDto.getEndDate() + "' ");
        }
        if (!isManager) {
            sql.append(" AND c.createdBy = '" + SecurityUtils.getPrincipal().getUsername() + "' ");
        }
        if (StringUtils.isNotBlank(contractDto.getNameUser())) {
            sql.append(" AND c.createdBy = '" + contractDto.getNameUser() + "' ");
        }
        if (StringUtils.isNotBlank(contractDto.getStartDateUnitprice())) {
            sql.append("AND Date(up.effectiveDate) >= '" + contractDto.getStartDateUnitprice() + "'");
        }
        if (StringUtils.isNotBlank(contractDto.getEndDateUnitPrice())) {
            sql.append("AND Date(up.effectiveDate) <= '" + contractDto.getEndDateUnitPrice() + "'");
        }
        if (StringUtils.isNotBlank(contractDto.getStartDateConsume())) {
            sql.append("AND Date(cs2.createdDate) >= '" + contractDto.getStartDateConsume() + "'");
        }
        if (StringUtils.isNotBlank(contractDto.getEndDateConsume())) {
            sql.append("AND Date(cs2.createdDate) <= '" + contractDto.getEndDateConsume() + "'");
        }
        if (contractDto.getDeleteBuilding() != null) {
            if (contractDto.getDeleteBuilding() == 0) {
                sql.append("AND c.isDelete is not null ");
            }
            if (contractDto.getDeleteBuilding() == 1) {
                sql.append("AND c.isDelete is  null ");
            }
        }

        if (contractDto.getProcessed1() != null) {
            if (contractDto.getProcessed1() == 1) {
                sql.append(" AND Month(cs2.createdDate) = Month(now())");
            } else {
                sql.append(" AND Month(cs2.createdDate) < Month(now())");
            }
        }

        if (contractDto.getPaidMoney() != null) {
            if (contractDto.getPaidMoney() == 1) {

                sql.append(" AND p.amountPaid is not null ");
            } else {
                sql.append(" AND p.amountPaid is null ");
            }
        }

        sql.append("order by c.createdDate desc");
        Query query = entityManager.createNativeQuery(sql.toString());
        List<Object[]> rows = query.getResultList();

        List<TestEntity> result = new ArrayList<>();

        for (Object[] row : rows) {
            TestEntity testEntity = new TestEntity();
            testEntity.setNameBuilding(row[0] == null ? null : (String) row[0]);
            testEntity.setNameCustomer(row[1] == null ? null : (String) row[1]);
            testEntity.setCreatedDate(row[2] == null ? null : (Date) row[2]);
            testEntity.setCreatedDateConsumer(row[3] == null ? null : (Date) row[3]);
            testEntity.setIdConsumer(row[4] == null ? null : (BigInteger) row[4]);
            testEntity.setIdPayment(row[5] == null ? null : (BigInteger) row[5]);
            testEntity.setAmountPaid(row[6] == null ? null : (Double) row[6]);
            testEntity.setId(row[7] == null ? null : (BigInteger) row[7]);
            testEntity.setPowerNumber(row[8] == null ? null : (Double) row[8]);
            testEntity.setWaterNumber(row[9] == null ? null : (Double) row[9]);
            testEntity.setElectricityPrice(row[10] == null ? null : (Double) row[10]);
            testEntity.setRoomPrice(row[11] == null ? null : (Double) row[11]);
            testEntity.setWaterPrice(row[12] == null ? null : (Double) row[12]);
            testEntity.setEffectiveDate(row[13] == null ? null : (Date) row[13]);
            testEntity.setAmountPayable(row[14] == null ? null : (Double) row[14]);
            testEntity.setPercentDaysStay(row[15] == null ? null : (Double) row[15]);
            testEntity.setCreateByContract(row[16] == null ? null : (String) row[16]);
            result.add(testEntity);
        }
        return result;
    }

    @Override
    public BigInteger getTotalItems(ContractDto contractDto, boolean isManager) {
        StringBuilder sql = new StringBuilder(" select count(*) from contract c left join ");
        sql.append(" (select cs.idContract,MAX(cs.createdDate) as maxdate from consume cs group by cs.idContract ) cs on cs.idContract = c.id ");
        sql.append(" left join consume cs2 on cs2.idContract = cs.idContract and cs2.createdDate = maxdate ");
        sql.append(" join unit_price up on cs2.idUnitPrice = up.id ");
        sql.append(" left join payment p on p.idConsume = cs2.id ");

        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(contractDto.getNameBuilding())) {
            sql.append("AND LOWER(c.nameBuilding) LIKE '%" + contractDto.getNameBuilding() + "%'");
        }
        if (StringUtils.isNotBlank(contractDto.getNameCustomer())) {
            sql.append("AND LOWER(c.nameCustomer) LIKE '%" + contractDto.getNameCustomer() + "%'");
        }
        if (StringUtils.isNotBlank(contractDto.getStartDate())) {
            sql.append("AND  Date(c.createdDate) >= '" + contractDto.getStartDate() + "' ");
        }
        if (StringUtils.isNotBlank(contractDto.getEndDate())) {
            sql.append("AND Date(c.createdDate) <= '" + contractDto.getEndDate() + "' ");
        }
        if (!isManager) {
            sql.append(" AND c.createdBy = '" + SecurityUtils.getPrincipal().getUsername() + "' ");
        }
        if (StringUtils.isNotBlank(contractDto.getNameUser())) {
            sql.append(" AND c.createdBy = '" + contractDto.getNameUser() + "' ");
        }
        if (StringUtils.isNotBlank(contractDto.getStartDateUnitprice())) {
            sql.append("AND Date(up.effectiveDate) >= '" + contractDto.getStartDateUnitprice() + "'");
        }
        if (StringUtils.isNotBlank(contractDto.getEndDateUnitPrice())) {
            sql.append("AND Date(up.effectiveDate) <= '" + contractDto.getEndDateUnitPrice() + "'");
        }
        if (StringUtils.isNotBlank(contractDto.getStartDateConsume())) {
            sql.append("AND Date(cs2.createdDate) >= '" + contractDto.getStartDateConsume() + "'");
        }
        if (StringUtils.isNotBlank(contractDto.getEndDateConsume())) {
            sql.append("AND Date(cs2.createdDate) <= '" + contractDto.getEndDateConsume() + "'");
        }
        if (contractDto.getDeleteBuilding() != null) {
            if (contractDto.getDeleteBuilding() == 0) {
                sql.append("AND c.isDelete is not null ");
            }
            if (contractDto.getDeleteBuilding() == 1) {
                sql.append("AND c.isDelete is  null ");
            }
        }
        if (contractDto.getProcessed1() != null) {
            if (contractDto.getProcessed1() == 1) {
                sql.append(" AND Month(cs2.createdDate) = Month(now())");
            } else {
                sql.append(" AND Month(cs2.createdDate) < Month(now())");
            }
        }

        if (contractDto.getPaidMoney() != null) {
            if (contractDto.getPaidMoney() == 1) {

                sql.append(" AND p.amountPaid is not null ");
            } else {
                sql.append(" AND p.amountPaid is null ");
            }
        }
        Query query = entityManager.createNativeQuery(sql.toString());
        return (BigInteger) query.getSingleResult();
    }

    @Override
    public ContractEntity findCustomerContractByBuildingId(Long idBuilding) {
        StringBuilder sql = new StringBuilder(" select * from contract c ");
        sql.append(" where c.isDelete is null ");
        sql.append(" and c.idBuilding = " + idBuilding + " ");
        Query query = entityManager.createNativeQuery(sql.toString(), ContractEntity.class);
        try {
            return (ContractEntity) query.getSingleResult();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
            return null;
        }
    }

    @Override
    public List<DetailContract> findDetailContract(Long idContract, Pageable pageable, DetailContract detailContract) {
        StringBuilder sql = new StringBuilder(" select c.createdBy,c.nameBuilding,c.nameCustomer,cs.createdDate,cs.powerNumber,cs.waterNumber, ");
        sql.append(" up.electricityPrice,up.roomPrice,up.waterPrice,p.amountPaid,p.amountPayable,cs.percentDaysStay ");
        sql.append(" from contract c ");
        sql.append(" join consume cs on c.id = cs.idContract ");
        sql.append(" join unit_price up on cs.idUnitPrice = up.id ");
        sql.append(" join payment p on p.idConsume = cs.id ");
        sql.append(" where c.id = " + idContract + " ");
        if (StringUtils.isNotBlank(detailContract.getStartDateConsume())) {
            sql.append("AND Date(cs.createdDate) >= '" + detailContract.getStartDateConsume() + "'");
        }
        if (StringUtils.isNotBlank(detailContract.getEndDateConsume())) {
            sql.append("AND Date(cs.createdDate) <= '" + detailContract.getEndDateConsume() + "'");
        }
        sql.append("order by cs.createdDate desc");
        Query query = entityManager.createNativeQuery(sql.toString()).setFirstResult(pageable.getOffset()).setMaxResults(pageable.getLimit());
        List<Object[]> rows = query.getResultList();
        List<DetailContract> results = new ArrayList<>();
        for (Object[] row : rows) {
            DetailContract entity = new DetailContract();
            entity.setCreateByContract(row[0] == null ? null : (String) row[0]);
            entity.setNameBuilding(row[1] == null ? null : (String) row[1]);
            entity.setNameCustomer(row[2] == null ? null : (String) row[2]);
            entity.setCreateDateConsume(row[3] == null ? null : (Date) row[3]);
            entity.setPowerNumber(row[4] == null ? null : (Double) row[4]);
            entity.setWaterNumber(row[5] == null ? null : (Double) row[5]);
            entity.setElectricityPrice(row[6] == null ? null : (Double) row[6]);
            entity.setRoomPrice(row[7] == null ? null : (Double) row[7]);
            entity.setWaterPrice(row[8] == null ? null : (Double) row[8]);
            entity.setAmountPaid(row[9] == null ? null : (Double) row[9]);
            entity.setAmountPayable(row[10] == null ? null : (Double) row[10]);
            entity.setPercentDaysStay(row[11] == null ? null : (Double) row[11]);
            results.add(entity);
        }
        return results;
    }

    @Override
    public BigInteger getTotalItemsDetailContract(Long idContract, DetailContract detailContract) {
        StringBuilder sql = new StringBuilder(" select count(*) from contract c ");
        sql.append(" join consume cs on c.id = cs.idContract ");
        sql.append(" join unit_price up on cs.idUnitPrice = up.id ");
        sql.append(" join payment p on p.idConsume = cs.id ");
        sql.append(" where c.id = " + idContract + " ");
        if (StringUtils.isNotBlank(detailContract.getStartDateConsume())) {
            sql.append("AND Date(cs.createdDate) >= '" + detailContract.getStartDateConsume() + "'");
        }
        if (StringUtils.isNotBlank(detailContract.getEndDateConsume())) {
            sql.append("AND Date(cs.createdDate) <= '" + detailContract.getEndDateConsume() + "'");
        }
        sql.append("order by cs.createdDate desc");
        Query query = entityManager.createNativeQuery(sql.toString());
        return (BigInteger) query.getSingleResult();
    }

    @Override
    public List<ContractEntity> findContractByIdBuilding(Long idBuilding, DetailContract detailContract, Pageable pageable) {
        StringBuilder sql = new StringBuilder(" select * from contract c ");
        sql.append(" WHERE 1=1 ");
        sql.append(" and c.idBuilding = " + idBuilding + " ");

        if (StringUtils.isNotBlank(detailContract.getNameBuilding())) {
            sql.append("AND LOWER(c.nameBuilding) LIKE '%" + detailContract.getNameBuilding() + "%'");
        }

        if (StringUtils.isNotBlank(detailContract.getNameCustomer())) {
            sql.append("AND LOWER(c.nameCustomer) LIKE '%" + detailContract.getNameCustomer() + "%'");
        }

        if (StringUtils.isNotBlank(detailContract.getStartContract())) {
            sql.append("AND Date(c.createdDate) >= '" + detailContract.getStartContract() + "'");
        }
        if (StringUtils.isNotBlank(detailContract.getEndContract())) {
            sql.append("AND Date(c.createdDate) <= '" + detailContract.getEndContract() + "'");
        }

        if (StringUtils.isNotBlank(detailContract.getStartDelete())) {
            sql.append("AND Date(c.isDelete) >= '" + detailContract.getStartDelete() + "'");
        }
        if (StringUtils.isNotBlank(detailContract.getEndDelete())) {
            sql.append("AND Date(c.isDelete) <= '" + detailContract.getEndDelete() + "'");
        }

        sql.append(" order by c.createdDate desc");
        Query query = entityManager.createNativeQuery(sql.toString(), ContractEntity.class).setFirstResult(pageable.getOffset()).setMaxResults(pageable.getLimit());
        List<ContractEntity> results = query.getResultList();
        return results;
    }

    @Override
    public BigInteger getTotalFindContractByIdBuilding(Long idBuilding, DetailContract detailContract) {
        StringBuilder sql = new StringBuilder(" select count(*) from contract c ");
        sql.append(" WHERE 1=1 ");
        sql.append(" and c.idBuilding = " + idBuilding + " ");
        if (StringUtils.isNotBlank(detailContract.getNameBuilding())) {
            sql.append("AND LOWER(c.nameBuilding) LIKE '%" + detailContract.getNameBuilding() + "%'");
        }

        if (StringUtils.isNotBlank(detailContract.getNameCustomer())) {
            sql.append("AND LOWER(c.nameCustomer) LIKE '%" + detailContract.getNameCustomer() + "%'");
        }

        if (StringUtils.isNotBlank(detailContract.getStartContract())) {
            sql.append("AND Date(c.createdDate) >= '" + detailContract.getStartContract() + "'");
        }
        if (StringUtils.isNotBlank(detailContract.getEndContract())) {
            sql.append("AND Date(c.createdDate) <= '" + detailContract.getEndContract() + "'");
        }

        if (StringUtils.isNotBlank(detailContract.getStartDelete())) {
            sql.append("AND Date(c.isDelete) >= '" + detailContract.getStartDelete() + "'");
        }
        if (StringUtils.isNotBlank(detailContract.getEndDelete())) {
            sql.append("AND Date(c.isDelete) <= '" + detailContract.getEndDelete() + "'");
        }
        sql.append(" order by c.createdDate desc");
        Query query = entityManager.createNativeQuery(sql.toString());
        return (BigInteger) query.getSingleResult();
    }

    @Override
    public List<ContractEntity> findContractByIdCustomer(Long idCustomer, DetailContract detailContract, Pageable pageable) {
        StringBuilder sql = new StringBuilder(" select * from contract c ");
        sql.append(" WHERE 1=1 ");
        sql.append(" and c.idCustomer = " + idCustomer + " ");

        if (StringUtils.isNotBlank(detailContract.getNameBuilding())) {
            sql.append("AND LOWER(c.nameBuilding) LIKE '%" + detailContract.getNameBuilding() + "%'");
        }

        if (StringUtils.isNotBlank(detailContract.getNameCustomer())) {
            sql.append("AND LOWER(c.nameCustomer) LIKE '%" + detailContract.getNameCustomer() + "%'");
        }

        if (StringUtils.isNotBlank(detailContract.getStartContract())) {
            sql.append("AND Date(c.createdDate) >= '" + detailContract.getStartContract() + "'");
        }
        if (StringUtils.isNotBlank(detailContract.getEndContract())) {
            sql.append("AND Date(c.createdDate) <= '" + detailContract.getEndContract() + "'");
        }

        if (StringUtils.isNotBlank(detailContract.getStartDelete())) {
            sql.append("AND Date(c.isDelete) >= '" + detailContract.getStartDelete() + "'");
        }
        if (StringUtils.isNotBlank(detailContract.getEndDelete())) {
            sql.append("AND Date(c.isDelete) <= '" + detailContract.getEndDelete() + "'");
        }

        sql.append(" order by c.createdDate desc");
        Query query = entityManager.createNativeQuery(sql.toString(), ContractEntity.class).setFirstResult(pageable.getOffset()).setMaxResults(pageable.getLimit());
        List<ContractEntity> results = query.getResultList();
        return results;
    }

    @Override
    public BigInteger getTotalFindContractByIdCustomer(Long idCustomer, DetailContract detailContract) {
        StringBuilder sql = new StringBuilder(" select count(*) from contract c ");
        sql.append(" WHERE 1=1 ");
        sql.append(" and c.idCustomer = " + idCustomer + " ");
        if (StringUtils.isNotBlank(detailContract.getNameBuilding())) {
            sql.append("AND LOWER(c.nameBuilding) LIKE '%" + detailContract.getNameBuilding() + "%'");
        }

        if (StringUtils.isNotBlank(detailContract.getNameCustomer())) {
            sql.append("AND LOWER(c.nameCustomer) LIKE '%" + detailContract.getNameCustomer() + "%'");
        }

        if (StringUtils.isNotBlank(detailContract.getStartContract())) {
            sql.append("AND Date(c.createdDate) >= '" + detailContract.getStartContract() + "'");
        }
        if (StringUtils.isNotBlank(detailContract.getEndContract())) {
            sql.append("AND Date(c.createdDate) <= '" + detailContract.getEndContract() + "'");
        }

        if (StringUtils.isNotBlank(detailContract.getStartDelete())) {
            sql.append("AND Date(c.isDelete) >= '" + detailContract.getStartDelete() + "'");
        }
        if (StringUtils.isNotBlank(detailContract.getEndDelete())) {
            sql.append("AND Date(c.isDelete) <= '" + detailContract.getEndDelete() + "'");
        }
        sql.append(" order by c.createdDate desc");
        Query query = entityManager.createNativeQuery(sql.toString());
        return (BigInteger) query.getSingleResult();
    }
}
