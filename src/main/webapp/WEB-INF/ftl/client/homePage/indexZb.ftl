<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>周边游——要旅游 先兔游</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="verify-v1" content="5QXKet/F/qJ+oH49AY1ee56bDt4iH19AXI/CzWn1DHw=" />
<META content="${webSite.desc}" name=description>
<META content="${webSite.keywords}" name=keywords>
<link rel="shortcut icon" href="../favicon.ico" />
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/bbp-panel4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/portal-ysj.css" rel="stylesheet" type="text/css" media="screen">
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
		<DIV class=protal-column-t1>
<DIV class=main>
<DIV class=lf-mantle-l>
<!-- 类目 -->
<DIV id=lf-category><SPAN class=x1><SPAN class=x1a></SPAN></SPAN><SPAN 
class=x2><SPAN class=x2a></SPAN></SPAN>
<DIV class=mod-content>
<DIV class=hd>
<!--
<SPAN class=act><A class=previous id=popularUPrev 
href="javascript:void(0)">&lt;Previous</A><A class=next id=popularUNext 
href="javascript:void(0)">Next&gt;</A>
</SPAN> -->

<H3>周边游热词</H3></DIV>
<DIV class=bd>
<DIV class=tree>
<UL id=tree-nav>
	
	<#list regions as regionP>
		
			<#if regionP_index==0>
				<LI class=first>
				<#else>
				<LI>
			</#if>
			<H3 id=cat${regionP_index+1}><A href="${base}/team/s.shtml?regionId=${regionP.regionId}&showType=1" target=_blank>${regionP.name}</A></H3>
			<P>
			<#list regionP.childrenList as regionC>
				<#if (regionP.regionId=="402880e71cd10bd3011cd1398b2e0033")||(regionP.regionId=="402880e71cd10bd3011cd11a6eb40001")>
					<#if regionC_has_next&&(regionC_index<5)>
					<A href="${base}/team/s.shtml?regionId=${regionC.regionId}&showType=1" target=_blank>${regionC.name}</A>&nbsp;&nbsp;|&nbsp;&nbsp;
					<#elseif ((!regionC_has_next)&&(regionC_index<=5))||((regionC_has_next)&&(regionC_index==5))>
					<A href="${base}/team/s.shtml?regionId=${regionC.regionId}&showType=1" target=_blank>${regionC.name}</A>
					</#if>
				<#elseif regionP.regionId=="402880e71cd10bd3011cd123fd1d000d">
					<#if regionC_has_next&&(regionC_index<3)>
					<A href="${base}/team/s.shtml?regionId=${regionC.regionId}&showType=1" target=_blank>${regionC.name}</A>&nbsp;&nbsp;|&nbsp;&nbsp;
					<#elseif ((!regionC_has_next)&&(regionC_index<=3))||((regionC_has_next)&&(regionC_index==3))>
					<A href="${base}/team/s.shtml?regionId=${regionC.regionId}&showType=1" target=_blank>${regionC.name}</A>
					</#if>
				<#elseif regionP.regionId=="402880e71cd10bd3011cd128b1e30013">
					<#if regionC_has_next&&(regionC_index<9)>
					<A href="${base}/team/s.shtml?regionId=${regionC.regionId}&showType=1" target=_blank>${regionC.name}</A>&nbsp;&nbsp;|&nbsp;&nbsp;
					<#elseif ((!regionC_has_next)&&(regionC_index<=9))||((regionC_has_next)&&(regionC_index==9))>
					<A href="${base}/team/s.shtml?regionId=${regionC.regionId}&showType=1" target=_blank>${regionC.name}</A>
					</#if>
				<#else>
					<#if regionC_has_next&&(regionC_index<2)>
					<A href="${base}/team/s.shtml?regionId=${regionC.regionId}&showType=1" target=_blank>${regionC.name}</A>&nbsp;&nbsp;|&nbsp;&nbsp;
					<#elseif ((!regionC_has_next)&&(regionC_index<=2))||((regionC_has_next)&&(regionC_index==2))>
					<A href="${base}/team/s.shtml?regionId=${regionC.regionId}&showType=1" target=_blank>${regionC.name}</A>
					</#if>
				</#if>
			</#list>
			</P>
		
	</#list>
	
 </UL>
 </DIV></DIV>
