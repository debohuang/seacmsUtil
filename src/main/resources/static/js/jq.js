$(function(){
$("img.lazy").lazyload();
$(".jsfrom li:first").addClass("on");
$(".jsplist:first").addClass("flod");
$(".sytab").click(function(){
  $(this).parent().next().toggle();
  $(this).children().children().toggleClass("v");
});
$(".playul li").hover(function(){
  $(this).addClass("v");
  $(this).siblings().removeClass("v");
});
$(".search .im").hover(function(){
  $(this).children().toggleClass("v");
});
$(".nav .jqnav").click(function(){
  $(this).parent().parent().next().toggleClass("mv");
  $(this).parent().parent().next().next().removeClass("mv");
});
$(".nav .jqms").click(function(){
  $(this).parent().parent().next().next().toggleClass("mv");
  $(this).parent().parent().next().removeClass("mv");
});
$(".searchgb").click(function(){
  $(this).parent().removeClass("mv");
});
});
function setTab(name,name2,cursel,n){  
 for(i=1;i<=n;i++){
  var menu=document.getElementById(name+i);
  var con=document.getElementById(name2+i);
  menu.className=i==cursel?"on":"";
  con.style.display=i==cursel?"block":"none";
 }
};

