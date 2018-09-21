package com.gb.component.crawler;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.spider.SpiderBean;

@Component
public class ConsolePipeline implements Pipeline<SpiderBean> {

	@Override
	public void process(SpiderBean bean) {
		System.out.println("consolePipeline out:"+JSON.toJSONString(bean));
	}

}
