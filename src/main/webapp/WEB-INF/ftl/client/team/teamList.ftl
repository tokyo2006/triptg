<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
<!--STATUS OK-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<META content="${base}/${webSite.desc}" name=description>
<META content="${base}/${webSite.keywords}" name=keywords>
<title>线路搜索——要旅游，先兔游</title>
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/module-front4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/bbp-panel4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/route_calendar.css" rel="stylesheet" type="text/css" media="screen">
<style>
.temp200808012845 {padding:4px;display:table-cell;vertical-align:middle;*display:block;*font-size:250px;text-align:-moz-center;*text-align:center;width:300px;height:300px;}
.temp200808012845 img {vertical-align:middle;margin:auto;}
.temp2008080 {padding:4px;}
</style>




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
var imgSiteUrl = 'http://www.tutu6.com/public/20081205/image';

//]]>
</script>
</head>
<body>
<#include "*/banner.ftl">
<#include "*/header.ftl">
<#include "*/footer.ftl">
<#include "*/search.ftl">
<div id="doc3" class="bb-l220">

	<div id="hd">
		<!-- {start:global master header -->
		<@header />
		<!-- }end:global master header -->
	</div>

	<div id="bd">
		
		<!-- {start:header tab -->
		<@banner />
		<!-- }end:header -->


		<div class="cls">
		
		
		<div class="bb-c">
				
				<div class="se-side">
				
				
				
				<!-- {start:history -->
				
				<div class="mod history" style="">
					<div class="hd cls"><a id="clear_history" href="#" class="clear">清空</a><h3>最近浏览过的线路</h3></div>
					<#if skimList??>
					<div class="bd">
					<ul id="item_navigation_history" class="cls">
					<#list skimList as team>
					<li><div class="img"><a href="${base}/team/teamContent/${team.teamId}" target="_blank"><img onload="resizeImg(this);" src="${base}/${team.orgUrl}" title="${team.teamName}" height="58"></a><img src="${base}/public/20081205/image/blank.gif" class="blank"></div>
					<a href="${base}/team/teamContent/${team.teamId}" target="_blank">${team.doorDisPrice!""}.00</a></li>
					</#list>
					</ul></div>
					<#else>
					<p style="padding:20px 10px;text-align:center;color:#666;">您已清空最近浏览过的线路</p>
					</#if>
					<div class="ft"></div>
				</div>
				<!-- }end:history -->
				
				<#if teamFlag!=-1>
				<!-- {start:regionList -->
				
				<div class="mod regionList">
					<div class="hd cls"><h3><#if teamFlag==1>
		短线旅游分类
		<#elseif teamFlag==2>
		长线旅游分类
		<#elseif teamFlag==3>
		出境旅游分类
		<#elseif teamFlag==4>
		自由行分类
		</#if></h3></div>
					
					<div class="bd">
					<ul class="cls">
					<!-- item -->
<#list regionList as sonRengion>
<div class="item <#if (sonRengion_index%2==0)>zebra</#if>">
<h3><A href="${base}/team/s.shtml?regionId=${sonRengion.regionId}&showType=1"><#if regionId==sonRengion.regionId><font color="#f97e06">${sonRengion.name}</font><#else>${sonRengion.name}</#if></a></h3>
<ul class="cls">
<#list sonRengion.childrenList as childRengion>
<#if childRengion.lineCnt!=0>
<li><a href="${base}/team/s.shtml?regionId=${childRengion.regionId}&showType=1"><#if regionId==childRengion.regionId><font color="#f97e06">${childRengion.name}</font><#else>${childRengion.name}</#if></a></li>
<#else>
<li><span><#if regionId==childRengion.regionId><font color="#f97e06">${childRengion.name}</font><#else>${childRengion.name}</#if></span></li>
</#if>
</#list>
</ul>
</div>
</#list>
<!-- //item -->
					</ul></div>
					
				</div>
				<!-- }end:regionList -->
				</#if>
				
				
				
				<!-- {start:filter -->
				<form action="s.shtml" method="get">
					<input name="showType" value="${showType}" type="hidden">
					<input name="regionId" value="${regionId}" type="hidden">
					<div class="mod filter">
						<div class="hd cls">
							<h3>缩小搜索范围</h3>
						</div>
						<div class="bd">
							<div class="filter-simply">
								<ul>
									<li><label for="">关键字：</label><input name="keyWord" class="inp1" value="${keyWord}" type="text"></li>
									<li>价&nbsp;&nbsp;&nbsp;&nbsp;格：<input id="priceStart" name="priceStart" class="inp2" <#if priceStart!=-1>value="#{priceStart}"<#else>value=""</#if> type="text">至<input id="priceEnd" name="priceEnd" class="inp2" <#if priceEnd!=-1>value="#{priceEnd}"<#else>value=""</#if> type="text"></li>
									<li><input id="t1" name="tejia" value="1" type="checkbox" <#if tejia==1> checked</#if>><label for="t1">特价优惠</label></li>
									<li><input id="t2" name="tuijian" type="checkbox" value="1" <#if tuijian==1> checked</#if>><label for="t2">热门推荐</label></li>
								</ul>
							</div>
							
							
							<div class="ft">
								<button type="submit" id="adv_search_button">确定</button>
							</div>
						</div>
					</div>
				</form>
				<!-- }end:filter -->

			</div>
			</div>
		
		<div id="bb-main">
		
		
		
			<div class="bb-c">
			
			
			<!-- {start:navigation -->
		<div class="se-flow">先验货后付款   1.选择商品 &gt; 2.付款到百付宝 &gt; 3.验货满意 &gt; 4.付款给卖家</div>
		<div class="nav">
			<p class="nav-position">
				<#if teamList??>
				<span class="amount">为您找到 <strong>${teamList?size}</strong> 条相关线路</span>	
				</#if>
				<a href="${base}/team/go.shtml">所有分类</a>
				
				<#if teamFlag==1>
		&nbsp;&gt;&nbsp;<a href="s.shtml?teamFlag=${teamFlag}&amp;showType=${showType}">短线旅游</a>
		<#elseif teamFlag==2>
		&nbsp;&gt;&nbsp;<a href="s.shtml?teamFlag=${teamFlag}&amp;showType=${showType}">长线旅游</a>
		<#elseif teamFlag==3>
		&nbsp;&gt;&nbsp;<a href="s.shtml?teamFlag=${teamFlag}&amp;showType=${showType}">出境旅游</a>
		<#elseif teamFlag==4>
		&nbsp;&gt;&nbsp;<a href="s.shtml?teamFlag=${teamFlag}&amp;showType=${showType}">自由行</a>
		</#if>
		<#if bannerRegions??>
			<#list bannerRegions as banner>
				<#if (banner.depth > 2)>
				<#if banner_has_next>
					&nbsp;&gt;&nbsp;<a href="s.shtml?regionId=${banner.regionId}&amp;showType=${showType}" >${banner.name}</a>
				<#else>
					&nbsp;&gt;&nbsp;${banner.name}
				</#if>
				</#if>
				</#list>
		</#if>
	</p>
	<#if teamList??>	
	
		<div class="nav-class">
		<h3 class="null">&nbsp;</h3>
			<ul class="cls">
				
				
				
				<li><a href="s.shtml?teamFlag=1&amp;showType=${showType}"><#if teamFlag==1&&regionId==""><font color=red>所有周边短线</font><#else>所有周边短线</#if></a></li>
			
				<li><a href="s.shtml?teamFlag=2&amp;showType=${showType}"><#if teamFlag==2&&regionId==""><font color=red>所有国内长线</font><#else>所有国内长线</#if></a></li>
				
				<li><a href="s.shtml?teamFlag=3&amp;showType=${showType}"><#if teamFlag==3&&regionId==""><font color=red>所有出境旅游</font><#else>所有出境旅游</#if></a></li>
				
				<li><a href="s.shtml?teamFlag=4&amp;showType=${showType}"><#if teamFlag==4&&regionId==""><font color=red>所有自由行</font><#else>所有自由行</#if></a></li>
				
			</ul>
		</div>
				
	
	
			
	<div class="nav-class">
			<h3>按<strong>天数</strong>选择：</h3>
			
  				<#if teamFlag=1>
    			<ul class="cls">
    				<li><a href="?teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='所有'>所有</a></li>
					<li><a href="?day=1&amp;dayFlag=0&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='一日游'><#if day==1><font style="color:red; font-weight:bold;">一日游</font><#else>一日游</#if></a></li>
					<li><a href="?day=2&amp;dayFlag=0&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='二日游'><#if day==2><font style="color:red; font-weight:bold;">二日游</font><#else>二日游</#if></a></li>
					<li><a href="?day=3&amp;dayFlag=1&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='三日游及以上'><#if day==3><font style="color:red; font-weight:bold;">三日游及以上</font><#else>三日游及以上</#if></a></li>
				</ul>
    			<#elseif teamFlag=-1>
<ul class="cls">
    				<li><a href="?teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='所有'>所有</a></li>
					<li><a href="?day=1&amp;dayFlag=0&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='一日游'><#if day==1><font style="color:red; font-weight:bold;">一日游</font><#else>一日游</#if></a></li>
					<li><a href="?day=2&amp;dayFlag=0&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='二日游'><#if day==2><font style="color:red; font-weight:bold;">二日游</font><#else>二日游</#if></a></li>
					<li><a href="?day=3&amp;dayFlag=0&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='三日游及以上'><#if day==3><font style="color:red; font-weight:bold;">三日游</font><#else>三日游</#if></a></li>
					<li><a href="?day=4&amp;dayFlag=0&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='四日游'><#if day==4><font style="color:red; font-weight:bold;">四日游</font><#else>四日游</#if></a></li>
					<li><a href="?day=5&amp;dayFlag=0&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='五日游'><#if day==5><font style="color:red; font-weight:bold;">五日游</font><#else>五日游</#if></a></li>
					<li><a href="?day=6&amp;dayFlag=1&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='六日游及以上'><#if day==6><font style="color:red; font-weight:bold;">六日游及以上</font><#else>六日游及以上</#if></a></li>
					
</ul>
			<#else>

    			<ul class="cls">
    				<li><a href="?teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='所有'>所有</a></li>
					<li><a href="?day=3&amp;dayFlag=0&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='三日游'><#if day==3><font style="color:red; font-weight:bold;">三日游</font><#else>三日游</#if></a></li>
					<li><a href="?day=4&amp;dayFlag=0&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='四日游'><#if day==4><font style="color:red; font-weight:bold;">四日游</font><#else>四日游</#if></a></li>
					<li><a href="?day=5&amp;dayFlag=0&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='五日游'><#if day==5><font style="color:red; font-weight:bold;">五日游</font><#else>五日游</#if></a></li>
					<li><a href="?day=6&amp;dayFlag=0&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='六日游'><#if day==6><font style="color:red; font-weight:bold;">六日游</font><#else>六日游</#if></a></li>
					<li><a href="?day=7&amp;dayFlag=0&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='七日游'><#if day==7><font style="color:red; font-weight:bold;">七日游</font><#else>七日游</#if></a></li>
					<li><a href="?day=8&amp;dayFlag=0&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='八日游'><#if day==8><font style="color:red; font-weight:bold;">八日游</font><#else>八日游</#if></a></li>
					<li><a href="?day=9&amp;dayFlag=1&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='九日游及以上'><#if day==9><font style="color:red; font-weight:bold;">九日游及以上</font><#else>九日游及以上</#if></a></li>
				</ul>
				</#if>
	
							
	</div>
	<!--
		<div class="nav-class">
			<h3>按<strong>价格</strong>选择：</h3>
			<#if teamFlag==1>
				<ul class="cls">								
					<li><a href="?priceEnd=200&amp;priceStart=0&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='0-200元'>0-200元</a></li>								
					<li><a href="?priceEnd=400&amp;priceStart=200&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='200-400元'>200-400元</a></li>								
					<li><a href="?priceEnd=600&amp;priceStart=400&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='400-600元'>400-600元</a></li>								
					<li><a href="?priceEnd=800&amp;priceStart=600&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='600-800元'>600-800元</a></li>
					<li><a href="?priceEnd=1000&amp;priceStart=800&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='800-1000元'>800-1000元</a></li>	
					<li><a href="?priceStart=1000&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='1000元以上 '>1000元以上 </a></li>	
				</ul>
			<#elseif (teamFlag==2||teamFlag==5)>
				<ul class="cls">								
					<li><a href="?priceEnd=1000&amp;priceStart=0&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}" title='0-1000元'>0-1000元</a></li>								
					<li><a href="?priceEnd=2000&amp;priceStart=1000&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='1000-2000元'>1000-2000元</a></li>								
					<li><a href="?priceEnd=3000&amp;priceStart=2000&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='2000-3000元'>2000-3000元</a></li>								
					<li><a href="?priceEnd=4000&amp;priceStart=3000&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='3000-4000元'>3000-4000元</a></li>
					<li><a href="?priceEnd=5000&amp;priceStart=4000&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='4000-5000元'>4000-5000元</a></li>	
					<li><a href="?priceStart=5000&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='5000元以上 '>5000元以上 </a></li>	
				</ul>
			<#else>
				<ul class="cls">								
					<li><a href="?priceEnd=3000&amp;priceStart=0&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='0-3000元'>0-3000元</a></li>								
					<li><a href="?priceEnd=5000&amp;priceStart=3000&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='3000-5000元'>3000-5000元</a></li>								
					<li><a href="?priceEnd=8000&amp;priceStart=5000&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='5000-8000元'>5000-8000元</a></li>								
					<li><a href="?priceEnd=10000&amp;priceStart=8000&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='8000-10000元'>8000-10000元</a></li>
					<li><a href="?priceStart=10000&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}" title='10000元以上 '>10000元以上 </a></li>	
				</ul>
			</#if>
		</div>-->
		</#if>	
					
			
			

		</div>
		<!-- }end:navigation -->
							
				
					
					<#if teamList??>
