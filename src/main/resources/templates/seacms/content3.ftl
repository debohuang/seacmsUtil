<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta name="applicable-device" content="pc,mobile">
<title>${newDataName}新闻详情页_草民看片网</title>
<meta name="keywords" content="${newDataName}" />
<#if data.seaContent1.body?length gt 50>  
    <meta name="description" content="${newDataName}剧情:${data.seaContent1.body?substring(0,30)}" /> 
<#else>
    <meta name="description" content="${newDataName}剧情:${data.seaContent1.body}" /> 
</#if> 
<#include "/seacms/head_include.ftl"> 
 
</head>
<body>
<#include "/seacms/head.ftl">  
<div class="wrap clearfix">
      <div class="pleft">

	</div>   	
</div>

<div class="container" style="height:50px;">
<div style="margin:10px auto;text-align: center;">
</div>
</div>

<div class="container" style="height:50px;">
	<div class="wz">
		<span>您所在的位置：</span><a href="${site.url}" title="${data.seaType1.tname}">首页</a><em>&gt;</em><a class="current" href="${site.url}/news/list.html" title="${data.seaType1.tname}新闻">${data.seaType1.tname}新闻</a>
	</div>
<div class="color">
<div style="margin: 0px auto; text-align:">
</div>
	<div class="row">
		<div class="col-md-8 col-xs-12 jide slides-border" style=" padding:22px;"> 
  		<h1 class="text-center" id="h1">${newDataName}</h1>
        <div class="center"><small>时间：</small>${(data.VAddtime*1000)?number_to_datetime} <small>来源:</small>草民看片网 </div>
<div style="margin: 0px auto; text-align:">
</div>
<div class="text-muted zsy-img ct " id="cr">
	　<p>
	${newDataName}全集百度云下载，${newDataName}全集720P百度云资源。喜欢看${dataName}的小伙伴可以前往草民看片网观看福利，现在小编为小伙伴们提供${data.seaType1.tname}${dataName}百度云下载、百度网盘地址、高清网盘资源链接，喜欢看${dataName}的小伙伴都是在这里找资料!喜欢看这部美剧${data.seaType1.tname}${dataName}的小伙伴不要错过这次机会哦。
</p>
<p>
	${dataName}资源下载地址：<b>版权原因暂不提供下载</b>
</p>
<p>
	${dataName}资源在线播放地址：<a href="/movie/index${data.VId?c}.html" target="_blank">  <b>《${dataName}》在线播放地址</b></a>
</p>
<p>
	<strong>${newDataName}剧照：</strong> 
</p>
<p>
	<strong><img src="${data.VPic}" alt="${newDataName}剧照—草民看片网"><br>
</strong> 
</p>
<p>
	<strong>${newDataName}基本信息</strong>：
</p>
<p>
	导演: ${data.VActor}
</p>
<p>
	编剧: ${data.VActor}
</p>
<p>
	主演: ${data.VActor} 更多...
</p>
<p>
	类型: ${data.seaType1.tname}
</p>
<p>
	<#if data.VPublisharea??>
		制片国家/地区: ${data.VPublisharea}
	<#else>
		制片国家/地区: 国产引进
	</#if>
</p>
<p>
	<#if data.vLetter??>
		语言: ${data.vLetter}
	<#else>
		语言: 中字
	</#if>
</p>
<p>
	上映日期: ${(data.VAddtime*1000)?number_to_date}(中国大陆/美国) / ${(data.VAddtime*1000)?number_to_date}(法国) / ${(data.VAddtime*1000)?number_to_date}(柏林国际电影节)
</p>
<p>
	片长: 135分钟
</p>
<p>
	<#if data.vNickname??>
		别名: ${data.VNickname}
	</#if>
</p>
<p>
		豆瓣评分: 
	<#if data.vDouban??>
		${data.vDouban}
		<#else>暂无
	</#if>
</p>
<p>
		时光网评分: 
	<#if data.VMtime??>
		${data.VMtime}
		<#else>暂无
	</#if>
</p>
<p>
		IMDB评分: 
	<#if data.VImdb??>
		${data.VImdb}
		<#else>暂无
	</#if>
</p>

<p>
	<strong>${newDataName}全集剧情：</strong> 
</p>
<p>
	${data.seaContent1.body}
</p>
<p>
	以上就是草民看片网小编为大家带来的${data.seaType1.tname}${newDataName}，${dataName} 全集百度云下载的全部内容了，谢谢大家。
</p><table width="100%" border="1">
  <tbody>
    <tr>
      <th colspan="3" style="text-align:center;">
        <span style="color:#E53333;">热门影视相关推荐</span> 
      </th>
    </tr>
    <tr>
      <td>
        <strong>热门${data.seaType1.tname}</strong><strong> </strong> 
      </td>
      <td>
        <strong>热门电影</strong><strong> </strong> 
      </td>
      <td>
        <strong>热门国产剧</strong><strong> </strong> 
      </td>
    </tr>

    <#assign n = 5 />
    <#if n!=0> 
		<#list 0..(n-1) as i> 
			 <#assign ls1 = commendSeadatas[((i*3)+1)] /> 
			 <#assign ls2 = commendSeadatas[((i*3)+2)] /> 
			 <#assign ls3 = commendSeadatas[((i*3)+3)] />
			 <tr>
		      <td>
		        <a href="/news/xinwen/${ls1.VId?c}.html" target="_blank"> ${ls1.VName}</a> 
		      </td>
		      <td>
		        <a href="/news/xinwen/${ls2.VId?c}.html" target="_blank">${ls2.VName}</a> 
		      </td>
		      <td>
		        <a href="/news/xinwen/${ls3.VId?c}.html" target="_blank">${ls3.VName}</a> 
		      </td>
		    </tr>
		</#list>
	</#if>
  </tbody>