<DIV class=ft>
<P><A href="http://www.tutu6.com/team/go.shtml" 
target=_blank>查看更多分类&raquo;</A></P>
</DIV>
</DIV>
</DIV>
<!-- //类目 -->
</DIV>
<DIV class=lf-mantle-r>
<!-- 幻灯 -->
<!-- 幻灯组件 -->
<DIV class="wm-mantle-slider cls" id=slider1>
<!-- 缩略图 -->
<DIV class=thumb>
<UL>
<#if zb??>
<#list zb as flash>
<#if flash_index<4>
<#if flash_index==0>
<li class="first">
<#else>
<li>
</#if>
<A href="${flash.url}" title="${flash.title}" target=${flash.target}><IMG src="${base}/${flash.breUrl}"></A></LI>
</#if>
</#list>
</#if>
 </UL>
 </DIV>
 <!-- //缩略图 -->
 <!-- 数字面板 -->
<DIV class=number>
<UL>
  <LI class="first selected">1 </LI>
  <LI>2 </LI>
  <LI>3 </LI>
  <LI>4 </LI>
</UL>
</DIV>
<!-- //数字面板 -->
<!-- 大图 -->
<DIV class=enlarge>
<#if zb??>
<#list zb as flash>
<#if flash_index<4>
<A href="${flash.url}" title="${flash.title}" target=${flash.target}><IMG src="${base}/${flash.picUrl}"></A>
</#if>
</#list>
</#if>
<!-- //大图 -->
</DIV>
<!-- //幻灯组件 -->
</DIV>
<!-- //幻灯 -->
<!-- 周边聚集 -->
<DIV id=lf-mantle-fashion><SPAN class=x1><SPAN class=x1a></SPAN></SPAN><SPAN 
class=x2><SPAN class=x2a></SPAN></SPAN>
<DIV class=mod-content>
<DIV class=hd>
<H3>周边聚集</H3></DIV>
<DIV class=bd>
<#list zb_jjs as jjs>
<#if jjs_index==0>
<H2><A href="${jjs.url}" title="${jjs.title}" target="${jjs.target}">${jjs.title}</A></H2>
</#if>
</#list>
<DIV class="ul1 cls">
<UL>
<#list zb_jjz as jjz>
<#if jjz_index<6>
<LI>[${jjz.subTitle!''}]&nbsp;&nbsp;<A href="${jjz.url}" title="${jjz.title}" target="${jjz.target}">${jjz.title}</A> </LI>
</#if>
</#list>
</UL></DIV>
<DIV class="ul2 cls">
<UL>
<#list zb_jjx as jjx>
<#if jjx_index<5>
<LI><A href="${jjx.url}" title="${jjx.title}" target="${jjx.target}"><IMG src="${base}/${jjx.picUrl}">
<#if jjx.title?length lte 7>
${jjx.title}<br>
<#elseif jjx.title?length lte 14>
${jjx.title[0..6]}<br>
${jjx.title[7..]}
<#else>
${jjx.title[0..6]}<br>
${jjx.title[7..13]}
</#if>
</A><BR>
<STRONG class=highlight>
${jjx.price}
</STRONG>
</LI>
</#if>
</#list>
 </UL>
 </DIV>
 </DIV>
<DIV class=ft></DIV>
</DIV>
</DIV>
<!-- //周边聚集 -->
</DIV>
</DIV>
<DIV class=side>

<STYLE>#fp-new LI {
	LINE-HEIGHT: 1.8
}
</STYLE>

<DIV class=fp-mod5 id=fp-new>
 <!-- 苏州周边旅游 -->
<DIV class=hd><SPAN class=act><A href="${base}/news/contentList/402881ff1fe4f8bf011fe51581260010.html" 
target=_blank>更多</A></SPAN> 
<H3>苏州周边旅游</H3></DIV>
<DIV class=bd>

