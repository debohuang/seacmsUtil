<div class="header clearfix">
  <div class="wrap head">
        <div class="logo"><a href="/"></a></div>
        <div class="nav">
                  <ul>
                      <li><a href="/"><i class="ico mhome"></i><b>首页</b></a></li>
                      <li class="jqnav"><i class="ico mnav"></i><b>分类</b></li>
                      <li class="jqms"><i class="ico ms"></i><b>搜索</b></li>
                  </ul>
        </div>
        <div class="menu">
                <ul>
                  <li><a class="now" href="/">首页</a></li>
                 <#list listTypeData as d>
                  	<li><a href="/frim/index${d.tid}.html" class="on">${d.tname}</a></li>
                  </#list>
                  	<li><a href="/news/list.html" class="on">新闻</a></li>
                </ul>
        </div>
        <div class="search">  
            <div class="search1">         
                <form name="formsearch" id="formsearch" action='/search.php' method="post"  target="_self" autocomplete="off">
                  <div class="search2">
                <div class="in"><div class="in1"><input class="input" name="searchword" type="text" id="keyword" placeholder="输入关键词"/></div></div>
                <div class="im"><input class="ico imgbt" type="submit" value=""/></div>
                  </div>
                </form>
            </div>
            <div class="searchgb"><i>关闭</i></div>    
        </div>
    </div>                    
</div>