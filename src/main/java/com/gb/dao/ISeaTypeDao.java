package com.gb.dao;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gb.dao.support.IBaseDao;
import com.gb.entity.SeaType;

@Repository
@CacheConfig(cacheNames = "resourceCache")
public interface ISeaTypeDao extends IBaseDao<SeaType, Integer> {
    
	@Query("select s from SeaType s where s.upid=0 and s.ishidden=0 and s.tptype=0")
	@Cacheable
	java.util.List<SeaType> findByupidFrom(Pageable pageable);

}