<!-- {start:view handler -->
					<div class="view-handler cls">

						<div class="s1">
							<label>展示：</label>
							<#if showType==1>
							<span class="sort-list"></span>
							<a href="s.shtml?keyWord=${keyWord}&amp;day=${day}&amp;dayFlag=${dayFlag}&amp;priceEnd=#{priceEnd}&amp;priceStart=#{priceStart}&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=0&amp;tejia=${tejia}&amp;tuijian=${tuijian}" class="sort-grid"></a>
							<#else>
							<a href="s.shtml?keyWord=${keyWord}&amp;day=${day}&amp;dayFlag=${dayFlag}&amp;priceEnd=#{priceEnd}&amp;priceStart=#{priceStart}&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=1&amp;tejia=${tejia}&amp;tuijian=${tuijian}" class="sort-list"></a>
							<span class="sort-grid"></span>
							</#if>
						</div>

						<div class="s2"><label for="mix_select">排序：</label>
						
						 <SELECT id=sort url_pre="s.shtml?keyWord=${keyWord}&amp;day=${day}&amp;dayFlag=${dayFlag}&amp;priceEnd=#{priceEnd}&amp;priceStart=#{priceStart}&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}&amp;tejia=${tejia}&amp;tuijian=${tuijian}"> 
  	<option value="" selected>默认排序</option>
  	<option value="doorDisPrice@@@ASC" <#if sort=="doorDisPrice@@@ASC">selected</#if>>按价格  从低到高</option>
 	<option value="doorDisPrice@@@DESC"  <#if sort=="doorDisPrice@@@DESC">selected</#if>>按价格  从高到低</option>
    <option value="teamday@@@DESC" <#if sort=="teamday@@@DESC">selected</#if>>按天数  从多到少</option>
    <option value="teamday@@@ASC" <#if sort=="teamday@@@ASC">selected</#if>>按天数  从少到多</option>
  </SELECT>	
						</div>

						<div class="s3">
						<#if tejia=1>
						<a href="s.shtml?teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}&amp;keyWord=${keyWord}" class="im-online"><font color="#ff0000"><#if teamFlag!=1>特价优惠<#else>买一送一</#if></font></a>
						<#else>
						<a href="s.shtml?teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}&amp;tejia=1&amp;keyWord=${keyWord}" class="seller-uncomb"><font color="#ff0000"><#if teamFlag!=1>特价优惠<#else>买一送一</#if></font></a>
						</#if>
						<#if tuijian=1>
						<a href="s.shtml?teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}&amp;keyWord=${keyWord}" class="im-online">热门推荐</a>
						<#else>
						<a href="s.shtml?teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}&amp;tuijian=1&amp;keyWord=${keyWord}" class="seller-uncomb">热门推荐</a>
						</#if>
						</div>
						<#if sort="doorDisPrice@@@ASC">
						<div class="s4"><a id="sort_by_price" class="price-order" href="s.shtml?keyWord=${keyWord}&amp;day=${day}&amp;dayFlag=${dayFlag}&amp;priceEnd=#{priceEnd}&amp;priceStart=#{priceStart}&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}&amp;sort=doorDisPrice@@@DESC">价格排序</a></div>
						<#else>
						<div class="s4"><a id="sort_by_price" class="price-order" href="s.shtml?keyWord=${keyWord}&amp;day=${day}&amp;dayFlag=${dayFlag}&amp;priceEnd=#{priceEnd}&amp;priceStart=#{priceStart}&amp;teamFlag=${teamFlag}&amp;regionId=${regionId}&amp;showType=${showType}&amp;sort=doorDisPrice@@@ASC">价格排序</a></div>
						</#if>
						<div class="s5"><label>天数</label></div>
						<div class="s6">
							<select id="location_auto_commit" url_pre="s?search_domain=1&amp;category=70ccc5fa3e420d39378b79fb&amp;im_status=1&amp;sort_mode=2&amp;display_mode=1&amp;show_tab=1">
							<option value="">出发地</option>
							<option value="苏州">苏州</option>
							</select>	
						</div>

					</div>
					<!-- }end:view handler -->

					
					
						<!-- {start:list -->
						<#if showType==1>
						<div class="list-view">
							
							<table id="data-table">
								
									<tbody>
									
									<#list teamList as team>
									<tr>
										<td class="c2"><div class="img"><a target="_blank" href="teamContent/${team.teamId}"><img onload="resizeImg(this);" src="${base}/${team.orgUrl!""}" height="75"><img src="${base}/public/20081205/image/blank.gif" class="blank"></a></div>
										</td>
										<td class="c3" style="color: rgb(102, 102, 102); ">
											<h3><a target="_blank" href="teamContent/${team.teamId}">${team.teamName}</a></h3>
											开班日期：<#if team.correct!=0><span style="color: rgb(102, 102, 102); CURSOR: hand"  class="seller" id="${team.teamId}">点击查看</span><#else><font color="orange">请来电咨询</font></#if>
								
										</td>
										<td class="c4">兔游价<br><strong>${team.doorDisPrice}.<span class="small">00</span></strong></td>
										<td class="c5">${team.teamday}&nbsp;天</td>
										<td class="c6">${team.area.name}</td>
									</tr>
									</#list>
								
									
								
								
							</tbody></table>
							<div class="list-page"></div>
						</div>
						<#else>
						<div class="grid-view">

							
							<ul class="cls" id="data-table">
								<#list teamList as team>
									<li>
										<div class="img"><img src="${base}/public/20081205/image/blank.gif" class="blank"><a target="_blank" href="${base}/team/teamContent/${team.teamId}"><img onload="resizeImg(this);" src="${base}/${team.orgUrl!""}" height="220"></a></div>
										<div class="title">
											<h3><a target="_blank" href="${base}/team/teamContent/${team.teamId}">${team.teamName}</a></h3>
											
										</div>
										<div class="bot">
											兔游价：<strong>${team.doorDisPrice}.<span class="small">00</span></strong>
											<#if team.correct!=0>
											<br/><span class='seller' style="CURSOR: hand" id="${team.teamId}">点击查看开班日期</span>
											<#else>
											<br/><font color="orange">开班日期请来电咨询</font>
											</#if>
										</div>
									</li>
								</#list>
							</ul>
							<div class="list-page"></div>
						</div>
						</#if>
						<!-- }end:list -->
					<#else>
						
						<div class="norst">

							<h2>很抱歉，没有找到合适的线路。</h2>

							<h3>您刚刚进行的搜索是：</h3>
							<p>关键词：<strong>${keyWord!""}</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分类：<strong><#if region??>${region.name}</#if></strong></p>

							<h3>建议您：</h3>
							<ol>
								<li>看看输入的文字是否有误</li>
								<li>去掉可能不必要的字词，如“的”、“什么”等</li>
								<li>查看<a href="${base}/team/go.shtml" target="_blank">所有线路类别</a></li>
								
							</ol>
							
						</div>
						
					</#if>
					

				
			</div>
		</div>

			
		</div>
	</div>

	<!-- {{start:footer search -->
	<div class="pheader pheader-bot">
		<span class="xl"></span><span class="xr"></span>
		<!-- search -->
		<@search />
		<!-- //search -->

	</div>
	<!-- }}end:footer search -->

	<!-- footer -->
	<@footer />
	<!-- //footer -->

