package com.estate.repository.custom.impl;

import com.estate.builder.BuildingBuilder;
import com.estate.dto.DetailContract;
import com.estate.entity.BuildingEntity;
import com.estate.entity.ContractEntity;
import com.estate.entity.TestEntity;
import com.estate.paging.Pageable;
import com.estate.repository.custom.BuildingRepositoryCustom;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    private Logger logger = Logger.getLogger(BuildingRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(BuildingBuilder buildingBuilder, Pageable pageable, boolean priority) {
        // entityManager.unwrap(Session.class).
        StringBuilder sql = new StringBuilder("SELECT * FROM building AS be");
        if (priority) {
            sql.append(" inner join proritize_building sp ON  be.id = sp.building_id ");
        }
//        if (StringUtils.isNotBlank(buildingBuilder.getStaffName())) {
//            sql.append(" inner join staff_building s ON be.id = s.building_id inner join users us on us.id = s.staff_id ");
//        }
        if (buildingBuilder.getCheckBuildingInContract() != null) {
            if (buildingBuilder.getCheckBuildingInContract() == 1) {
                sql.append(" inner join contract c on c.idBuilding = be.id and c.isDelete is null ");
            } else {
                sql.append(" inner join ");
                sql.append(" ( ");
                sql.append(" select be1.id as ss from building be1 ");
                sql.append(" join (select * from contract c where c.idBuilding not in ");
                sql.append(" ( select c2.idBuilding from contract c2 where c2.isDelete is null ) ");
                sql.append(" group by c.idBuilding ) cs3 ");
                sql.append(" on cs3.idBuilding = be1.id ");
                sql.append(" union ");
                sql.append(" select be2.id as ss from building be2 left join contract c3 on be2.id = c3.idBuilding where c3.id is null ");
                sql.append(" ) as t ");
                sql.append(" on be.id = t.ss ");
            }
        }

        sql.append(" WHERE 1=1 ");
        sql = buildQuery(sql, buildingBuilder, priority);
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class).setFirstResult(pageable.getOffset()).setMaxResults(pageable.getLimit());

//        query.setFirstResult(pageable.getOffset());
//        query.setMaxResults(pageable.getLimit());
        List<BuildingEntity> results = query.getResultList();
        return results;
    }

    @Override
    public BigInteger getTotalItems(BuildingBuilder buildingBuilder, boolean priority) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM building AS be");

        if (priority) {
            sql.append(" inner join proritize_building sp ON  be.id = sp.building_id ");
        }
//        if (StringUtils.isNotBlank(buildingBuilder.getStaffName())) {
//            sql.append(" inner join staff_building s ON be.id = s.building_id inner join users us on us.id = s.staff_id ");
//        }
        if (buildingBuilder.getCheckBuildingInContract() != null) {
            if (buildingBuilder.getCheckBuildingInContract() == 1) {
                sql.append(" inner join contract c on c.idBuilding = be.id and c.isDelete is null ");
            } else {
                sql.append(" inner join ");
                sql.append(" ( ");
                sql.append(" select be1.id as ss from building be1 ");
                sql.append(" join (select * from contract c where c.idBuilding not in ");
                sql.append(" ( select c2.idBuilding from contract c2 where c2.isDelete is null ) ");
                sql.append(" group by c.idBuilding ) cs3 ");
                sql.append(" on cs3.idBuilding = be1.id ");
                sql.append(" union ");
                sql.append(" select be2.id as ss from building be2 left join contract c3 on be2.id = c3.idBuilding where c3.id is null ");
                sql.append(" ) as t ");
                sql.append(" on be.id = t.ss ");
            }
        }
        sql.append(" WHERE 1=1 ");
        sql = buildQuery(sql, buildingBuilder, priority);
        Query query = entityManager.createNativeQuery(sql.toString());
        return (BigInteger) query.getSingleResult();
    }

    @Override
    public List<TestEntity> test() {
        StringBuilder sql = new StringBuilder("SELECT be.name as name1,be.id as id  FROM building AS be");
        Query query = entityManager.createNativeQuery(sql.toString());
        List<TestEntity> results = query.getResultList();

        return results;
    }

    @Override
    public BuildingEntity existBuildingInContract(Long idBuilding) {
        StringBuilder sql = new StringBuilder(" select * from building b ");
        sql.append(" join contract c on b.id = c.idBuilding and c.isDelete is null ");
        sql.append(" where b.id = " + idBuilding + "");
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        try {
            return (BuildingEntity) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    private StringBuilder buildQuery(StringBuilder sql, BuildingBuilder buildingBuilder, boolean priority) {
        Field[] fields = BuildingBuilder.class.getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equals("checkBuildingInContract") && !field.getName().equals("staffName") && !field.getName().startsWith("area") && !field.getName().startsWith("price")) {
                String fieldType = field.getType().getName();
                if (fieldType.equals("java.lang.String")) {
                    String value = getValueField(field, buildingBuilder, String.class);
                    if (StringUtils.isNotBlank(value)) {
                        sql.append("AND LOWER(be." + field.getName() + ") LIKE '%" + value.toLowerCase() + "%'");
                    }
                } else if (fieldType.equals("java.lang.Integer")) {
                    Integer value = getValueField(field, buildingBuilder, Integer.class);
                    if (value != null) {
                        sql.append(" AND be." + field.getName() + ") = " + value + "");
                    }
                }
            }
        }
        Optional.ofNullable(buildingBuilder.getAreaFrom()).map(item -> sql.append(" AND be.rentArea >= " + buildingBuilder.getAreaFrom() + "")).orElse(sql.append(""));
        Optional.ofNullable(buildingBuilder.getAreaTo()).map(item -> sql.append(" AND be.rentArea <= " + buildingBuilder.getAreaTo() + "")).orElse(sql.append(""));
        Optional.ofNullable(buildingBuilder.getPriceFrom()).map(item -> sql.append(" AND be.price >= " + buildingBuilder.getPriceFrom() + "")).orElse(sql.append(""));
        Optional.ofNullable(buildingBuilder.getPriceTo()).map(item -> sql.append(" AND be.price <= " + buildingBuilder.getPriceTo() + "")).orElse(sql.append(""));
        if (buildingBuilder.getTypeArrays().length > 0) {
            sql.append(" AND (").append("be.types LIKE '%" + buildingBuilder.getTypeArrays()[0] + "%'");
            Arrays.stream(buildingBuilder.getTypeArrays()).filter(item -> !item.equals(buildingBuilder.getTypeArrays()[0]))
                    .forEach(item -> sql.append(" OR be.types LIKE '%" + item + "%' "));
            sql.append(")");
        }
        if (StringUtils.isNotBlank(buildingBuilder.getStaffName())) {
            sql.append(" AND be.createdBy = '" + buildingBuilder.getStaffName() + "'");
        }
        if (priority) {
            sql.append(" AND sp.staff_id = '" + buildingBuilder.getUserId() + "'");
        }
        // sql.append(" group by be.id ");
        // sql.append(" AND DAY(be.createdDate) = DAY(now())");
        // sql.append(" AND DAY(be.createdDate) = 27");
        return sql;
    }

    private <T> T getValueField(Field field, BuildingBuilder buildingBuilder, Class<T> type) {
        Object result = null;
        Method getter = getGetter(field, buildingBuilder);
        if (getter != null) {
            try {
                result = getter.invoke(buildingBuilder);
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return type.cast(result);
    }

    private Method getGetter(Field field, BuildingBuilder buildingBuilder) {
        String getterMethod = "get" + StringUtils.capitalize(field.getName());
        try {
            return buildingBuilder.getClass().getMethod(getterMethod);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
