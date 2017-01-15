<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>企业团队——要旅游 先兔游</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="verify-v1" content="5QXKet/F/qJ+oH49AY1ee56bDt4iH19AXI/CzWn1DHw=" />
<META content="${webSite.desc}" name=description>
<META content="${webSite.keywords}" name=keywords>
<link rel="shortcut icon" href="../favicon.ico" />
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/bbp-panel4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/portal-ymt.css" rel="stylesheet" type="text/css" media="screen">
<script type="text/javascript" src="${base}/public/20081205/script/core-mini.js"></script>

<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-8094470-3");
pageTracker._trackPageview();
} catch(err) {}</script>

</head>
<body>
<#include "*/banner.ftl">
<#include "*/header.ftl">
<#include "*/footer.ftl">
<div id="doc3" class="bb-r220">
	<div id="hd"> 
	<!-- {start:global master header -->
		<@header />
	<!-- }end:global master header --> 
	</div>
  <div id="bd">

		<!-- {start:header -->
		<@banner />
		<!-- }end:header -->



    <!-- 第一屏 -->
    <div class="protal-column-t1">
      <div class="main">
        <!-- 名店街 -->
        <div class="lf-mantle-l main-l" id="mdj">
<div class="tit">
<h3>企业出游推荐景点</h3></div>
<div class="content" id="popularU">
<ul>
<#list qytd_hydd as hydd>
<#if hydd_index==0>
<li class="first"><a href="${hydd.url}" title="${hydd.title}" target="${hydd.target}"><img src="${base}/${hydd.picUrl}"><em>${hydd.subTitle!''}</em></a><br>${hydd.title}</li>
<#elseif hydd_index<3>
<li><a href="${hydd.url}" title="${hydd.title}" target="${hydd.target}"><img src="${base}/${hydd.picUrl}"><em>${hydd.subTitle!''}</em></a><br>${hydd.title}</li>
</#if>
</#list>
</ul></div></div>
        <!-- //名店街 -->
        <div class="lf-mantle-r main-r">
          <!-- 幻灯 -->
          <div id="lf-mantle-slider"> <div class="ym-mantle-slider cls" id="slider1"><!-- 缩略图 -->
<div class="thumb">
<ul>
<#if qytd??>
<#list qytd as flash>
<#if flash_index<4>
<#if flash_index==0>
<li class="selected">
<#else>
<li class="">
</#if>
<A href="${flash.url}" title="${flash.title}" target=${flash.target}><IMG src="${base}/${flash.breUrl}"></A></LI>
</#if>
</#list>
</#if>
</ul>
</div>
<!-- //缩略图 -->
<!-- 数字面板 -->
<div class="number">
<ul>
<li class="first selected">1</li>
<li>2</li>
<li>3</li>
<li>4 </li>
</ul>
</div>
<!-- //数字面板 -->
<!-- 大图 -->
<div class="enlarge"> 
<#if qytd??>
<#list qytd as flash>
<#if flash_index<4>
	<#if flash_index==0>
	<a href="${flash.url}" title="${flash.title}" target="${flash.target}"><img style="opacity: 0.3;" src="${base}/${flash.picUrl}"></a>
	<#else>
	<a href="${flash.url}" title="${flash.title}" target="${flash.target}"><img style="opacity: 1; display: none;" src="${base}/${flash.picUrl}"></a>
	</#if>
</#if>
</#list>
</#if>
</div>
<!-- //大图 -->
</div>
<!-- //幻灯组件 --> 
</div>
    <!-- //幻灯 -->
        </div>
      </div>
      <div class="side">
       
        <!--企业热线 -->
        <style>#fp-new li {line-height:1.8;}</style>
<div id="fp-new" class="fp-mod5">
	<div class="hd">
		<span class="act"><a href="${base}/news/contentList/f2d041b81fe688c0011fefb6d8c9019a.html" target="_blank">更多</a></span>
		<h3>企业热线</h3>
	</div>
	<div class="bd">
		<p style="border-bottom: 1px solid rgb(212, 212, 212); margin: 8px 10px 7px; padding-bottom: 8px;">
<#list qytd_qyrxs as qyrxs>
<#if qyrxs_index==0>
<a href="" style="background: transparent url(http://www.tutu6.com/public/20081205/image/bfb_safe.gif) no-repeat scroll 0pt center; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial; font-size: 14px; font-weight: bold; padding-left: 32px; height: 27px; line-height: 27px; display: block;" target="${qyrxs.target}">${qyrxs.title}</a>
</#if>
</#list>
		</p>
		<ul>
<#list qytd_qyrxx as qyrxx>
<#if qyrxx_index==0>
<li class="first"><a href="${qyrxx.url}" title="${qyrxx.title}" target="${qyrxx.target}">${qyrxx.title}</a></li>
<#elseif qyrxx_index<9>
<li><a href="${qyrxx.url}" title="${qyrxx.title}" target="${qyrxx.target}">${qyrxx.title}</a></li>
</#if>
</#list>
		</ul>
	
	</div>