<UL>
	<#if zb_szzb??>
	<#list zb_szzb as szzb>
		<#if szzb_index<11>
		<#if szzb_index==0>
		<li class="first"><a href="${base}/news/content/${szzb.newsId}.html" title="${szzb.title}" target="_blank">
		<#else>
		<li><a href="${base}/news/content/${szzb.newsId}.html" title="${szzb.title}" target="_blank">
		</#if>
		<#if szzb.subTitle?length lte 10>
		${szzb.subTitle}
		<#else>
		${szzb.subTitle[0..9]}
		</#if> 
		</a>
		</li>
		</#if>
	</#list>
	</#if>
 </UL>
 </DIV>
 </DIV>
 <!-- //苏州周边旅游 -->
  <!-- 周边精彩推荐 -->
<DIV class=lf-mod1 id=lf-newstore>
<DIV class=hd>
<H3>周边精彩推荐</H3></DIV>
<DIV class=bd>
<UL>
<#list zb_jctj as jctj>
<#if jctj_index==0>
<LI class=first><A href="${jctj.url}" title="${jctj.title}" target="${jctj.target}"><IMG src="${base}/${jctj.picUrl}"></A>  <H3><A href="${jctj.url}" target="${jctj.target}">${jctj.subTitle!''}</A></H3> <P>${jctj.title}</P></LI>
<#elseif jctj_index<4>
<LI ><A href="${jctj.url}" title="${jctj.title}" target="${jctj.target}"><IMG src="${base}/${jctj.picUrl}"></A>  <H3><A href="${jctj.url}" target="${jctj.target}">${jctj.subTitle!''}</A></H3> <P>${jctj.title}</P></LI>
</#if>
</#list>
 </UL>
 </DIV>
 </DIV>
 <!-- //周边精彩推荐 -->
 </DIV>
 </DIV>
		<!-- //第一屏 -->
<script type="text/javascript">
(function(){BBoard.render("popularU","popularUPrev","popularUNext",5000);new PicSlide({container:G("slider1"),pics:Dom.getElementsByClassName("enlarge",G("slider1"))[0].getElementsByTagName("img"),pages:Dom.getElementsByClassName("thumb",G("slider1"))[0].getElementsByTagName("li"),autoPlay:true,interval:5000,delay:50,eventType:"mouseover",effect:"fade"}).run();})();
</script>				
		<!-- 热卖排行 -->
<div class="protal-column-t1">
<div class="main" id="lf-hot">
<div class="goods_list">

<ul>
<#list zb_ysws as ysws>
<#if ysws_index<8>
<li><a href="${ysws.url}" title="${ysws.title}" target="${ysws.target}"><img alt="${ysws.title}" src="${base}/${ysws.picUrl}"></a>
<p>
<#if ysws.title?length lte 8>
${ysws.title}<br>
<#elseif ysws.title?length lte 16>
${ysws.title[0..7]}<br>
${ysws.title[8..]}
<#else>
${ysws.title[0..7]}<br>
${ysws.title[8..14]}..
</#if>
<del></del>
<strong>${ysws.price}</strong>
</p>
</li>
</#if>
</#list>
</ul>

<ul class="unselected">
<#list zb_gcmz as gcmz>
<#if gcmz_index<8>
<li><a href="${gcmz.url}" title="${gcmz.title}" target="${gcmz.target}"><img alt="${gcmz.title}" src="${base}/${gcmz.picUrl}"></a>
<p>
<#if gcmz.title?length lte 8>
${gcmz.title}<br>
<#elseif gcmz.title?length lte 16>
${gcmz.title[0..7]}<br>
${gcmz.title[8..]}
<#else>
${gcmz.title[0..7]}<br>
${gcmz.title[8..14]}..
</#if>
<del></del>
<strong>${gcmz.price}</strong>
</p>
</li>
</#if>
</#list>
</ul>

<ul class="unselected">
<#list zb_hbwq as hbwq>
<#if hbwq_index<8>
<li><a href="${hbwq.url}" title="${hbwq.title}" target="${hbwq.target}"><img alt="${hbwq.title}" src="${base}/${hbwq.picUrl}"></a>
<p>
<#if hbwq.title?length lte 8>
${hbwq.title}<br>
<#elseif hbwq.title?length lte 16>
${hbwq.title[0..7]}<br>
${hbwq.title[8..]}
<#else>
${hbwq.title[0..7]}<br>
${hbwq.title[8..14]}..
</#if>
<del></del>
<strong>${hbwq.price}</strong>
</p>
</li>
</#if>
</#list>
</ul>

