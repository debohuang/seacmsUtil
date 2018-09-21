package com.gb.crawler.sougouwx;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Html;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * 
 * @author huangdb
 *
 *@date 2018年5月9日
 */
//@Gecco(matchUrl="http://mp.weixin.qq.com/s?{params}", pipelines= "articlePipeline")

//使用自定义的正则表达式规则。类似Jersey的@Path语法。{} 括号里里，添加自定义的正则表达式，匹配http 和https
@Gecco(matchUrl="{(http[s]?)}://mp.weixin.qq.com/s?{params}", pipelines= {"consolePipeline","saveWeiXinPipeline"})
public class Article implements HtmlBean {

	private static final long serialVersionUID = 5310736056776105882L;
	
	@HtmlField(cssPath="#activity-name")
	private String title;
	
//	@Text(own=false)
	@Html()
	@HtmlField(cssPath="#js_content")
	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