</div>
        <!--//企业热线 -->
      </div>
    </div>
    <!-- //第一屏 -->

<script type="text/javascript">
(function(){new PicSlide({container:G("slider1"),pics:Dom.getElementsByClassName("enlarge",G("slider1"))[0].getElementsByTagName("img"),pages:Dom.getElementsByClassName("thumb",G("slider1"))[0].getElementsByTagName("li"),autoPlay:true,interval:5000,delay:50,eventType:"mouseover",effect:"fade"}).run();})();
</script>

    <!--第三通屏-->
    <div class="protal-column-t1">
      <div class="main">
        
        <!-- 企业服务 -->
          <div id="letao">
<h3>企业服务</h3><div class="cover">
<#list qytd_qyfwz as qyfwz>
<#if qyfwz_index==0>
	<a href="${qyfwz.url}" title="${qyfwz.title}" target="${qyfwz.target}"><img src="${base}/${qyfwz.picUrl}"></a>
</#if>
</#list>
</div>
<ul class="news">
<#list qytd_qyfwzh as qyfwzh>
<#if qyfwzh_index<6>
<li><a href="${qyfwzh.url}" title="${qyfwzh.title}" target="${qyfwzh.target}">${qyfwzh.title}</a></li>
</#if>
</#list>
</ul>
<ul class="img">
<#list qytd_qyfwy as qyfwy>
<#if qyfwy_index<4>
<li><a href="${qyfwy.url}" title="${qyfwy.title}" target="${qyfwy.target}"><img src="${base}/${qyfwy.picUrl}"></a></li>
</#if>
</#list>
</ul>
</div>
        <!-- //企业服务 -->
        <!-- 景点街 -->
          <div class="mod-o" id="member">
<h3>名店街</h3>
<div id="activity">
<h4>名店之星</h4>
<ul>
<#list qytd_jdjzs as jdjzs>
<#if jdjzs_index<4>
<li><a href="${jdjzs.url}" title="${jdjzs.title}" target="${jdjzs.target}">${jdjzs.title}</a></li>
</#if>
</#list>
</ul>
<div id="member-ad">
<#list qytd_jdjzx as jdjzx>
<#if jdjzx_index==0>
<a href="${jdjzx.url}" title="${jdjzx.title}" target="${jdjzx.target}"><img alt="" src="${base}/${jdjzx.picUrl}" target="${jdjzx.target}"></a>
</#if>
</#list>
</div></div>
<div class="mod-w">
<div class="rc l"></div><!-- 圆角勿动 -->
<div class="rc r"></div>
<div class="bd">
<div class="cls top">
<ul>
<#list qytd_jdjysz as jdjysz>
<#if jdjysz_index==0>
<li class="cls first"><a href="${jdjysz.url}" title="${jdjysz.title}" target="${jdjysz.target}"><img src="${base}/${jdjysz.picUrl}"><p>
	<#if jdjysz.title?length lte 5>
	${jdjysz.title}<br>
	<#elseif jdjysz.title?length lte 17>
	${jdjysz.title[0..4]}<br>
	${jdjysz.title[5..]}
	<#else>
	${jdjysz.title[0..4]}<br>
	${jdjysz.title[5..16]}
	</#if>
</p></a></li>
<#elseif jdjysz_index==1>
<li class="cls"><a href="${jdjysz.url}" title="${jdjysz.title}" target="${jdjysz.target}"><img src="${base}/${jdjysz.picUrl}"><p>
	<#if jdjysz.title?length lte 5>
	${jdjysz.title}<br>
	<#elseif jdjysz.title?length lte 17>
	${jdjysz.title[0..4]}<br>
	${jdjysz.title[5..]}
	<#else>
	${jdjysz.title[0..4]}<br>
	${jdjysz.title[5..16]}
	</#if>
</p></a></li>
</#if>
</#list>
</ul>
<#list qytd_jdjysy as jdjysy>
<#if jdjysy_index<2>
<p class="img"><a href="${jdjysy.url}" title="${jdjysy.title}" target="${jdjysy.target}"><img src="${base}/${jdjysy.picUrl}">
	<#if jdjysy.title?length lte 6>
	${jdjysy.title}<br>
	<#elseif jdjysy.title?length lte 14>
	${jdjysy.title[0..5]}<br>
	${jdjysy.title[6..]}
	<#else>
	${jdjysy.title[0..5]}<br>
	${jdjysy.title[6..14]}
	</#if>
