<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<META content=兔游全新大型旅游购物网站，为人们提供更便捷、可靠的一站式旅游消费体验，使您旅游省事更安心。name=description>
<META content=兔游网，苏州旅游，苏州青旅 name=keywords>
<title>中国城市景区导航——要旅游 先兔游</title>
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/bbp-panel.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/dcontent.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/destination.css" type="text/css" rel="stylesheet">

</head><body class="portal-women">
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

		
		<!-- channel nav -->
		<div id="city-nav">
				<div class="path">位置： 
				<a href="${base}/">兔游</a> &gt; 
				<#list directList as city>
				<#if city_has_next>
				<#if city.depth==1>
				<a href="${base}/destination/continent/${city.areaId}.html">${city.name}</a> &gt;
				</#if>
				<#else>
				${city.name}
				</#if>
				</#list>
				</div>
		</div>
		<!-- //channel nav -->

		<!-- 主要内容 -->
		<div id="main02">
                





<div class="cf">

<div class="area07">

<div class="block21">
<span class="topBar"></span>
<div class="map"><img src="${base}/${nation.mapUrl!""}" alt="" usemap="#Map" /></div>
<div class="mod23">
<#if sceneryList??>
<h2>热门景点</h2>
<ul class="cf">
<#list sceneryList as scenery>
<li><a href="${base}/scenery/sceneryDetail/${scenery.sceneryId}.html">${scenery.name}</a></li>
</#list>
</ul>
</#if>
</div>
<span class="btmBar"></span>
</div>

</div>

<div class="area08">


<div class="block22">
<#list cityList as pro>
<h2>
<#if pro_index==0||pro_index==1>
${pro.name}
<#else>
<a href="${base}/destination/proviceDetail/${pro.areaId}.html">${pro.name}</a></#if>
</h2>
<ul class="cf">
<#list pro.areaList as city>
<li><a href="${base}/destination/cityDetail/${city.areaId}.html" title="${city.name}">
<#if city.name?length <= 6>
${city.name}
<#else>
${ city.name [0..4]}...
</#if>
</a></li>
</#list>
</ul>
</#list>


</div>


</div>

</div>


<map name="Map" id="Map">
${nation.mapTopic!""}
</map>

            </div>
		<!-- //主要内容 -->

	</div>

	<!-- footer -->
	<@footer />
	<!-- //footer -->
</div>

</body></html>