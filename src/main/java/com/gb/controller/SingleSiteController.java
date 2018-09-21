package com.gb.controller;

import java.sql.Timestamp;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gb.common.util.JsonResult;
import com.gb.component.crawler.SpringContextUtil;
import com.gb.component.crawler.SpringPipelineFactory;
import com.gb.controller.support.BaseController;
import com.gb.handle.SeaCmsContentHandle;
import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.request.HttpGetRequest;

@Controller
@RequestMapping("/s")
public class SingleSiteController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SpringPipelineFactory springPipelineFactory;
	
	@RequestMapping(value = { "/", "/index.html" })
	public String index(ModelMap map) {
		
		logger.debug("进入首页");
		
		String url="https://api.douban.com/v2/movie/subject/24773958";
		
		RestTemplate restTemplate = new RestTemplate();
		try {
			logger.info("collectData start,url:"+url);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
			
			HttpEntity<String> entity = new HttpEntity<String>("",headers);
			ResponseEntity<byte[]> res = restTemplate.exchange(url, HttpMethod.GET, entity,
					byte[].class);
			
			byte[] result = res.getBody();
			String str=new String(result);
			System.out.println(str);
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String,Object> returnMap = objectMapper.readValue(str, Map.class);  //任何的json字符串，应该都能转成 Map<String,Object>
			map.put("data", returnMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return "single/index";
	}
	
	@ResponseBody
	@RequestMapping(value = { "/cj"})
	public JsonResult cj(ModelMap map) {
		logger.debug("进入采集页");
		try {
			HttpGetRequest start = new HttpGetRequest("http://weixin.sogou.com/weixin?type=2&query=灭霸被儿子完虐，失去力量的灭霸如此窘迫让人心痛");
			start.addCookie("SNUID", "1D22392EF4F6C4A92076C208F4DE3AAB");
			start.addCookie("SUID", "EED1CDDA6B20900A00000000570E1872");
			start.addCookie("IPLOC", "CN1100");
			//start.addCookie("SUV", "1460541527037365");
			start.addHeader("Host", "weixin.sogou.com");
			start.addHeader("Upgrade-Insecure-Requests", "1");
			start.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			start.addHeader("Accept-Encoding", "gzip, deflate, sdch");
			start.addHeader("Cache-Control", "max-age=0");
			
//			SpringPipelineFactory springPipelineFactory = SpringContextUtil.getBean("springPipelineFactory");
			
			GeccoEngine.create()
			.pipelineFactory(springPipelineFactory)
			.classpath("com.gb.crawler.sougouwx")
			.start(start)
			.interval(100)
			.run();
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		
		return JsonResult.success();
	}
	
	
	
}
