package com.gb.crawler.sougouwx;

import com.geccocrawler.gecco.annotation.GeccoClass;
import com.geccocrawler.gecco.downloader.BeforeDownload;
import com.geccocrawler.gecco.request.HttpRequest;

@GeccoClass(Article.class)
public class BeforeArticleDownloader implements BeforeDownload {

	@Override
	public void process(HttpRequest request) {
		request.clearCookie();
		request.clearHeader();
	}

}