<ul class="unselected">
<#list zb_ysly as ysly>
<#if ysly_index<8>
<li><a href="${ysly.url}" title="${ysly.title}" target="${ysly.target}"><img alt="${ysly.title}" src="${base}/${ysly.picUrl}"></a>
<p>
<#if ysly.title?length lte 8>
${ysly.title}<br>
<#elseif ysly.title?length lte 16>
${ysly.title[0..7]}<br>
${ysly.title[8..]}
<#else>
${ysly.title[0..7]}<br>
${ysly.title[8..14]}..
</#if>
<del></del>
<strong>${ysly.price}</strong>
</p>
</li>
</#if>
</#list>
</ul>

</div>
<div class="hot_menu"><img alt="热卖排行" src="${base}/public/20081205/image/ico_hot.gif">  
<div id="ddContainer-2" style="overflow: hidden; float: left; width: 134px; height: 200px;">

<ul>
<li class="first selected"><a href="">游山玩水</a></li>
<li><a href="">古镇名城</a></li>
<li><a href="">海滨温泉</a></li>
<li><a href="">影视乐园</a></li>
</ul>

</div>
<div class="scroll" id="hot_scroll">
<div class="bar" id="ddScroll-2" style="display: none;"></div></div></div></div>
<div class="side" id="vane">

<ul>
<#list zb_fxb as fxb>
<#if fxb_index<8>
<li><a href="${fxb.url}" title="${fxb.title}" target="${fxb.target}"><img src="${base}/${fxb.picUrl}"></a><br><em>[${fxb.subTitle!''}]</em>${fxb.title} </li>
</#if>
</#list>
</ul>
</div>
</div>
<!-- //热卖排行 -->
		<!-- //热卖排行 -->
		
		<!-- 通栏 -->
		<!--

<DIV class=protal-column-t0>
<DIV class=main style="PADDING-RIGHT: 50px; PADDING-LEFT: 50px; BACKGROUND: #ccc; PADDING-BOTTOM: 30px; COLOR: #fff; PADDING-TOP: 30px">Advertisement 950*70</DIV></DIV>


-->
		<!-- //通栏 -->
		
		<!-- 主题推荐 -->
		<div class="protal-column-t1">
<div class="main" id="beauty">
<h3>主题推荐</h3>
<div class="cover">
<#list zb_zttjsz as zttjsz>
<#if zttjsz_index==0>
<a href="${zttjsz.url}" title="${zttjsz.title}" target="${zttjsz.target}"><img src="${base}/${zttjsz.picUrl}"></a><a></a>
</#if>
</#list>
</div>
<div class="info">
<#list zb_zttjsys as zttjsys>
<#if zttjsys_index==0>
<a class="title" href="${zttjsys.url}" title="${zttjsys.title}" target="${zttjsys.target}">${zttjsys.title}</a><br>
<#elseif zttjsys_index==1>
<a href="${zttjsys.url}" title="${zttjsys.title}" target="${zttjsys.target}"><em>[${zttjsys.title}]</em></a> &nbsp; 
<#elseif zttjsys_index==2>
<a href="${zttjsys.url}" title="${zttjsys.title}" target="${zttjsys.target}">[${zttjsys.title}]</a> 
</#if>
</#list>
</div>
<ul class="info_list">
<#list zb_zttjsyx as zttjsyx>
<#if zttjsyx_index<5>
<li><a href="${zttjsyx.url}" title="${zttjsyx.title}" target="${zttjsyx.target}"><em>[${zttjsyx.subTitle!''}] </em>${zttjsyx.title}</a>
</#if>
</#list>
</ul>
<h4>主题推荐</h4>
<ul class="hot">
<#list zb_zttjx as zttjx>
<#if zttjx_index<7>
<li><a href="${zttjx.url}" title="${zttjx.title}" target="${zttjx.target}"><img src="${base}/${zttjx.picUrl}"><br>
	<#if zttjx.title?length lte 6>
	${zttjx.title[0..]}
	<#else>
	${zttjx.title[0..5]}	
	</#if>
	</a><br>
