package com.gb.dao;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;
import com.gb.dao.support.IBaseDao;
import com.gb.entity.SeaNew;

@Repository
@CacheConfig(cacheNames = "resourceCache")
public interface ISeaNewDao extends IBaseDao<SeaNew, Integer> {
    
}
