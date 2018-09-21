package com.gb.component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

@Component
public class FreemarkerMakeHtml {
	
	@Autowired
	Configuration config;
	
	public void makeTemplateHtml(Map<String, Object> map,String templatePath,String filepath){
		Writer out=null;
		try {
//			String path = ResourceUtils.getURL("classpath:").getPath();
			config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			Template temple = config.getTemplate(templatePath);// 获取模板
			out = new OutputStreamWriter(new FileOutputStream(filepath));// 生成最终页面并写到文件
			temple.process(map, out);// 处理
		}catch (TemplateException e1) {
			e1.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}  finally {
//			try {
//				out.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}

	}
	
}
