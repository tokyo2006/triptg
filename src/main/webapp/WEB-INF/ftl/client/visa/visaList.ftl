<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>各国签证列表——要旅游 先兔游</title>
<META content="${base}/${webSite.desc}" name=description>
<META content="${base}/${webSite.keywords}" name=keywords>

<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" 

media="screen"<link href="${base}/public/20081205/css/bbp-panel.css" rel="stylesheet" 

type="text/css" media="screen">
<link href="${base}/public/20081205/css/visaList.css" rel="stylesheet" type="text/css" 

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
				

				

				<!-- bn-list2 -->
				<div class="bn-list2">
<h2>查看国家签证</h2>
<!-- item -->
<#list areas as areaVisa>
<div class="item <#if (areaVisa_index%2==0)>zebra</#if>"><#if (areaVisa_index>0)><span 

class="backtop"><a href="#">回到顶部</a></span></#if>

<h3><a name="#{areaVisa_index+1}"></a>${areaVisa.name}</h3>
<ul class="cls">
<#list areaVisa.areaList as childVisa>
<#if childVisa.name!="中国">
<li><a href="visaDetail.shtml?areaId=${childVisa.areaId}" target="_blank">${childVisa.name}

</a></li>
</#if>
</#list>
</ul></div><!-- //item --><!-- item -->
</#list>



</div>
<!-- //item -->
				<!-- //bn-list2 -->


			</div>
			<div class="side">

			
				

				

				<!-- 兔游签证 -->
				<div class="fp-mod2">
	<div class="hd">
		<h3> 兔游签证</h3>
	</div>
	<div class="bd">
		<ul class="fp-ul1">
			
			
			<#if tenVisas??>
						<#list tenVisas as qzxx>
							<#if qzxx_index<10>
							<#if qzxx_index==0>
							<li class="first"><span 

class="num">${qzxx_index+1}.</span><a href="${base}/visa/visaDetail/

${qzxx.visaId}.html" title="${qzxx.name}" target="_blank">
							<#else>
							<li><span 

class="num">${qzxx_index+1}.</span><a href="${base}/visa/visaDetail/${qzxx.visaId}.html" 

title="${qzxx.name}" target="_blank">
							</#if>
							
							${qzxx.name}
							
						
							</a>
							</li>
							</#if>
						</#list>
						</#if>
			
		</ul>
	</div>
</div>
				<!-- // 兔游签证 -->




				

			</div>
		</div>




	</div>
	<!-- footer -->
	<@footer />
	<!-- //footer -->
	
</div>
</body></html>