package com.gb.crawler.sougouwx;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gb.dao.ISeaNewDao;
import com.gb.entity.SeaNew;
import com.geccocrawler.gecco.pipeline.Pipeline;


/**
 * @Description :
 * @Author :WeiHui.Zhang
 * @Data : 2016/3/30 10:51
 * @Version:1.0.0
 */
@Service
public class SaveWeiXinPipeline implements Pipeline<Article> {
	
	@Autowired
	ISeaNewDao iSeaNewDao;
	
	@Override
	public void process(Article bean) {
//		System.out.println("aa###########"+JacksonJsonUtil.objectToJsonString(bean));
//		System.out.println("aa###########"+JacksonJsonUtil.objectToJsonString(bean));
		SeaNew seaNew=new SeaNew();
		seaNew.setNTitle(bean.getTitle());
		String body=bean.getBody();
//		body=body.replaceAll("data-src=\"", "src=\"http://bbkdj.vicp.cc/pic.php?pic=");  //图片处理
		Document doc = Jsoup.parseBodyFragment(body);
		Elements elements = doc.getElementsByTag("img");
		for(Element element:elements) {
			element.removeAttr("style");
			element.attr("src", "http://bbkdj.vicp.cc/pic.php?pic="+element.attr("data-src"));
			element.attr("width","650px");
		}
		
		seaNew.setNContent(doc.toString());
		seaNew.setTid(41);
		seaNew.setNPic("");
		seaNew.setNColor("");
		seaNew.setNColor("");
		seaNew.setNLetter("");
		
		iSeaNewDao.save(seaNew);
		
	}
}
