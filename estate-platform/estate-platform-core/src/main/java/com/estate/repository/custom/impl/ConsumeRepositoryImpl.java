package com.estate.repository.custom.impl;

import com.estate.entity.ConsumeEntity;
import com.estate.repository.custom.ConsumeRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ConsumeRepositoryImpl implements ConsumeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ConsumeEntity getConsumeLast(Long idContract) {
        StringBuilder sql = new StringBuilder(" Select * From consume c ");
        sql.append(" where c.idContract = " + idContract + " ");
        sql.append(" order by c.createdDate desc limit 1 ");
        Query query = entityManager.createNativeQuery(sql.toString(), ConsumeEntity.class);

        try {
            return (ConsumeEntity) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ConsumeEntity> findTwoConsume(Long IdContract) {
        StringBuilder sql = new StringBuilder(" Select * From consume c ");
        sql.append(" where c.idContract = " + IdContract + " ");
        sql.append(" order by c.createdDate desc limit 2 ");
        Query query = entityManager.createNativeQuery(sql.toString(), ConsumeEntity.class);
        List<ConsumeEntity> results = query.getResultList();
        return results;
    }
}
