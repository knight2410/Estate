package com.estate.repository.custom;

import com.estate.entity.ConsumeEntity;

import java.util.List;

public interface ConsumeRepositoryCustom {

    ConsumeEntity getConsumeLast(Long idContract);
    List<ConsumeEntity> findTwoConsume(Long IdContract);

}
