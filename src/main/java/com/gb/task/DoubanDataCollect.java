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
import com.gb.common.util.HttpUtil;
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
public class DoubanDataCollect {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISeaDataDao iSeaDataDao;
	
	@Value("${application.seacms.collectData.url_douban1}")
	private String collectDataUrl1;
	
	@Value("${application.seacms.collectData.url_douban2}")
	private String collectDataUrl2;
	
	@Value("${application.seacms.collectData.url_douban3}")
	private String collectDataUrl3;
	
	
	@Scheduled(cron = "${task.time.seacms.collectData_douban}")
	public void timerCron() {
		String url=collectDataUrl1.replace("{start}", "0");
		collectData(collectDataUrl1,url);
		
		url=collectDataUrl2.replace("{start}", "0");
		collectData(collectDataUrl2,url);
		
		url=collectDataUrl3.replace("{start}", "0");
		collectData(collectDataUrl3,url);
		
	}
	
	public void collectData(String collectDataUrl,String url) {
//		RestTemplate restTemplate = new RestTemplate();
//		String url="https://api.douban.com/v2/movie/in_theaters??city=&start=0&count=20";
		try {
			logger.info("collectData start,url:"+url);
			
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
//			
//			HttpEntity<String> entity = new HttpEntity<String>("",headers);
//			ResponseEntity<byte[]> res = restTemplate.exchange(url, HttpMethod.GET, entity,
//					byte[].class);
//			
//			byte[] result = res.getBody();
//			String str=new String(result);
			
			String str=HttpUtil.get(url);
			System.out.println(str);
			
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String,Object> returnMap = objectMapper.readValue(str, Map.class);  //任何的json字符串，应该都能转成 Map<String,Object>
			
			List<Object> subjects=(List<Object>) returnMap.get("subjects");  //根据json字符串格式，一层层的转化解析
			for (int i = 0; i < subjects.size(); i++) {
				Object object=subjects.get(i);
				Map<String, Object> subObject=(Map<String, Object>) object;
				String title=subObject.get("title").toString();
//				System.out.println(title);
				SimpleSpecificationBuilder<SeaData> builder = new SimpleSpecificationBuilder<SeaData>();
				if(StringUtils.isNotBlank(title)){
					builder.add("vName", Operator.eq.name(), title);
				}
				List<SeaData> seadatas=iSeaDataDao.findAll(builder.generateSpecification());
				if(seadatas!=null && seadatas.size()>0) {
					for(SeaData seaData:seadatas) {
						seaData.setVCommend((short)5);	//设置为5星,系统查询时不单是根据推荐星数，还根据v_addtime
						iSeaDataDao.saveAndFlush(seaData); 
					}
				}
			}
			
			int count=(int) returnMap.get("count");
			int start=(int) returnMap.get("start");
			int total=(int) returnMap.get("total");
			int curCount=count+start;
			if(curCount < total) {
//				url="https://api.douban.com/v2/movie/in_theaters?city=&start="+curCount+"&count=20";
				url=collectDataUrl.replace("{start}", Integer.toString(curCount));
				collectData(collectDataUrl,url);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	

}
