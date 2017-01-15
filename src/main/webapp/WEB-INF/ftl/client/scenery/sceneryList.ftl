<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<META content="${base}/${webSite.desc}" name=description>
<META content="${base}/${webSite.keywords}" name=keywords>
<title>${area.name}景点列表——要旅游 先兔游</title>
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/index.css" rel="stylesheet" type="text/css" media="all">
<script type="text/javascript" src="${base}/public/20081205/script/commons.js"></script>
<script type="text/javascript">
function doSearch(keyword) {
	if (keyword != "") {
		return true;
	} else {
		alert("请输入您要查询的关键字!");
	}
	return false;
}
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
</head><body>
<#include "*/banner.ftl">
<#include "*/header.ftl">
<#include "*/footer.ftl">
<#include "page.ftl">
<div id="doc3">
	<div id="hd">
		<!-- {start:global master header -->
		<@header />
		<!-- }end:global master header -->
	</div>

	<div id="bd">
		
		<!-- {start:header -->
		<@banner />
		<!-- }end:header -->
		<div class="wrap clearfix">
	<!--头部-->
    	

    <!--//头部-->

	<div id="where_nav">位置： 
				<a href="${base}/">兔游</a> &gt; 
				<#list directList as city>
				<#if city_has_next>
				<#if city.depth==1>
				<a href="${base}/scenery/sceneryList.shtml?areaId=${city.areaId}">${city.name}</a> &gt;
				<#elseif city.depth==2>
				<a href="${base}/scenery/sceneryList.shtml?areaId=${city.areaId}">${city.name}</a> &gt;
				<#elseif city.depth==3>
				<#if !((city.name=="直辖市")||city.name==("特别行政区"))>
				<a href="${base}/scenery/sceneryList.shtml?areaId=${city.areaId}">${city.name}</a> &gt;
				</#if>
				</#if>
				<#else>
				${city.name}景点列表
				</#if>
				</#list>
	</div>

	<div class="left">
		

		<!--搜索-->
        <div id="left_search">
			<h2><label>景点搜索</label></h2>
			<form action="${base}/scenery/sceneryList.shtml" method="get" onsubmit="return doSearch(this.keyWord.value)">
			<input name="areaId" value="${areaId}" type="hidden">
			<div class="border_1c">
				
				<dl class="list">
					<dt>自然风光</dt>
					<dd>
					<#list ziranList as ziran>
					<#if typeId==ziran.typeId>
					<a class="select">${ziran.name}</a>
					<#else>
					<a href="${base}/scenery/sceneryList.shtml?areaId=${areaId}&amp;typeId=${ziran.typeId}&amp;showType=${showType}">${ziran.name}</a>
					</#if>
					</#list>
					</dd>
					</dl>
					<dl class="list">
					<dt>人文景观</dt>
					<dd>
					<#list renwenList as renwen>
					
					<#if typeId==renwen.typeId>
					<a class="select">${renwen.name}</a>
					<#else>
					<a href="${base}/scenery/sceneryList.shtml?areaId=${areaId}&amp;typeId=${renwen.typeId}&amp;showType=${showType}">${renwen.name}</a>
					</#if>
					</#list>
					
					</dd>
					</dl>
					<dl class="list">
					<dt>首字母</dt>
					<dd>
					<#list arrayLetter as thisletter>
					<#if letter==thisletter>
					<a class="select">${thisletter}</a>
					<#else>
					<a href="${base}/scenery/sceneryList.shtml?areaId=${areaId}&amp;letter=${thisletter}&amp;showType=${showType}">${thisletter}</a>
					</#if>
					</#list>
					</dd>
					</dl>
					
				<dl>
					<dt>关键字</dt>
					<dd><input id="search_input" name="keyWord" value="" class="text2" type="text"><input name="Submit" value="搜索" class="btn" type="submit"></dd>
				</dl>
				
				<p><a href="${base}/scenery/sceneryList.shtml?areaId=${area.areaId}">查看${area.name}所有景点</a></p>
			</div>
			</form>
		</div>
        <!--//搜索-->
