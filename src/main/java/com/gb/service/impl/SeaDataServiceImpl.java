package com.gb.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gb.dao.ISeaDataDao;
import com.gb.dao.support.IBaseDao;
import com.gb.entity.SeaData;
import com.gb.service.ISeaDataService;
import com.gb.service.support.impl.BaseServiceImpl;

/**
 * 
 * @author huangdb
 *
 *@date 2018年4月18日
 */
@Service
public class SeaDataServiceImpl extends BaseServiceImpl<SeaData, Integer> implements ISeaDataService {
	@Autowired
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private ISeaDataDao SeaDataDao;

	@Override
	public IBaseDao<SeaData, Integer> getBaseDao() {
		return this.SeaDataDao;
	}

	@Override
	@Cacheable(value = "resourceCache", key = "'starttime' + #starttime")
	public Page<SeaData> findByvAddtimePage(int starttime, int endtime, Pageable pageable) {
		return SeaDataDao.findByvAddtimePage(starttime, endtime, pageable);
	}

	@Override
	@Cacheable(value = "resourceCache", key = "'starttimePage' + #starttime")
	public List<SeaData> findByvAddtimeBetweenTime(int starttime, int endtime, Pageable pageable) {
		return SeaDataDao.findByvAddtimeBetweenTime(starttime, endtime, pageable);
	}

	@Override
	@Cacheable(value = "resourceCache", key = "'findByvIdBetween' + #startVid")
	public List<SeaData> findByvIdBetween(int startVid, int endVid) {
		return SeaDataDao.findByvIdBetween(startVid, endVid);
	}

	@Override
	@Cacheable(value = "resourceCache", key = "'findByVCommend' + #vCommend")
	public List<SeaData> findByVCommend(short vCommend, Pageable pageable) {
		return SeaDataDao.findByVCommend(vCommend, pageable);
	}

	@Override
	@Cacheable(value = "resourceCache", key = "'findByVCommendFromParam' + #vCommend")
	public List<SeaData> findByVCommendFromParam(int vCommend, Pageable pageable) {
		return SeaDataDao.findByVCommendFromParam(vCommend, pageable);
	}

	@Override
	@Cacheable(value = "resourceCache", key = "'findAllByPage' + #pageable")
	public Page<SeaData> findAllByPage(Pageable pageable) {
		return SeaDataDao.findAllByPage(pageable);
	}

	@Override
	@Cacheable(value = "resourceCache", key = "'findOne' + #id")
	public SeaData findOne(int id) {
		return SeaDataDao.findOne(id);
	}

}
