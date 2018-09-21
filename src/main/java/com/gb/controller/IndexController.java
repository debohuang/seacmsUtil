package com.gb.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gb.common.util.JsonResult;
import com.gb.common.util.RandomUtils;
import com.gb.component.FreemarkerMakeHtml;
import com.gb.controller.support.BaseController;
import com.gb.dao.ISeaDataDao;
import com.gb.dao.ISeaTypeDao;
import com.gb.emum.SeaCmsMakeHtmlCodeEnum;
import com.gb.entity.SeaData;
import com.gb.entity.SeaType;
import com.gb.handle.SeaCmsContentHandle;
import com.gb.service.ISeaDataService;

@Controller
@RequestMapping("/news")
public class IndexController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ISeaDataDao iSeaDataDao;

	@Autowired
	ISeaTypeDao iSeaTypeDao;
	
	@Autowired
	ISeaDataService seaDataService;
	
	@Autowired
	SeaCmsContentHandle seaCmsMakeHtml;
	
	@Autowired
	private FreemarkerMakeHtml freemarkerMakeHtml;

	@Value("${application.seacms.contentFilePath}")
	private String contentFilePath;

	@RequestMapping(value = { "/{path}/{id}.html", "/index.html" })
	public String index(@PathVariable String path,@PathVariable Integer id,ModelMap map) {
		
		logger.debug("进入内容页");
		if(StringUtils.isBlank(path)) {
			path="bdyp";
		}
		if(id==null) {
			id=0;
		}
		
		// 推荐数据
		Pageable pageable1 = new PageRequest(0, 20);
//		List<SeaData> commendSeadatas = iSeaDataDao.findByVCommend((short) 5, pageable1);
		List<SeaData> commendSeadatas = seaDataService.findByVCommend((short) 5, pageable1);
		
//		SeaData seaData=iSeaDataDao.findOne(id);
		SeaData seaData=seaDataService.findOne(id);
		if(seaData==null){
			seaData = commendSeadatas.get(0);
		}
		
		int startVid=(seaData.getVId()-12)<=0?1:(seaData.getVId()-12);
		int endVid=(seaData.getVId()-12)<=0?24:(seaData.getVId()+12);
//		List<SeaData> nearSeadatas=iSeaDataDao.findByvIdBetween(startVid, endVid);
		List<SeaData> nearSeadatas=seaDataService.findByvIdBetween(startVid, endVid);
		
		//推荐数据
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
				map1.put("link", "/news/"+k +"/"+ seaData1.getVId() + ".html");
				map1.put("VPic", seaData1.getVPic());
				map1.put("VAddtime", seaData1.getVAddtime());

				newCommendlist.add(map1);
			}
		}

		// 相关数据
		List<Map> newlist = new ArrayList<Map>();
		for (SeaCmsMakeHtmlCodeEnum code : SeaCmsMakeHtmlCodeEnum.values()) {
			String k = code.getCode();
			String v = code.getMsg();
			v = String.format(v, seaData.getVName(), seaData.getVState());
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("name", v);
			map1.put("link", "/news/"+k +"/"+ seaData.getVId() + ".html");
			map1.put("VPic", seaData.getVPic());
			map1.put("VAddtime", seaData.getVAddtime());

			newlist.add(map1);
		}
		
		String newDataName="";
		for (SeaCmsMakeHtmlCodeEnum code : SeaCmsMakeHtmlCodeEnum.values()) {
			String k = code.getCode();
			String v = code.getMsg();
			v = String.format(v, seaData.getVName(), seaData.getVState());
			if(path.equals(k)) { //设置名称
//				seaData.setVName(v);
				newDataName=v;
			}
		}
		
		// Collections.shuffle(commendSeadatas); // 利用集合(collections）的静态方法，打乱list集合顺序
		Collections.shuffle(newlist); 
		Collections.shuffle(newCommendlist); 
		
		map.put("dataName", seaData.getVName());
		map.put("newDataName", newDataName);
		map.put("data", seaData);
		
		map.put("listData", nearSeadatas); //// 其他数据
		map.put("commendSeadatas", commendSeadatas); // 推荐
		map.put("newCommendlist", newCommendlist); // 新推荐数据
//		map.put("otherListData", list); // 其他相关数据
		map.put("newListData", newlist); // 相关数据

		Pageable pageable2 = new PageRequest(0, 6);
		List<SeaType> seaTypes = iSeaTypeDao.findByupidFrom(pageable2);
		map.put("listTypeData", seaTypes);

		return "seacms/content3";
	}

	@RequestMapping(value = {"/list.html"})
	public String list(ModelMap map) {
		
//		Page<SeaData> seadatas=iSeaDataDao.findAllByPage(getPageRequest());
		Page<SeaData> seadatas=seaDataService.findAllByPage(getPageRequest());
		
		// 推荐数据
		Pageable pageable1 = new PageRequest(0,30);
//		List<SeaData> commendSeadatas = iSeaDataDao.findByVCommend((short) 5, pageable1);
		List<SeaData> commendSeadatas = seaDataService.findByVCommend((short) 5, pageable1);
		
		int enumLength=SeaCmsMakeHtmlCodeEnum.values().length;
		SeaCmsMakeHtmlCodeEnum[] enumArray=SeaCmsMakeHtmlCodeEnum.values();
		for (SeaData seaData : seadatas) {  //转换到新list中,因为seadatas1.getContent()无法再添加
			
			int r=RandomUtils.getRandom(0, enumLength-1);
			SeaCmsMakeHtmlCodeEnum code=enumArray[r];
			String k = code.getCode();
			String v = code.getMsg();
			v = String.format(v, seaData.getVName(), seaData.getVState());
			seaData.setVName(v);
			seaData.setVLongtxt("/news/"+k +"/"+ seaData.getVId() + ".html");
		}
		
		for (SeaData seaData : commendSeadatas) {
			int r=RandomUtils.getRandom(0, enumLength-1);
			SeaCmsMakeHtmlCodeEnum code=enumArray[r];
			String k = code.getCode();
			String v = code.getMsg();
			v = String.format(v, seaData.getVName(), seaData.getVState());
//			seaData.setVName(v);
			seaData.setVPsd(v);  //代替vName
			seaData.setVLongtxt("/news/"+k +"/"+ seaData.getVId() + ".html");
		}
		
		//类型数据
		Pageable pageable2 = new PageRequest(0, 6);
		List<SeaType> seaTypes = iSeaTypeDao.findByupidFrom(pageable2);
		
		Collections.shuffle(commendSeadatas);
		
		map.put("data", seadatas);
		map.put("commendSeadatas", commendSeadatas);
		map.put("listTypeData", seaTypes);
		
		return "seacms/content2";
	}
	
	@ResponseBody
	@RequestMapping(value = {"/make"})
	public JsonResult make(ModelMap map) {
		try {
			String cYmd="2016-8-29";
			Timestamp fromTs = Timestamp.valueOf(cYmd + " 00:00:00");//Timestamp aa = new Timestamp(0l);
			Timestamp toTs = Timestamp.valueOf(cYmd + " 23:59:59");
			Pageable pageable=new PageRequest(0,15);
			
			seaCmsMakeHtml.makeByData((int)(fromTs.getTime()/1000), (int)(toTs.getTime()/1000),pageable);
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	
}