<div id="week_star">
			<h2>本月热门景点</h2>
			<div class="left_c">
				<#list topTen as scence>
				<#if scence_index gt 0>
				<#break>
				</#if>
				<div class="top1 clearfix">
					<a  href="${base}/scenery/sceneryDetail/${scence.sceneryId}.html" class="img" target="_blank">
					<img src="${base}/${scence.displayUrl}" alt="${scence.name}" height="64" onload="resizeImg(this);" /></a>
					<dl>
						<dt><a  href="${base}/scenery/sceneryDetail/${scence.sceneryId}.html" target="_blank">
						<img src="${base}/public/20081205/image/week_star_ico1.jpg" alt="1" />${scence.name}</a></dt>
						<dd>等级：<#switch scence.level>
					<#case 1>
						A
    				<#break>
    				<#case 2>
						AA
    				<#break>
    				<#case 3>
						AAA
    				<#break>
    				<#case 4>
						AAAA
    				<#break>
    				<#case 5>
						AAAAA
    				<#break>
    		
				</#switch></dd>
						<dd>
						<a  href="${base}/scenery/sceneryDetail/${scence.sceneryId}.html" target="_blank"><img src="${base}/public/20081205/image/icon_search.jpg"  />详细介绍</a>
						</dd>
					</dl>
				</div>
				
				</#list>
				<ul>
				<#list topTen as scence>
				<#if scence_index gt 0>
					<li><span><a  href="${base}/scenery/sceneryDetail/${scence.sceneryId}.html" target="_blank"><img src="${base}/public/20081205/image/icon_search.jpg" alt="${scence.name}" /></a></span>
					<img src="${base}/public/20081205/image/week_star_ico#{scence_index+1}.jpg" alt="2" class="no" />
					<a  href="${base}/scenery/sceneryDetail/${scence.sceneryId}.html" class="cn" target="_blank" blockid="590">${scence.name}</a> <a  href="${base}/scenery/sceneryDetail/${scence.sceneryId}.html" class="en" target="_blank" blockid="590"><#switch scence.level>
					<#case 1>
						A
    				<#break>
    				<#case 2>
						AA
    				<#break>
    				<#case 3>
						AAA
    				<#break>
    				<#case 4>
						AAAA
    				<#break>
    				<#case 5>
						AAAAA
    				<#break>
    		
				</#switch></a>
					</li>
				</#if>
				</#list>
					
