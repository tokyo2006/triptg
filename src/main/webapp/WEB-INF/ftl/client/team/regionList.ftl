<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<META content="${base}/${webSite.desc}" name=description>
<META content="${base}/${webSite.keywords}" name=keywords>
<title>我要去旅游——要旅游，先兔游</title>
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" 

media="screen">
<link href="${base}/public/20081205/css/bbp-panel.css" rel="stylesheet" type="text/css" 

media="screen">
<link href="${base}/public/20081205/css/buynow.css" rel="stylesheet" type="text/css" 

media="screen">
<style>

.bn-column-t1 .main {width:750px;}
.bn-column-t1 .side {width:190px;}
/*newbee*/
#fp-new {margin-top:4px;*margin-top:1px;border:1px solid #d3d3d3;margin-bottom:1em;}
#fp-new .hd {background:url(${base}/public/20081205/image/bg_fp_sp1.gif) 0 -200px repeat-x 

#e5e5e5;border:1px solid #fff;padding:.2em .8em;*padding:.2em .8em .1em .8em;}
#fp-new .hd h3 {font-weight:bold;font-size:108%;color:#fe6603;}
#fp-new .hd .act {float:right;font-size:93%;}
#fp-new ul {margin:.5em 1em;}
#fp-new li {font-size:93%;line-height:1.5;}
#fp-new li a {color:#444;background:url(${base}/public/20081205/image/dot.gif) 0 center no-

repeat;padding-left:8px;}
.bn-list2 .item {position:relative;}
.backtop a {background:url(${base}/public/20081205/image/sprites_ico.gif) 0 -1638px;font-

size:93%;color:#a0a0a0;padding:3px 0 0 45px;;text-

decoration:none;zoom:1;position:absolute;right:.8em;top:.3em;}
.backtop a:hover {text-decoration:underline;}

</style>

</head><body>
<#include "*/banner.ftl">
<#include "*/header.ftl">
<#include "*/footer.ftl">
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

		<div class="bn-column-t1">
			<div class="main">
				
<!-- bn-list1 -->
				<!-- bn-list1 -->
				<div class="bn-list1">

					<h2>查看所有分类</h2>

					<ul class="ul1 cls">
					<#list regions as region>
					<#if (region_index&gt;3)><#break></#if>
					<li class="s#{region_index+1}">
					
					<#if region.flag==1>
						<a href="#1">周边短线</a>
					<#elseif region.flag==2>
						<a href="#2">国内长线</a>
					<#elseif region.flag==3>
						<a href="#3">出境旅游</a>
					<#elseif region.flag==4>
						<a href="#4">自由行</a>
					</#if>
					</li>
					
					</#list>
					</ul>
				

				</div>
				<!-- //bn-list1 -->
				<!-- //bn-list1 -->
				

				<!-- region-list2 -->
				<#list regions as region>
				<#if (region_index&gt;3)><#break></#if>
				<div class="bn-list2">
<h2><a name="#{region_index+1}"></a>
					<#if region.flag==1>
						<a href="http://www.tutu6.com/team/s.shtml?

teamFlag=1&showType=1" target=_blank style="color:#000000">周边短线</a>
					<#elseif region.flag==2>
						<a href="http://www.tutu6.com/team/s.shtml?

teamFlag=2&showType=1" target=_blank style="color:#000000">国内长线</a>
					<#elseif region.flag==3>
						<a href="http://www.tutu6.com/team/s.shtml?

teamFlag=3&showType=1" target=_blank style="color:#000000">出境旅游</a>
					<#elseif region.flag==4>
						<a href="http://www.tutu6.com/team/s.shtml?

teamFlag=4&showType=1" target=_blank style="color:#000000">自由行</a>
					</#if>
			</h2>
<!-- item -->
<#list region.childrenList as sonRengion>
<div class="item <#if (sonRengion_index%2==0)>zebra</#if>"><#if (sonRengion_index>0)><span 

class="backtop"><a href="#">回到顶部</a></span></#if>

<h3><A href="${base}/team/s.shtml?regionId=${sonRengion.regionId}&showType=1" 

target=_blank>${sonRengion.name}</a></h3>
<ul class="cls">
<#list sonRengion.childrenList as childRengion>
<#if childRengion.lineCnt!=0>
<li><a href="${base}/team/s.shtml?regionId=${childRengion.regionId}&showType=1" target="_blank">${childRengion.name}</a></li>
<#else>
<li><span>${childRengion.name}</span></li>
</#if>
</#list>
</ul></div><!-- //item --><!-- item -->
</#list>
<!-- //item -->
</div>
</#list>
				<!-- //region-list2 -->


			</div>
			<div class="side">

			
				

				

				<!-- 兔游帮助 -->
				<div class="fp-mod2">
	<div class="hd">
		<h3>兔游帮助</h3>
	</div>
	<div class="bd">
		<ul class="fp-ul1">
			
			
			<#list rightHelps as help>
			<#if help_index==0>
			<li class="first"><span class="num">#{help_index+1}</span><a 

href="${base}/homePage/helpContent/${help.newsId}.html" target="_blank">${help.title}

</a></li>
			<#else>
			<li><span class="num">#{help_index+1}.&nbsp;</span><a 

href="${base}/homePage/helpContent/${help.newsId}.html" target="_blank">${help.title}

</a></li>
			</#if>
			
			</#list>
			
		</ul>
	</div>
</div>
				<!-- //兔游帮助 -->


				


				

			</div>
		</div>




	</div>
	
	<!-- footer -->
	<@footer />
	<!-- //footer -->


</div>
</body></html>