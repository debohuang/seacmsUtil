package com.gb;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gb.common.util.JacksonJsonUtil;
import com.gb.common.util.RandomUtils;
import com.gb.emum.SeaCmsMakeHtmlCodeEnum;
import com.gb.entity.DoubanData;

public class Test {
	
	@Ignore  
	@org.junit.Test
	public void jodaTimeTest() {
		DateTime today = new DateTime();
        DateTime datetorrow = today.plusDays(1);

        System.out.println(today.toString("yyyy-MM-dd"));//2017-06-26
        System.out.println(today.toString("yyyy-MM-dd HH:mm:ss"));//2017-06-26 22:04:03
        System.out.println(datetorrow.toString("yyyy-MM-dd"));//2017-06-27
	}
	
	@Ignore 
	@org.junit.Test
	public void test() {
//		Calendar c = Calendar.getInstance();
//		String cYmd = ObjectConverter.calendarToString(c,"yyyy-MM-dd");
		String cYmd="2016-8-29";
		Timestamp fromTs = Timestamp.valueOf(cYmd + " 00:00:00");//Timestamp aa = new Timestamp(0l);
		Timestamp toTs = Timestamp.valueOf(cYmd + " 23:59:59");
		
		System.out.println(fromTs.getTime()/1000);
		System.out.println(toTs.getTime()/1000);
	}
	
	@Ignore
	@org.junit.Test
	public void enumTest() {
//		for (SeaCmsMakeHtmlCodeEnum code : SeaCmsMakeHtmlCodeEnum.values()) {  
//			String k=code.getCode();
//			String v=code.getMsg();
//			if(v.contains("")) {
//				v=String.format(v, "eee");
//				System.out.println(v);
//			}
//			
//        }
		
//	    String str=null;
//	    str=String.format("Hi,%s", "飞龙","4444飞龙");          // 格式化字符串
//		System.out.println(str);
		
		int enumLength=SeaCmsMakeHtmlCodeEnum.values().length;
		SeaCmsMakeHtmlCodeEnum[] enumArray=SeaCmsMakeHtmlCodeEnum.values();
//		for (SeaData seaData : seadatas) {  //转换到新list中,因为seadatas1.getContent()无法再添加
			int r=RandomUtils.getRandom(0, enumLength-1);
			SeaCmsMakeHtmlCodeEnum s=enumArray[r];
			System.out.println(s.getCode());
//		}
		
	}
	
	@Ignore
	@org.junit.Test
	public void httpGetTest() {
		RestTemplate restTemplate = new RestTemplate();
		String url="https://api.douban.com/v2/movie/in_theaters?city=&start=0&count=20";
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("appId", "");
			headers.add("X_AUTH_TOKEN", "");
			headers.add("sign", "");
			
			HttpEntity<String> entity = new HttpEntity<String>("",headers);
			ResponseEntity<byte[]> res = restTemplate.exchange(url, HttpMethod.GET, entity,
					byte[].class);
			
			byte[] result = res.getBody();
			String str=new String(result);
			System.out.println(str);
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String,Object> returnMap = objectMapper.readValue(str, Map.class);
			
//			str=returnMap.get("title").toString();
			List<Object> subjects=(List<Object>) returnMap.get("subjects");
			for (int i = 0; i < subjects.size(); i++) {
				Object object=subjects.get(i);
				Map<String, Object> subObject=(Map<String, Object>) object;
				String title=subObject.get("title").toString();
				System.out.println(title);
			}
			
			
			int count=(int) returnMap.get("count");
			int start=(int) returnMap.get("start");
			int total=(int) returnMap.get("total");
			int curCount=count+start;
			if(curCount < total) {
				url="https://api.douban.com/v2/movie/in_theaters?city=&start="+curCount+"&count=20";
				System.out.println(url);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
	}
	
	
	@org.junit.Test
	public void httpGetPost() {
		RestTemplate restTemplate = new RestTemplate();
		String url="http://data.zz.baidu.com/urls?site=www.178kp.com&token=uodKMA1Jl3HOLIEs";
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/plain");
			
			StringBuilder stringBuilder=new StringBuilder();
			stringBuilder.append("http://www.178kp.com");
			stringBuilder.append("\n");
			stringBuilder.append("http://www.178kp.com/news/720p/43804.html");
			
			HttpEntity<String> entity = new HttpEntity<String>(stringBuilder.toString(),headers);
			ResponseEntity<byte[]> res = restTemplate.exchange(url, HttpMethod.POST, entity,
					byte[].class);
			
			byte[] result = res.getBody();
			String str=new String(result);
			System.out.println(str);
//			ObjectMapper objectMapper = new ObjectMapper();
//			Map<String,Object> returnMap = objectMapper.readValue(str, Map.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
	}
	
	
}