</ul>
			</div>
		</div>
				
	</div>
	



		<div class="right starListBoxRight">
		<div class="starListBox">
		<div class="yuanjiao_lt"></div>
		<div class="yuanjiao_rt"></div>
		<div class="yuanjiao_lb"></div>
		<div class="yuanjiao_rb"></div>
		<ul class="modeChoice">
		<#if showType==0>
			<li><a class="currA"><span>文字模式</span></a></li>
			<li><a href="${base}/scenery/sceneryList.shtml?areaId=${area.areaId}&amp;letter=${letter}&amp;typeId=${typeId}&amp;keyWord=${keyWord}&amp;showType=1" title="图片模式"><span>图片模式</span></a></li>
		<#else>
			<li><a href="${base}/scenery/sceneryList.shtml?areaId=${area.areaId}&amp;letter=${letter}&amp;typeId=${typeId}&amp;keyWord=${keyWord}&amp;showType=0" title="文字模式"><span>文字模式</span></a></li>
			<li><a class="currA"><span>图片模式</span></a></li>
		</#if>
		</ul>
			
			<#if showType==1>
			<div id="star_list">
			
			<#if scList??>
			<#list scList as scen>
			<div class="film_block">
				<div class="img"><a target="_blank" href="${base}/scenery/sceneryDetail/${scen.sceneryId}.html"><img onload="resizeImg(this);" src="${base}/${scen.displayUrl}" height="218"></a><img src="${base}/public/20081205/image/blank.gif" class="blank"></div>
				
				<dl>
					<dt class="w1"><a href="${base}/scenery/sceneryDetail/${scen.sceneryId}.html" target="_blank">${scen.name}</a></dt>
					
					<dd style="margin-top:10px;color:orange"><label>类型：</label><#list scen.types as type>${type.name}<#if type_has_next>&nbsp;,&nbsp;</#if></#list></dd>
					<dd style="width:440px; height:90px"><label>简介：</label><#if scen.synopsis?length lt 140>${scen.synopsis}<#else>${scen.synopsis [0..139]}...</#if></dd>
					
					<dd><span><label>首字母：</label>${scen.firstLetter}</span></dd>
				</dl>
				<p><a href="${base}/scenery/sceneryDetail/${scen.sceneryId}.html" target="_blank"><img src="${base}/public/20081205/image/film_more_icon.jpg" alt="详情"></a><a href="${base}/scenery/getSceneryPic/${scen.sceneryId}" target="_blank"><img src="${base}/public/20081205/image/film_col_icon_jia.jpg" alt="景点图片"></a></p>
				<span class="ge">
				<#switch scen.level>
					<#case 1>
						A
    				<#break>
    				<#case 2>
						AA
    				<#break>
    				<#case 3>
						AAA
    				<#break>
    				<#case 4>
						AAAA
    				<#break>
    				<#case 5>
						AAAAA
    				<#break>
    		
				</#switch>
				</span> </div>
				</#list>
			</#if>
			 			
			 		
			<!-- pagenav --> 
			
			<p class="b_page" style="text-align:center"><#if scList??><@pagination /></#if></p>
			
		<!--// pagenav -->
		</div>
		<#else>
		
			<div class="starNameList">
			<#if letter=="">
			<#list arrayLetter as letters>
					<a href="#tag_${letters}" title="${letters}">${letters}</a>
			</#list>
				
			<#else>
			<#list arrayLetter as letters>
				<a href="?areaId=${areaId}&amp;letter=${letters}&amp;showType=0" title="${letters}" <#if letters==letter>class="currA"</#if>>${letters}</a>
			</#list>
				<a href="?areaId=${areaId}&amp;showType=0" title="全部" >全部</a>
			</#if>
			</div>
		<div class="starList" id="starListTop">
			<#if letter=="">
					<#list losList as sclitter>
					
												<div class="starTextList">
					<a name="tag_${sclitter.letter}"></a>
					<h3>${sclitter.letter}</h3>
					<#if sclitter.scList??>
					<ul>
					
					<#list sclitter.scList as scence>
						<li><a href="${base}/scenery/sceneryDetail/${scence.sceneryId}.html" title="${scence.name}" target="_blank">${scence.name}<#if scence.subTopic??&&scence.subTopic!="">(${scence.subTopic})</#if></a></li>
					</#list>	
					
					</ul>
					</#if>
					<div class="otherLink">
					<#if (sclitter.scList??)>
					<#if sclitter.scList?size==20>
						<a href="${base}/scenery/sceneryList.shtml?areaId=${areaId}&amp;letter=${sclitter.letter}&amp;showType=0" title="更多&gt;&gt;">更多&gt;&gt;</a>	
					</#if>
					</#if><a href="#top">↑</a>
					</div>
				</div>
				
				</#list>
			<#else>	
			
				
												<div class="starTextList">
					<a name="tag_${letter}"></a>
					<h3>${letter}</h3>
					<#if scList??>
					<ul>
					
					<#list scList as scence>
						<li><a href="#" title="${scence.name}" target="_blank">${scence.name}<#if scence.subTopic??&&scence.subTopic!="">(${scence.subTopic})</#if></a></li>
					</#list>	
					
					</ul>
					</#if>
					
				</div>
			</#if>
						
			</div>
			
		</#if>
		</div>
		
	</div>
	<div style="clear: both;"></div>
</div>
		
		
		
	</div>
	<!-- footer -->
	<@footer />
	<!-- //footer -->

</div>

</body></html>