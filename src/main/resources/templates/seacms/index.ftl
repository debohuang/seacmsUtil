<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type='text/css'>
    *{
        margin: 0;
        padding:0;
    }
    html{
        font-size: 14px;
    }
    p{
        width: 100%;
        margin-bottom: 5px;
    }
    p img{
        width: 100%;

    }
</style>
<!DOCTYPE html>
<html>
<body>
<h4>标题:${data.VName}</h4>


</br>
${data.seaContent1.body}
</br>

<#list listData as d><br>  
   
   <#if data.VId==d.VId>
   
   <#elseif (data.VId-d.VId) gt 0 && (data.VId-d.VId) lt 6>  
   		<li>${d.VName}<br> 
   <#elseif (data.VId-d.VId) gt -1 && (data.VId-d.VId) lt 0>  
   		<li>${d.VName}<br> 
   <#else>  
  	 
   </#if>
    
</#list>



</body>
</html>