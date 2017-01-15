<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<META content=兔游全新大型旅游购物网站，为人们提供更便捷、可靠的一站式旅游消费体验，使您旅游省事更安心。name=description>
<META content=兔游网，苏州旅游，苏州青旅 name=keywords>
<title>${nation.area.name}详细介绍——要旅游 先兔游</title>
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/bbp-panel.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/dcontent.css" rel="stylesheet" type="text/css" media="screen">
<script type="text/javascript">
//<![CDATA[
function resizeImg(img) {
	try {
		if (img.offsetWidth > 0) {
			if (img.offsetWidth > img.height) {
				img.width = img.height;
				img.removeAttribute('height');

			}
		}
		else {
			setTimeout(function() {resizeImg(img);}, 50);
		}
	}
	catch (ex) {
		;
	}
}

var imgSiteUrl = 'http://tutu6.com/public/20081205/image';
//]]>
</script>

</head><body class="portal-women">
<#include "*/banner.ftl">
<#include "*/header.ftl">
<#include "*/teamfoot.ftl">
<#include "slide.ftl">
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

		
		<!-- channel nav -->
				<!-- {{start:portal nav -->
		<div id="portal-nav" class="lf-mod4">
		<span class="x1"><span class="x1a"></span></span>
		   <div class="mod-content">
			<div class="bd">
				<ul class="cls">
					<li>${nation.area.name}旅游城市：</li>
					
					<#list cityList as city>
					<li><a href="${base}/destination/cityDetail/${city.areaId}.html">${city.name}</a></li>
					</#list>
				</ul>
			</div>
		   </div>
		<span class="x2"><span class="x2a"></span></span>
		</div>
		<!-- }}end:portal nav -->
		<!-- //channel nav -->

		<!-- 主要内容 -->
		<div class="protal-column-t1 column-list">
			<div class="main">
				<div id="article">
				<!-- 正文 -->
				<div class="path">位置： 
				<a href="#">兔游</a> &gt; 
				<#list directList as city>
				<#if city_has_next>
				<a href="${base}/destination/continent/${city.areaId}.html">${city.name}</a> &gt;
				<#else>
				${city.name}
				</#if>
				</#list>
				</div>
				<#if nation.gloze??>
				<#if nation.gloze!="">
				<div class="title">
				${nation.gloze!""}
				<#if sceneryList??>
				<p>相关景点：<#list sceneryList as scen><a href="${base}/scenery/sceneryDetail/${scen.sceneryId}.html" target="_blank">${scen.name}</a></#list><span><a href="${base}/scenery/sceneryList.shtml?areaId=${con.area.areaId}">更多景点&gt;&gt;&gt;</a></span></p>	
				</#if>
				</div>
				
				</#if>
				</#if>
				<div class="content">
					
						<div class="richtext">
						
${content}
<div id="paging_div" style="padding-left: 18px; font-size: 14px; word-spacing: 4px;" align="right">
<#if pageId!=1>
<a class="paging_pre" href="${base}/destination/countryDetail/${areaId}/#{pageId-1}.html" target="_self">上一页</a>
</#if>
<#list 1..page.pageCnt as i>
<#if i==pageId>
${i}&nbsp;
<#else>
<a class="paging_a" href="${base}/destination/countryDetail/${areaId}/${i}.html" target="_self">${i}</a>&nbsp;
</#if>
</#list>
<#if pageId!=page.pageCnt>
<a class="paging_pre" href="${base}/destination/countryDetail/${areaId}/#{pageId+1}.html" target="_self">下一页</a>
</#if>
</div>
<br>
						
						</div>

				</div>
				<!-- //正文 -->
				</div>
				<!-- 热卖单品 -->		
					<@teamfooter />
				<!-- //热卖单品 -->
			</div>
			
			<div class="side">
				<#if rightPicList??>
<div class="slide_module">
<div class="mod_title">
<span class="act"><a href="${base}/destination/getAreaPic/${nation.area.areaId}" target="_blank">更多</a></span>
<h3>美图赏析</h3></div>
<div class="mod_content">
<div class="show sight">
<#list rightPicList as sightPic>
<div class="sightPic"><div class="img"><a href="${base}/destination/getAreaPic/${nation.area.areaId}" target="_blank" title="更多${nation.area.name}图片"><img src="${base}/${sightPic.breviaryUrl}" onload="resizeImg(this);" height=120px><img src="${base}/public/20081205/image/blank.gif" class="blank"></a></div></div>
</#list>
</div></div></div>
</#if>
			<!-- 边栏内容 -->
			<@slide />
			<!-- //边栏内容 -->
			</div>
		<!-- //主要内容 -->

	</div>


	<!-- footer -->
	<@footer />
	<!-- //footer -->
</div>


</body></html>