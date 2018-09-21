package com.gb.freemaker;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FreemakerMakeHtml {

	@Autowired
	Configuration config;

	@Test
	public void makeHtml() throws IOException {
		Writer out=null;
		try {
			String path = ResourceUtils.getURL("classpath:").getPath();
			 System.out.println("###########"+path);
			config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			Template temple = config.getTemplate("test/index.ftl");// 获取模板
			out = new OutputStreamWriter(new FileOutputStream(path + "1.html"));// 生成最终页面并写到文件
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "test FreemakerMakeHtml title");
			map.put("content", "test FreemakerMakeHtml");
			
			
			temple.process(map, out);// 处理
			
		}catch (TemplateException e1) {
			e1.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}  finally {
			out.close();
		}

	}

}