</table>

</div>
<div style="margin:10px auto;text-align: center;">
</div>

	 <div class="all-title"><h2>相关内容</h2></div>
 <ul class="list_module_img row margin">
 
        <#assign m = 3 />
		<#list 0..(m-1) as j> 
			 <#assign ls4 = newListData[j] /> 
			 <li><a class="list-img aleft col-md-3" href="${ls4.link}" title="${ls4.name}" target="_blank" style="height:auto; padding-bottom:17.6%"><img class="loading" src="${ls4.VPic}" data-original="${ls4.VPic}" alt="${ls4.name}" style="display: block;"><label class="name">${ls4.name}…</label></a>
		      <div class="news-info col-md-9">
				<h2><a href="${ls4.link}" title="${ls4.name}" target="_blank">${ls4.name}</a></h2><span>${(ls4.VAddtime*1000)?number_to_datetime}</span>
				<p style="height:74px;"> ${ls4.name}。大家关注的${dataName}即将上映了，那么具体的上映时间是多久呢?是否会和大家期望的一样?下面就是小编为大家提供的关于${dataName}：…</p>
				</div>
			</li>
		</#list>
                  
 </ul>
 <div class="col-md-12 padding">
<div class="tt"><a href="${site.url}/news/list.html" class="pull-right">更多&gt;&gt;</a> <span><a href="${site.url}/news/list.html">最新视频</a></span> </div>
	<div class=" slides list_module_img " id="mdear">
	<ul>
	
		<#assign k = 6 />
		<#list 1..(k) as j> 
			 <#assign ls5 = listData[j] /> 
			 <li class="col-md-2 col-xs-4">
				<a class="list-img " href="/news/xinwen/${ls5.VId?c}.html">
				<img class="loading" src="${ls5.VPic}" data-original="${ls5.VPic}" alt="${ls5.VName}" style="display: block;">
				<label class="name">${ls5.VName}</label>
				</a>
			</li>
		</#list>
		
	</ul>
	</div>
	</div>
		</div>
		<div class="col-md-4 bt15">
		<!--
        <div style="height:250px; margin:10px auto;text-align: center;">
		</div>
		<div class="clerfix"></div>
		-->
			
			<div class="coid-img">
		<div class="col-md-12 ts"><p>最新${data.seaType1.tname}</p></div>
				<#assign k = 12 />
				<#list 7..(k) as j> 
					 <#assign ls5 = listData[j] /> 
					<div class="col-md-6 ">
					<a class="list-img " href="/news/xinwen/${ls5.VId?c}.html" title="${ls5.VName}" target="_blank"><img class="loading" src="${ls5.VPic}" data-original="${ls5.VPic}" alt="${ls5.VName}" style="display: block;"><label class="name">${ls5.VName}</label></a>
					</div>
				</#list>
		</div>
		
		<div class="clearfix"></div>
           <div style="margin:10px auto;text-align: center;">
		</div>
		
		<div class="tab-r list-tab-r bt15 wmax">
			 <div class="all-title">
				<h2>热门${data.seaType1.tname}资讯</h2><span><a href="/news/list.html" title="热门${data.seaType1.tname}资讯" target="_blank">更多&gt;&gt;</a></span>
			</div>
				<ul class="list-news">
					<#assign m1 = 15 />
					<#list 0..(m1-1) as j> 
						 <#assign ls5 = newCommendlist[j] /> 
						<li><i class="c">${j+1}.</i><a href="${ls5.link}" title="${ls5.name}" target="_blank">${ls5.name}</a><span class="c"></span></li>
					</#list>
				</ul>
		</div>
		
		<div class="tab-r list-tab-r bt15 wmax">
			 <div class="all-title">
				<h2>${dataName}相关资讯</h2><span><a href="/news/list.html" title="热门${dataName}资讯" target="_blank">更多&gt;&gt;</a></span>
			</div>
				<ul class="list-news">
					<#assign m2 = 20 />
					<#assign m3 = 1 />
					<#list 3..(m2) as j> 
						 <#assign ls5 = newListData[j] /> 
						<li><i class="c">${m3}.</i><a href="${ls5.link}" title="${ls5.name}" target="_blank">${ls5.name}</a><span class="c"></span></li>
						<#assign m3 =m3+1 />
					</#list>
				</ul>
		</div>
		
		<div style="margin:10px auto;text-align: center;">
		</div>
		</div>
	</div>
</div>
	</div>
</div>
</div>
<#include "/seacms/foot.ftl"> 
</body>
</html>