<em>兔游价：${zttjx.price}</em> </li>
</#if>
</#list>
</ul></div>
<div class="side" id="magic">
<h3>一日游景点</h3>
<div class="mod-content">
<div class="in">
<#list zb_yryjds as yryjds>
<#if yryjds_index<2>
<p><a href="${yryjds.url}" title="${yryjds.title}" target="${yryjds.target}"><img src="${base}/${yryjds.picUrl}"></a><br><em>[${yryjds.subTitle!''}]</em>${yryjds.title}</p>
</#if>
</#list>
</div>
<ul class="display">
<#list zb_yryjdz as yryjdz>
<#if yryjdz_index==0>
<li class="first"><p><a title="${yryjdz.title}" href="${yryjdz.url}" target="${yryjdz.target}"><img src="${base}/${yryjdz.picUrl}"></a><br>
	<#if yryjdz.title?length lte 5>
	${yryjdz.title}<br>
	<#elseif yryjdz.title?length lte 10>
	${yryjdz.title[0..4]}<br>
	${yryjdz.title[5..]}
	<#else>
	${yryjdz.title[0..4]}<br>
	${yryjdz.title[5..8]}..
	</#if>
<em>${yryjdz.subTitle!""}</em></p>
<#elseif (yryjdz_index%2)==0>
<li><p><a href="${yryjdz.url}" title="${yryjdz.title}" target="${yryjdz.target}"><img src="${base}/${yryjdz.picUrl}"></a><br>
	<#if yryjdz.title?length lte 5>
	${yryjdz.title}<br>
	<#elseif yryjdz.title?length lte 10>
	${yryjdz.title[0..4]}<br>
	${yryjdz.title[5..]}
	<#else>
	${yryjdz.title[0..4]}<br>
	${yryjdz.title[5..8]}..
	</#if>
<em>${yryjdz.subTitle!""}</em></p>
<#else>
<p><a href="${yryjdz.url}" title="${yryjdz.title}" target="${yryjdz.target}"><img src="${base}/${yryjdz.picUrl}"></a><br>
	<#if yryjdz.title?length lte 5>
	${yryjdz.title}<br>
	<#elseif yryjdz.title?length lte 10>
	${yryjdz.title[0..4]}<br>
	${yryjdz.title[5..]}
	<#else>
	${yryjdz.title[0..4]}<br>
	${yryjdz.title[5..8]}..
	</#if>
<em>${yryjdz.subTitle!""}</em></p></li>
</#if>
</#list>

</ul>
<ul class="info">
<#list zb_yryjdx as yryjdx>
<#if yryjdx_index<2>
<li><em>[${yryjdx.subTitle!''}]</em><a href="${yryjdx.url}" title="${yryjdx.title}" target="${yryjdx.target}">${yryjdx.title}</a></li>
</#if>
</#list>
</ul></div></div></div>
		<!-- //主题推荐 -->

		<!-- 企业拓展 -->
		<div class="protal-column-t1">
<div class="main" id="clothes">
<h3>企业拓展</h3>
<div class="cover">
<#list zb_qytzsz as qytzsz>
<#if qytzsz_index==0>
<a href="${qytzsz.url}" title="${qytzsz.title}" target="${qytzsz.target}"><img src="${base}/${qytzsz.picUrl}"></a>
</#if>
</#list>
</div>
<div class="info">
<#list zb_qytzsys as qytzsys>
<#if qytzsys_index==0>
<a class="title" href="${qytzsys.url}" title="${qytzsys.title}" target="${qytzsys.target}">${qytzsys.title}</a><br>
<#elseif qytzsys_index==1>
<a href="${qytzsys.url}" title="${qytzsys.title}" target="${qytzsys.target}"><em>[${qytzsys.title}]</em></a> &nbsp; 
<#elseif qytzsys_index==2>
<a href="${qytzsys.url}" title="${qytzsys.title}" target="${qytzsys.target}">[${qytzsys.title}]</a> 
</#if>
</#list>
</div>
<ul class="info_list">
<#list zb_qytzsyx as qytzsyx>
<#if qytzsyx_index<5>
<li><a href="${qytzsyx.url}" title="${qytzsyx.title}" target="${qytzsyx.target}"><em>[${qytzsyx.subTitle!''}] </em>${qytzsyx.title}</a>
</#if>
</#list>
</ul>
<h4>企业拓展下</h4>
<ul class="hot">
<#list zb_qytzx as qytzx>
<#if qytzx_index<7>
<li><a href="${qytzx.url}" title="${qytzx.title}" target="${qytzx.target}"><img src="${base}/${qytzx.picUrl}"><br>
	<#if qytzx.title?length lte 6>
	${qytzx.title[0..]}
	<#else>
	${qytzx.title[0..5]}
	</#if>
