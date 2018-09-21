package com.gb.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gb.dao.support.IBaseDao;
import com.gb.entity.SeaData;

@Repository
public interface ISeaDataDao extends IBaseDao<SeaData, Integer> {
    
	
	@Query("select s from SeaData s where s.vAddtime > ?1 and s.vAddtime < ?2 order by s.vId desc")
	Page<SeaData> findByvAddtimePage(int starttime,int endtime,Pageable pageable);
	
	@Query("select s from SeaData s where s.vAddtime > ?1 and s.vAddtime < ?2 order by s.vId desc")
	List<SeaData> findByvAddtimeBetweenTime(int starttime,int endtime,Pageable pageable);
	
	//根据id查询前后记录
	@Query("select s from SeaData s where s.vId > ?1 and s.vId < ?2")
	List<SeaData> findByvIdBetween(int startVid,int endVid);
	
	List<SeaData> findByVCommend(short vCommend,Pageable pageable);
	
	@Query("select s from SeaData s where s.vCommend > ?1 order by s.vId desc")
	List<SeaData> findByVCommendFromParam(int vCommend,Pageable pageable);
	
	@Query("select s from SeaData s order by s.vCommend desc")
	Page<SeaData> findAllByPage(Pageable pageable);
	
}