</div>

<script type="text/javascript" src="${base}/public/20081205/script/core.2"></script>


<script type="text/javascript" src="${base}/public/20081205/script/panel.js"></script>
<script type="text/javascript">
function BBAjax(A){this.options={url:"",method:"post",async:true,user:"",pwd:"",contentType:"application/x-www-form-urlencoded",charset:"UTF-8",requestHeaders:{"x-baidu-ie":"utf-8","x-baidu-rf":"json"},data:"",useLock:0,sendImdtly:0,timeout:30000};Object.extendJson(this.options,A);this._initialize();}BBAjax.Versions=["MSXML2.XMLHttp.6.0","MSXML2.XMLHttp.3.0","MSXML2.XMLHttp.5.0","MSXML2.XMLHttp.4.0","Msxml2.XMLHTTP","MSXML.XMLHttp","Microsoft.XMLHTTP"];BBAjax.Delay=950;BBAjax.prototype._initialize=function(){this._locked=0;this._completed=0;this.isTimeout=0;this.aborted=0;this.requester=BBAjax.getRequestObj(this.options.useRequestPool);if(!this.requester){alert("�ǳ���Ǹ����ʼ������ʧ�ܣ�#"+BBAjax.Versions.length);return ;}var B=this.options;var A=this;if(B.async){this.requester.onreadystatechange=function(){var C=A.requester.readyState;if(C==4){A._onComplete();}};}this.dispose=function(){if(A){A.requester=null;A=null;}};if(B.sendImdtly){this.send();}BBEvent.observe(window,"unload",this.dispose);};BBAjax.getRequestObj=function(){if(window.ActiveXObject){while(BBAjax.Versions.length>0){try{return new ActiveXObject(BBAjax.Versions[0]);}catch(A){BBAjax.Versions.shift();continue;}}}else{if(window.XMLHttpRequest){return new XMLHttpRequest();}}return null;};BBAjax.prototype._setRequestHeader=function(){var D={};D["Content-type"]=this.options.contentType+(this.options.charset?"; charset="+this.options.charset:"");var C=this.options.requestHeaders;if(typeof (C)=="object"){Object.extendJson(D,C);}else{if(C&&typeof (C.push)=="function"){for(var B=0,A=C.length;B<A;B+=2){D[C[B]]=C[B+1];}}}for(var B in D){this.requester.setRequestHeader(B,D[B]);}};BBAjax.prototype._onComplete=function(){var A=this.requester;var B=this.options;this._completed=1;if(B.useLock){this._locked=0;}if(this._timer){clearTimeout(this._timer);}if(B.oncomplete){B.oncomplete.call(this,A,"Complete");}if(this.aborted){return ;}if(B.onsuccess&&this.isSuccess()){B.onsuccess.call(this,A,"Success");}else{if(B.onerror&&!this.isSuccess()){B.onerror.call(this,A,"Error");}}};BBAjax.prototype.send=function(A,D,B){try{var E=this.options;if(E.useLock&&this._locked){return ;}else{if(!E.useLock&&this.inProcess()){this.cancel();setTimeout(function(){_self.send(A,D,B);},0);return ;}}A=A||E.url;D=D||E.method;B=B||E.data;if(typeof (B)=="object"){B=this._getURLString(B);}if(B&&D.toLowerCase()=="get"){A=A.indexOf("?")!=-1?A+"&"+B:A+"?"+B;}if(E.user==""&&E.pwd==""){this.requester.open(D.toUpperCase(),A,E.async);}else{this.requester.open(D.toUpperCase(),A,E.async,E.user,E.pwd);}this._setRequestHeader();this.isTimeout=0;if(E.useLock){this._locked=1;}this._completed=0;this.aborted=0;if(E.timeout>0){this._checkTimeout(E.timeout);}if(E.onsend){E.onsend.call(this);}BBAjax.lastPost={data:B,url:A,method:D};if(typeof (B)!="undefined"&&D.toLowerCase()=="post"){if(B==""){this.options.requestHeaders["Content-Length"]=0;B=" ";}this.requester.send(B);}else{this.requester.send(null);}}catch(C){alert("�ǳ���Ǹ������ʧ��:"+C);}};BBAjax.prototype.get=function(A,B){this.send(A,"get",B);};BBAjax.prototype.post=function(A,B){this.send(A,"post",B);};BBAjax.prototype.sendForm=function(C,B,E,A){var F=Dom.formURIEncode(C,A);var D=this.options.data;if(typeof (D)=="object"){D=this._getURLString(D);}if(D!=""){D+="&";}this.send(B||C.action,E||C.method,D+F);};BBAjax.getResponseText=function(A,D,E,C){var B=new BBAjax({url:A,method:D,data:E,sendImdtly:1,async:(typeof C!="undefined")});return B.requester.responseText;};BBAjax.prototype.cancel=function(){if(!this.requester){return false;}if(this.inProcess()){this.aborted=1;this.requester.abort();if(!this._completed){this._onComplete.call(this,this.requester,"aborted");}if(this.options.onabort){this.options.onabort(this.requester);}return true;}return false;};BBAjax.prototype._checkTimeout=function(B){var A=this;this._timer=setTimeout(function(){if(A.requester&&!A._completed){A.isTimeout=1;A.requester.abort();if(!A._completed){A._onComplete.call(A,A.requester,"timeout");}A._completed=1;}},B);};BBAjax.prototype.isSuccess=function(){if(this.isTimeout){return false;}var A=this.requester.status;return !A||(A>=200&&A<300);};BBAjax.prototype.inProcess=function(){var A=this.requester.readyState;return A>0&&A<4;};BBAjax.prototype._getURLString=function(B){var D=[];for(var C in B){if(B[C]==null){continue;}if(B[C].constructor==Array){for(var A=0;A<B[C].length;A++){D.push(C+"="+encodeURIComponent(B[C][A]));}}else{D.push(C+"="+encodeURIComponent(B[C]));}}return D.join("&");};BBAjax.opResults=function(Results,url){if(!Results){return false;}if(Results.replace(/(\n|\r)+/g,"").trim().substr(0,1)!="{"){return Results;}else{try{eval("var status="+Results);}catch(e){alert("���س��?���ص�����Ϊ��\n"+Results);BB.Console.log("json���س��?");return{err:"inter"};}}status.isop=true;switch(status.err){case"ok":if(url!=false){if(url){setTimeout("window.location='"+url+"'",BBAjax.Delay);}else{if(status.url==null){setTimeout("window.location = window.location.href;window.location.reload(true);",BBAjax.Delay);}else{setTimeout("window.location='"+status.url+"'",BBAjax.Delay);}}}break;case"input":if(status.err_desc=="format"){for(var i in status.fields){var tempEl=document.getElementsByName(i);try{if(tempEl.length>0){var unfound=true;if(tempEl.length==1){BBValid.vldErrorFun(tempEl[0],"����������ݸ�ʽ���Ի򳬳��������Ƿ�������ַ�",true);unfound=false;}else{for(var x=0;x<tempEl.length;x++){if(status.fields[i]==tempEl[x].value.encode4Html()){BBValid.vldErrorFun(tempEl[x],"����������ݸ�ʽ���Ի򳬳��������Ƿ�������ַ�",true);unfound=false;break;}}}if(unfound){alert("����������ݸ�ʽ���Ի򳬳��������Ƿ�������ַ�");}}else{alert("����������ݸ�ʽ���Ի򳬳��������Ƿ�������ַ�");}}catch(e){alert("����������ݸ�ʽ���Ի򳬳��������Ƿ�������ַ�");}BB.Console.log("post input format",status.di);}}else{if(status.err_desc=="antispam"){alert("���ύ�����ݰ����дʻ㣬����������ύ��");BB.Console.log("post antispam",status.di);}else{status.isop=false;}}break;case"fake":window.location="/static/error/?di="+status.di;break;case"antispam":alert("���ύ�����ݰ����дʻ㣬����������ύ��");BB.Console.log("post antispam",status.di);break;case"maintain":alert("ϵͳ��æ�������Ժ����ԣ�");BB.Console.log("post maintain",status.di);break;case"inter":alert("ϵͳ��æ�������Ժ����ԣ�");BB.Console.log("post inter",status.di);break;case"unknow":alert("ϵͳ��æ�������Ժ����ԣ�");BB.Console.log("post unknow",status.di);break;case"expires":alert("����Ѿ����ڣ�");BB.Console.log("post expires",status.di);setTimeout("window.location = window.location.href;window.location.reload(true);",1);break;case"forbid":alert("�ܱ�Ǹ����û��Ȩ�޽��д˲�����");BB.Console.log("post forbid",status.di);break;case"nopower":window.location="/static/error/nopower.html";break;case"login":User.Login.show();User.Login.hint("����Ҫ��¼����ܼ���ղŵĲ���");BB.Console.log("post login",status.di);break;default:status.isop=false;}return status;};
</script>
<script type="text/javascript" src="${base}/public/20081205/script/cache.2"></script>

<script type="text/javascript" src="${base}/public/20081205/script/search_result.js"></script>
<script type="text/javascript" src="${base}/public/20081205/script/autocomplete.js"></script>



</body></html>