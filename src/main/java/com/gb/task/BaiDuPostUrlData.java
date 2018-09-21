package com.gb.task;

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
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gb.common.util.RandomUtils;
import com.gb.dao.ISeaDataDao;
import com.gb.dao.ISeaTypeDao;
import com.gb.emum.SeaCmsMakeHtmlCodeEnum;
import com.gb.entity.SeaData;
import com.gb.entity.SeaType;
import com.gb.service.specification.SimpleSpecificationBuilder;
import com.gb.service.specification.SpecificationOperator.Operator;

/**
 * 豆瓣api 接口数据采集，用于更新推荐数据
 * @author huangdb
 *
 *@date 2018年4月27日
 */
@Component
@Lazy(false)
@EnableScheduling
public class BaiDuPostUrlData {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISeaDataDao iSeaDataDao;
	
	@Value("${application.seacms.collectData.url_baidu}")
	private String collectDataUrl1;
	
	@Scheduled(cron = "${task.time.seacms.collectData_baidu}")
	public void timerCron() {
		postData(collectDataUrl1,0);
	}
	
	public void postData(String url,int pageNumber) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			Pageable pageable = new PageRequest(pageNumber, 100);
			Page<SeaData> seadatas=iSeaDataDao.findAllByPage(pageable);
			
			StringBuilder stringBuilder=new StringBuilder();

			int enumLength=SeaCmsMakeHtmlCodeEnum.values().length;
			String uString="http://www.178kp.com/%s";
			SeaCmsMakeHtmlCodeEnum[] enumArray=SeaCmsMakeHtmlCodeEnum.values();
			for (SeaData seaData : seadatas) {
				int r=RandomUtils.getRandom(0, enumLength-1);
				SeaCmsMakeHtmlCodeEnum code=enumArray[r];
				String k = code.getCode();
				stringBuilder.append(String.format(uString, "news/"+k +"/"+ seaData.getVId() + ".html"));
				stringBuilder.append("\n");
				
				stringBuilder.append(String.format(uString, "movie/index"+ seaData.getVId() + ".html"));
				stringBuilder.append("\n");
			}
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/plain");
			
			HttpEntity<String> entity = new HttpEntity<String>(stringBuilder.toString(),headers);
			ResponseEntity<byte[]> res = restTemplate.exchange(url, HttpMethod.POST, entity,
					byte[].class);
			
			byte[] result = res.getBody();
			String str=new String(result);
			logger.info("baidu response:"+str);
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String,Object> returnMap = objectMapper.readValue(str, Map.class);
			int remain=(int) returnMap.get("remain");
			System.out.println("remain:"+remain);
			if(remain>0 && (pageNumber+1)<seadatas.getTotalPages()) {
				postData(url,pageNumber+1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	

}
