<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta name="applicable-device" content="pc,mobile">
<title>${data.VName}_看片网</title>
<meta name="keywords" content="${data.VName}" />
<#if data.seaContent1.body?length gt 50>  
    <meta name="description" content="${data.VName}剧情:${data.seaContent1.body?substring(0,30)}" /> 
<#else>
    <meta name="description" content="${data.VName}剧情:${data.seaContent1.body}" /> 
</#if> 
<#include "/seacms/head_include.ftl">  
</head>
<body>
<#include "/seacms/head.ftl">  
<div class="wrap clearfix">
      <div class="pleft">
                        <div class="content clearfix">
                              <div class="pic">
                                    <div class="img"><img class="lazy" data-original="${data.VPic}" src="images/load.png" alt="${data.VName}"></div>
                              </div>
                              <div class="info">
                                    <dl>
                                            <dt><em>${data.VName}</em></dt>
                                            <#if data.vNickname??>
   												<dd class="nickname"><span>别名：</span>${data.VNickname}</dd>		
											</#if>
                                            <dd><em><span>主演：</span>${data.VActor}</em></dd>
                                            <dd><em><span>导演：</span>${data.VDirector}</em></dd>
                                            <dd><em><span>类型：</span>${data.seaType1.tname}</em></dd>
                                            <dd><em><span>地区：</span><a href="/search.php?searchword=${data.VPublisharea}">${data.VPublisharea}</a><span class="y">年份：</span><a href="/search.php?searchword=${data.VPublishyear}">${data.VPublishyear}</a><span class="y">语言：</span>${data.VLang}</em></dd>
                                    </dl>
                                    <div class="starpf">
                                            <span>评分：</span>${data.mark}
                                    </div>
                                    <div class="zan">
                                            <a class="digg" href="javascript:viod()" onclick="diggVideo(${data.id},'digg_num')">好片</a>
                                            <div class="num">
                                              <span class="dnum">${data.diggnum}</span>
                                              <span class="tnum">${data.treadnum}</span>
                                              <div class="nu">
                                              </div>
                                              <div class="{if:${data.diggnum}>${data.treadnum}}nu1{elseif:${data.diggnum}<${data.treadnum}}nu2{else}nu3{end if}"></div>
                                            </div>
                                            <a class="tread" href="javascript:viod()" onclick="treadVideo(${data.id},'tread_num')">烂片</a>
                                    </div>
                                    <div class="share">
                                            <div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a></div>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdMiniList":false,"bdPic":"","bdStyle":"0","bdSize":"32"},"share":{},"image":{"viewList":["qzone","tsina","tqq","renren","weixin"],"viewText":"分享到：","viewSize":"16"},"selectShare":{"bdContainerClass":null,"bdSelectMiniList":["qzone","tsina","tqq","renren","weixin"]}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
                                    </div>
                              </div>
                        </div>


                        <div class="playfrom jsfrom tab1 clearfix">
                              <ul>
                                    ${data.playlist}
                                    <li id="tab1[playlist:i]" onclick="setTab('tab1','stab1',[playlist:i],${data.playlistlen})"><i class="playerico ico-[playlist:ename]"></i>[playlist:from]</li>
                                    {/playpage:playlist}
                              </ul>
                        </div>
                        ${data.playlist}
                        <div id="stab1[playlist:i]" class="playlist jsplist clearfix">
                              <ul class="playul">
                                [playlist:link target=_self]
                              </ul>
                        </div>
                        {/playpage:playlist}
                        <div class="cdes clearfix">${data.des}</div>
                        <div class="xgfrom tab2 clearfix" style="margin-bottom:20px;">
                                <div class="line"></div>
                                <ul>
                                        <li id="tab21" class="on">下载地址</li>
                                </ul>
                        </div>
        
                        <div class="xgfrom tab2 clearfix">
                                <div class="line"></div>
                                <ul>
                                        <li id="tab21" class="on" onclick="setTab('tab2','stab2',1,3)">你喜欢</li>
                                </ul>
                        </div>
                        <div id="stab21" class="xg clearfix">
                                <ul class="xgul">
                                        {seacms:videolist num=6 order=time type=current}
                                        <li>
                                              <a class="link" href="[videolist:link]" tiile="[videolist:name]">
                                              <div class="pic"><div class="pic1">
                                                      <img src="[videolist:pic]" alt="[videolist:name]">
                                                      <span class="over"></span>
                                                      <span class="ico player-ico"></span>
                                                      <span class="bg"></span>
                                                      <p>[videolist:name]</p>
                                              </div></div>
                                              </a>
                                        </li>
                                        {/seacms:videolist}
                                </ul>
                        </div>


</div>
      <div class="pright">
                  <div class="right-list clearfix">
                        <ul class="itit"><span class="mz">排行榜</span></ul>
                        <ul>
                               <#list newListData as item>
                             	 <li><a href='${item["link"]}'>${item["name"]}</a></li>
			                  </#list>
                        </ul>
                  </div>
                  <div class="right-list clearfix">
                        <ul class="itit"><span class="mz">好评榜</span></ul>
                        <ul>
                              {seacms:videolist num=10 order=digg type=current}
                              <li><a href="[videolist:link]" title="[videolist:name]"><i class="ico i{if:[videolist:i]<4} i3{end if}">[videolist:i]</i>[videolist:name]</a></li>
                              {/seacms:videolist}
                        </ul>
                  </div>
      </div>        
	

</div>
{seacms:foot}
</body>
</html>
