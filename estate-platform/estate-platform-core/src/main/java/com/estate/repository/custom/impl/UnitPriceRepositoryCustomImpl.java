package com.estate.repository.custom.impl;

import com.estate.entity.UnitPriceEntity;
import com.estate.paging.Pageable;
import com.estate.repository.custom.UnitPriceRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@Repository
public class UnitPriceRepositoryCustomImpl implements UnitPriceRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UnitPriceEntity findByIdBuildingAndEffectiveDate(Long idBuilding) {
        StringBuilder sql = new StringBuilder(" select * from unit_price u ");
        sql.append(" where  u.idBuilding = '" + idBuilding + "'");
        sql.append(" AND u.effectiveDate <= now() ");
        sql.append(" order by u.effectiveDate desc limit 1 ");
        Query query = entityManager.createNativeQuery(sql.toString(), UnitPriceEntity.class);
        return (UnitPriceEntity) query.getSingleResult();
    }

    @Override
    public List<UnitPriceEntity> findAllUnitPriceByBuildingId(Long idBuilding, Pageable pageable) {
        StringBuilder sql = new StringBuilder("select * from unit_price up");
        sql.append(" Where up.idBuilding = " + idBuilding + " ");
        sql.append(" order by up.effectiveDate desc ");
        Query query = entityManager.createNativeQuery(sql.toString(), UnitPriceEntity.class).setFirstResult(pageable.getOffset()).setMaxResults(pageable.getLimit());
        List<UnitPriceEntity> results = query.getResultList();
        return results;
    }

    @Override
    public BigInteger getTotalItems(Long idBuilding) {
        StringBuilder sql = new StringBuilder("select count(*) from unit_price up");
        sql.append(" Where up.idBuilding = " + idBuilding + " ");
        Query query = entityManager.createNativeQuery(sql.toString());
        return (BigInteger) query.getSingleResult();
    }

    @Override
    public UnitPriceEntity findLastUnitPrice(Long idBuilding) {
        StringBuilder sql = new StringBuilder("select * from unit_price up");
        sql.append(" Where up.idBuilding = " + idBuilding + " ");
        sql.append(" order by up.effectiveDate desc limit 1 ");
        Query query = entityManager.createNativeQuery(sql.toString(), UnitPriceEntity.class);
        try {
            return (UnitPriceEntity) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
