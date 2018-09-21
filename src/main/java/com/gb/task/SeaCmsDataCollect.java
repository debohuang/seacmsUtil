package com.gb.task;

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

import com.gb.common.util.HttpUtil;
import com.gb.dao.ISeaDataDao;
import com.gb.dao.ISeaTypeDao;
import com.gb.emum.SeaCmsMakeHtmlCodeEnum;
import com.gb.entity.SeaData;
import com.gb.entity.SeaType;

import net.sf.ehcache.constructs.refreshahead.ThreadedWorkQueue;

@Component
@EnableScheduling
public class SeaCmsDataCollect {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${application.seacms.siteUrl}")
	private String siteUrl;
	
	@Value("${application.seacms.collectData.url}")
	private String collectDataUrl;
	
	@Value("${application.seacms.collectData.url_m3u8}")
	private String collectDataUrlM3u8;
	
	@Scheduled(cron = "${task.time.seacms.collectData}")
	public void timerCron() {
		logger.info("---------collectData start-----------");
		collectData(collectDataUrl);
		logger.info("---------collectData end-----------");
	}
	
	/**
	 * 采集m3u8数据
	 */
	@Scheduled(cron = "${task.time.seacms.collectData_m3u8}")
	public void timerCron1() {
		logger.info("---------collectData_m3u8 start-----------");
		collectData(collectDataUrlM3u8);
		logger.info("---------collectData_m3u8 end-----------");
	}
	
	public void collectData(String url) {
//		RestTemplate restTemplate = new RestTemplate();
		try {
//			HttpHeaders headers = new HttpHeaders();
//			HttpEntity<String> entity = new HttpEntity<String>("",headers);
//			ResponseEntity<byte[]> res = restTemplate.exchange(url, HttpMethod.GET, entity,
//					byte[].class);
//			byte[] result = res.getBody();
//			String str=new String(result);
			
			String str=HttpUtil.get(url);
//			logger.info(str);
			logger.info(url);
			if((str!=null || str!="") && str.contains("即将开始同步") ) {
				str=str.substring(str.indexOf("href=")+6,str.indexOf("php")+3);
				collectData(siteUrl+"/cj/fewfwesd454545.php"+str);
			}
			
			if((str!=null || str!="") && str.contains("获取资源失败") ) {
//				System.out.println("curPage="+url.substring(url.indexOf("pg=")+3, url.indexOf("&url=")));
				int aa=Integer.parseInt(url.substring(url.indexOf("pg=")+3, url.indexOf("&url=")));
				aa=aa+1;

				url=url.substring(0, url.indexOf("pg=")+3)+aa+url.substring(url.indexOf("&url="), url.length());
//				System.out.println(url);
				logger.info("new start:"+url);
				Thread.sleep(5000);
				collectData(url);  //继续获取
			}
			
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("采集失败!", e);
			collectData(url);  //继续获取
		}
		
	}
	

}
