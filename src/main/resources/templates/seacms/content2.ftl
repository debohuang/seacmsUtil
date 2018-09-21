<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta name="applicable-device" content="pc,mobile">
<title>热门影视新闻当前${data.number+1}页_草民看片网</title>
<meta name="keywords" content="" />

<#include "/seacms/head_include.ftl">
</head>
<body>
	<#include "/seacms/head.ftl">
	<div class="wrap clearfix">
		<div class="pleft"></div>
	</div>

	<div class="container" style="height: 50px;">
		<div style="margin: 10px auto; text-align: center;"></div>
	</div>

	<div class="container" style="height: 50px;">
		<div class="color">
			<div style="margin: 0px auto; text-align:"></div>
			<div class="row">
				<div class="col-md-8 col-xs-12 jide slides-border"
					style="padding: 22px;">
				
					<div class="all-title">
						<h2>热门影视新闻</h2>
					</div>
					<ul class="list_module_img row margin">

						<#assign m=10 />
						<#list 0..(m-1) as j> 
						<#assign ls4=(data.content)[j] />
						<li><a class="list-img aleft col-md-3"
							href="${ls4.VLongtxt}" title="${ls4.VName}" target="_blank"
							style="height: auto; padding-bottom: 17.6%"><img
								class="loading" src="${ls4.VPic}" data-original="${ls4.VPic}"
								alt="${ls4.VName}" style="display: block;"><label
								class="name">${ls4.VName}…</label></a>
							<div class="news-info col-md-9">
								<h2>
									<a href="${ls4.VLongtxt}" title="${ls4.VName}" target="_blank">${ls4.VName}</a>
								</h2>
								<span>${(ls4.VAddtime*1000)?number_to_datetime}</span>
								<p style="height: 74px;">
									${ls4.VName}。大家关注的${ls4.VName}即将上映了，那么具体的上映时间是多久呢?是否会和大家期望的一样?下面就是小编为大家提供的关于${ls4.VName}：…</p>
							</div></li>
						</#list>

					</ul>
					<!--
					<div class="col-md-12 padding">
						<div class="tt">
							<a href="${site.url}/news/type1.html"
								class="pull-right">更多&gt;&gt;</a> <span><a
								href="${site.url}/news/type.html">最新视频</a></span>
						</div>
						<div class=" slides list_module_img " id="mdear">
							<ul>

								<#assign k=6 />
								<#list 1..(k) as j> 
								<#assign ls5=(data.content)[j] />
								<li class="col-md-2 col-xs-4"><a class="list-img "
									href="/news/xinwei${ls5.VId?c}.html"> <img class="loading"
										src="${ls5.VPic}" data-original="${ls5.VPic}"
										alt="${ls5.VName}" style="display: block;"> <label
										class="name">${ls5.VName}</label>
								</a></li>
								</#list>

							</ul>
						</div>
					</div>
					-->
					<div class="pager">
  						<strong>共${data.totalElements?c}部&nbsp;1/${data.totalPages}</strong>
  						<em class="prev"><a href="/news/list.html">首页</a></em>&nbsp;
  						
  						<#if data.number==0>
  							<em class="prev" data="p-0">上一页</em>&nbsp;
  						<#else>
  							<em class="prev" data="p-0"><a href="/news/list.html?pageNumber=${data.number}">上一页</a></em>&nbsp;
						</#if>
  						
						<#if data.number==0>
							<span class="current">1</span>&nbsp;
  							<a href="/news/list.html?pageNumber=2" data="p-2">2</a>&nbsp;
  							<a href="/news/list.html?pageNumber=3" data="p-3">3</a>&nbsp;
  							<a href="/news/list.html?pageNumber=4" data="p-4">4</a>&nbsp;
						<#elseif (data.number gt 0 && data.number lte data.totalPages-3 ) >
							<a href="/news/list.html?pageNumber=${data.number}" data="p-2">${data.number}</a>
							<span class="current">${data.number+1}</span>&nbsp;
  							<a href="/news/list.html?pageNumber=${data.number+2}" data="p-2">${data.number+2}</a>&nbsp;
  							<a href="/news/list.html?pageNumber=${data.number+3}" data="p-3">${data.number+3}</a>&nbsp;
						<#elseif data.number lt data.totalPages-3 >
  							<span class="current">${data.number+1}</span>&nbsp;
						</#if>
						
  						<#if data.number gte data.totalPages-1>
  							<em class="prev" data="p-0">下一页</em>&nbsp;
  						<#else>
  							<a href="/news/list.html?pageNumber=${data.number+2}" class="next pagegbk" data="p-2">下一页</a>&nbsp;
						</#if>
						
  						<a href="/news/list.html?pageNumber=${data.totalPages}" class="next pagegbk" data="p-42">尾页</a>  
  					</div>
					
				</div>
				
				<div class="col-md-4 bt15">
					<div class="coid-img">
						<div class="col-md-12 ts">
							<p>热门影视推荐</p>
						</div>
						<#assign k=12 />
						<#list 7..(k) as j> <#assign ls5=commendSeadatas[j] />
						<div class="col-md-6 ">
							<a class="list-img " href="${ls5.VLongtxt}"
								title="" target="_blank"><img class="loading"
								src="${ls5.VPic}" data-original="${ls5.VPic}" alt="${ls5.VPsd}"
								style="display: block;"><label class="name">${ls5.VPsd}</label></a>
						</div>
						</#list>
					</div>

					<div class="clearfix"></div>
					<div style="margin: 10px auto; text-align: center;"></div>

					<div class="tab-r list-tab-r bt15 wmax">
						<div class="all-title">
							<h2>热门资讯</h2>
							<span><a
								href="/"
								title="热门资讯" target="_blank">更多&gt;&gt;</a></span>
						</div>
						<ul class="list-news">
							<#assign m1=15 />
							<#list 0..(m1-1) as j> 
							<#assign ls5=commendSeadatas[j] />
							<li><i class="c">${j+1}.</i><a href="${ls5.VLongtxt}"
								title="${ls5.VPsd}" target="_blank">${ls5.VPsd}</a><span
								class="c"></span></li>
							</#list>
						</ul>
					</div>
					
					<div style="margin: 10px auto; text-align: center;"></div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
	<#include "/seacms/foot.ftl">
</body>
</html>
