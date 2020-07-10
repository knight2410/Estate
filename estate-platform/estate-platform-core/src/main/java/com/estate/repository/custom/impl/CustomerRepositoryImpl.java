package com.estate.repository.custom.impl;

import com.estate.dto.CustomerDTO;
import com.estate.entity.CustomerEntity;
import com.estate.paging.Pageable;
import com.estate.repository.custom.CustomerRepositoryCustom;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    private Logger logger = Logger.getLogger(CustomerRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<?> findAll(CustomerDTO customerDTO, Pageable pageable) {
        StringBuilder sql = new StringBuilder("FROM CustomerEntity AS ce");
//        if (StringUtils.isNotBlank(customerDTO.getStaffName())) {
//            sql.append(" JOIN ce.users u ");
//        }
        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(customerDTO.getName())) {
            sql.append("AND LOWER(ce.name) LIKE '%" + customerDTO.getName() + "%'");
        }
        if (StringUtils.isNotBlank(customerDTO.getPhoneNumber())) {
            sql.append("AND LOWER(ce.phoneNumber) LIKE '%" + customerDTO.getPhoneNumber() + "%'");
        }
        if (StringUtils.isNotBlank(customerDTO.getEmail())) {
            sql.append("AND LOWER(ce.email) LIKE '%" + customerDTO.getEmail() + "%'");
        }
        if (StringUtils.isNotBlank(customerDTO.getStaffName())) {
            sql.append(" AND ce.createdBy = '" + customerDTO.getStaffName() + "'");
        }
        Query query = entityManager.createQuery(sql.toString());
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getLimit());
        return query.getResultList();
    }

    @Override
    public Long getTotalItems(CustomerDTO customerDTO) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM CustomerEntity AS ce");
//        if (StringUtils.isNotBlank(customerDTO.getStaffName())) {
//            sql.append(" JOIN ce.users u ");
//        }
        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(customerDTO.getName())) {
            sql.append("AND LOWER(ce.name) LIKE '%" + customerDTO.getName() + "%'");
        }
        if (StringUtils.isNotBlank(customerDTO.getPhoneNumber())) {
            sql.append("AND LOWER(ce.phoneNumber) LIKE '%" + customerDTO.getPhoneNumber() + "%'");
        }
        if (StringUtils.isNotBlank(customerDTO.getEmail())) {
            sql.append("AND LOWER(ce.email) LIKE '%" + customerDTO.getEmail() + "%'");
        }
        if (StringUtils.isNotBlank(customerDTO.getStaffName())) {
            sql.append(" AND ce.createdBy = '" + customerDTO.getStaffName() + "'");
        }

        Query query = entityManager.createQuery(sql.toString());
        return (Long) query.getSingleResult();
    }

    @Override
    public List<CustomerEntity> getCustomerNotContractAndByUserId(String userName) {
        StringBuilder sql = new StringBuilder(" select * from customer c ");
        sql.append(" where ");
        sql.append("  c.createdBy = '" + userName + "' ");
        sql.append(" and c.id not in ( select ct.idCustomer from contract ct where ct.isDelete is null ) ");
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        List<CustomerEntity> results = query.getResultList();
        return results;
    }

    @Override
    public List<CustomerEntity> getCustomerNotContractAndByUserIdIsManager() {
        StringBuilder sql = new StringBuilder(" select * from customer c ");
        sql.append(" where ");
        sql.append(" c.id not in ( select ct.idCustomer from contract ct where ct.isDelete is null ) ");
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        List<CustomerEntity> results = query.getResultList();
        return results;
    }

    @Override
    public CustomerEntity existCustomerInContract(Long idCustomer) {
        StringBuilder sql = new StringBuilder(" select * from customer b ");
        sql.append(" join contract c on b.id = c.idCustomer and c.isDelete is null ");
        sql.append(" where b.id = " + idCustomer + "");
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        try {
            return (CustomerEntity) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