</a><br><em>兔游价：${qytzx.price}</em> </li>
</#if>
</#list>
</ul></div>
<div class="side" id="magic">
<h3>二日游景点</h3>
<div class="mod-content">
<div class="in">
<#list zb_eryjds as eryjds>
<#if eryjds_index<2>
<p><a href="${eryjds.url}" title="${eryjds.title}" target="${eryjds.target}"><img src="${base}/${eryjds.picUrl}"></a><br><em>[${eryjds.subTitle!''}]</em>${eryjds.title}</p>
</#if>
</#list>
</div>
<ul class="display">
<#list zb_eryjdz as eryjdz>
<#if eryjdz_index==0>
<li class="first"><p><a href="${eryjdz.url}" title="${eryjdz.title}" target="${eryjdz.target}"><img src="${base}/${eryjdz.picUrl}"></a><br>
	<#if eryjdz.title?length lte 5>
	${eryjdz.title}<br>
	<#elseif eryjdz.title?length lte 10>
	${eryjdz.title[0..4]}<br>
	${eryjdz.title[5..]}
	<#else>
	${eryjdz.title[0..4]}<br>
	${eryjdz.title[5..8]}..
	</#if>
<em>${eryjdz.subTitle!""}</em></p>
<#elseif (eryjdz_index%2)==0>
<li><p><a href="${eryjdz.url}" title="${eryjdz.title}" target="${eryjdz.target}"><img src="${base}/${eryjdz.picUrl}"></a><br>
	<#if eryjdz.title?length lte 5>
	${eryjdz.title}<br>
	<#elseif eryjdz.title?length lte 10>
	${eryjdz.title[0..4]}<br>
	${eryjdz.title[5..]}
	<#else>
	${eryjdz.title[0..4]}<br>
	${eryjdz.title[5..8]}..
	</#if>
<em>${eryjdz.subTitle!""}</em></p>
<#else>
<p><a href="${eryjdz.url}" title="${eryjdz.title}" target="${eryjdz.target}"><img src="${base}/${eryjdz.picUrl}"></a><br>
	<#if eryjdz.title?length lte 5>
	${eryjdz.title}<br>
	<#elseif eryjdz.title?length lte 10>
	${eryjdz.title[0..4]}<br>
	${eryjdz.title[5..]}
	<#else>
	${eryjdz.title[0..4]}<br>
	${eryjdz.title[5..8]}..
	</#if>
<em>${eryjdz.subTitle!""}</em></p></li>
</#if>
</#list>
</ul>
<ul class="info">
<#list zb_eryjdx as eryjdx>
<#if eryjdx_index<2>
<li><em>[${eryjdx.subTitle!''}]</em><a href="${eryjdx.url}" title="${eryjdx.title}" target="${eryjdx.target}">${eryjdx.title}</a></li>
</#if>
</#list>
</ul></div></div></div>
		<!-- //企业拓展 -->
		
		<!-- 苏州游玩 -->
		<div class="protal-column-t1">