</a></p>
</#if>
</#list>
</div>
<div class="info">
<#list qytd_jdjyx as jdjyx>
<#if (jdjyx_has_next)&&(jdjyx_index<4)>
<a href="${jdjyx.url}" title="${jdjyx.title}" target="${jdjyx.target}">${jdjyx.title}</a> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;
<#elseif (!jdjyx_has_next)||(jdjyx_index==4)>
<a href="${jdjyx.url}" title="${jdjyx.title}" target="${jdjyx.target}">${jdjyx.title}</a>
</#if>
</#list>
</div></div></div></div>
        <!-- //景点街 -->
        <!-- 精彩资讯 -->
          <div class="mod-o" id="info">
<h3>精彩资讯</h3>
<div class="mod-w">
<div class="rc r"></div>
<div class="bd">
<#list qytd_jczxz as jczxz>
<#if jczxz_index==0>
<p class="img first"><a href="${jczxz.url}" title="${jczxz.title}" target="${jczxz.target}"><img src="${base}/${jczxz.picUrl}">${jczxz.title}</a></p>
</#if>
</#list>
<ul>
<#list qytd_jczxzh as jczxzh>
<#if jczxzh_index<7>
<li><a href="${jczxzh.url}" title="${jczxzh.title}" target="${jczxzh.target}">[${jczxzh.subTitle!''}]${jczxzh.title}</a></li>
</#if>
</#list>
</ul>
<#list qytd_jczxy as jczxy>
<#if jczxy_index<7>
<p class="img"><a href="${jczxy.url}" title="${jczxy.title}" target="${jczxy.target}"><img src="${base}/${jczxy.picUrl}">${jczxy.title}</a></p>
</#if>
</#list>
</div></div></div>
        <!-- //精彩资讯 -->
      </div>
      <div class="side">
        
        <!--排行榜-->
          <div id="rank">
<ul class="menu cls" id="rank_nav">
<li class="current" id="g"><a href="javascript:void(0)">企业热点景区</a> 
</li><li id="v"><a href="javascript:void(0)">企业游玩攻略榜</a> </li></ul>
<ol class="list cls">
<#list qytd_rdjq as rdjq>
<#if rdjq_index==0>
<li class="first cls"><a href="${rdjq.url}" title="${rdjq.title}" target="${rdjq.target}"><img src="${base}/${rdjq.picUrl}">${rdjq.title}</a> </li>
<#elseif rdjq_index<10>
<li>&nbsp;<a href="${rdjq.url}" title="${rdjq.title}" target="${rdjq.target}">${rdjq.title}</a></li>
</#if>
</#list>
</ol>
<ol class="list cls unselected">
<#list qytd_ywgl as ywgl>
<#if ywgl_index==0>
<li class="first cls"><a href="${ywgl.url}" title="${ywgl.title}" target="${ywgl.target}"><img src="${base}/${ywgl.picUrl}">${ywgl.title}</a> </li>
<#elseif ywgl_index<10>
<li>&nbsp;<a href="${ywgl.url}" title="${ywgl.title}" target="${ywgl.target}">${ywgl.title}</a></li>
</#if>
</#list>
</ol></div>
<script defer="defer" type="text/javascript">								
					(function(){
						var tabView = new TabView({
								'TAB_DEACTIVE_CN'     : '',
								'TAB_ACTIVE_CN'       : 'current',
								'events': ['mouseover'],
								'preventDefault': true
							});
						var tabNodes = G('rank_nav').getElementsByTagName('li');
						var contentNodes = Dom.getElementsByClassName("list",G

("rank"));
						for (var i=0, l=tabNodes.length; i<l; i++){
						  tabView.addTab( { tabNode:tabNodes[i], 

contentNode:contentNodes[i] } );
						}
					})();
				</script>
        <!--//排行榜-->
        <!--精彩主题-->
          <div class="mod-b" id="fashion">
<div class="hd">
<h3>精彩主题</h3></div>
<div class="bd">
<ul class="cls">
<#list qytd_jczt as jczt>
<#if jczt_index<4>
<li><a href="${jczt.url}" title="${jczt.title}" target="${jczt.target}"><img src="${base}/${jczt.picUrl}"><em>[${jczt.subTitle!''}] </em>
<#if jczt.title?length lte 11>
${jczt.title}
<#else>
${jczt.title[0..10]}
</#if>
</a></li>
</#if>
</#list>
</ul></div></div>

        <!--//精彩主题-->
		
		<!-- banner 220x112 -->
				<div id="lf-ad1">
					<#if qytd_yx??>
					<#list qytd_yx as yx>
					<A href="${yx.url}" target="${yx.target}"><IMG src="${base}/${yx.picUrl}" height="112px" width="220px"></A> 
					</#list>
					</#if>
				</div>
		<!-- banner 220x112 -->
      </div>
    </div>
    <!--//第三通屏-->
  </div>
  <!-- footer -->
	<@footer />
	<!-- //footer -->
 </div>
</body></html>