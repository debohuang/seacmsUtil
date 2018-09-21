package com.gb.handle;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.gb.component.FreemarkerMakeHtml;
import com.gb.dao.ISeaDataDao;
import com.gb.dao.ISeaTypeDao;
import com.gb.emum.SeaCmsMakeHtmlCodeEnum;
import com.gb.entity.SeaData;
import com.gb.entity.SeaType;

@Component
public class SeaCmsChannelHandle {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISeaDataDao iSeaDataDao;

	@Autowired
	ISeaTypeDao iSeaTypeDao;

	@Autowired
	private FreemarkerMakeHtml freemarkerMakeHtml;

	@Value("${application.seacms.contentFilePath}")
	private String contentFilePath;
	
	
	public void makeByData(int startTime,int endtime, Pageable pageable) {
		Page<SeaData> seadatas=iSeaDataDao.findByvAddtimePage(startTime, endtime,pageable);
		if(seadatas==null || seadatas.getTotalPages()==0 || (seadatas.getTotalPages()<pageable.getPageNumber())) {
			return;
		}
//		for (int i = 0; i < seadatas.getContent().size(); i++) {
//			SeaData seaData=seadatas.getContent().get(i);
//			System.out.println(seaData.getVName());
//		}
		//获取多一页，增加单页页的关联性
		Page<SeaData> seadatas1=null;
		Pageable newPageable1 =null;
		if(seadatas.getTotalPages()>pageable.getPageNumber()) {
			 newPageable1 = new PageRequest(pageable.getPageNumber()+1, pageable.getPageSize());
		}else {
			 newPageable1 = new PageRequest(pageable.getPageNumber()-1, pageable.getPageSize());
		}
		seadatas1=iSeaDataDao.findByvAddtimePage(startTime, endtime,newPageable1);
		List<SeaData> nearSeadatas=new ArrayList<SeaData>();
		for (SeaData seaData : seadatas1) {  //转换到新list中,因为seadatas1.getContent()无法再添加
			nearSeadatas.add(seaData);
		}
		
		makeHtml(seadatas.getContent(),nearSeadatas);  //生成页面
		logger.info("startTime:"+startTime+",endtime："+endtime+",pageSize:"+seadatas.getTotalPages()+",curPage:"+pageable.getPageNumber());
		
		if(seadatas.getTotalPages()>=pageable.getPageNumber()) {
			Pageable newPageable = new PageRequest(pageable.getPageNumber()+1, pageable.getPageSize());
			makeByData(startTime,endtime,newPageable);
		}
	}
	
	
	public void makeHtml(List<SeaData> seadatas,List<SeaData> nearSeadatas) {

//		String cYmd = "2016-8-29";
//		Timestamp fromTs = Timestamp.valueOf(cYmd + " 00:00:00");// Timestamp aa = new Timestamp(0l);
//		Timestamp toTs = Timestamp.valueOf(cYmd + " 23:59:59");
//
//		// 新增数据
//		Pageable pageable = new PageRequest(0, 15);
//		List<SeaData> seadatas = iSeaDataDao.findByvAddtimeBetweenTime((int) (fromTs.getTime() / 1000),
//				(int) (toTs.getTime() / 1000), pageable);

		// 推荐数据
		Pageable pageable1 = new PageRequest(0, 20);
		List<SeaData> commendSeadatas = iSeaDataDao.findByVCommend((short) 5, pageable1);

		// 类型
		Pageable pageable2 = new PageRequest(0, 6);
		List<SeaType> seaTypes = iSeaTypeDao.findByupidFrom(pageable2);

		for (int i = 0; i < seadatas.size(); i++) {
			SeaData seaData = seadatas.get(i);
			SeaType seaType=iSeaTypeDao.findOne(seaData.getTid());
			seaData.setSeaType1(seaType);
			
//			int startVid=(seaData.getTid()-12)<=0?1:(seaData.getTid()-12);
//			int endVid=(seaData.getTid()-12)<=0?12:(seaData.getTid()+12);
//			List<SeaData> nearSeadatas=iSeaDataDao.findByvIdBetween(startVid, endVid);
			
			nearSeadatas.add(seaData);
			createNewData(seaData, nearSeadatas, commendSeadatas, seaTypes);
		}

	}

	public void createNewData(SeaData seaData, List<SeaData> seadatas, List<SeaData> commendSeadatas,
			List<SeaType> seaTypes) {

		List<Map> newlist = new ArrayList<Map>();
		List<Map> copyNewlist = new ArrayList<Map>();
		
		// 获取同一seadata,所有链接集合
		for (SeaCmsMakeHtmlCodeEnum code : SeaCmsMakeHtmlCodeEnum.values()) {
			String k = code.getCode();
			String v = code.getMsg();
			v = String.format(v, seaData.getVName(), seaData.getVState());

			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("name", v);
			map1.put("link", k + seaData.getVId() + ".html");
			map1.put("VPic", seaData.getVPic());
			map1.put("VAddtime", seaData.getVAddtime());

			newlist.add(map1);
			copyNewlist.add(map1);
		}

		// 新推荐数据
		List<Map> newCommendlist = new ArrayList<Map>();
		for (int i = 0; i < commendSeadatas.size(); i++) {
			SeaData seaData1 = commendSeadatas.get(i);
			if (seaData1.getVId() == seaData.getVId()) {
				continue;
			}
			// 获取同一seadata,所有链接集合
			for (SeaCmsMakeHtmlCodeEnum code : SeaCmsMakeHtmlCodeEnum.values()) {
				String k = code.getCode();
				String v = code.getMsg();
				v = String.format(v, seaData1.getVName(), seaData1.getVState());

				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("name", v);
				map1.put("link", k + seaData1.getVId() + ".html");
				map1.put("VPic", seaData1.getVPic());
				map1.put("VAddtime", seaData1.getVAddtime());

				newCommendlist.add(map1);
			}
		}
		
//		logger.info("data:"+seaData.getVName()+",size:"+newlist.size());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataName", seaData.getVName());
		map.put("listTypeData", seaTypes);
		for (int i = 0; i < newlist.size(); i++) {
			Map<String, String> newSeaData = newlist.get(i);
			seaData.setVName(newSeaData.get("name"));

			Collections.shuffle(seadatas);
			Collections.shuffle(newCommendlist);
			Collections.shuffle(copyNewlist);

			map.put("data", seaData);
			map.put("listData", seadatas);
			map.put("listCommendData", commendSeadatas); // 推荐
			map.put("newCommendlist", newCommendlist); // 新推荐数据
			map.put("newListData", copyNewlist); // 相关数据

			freemarkerMakeHtml.makeTemplateHtml(map, "seacms/content1.ftl", contentFilePath + newSeaData.get("link"));
			map.remove("data");
			map.remove("listData");
			map.remove("listCommendData");
			map.remove("newCommendlist");
			map.remove("newListData");
		}
	}

}