<div class="main" id="decoration">
<h3>苏州游玩</h3>
<div class="cover">
<#list zb_szywsz as szywsz>
<#if szywsz_index==0>
<a href="${szywsz.url}" title="${szywsz.title}" target="${szywsz.target}"><img src="${base}/${szywsz.picUrl}"></a>
</#if>
</#list>
</div>
<div class="info">
<#list zb_szywsys as szywsys>
<#if szywsys_index==0>
<a class="title" href="${szywsys.url}" title="${szywsys.title}" target="${szywsys.target}">${szywsys.title}</a><br>
<#elseif szywsys_index==1>
<a href="${szywsys.url}" title="${szywsys.title}" target="${szywsys.target}"><em>[${szywsys.title}]</em></a> &nbsp; 
<#elseif szywsys_index==2>
<a href="${szywsys.url}" title="${szywsys.title}" target="${szywsys.target}">[${szywsys.title}]</a> 
</#if>
</#list>
</div>
<ul class="info_list">
<#list zb_szywsyx as szywsyx>
<#if szywsyx_index<5>
<li><a href="${szywsyx.url}" title="${szywsyx.title}" target="${szywsyx.target}"><em>[${szywsyx.subTitle!''}] </em>${szywsyx.title}</a>
</#if>
</#list>
</ul>
<h4>苏州游玩下</h4>
<ul class="hot">
<#list zb_szywx as szywx>
<#if szywx_index<7>
<li><a href="${szywx.url}" title="${szywx.title}" target="${szywx.target}"><img src="${base}/${szywx.picUrl}"><br>
	<#if szywx.title?length lte 6>
	${szywx.title[0..]}
	<#else>
	${szywx.title[0..5]}
	</#if>
</a><br>
<em>兔游价：${szywx.price}</em> </li>
</#if>
</#list>
</ul></div>
<div class="side" id="magic">
<h3>三日游景点</h3>
<div class="mod-content">
<div class="in">
<#list zb_sryjds as sryjds>
<#if sryjds_index<2>
<p><a href="${sryjds.url}" title="${sryjds.title}" target="${sryjds.target}"><img src="${base}/${sryjds.picUrl}"></a><br><em>[${sryjds.subTitle!''}]</em>${sryjds.title}</p>
</#if>
</#list>
</div>
<ul class="display">
<#list zb_sryjdz as sryjdz>
<#if sryjdz_index==0>
<li class="first"><p><a href="${sryjdz.url}" title="${sryjdz.title}" target="${sryjdz.target}"><img src="${base}/${sryjdz.picUrl}"></a><br>
	<#if sryjdz.title?length lte 5>
	${sryjdz.title}<br>
	<#elseif sryjdz.title?length lte 10>
	${sryjdz.title[0..4]}<br>
	${sryjdz.title[5..]}
	<#else>
	${sryjdz.title[0..4]}<br>
	${sryjdz.title[5..8]}..
	</#if>
<em>${sryjdz.subTitle!""}</em></p>
<#elseif (sryjdz_index%2)==0>
<li><p><a href="${sryjdz.url}" title="${sryjdz.title}" target="${sryjdz.target}"><img src="${base}/${sryjdz.picUrl}"></a><br>
	<#if sryjdz.title?length lte 5>
	${sryjdz.title}<br>
	<#elseif sryjdz.title?length lte 10>
	${sryjdz.title[0..4]}<br>
	${sryjdz.title[5..]}
	<#else>
	${sryjdz.title[0..4]}<br>
	${sryjdz.title[5..8]}..
	</#if>
<em>${sryjdz.subTitle!""}</em></p>
<#else>
<p><a href="${sryjdz.url}" title="${sryjdz.title}" target="${sryjdz.target}"><img src="${base}/${sryjdz.picUrl}"></a><br>
	<#if sryjdz.title?length lte 5>
	${sryjdz.title}<br>
	<#elseif sryjdz.title?length lte 10>
	${sryjdz.title[0..4]}<br>
	${sryjdz.title[5..]}
	<#else>
	${sryjdz.title[0..4]}<br>
	${sryjdz.title[5..8]}..
	</#if>
<em>${sryjdz.subTitle!""}</em></p></li>
</#if>
</#list>
</ul>
<ul class="info">
<#list zb_sryjdx as sryjdx>
<#if sryjdx_index<2>
<li><em>[${sryjdx.subTitle!''}]</em><a href="${sryjdx.url}" title="${sryjdx.title}" target="${sryjdx.target}">${sryjdx.title}</a></li>
</#if>
</#list>
</ul></div></div></div>
		<!-- //苏州游玩 -->

		<!-- 通栏 -->
		<!--

<DIV class=protal-column-t0>
<DIV class=main style="PADDING-RIGHT: 50px; PADDING-LEFT: 50px; BACKGROUND: #ccc; PADDING-BOTTOM: 30px; COLOR: #fff; PADDING-TOP: 30px">Advertisement 950*70</DIV></DIV>


