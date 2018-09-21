package com.gb.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gb.entity.SeaData;
import com.gb.service.support.IBaseService;

/**
 * 
 * @author huangdb
 *
 *@date 2018年4月18日
 */
public interface ISeaDataService extends IBaseService<SeaData, Integer> {
	
	Page<SeaData> findByvAddtimePage(int starttime,int endtime,Pageable pageable);
	
	List<SeaData> findByvAddtimeBetweenTime(int starttime,int endtime,Pageable pageable);
	
	//根据id查询前后记录
	List<SeaData> findByvIdBetween(int startVid,int endVid);
	
	List<SeaData> findByVCommend(short vCommend,Pageable pageable);
	
	List<SeaData> findByVCommendFromParam(int vCommend,Pageable pageable);
	
	Page<SeaData> findAllByPage(Pageable pageable);
	
	abstract SeaData findOne(int id);
	
}