-->
		<!-- //通栏 -->
			
		<!-- 人气城市和景区排行 -->
		<div class="protal-column-t0" id="sales">
<div class="corner">&nbsp;</div>
<div>
<div class="display">
<ul>
<#list zb_rqcs as rqcs>
<#if rqcs_index<5>
<li><a href="${rqcs.url}" title="${rqcs.title}" target="${rqcs.target}"><img src="${base}/${rqcs.picUrl}"><br>${rqcs.title}<br><em>${rqcs.subTitle!''}</em></a></li>
</#if>
</#list>
</ul></div>
<div class="rank">
<div class="tit">
<h3>景区排行</h3>
<a class="more" href="${base}/scenery/sceneryList.shtml" target="_blank">更多&#187;</a></div>
<ol>
<#list zb_jqph as jqph>
<#if jqph_index<3>
<li><a href="${jqph.url}" title="${jqph.title}" target=""${jqph.target}"><img src="${base}/${jqph.picUrl}"><em>${jqph.subTitle!''}</em><br>${jqph.title}</a></li>
</#if>
</#list>
</ol></div></div></div>
		<!-- //人气城市和景区排行 -->

	</div>

	<!-- footer -->
	<@footer />
	<!-- //footer -->
	
</div>
<div class="panel panel-t0" id="dlg_nav" style="display: none; width: 239px; position: absolute;">
<div class="panel-content">
<div class="bd">
<!-- content -->
<style>
			.panel-temp-0809279801 {position:relative;zoom:1;width:239px;height:163px;}
			.panel-temp-0809279801 .bg {width:239px;height:163px;position:absolute;background:url("http://www.tutu6.com/public/20081205/image/panel_wm_t.png") no-repeat;_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="http://www.tutu6.com/public/20081205/image/panel_wm_t.png");_background:none;}
			.panel-temp-0809279801 .inner {position:relative;padding:15px 15px 15px 28px;z-index:10;zoom:1;}
			.panel-temp-0809279801 .inner li {width:49%;float:left;font-size:12px;line-height:1.8;}
			.panel-temp-0809279801 .inner li a {color:#fb4142;}
			</style>

<div class="panel-temp-0809279801">
<div class="bg"></div>
<div class="inner">

<#list regions as regionP>
	<#if regionP_index==0>
	<ul class="selected">
	<#else>
	<ul class="unselected">
	</#if>
	<#list regionP.childrenList as regionC>
		<#if regionC_index<12>
		<li><a href="${base}/team/s.shtml?regionId=${regionC.regionId}&showType=1" target="_blank">${regionC.name}</a> </li>
		</#if>
	</#list>
	</ul>
</#list>

</div>
</div>
<!-- //content -->
</div>
</div>
<span class="co1">
<span></span>
</span>
<span class="co2">
<span></span>
</span>
<span class="cue"></span><span class="sd"></span><span class="resize"></span></div>

<script type="text/javascript">
var lis=G("ddContainer-2").getElementsByTagName("li"),uls=G("lf-hot").getElementsByTagName("ul");var tabView=new TabView({"events":["click"],"preventDefault":true});CustEvent.observe(tabView,"active",function(B){var A=B.contentNode.getElementsByTagName("textarea");if(A.length>0){B.contentNode.innerHTML=A[0].value;}});for(var i=0,l=lis.length;i<l;i++){tabView.addTab({tabNode:lis[i],contentNode:uls[i]});}document.DOMLoaded(function(){var tree=G("tree-nav");if(!tree){return ;}var h3s=tree.getElementsByTagName("h3");if(h3s.length<1){return ;}var popup=new LayerPopup(G("dlg_nav"),{showTimeout:250,hideTimeout:250,autoPosition:false});Dom.getArray(h3s).each(function(el,index){el=el.getElementsByTagName("a")[0];BBEvent.observe(el,"mouseover",function(e){with({i:index}){var uls=popup.getPopup().getElementsByTagName("ul");Dom.getArray(uls).each(function(el,index){el.className="unselected";if(i==index){el.className="selected";}});}popup.hide();popup.delayShow(el.offsetWidth-3,-18,null,null,el);});BBEvent.observe(el,"mouseout",function(e){popup.delayHide();});});});
</script>

</body></html>