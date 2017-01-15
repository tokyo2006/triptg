<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
<title>兔游网——要旅游 先兔游</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="verify-v1" content="5QXKet/F/qJ+oH49AY1ee56bDt4iH19AXI/CzWn1DHw=" />
<META content="${webSite.desc}" name=description>
<META content="${webSite.keywords}" name=keywords>
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/bbp-panel4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/portal-life.css" rel="stylesheet" type="text/css" media="screen">
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

<style type="text/css">
	div.unselected { display:none; }
	div.selected { display:block; }
	ul.unselected { display:none; }
	ul.selected { display:block; }
	body {font-size:13px;}
	#doc3 {width:950px;}
	.protal-column-t1 .main {width:720px;}
	.protal-column-t1 .side {width:220px;}
	.lf-mantle-l {width:190px;}
	.lf-mantle-r {width:520px;}
	#lf-fitment-r1 .r,#lf-toy-r1 .r,#lf-pet-r1 .r {width:430px;}
	#lf-mantle-fashion .ul2 {margin:1.4em .3em 0 .3em;}
	#lf-hotinfo {width:48%;}
	#lf-commelite {width:48%;}
	.lf-mantle-slider .thumb li {_margin-top:6px;}
</style>

</head>
<body class="portal-life">
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
			
				<div class="lf-mantle-l">
					
					<!-- 类目 -->
					<div id="lf-category">
					<span class="x1"><span class="x1a"></span></span>
					<span class="x2"><span class="x2a"></span></span>
					   <div class="mod-content">
						<div class="hd">
						  <h3>出境热词</h3>
						</div>
						<div class="bd">
							<div class="tree">
								<ul id="tree-nav">
								
									<#list regions as regionP>
										
											<#if regionP_index==0>
												<LI class=first>
												<#else>
												<LI>
											</#if>
											<H3 id=cat${regionP_index+1}><A href="${base}/team/s.shtml?regionId=${regionP.regionId}&showType=1" target=_blank>${regionP.name}</A></H3>
											<P>
											<#list regionP.childrenList as regionC>
												<#if regionC_has_next&&(regionC_index<2)>
												<A href="${base}/team/s.shtml?regionId=${regionC.regionId}&showType=1" target=_blank>${regionC.name}</A>&nbsp;&nbsp;|&nbsp;&nbsp;
												<#elseif ((!regionC_has_next)&&(regionC_index<=2))||((regionC_has_next)&&(regionC_index==2))>
												<A href="${base}/team/s.shtml?regionId=${regionC.regionId}&showType=1" target=_blank>${regionC.name}</A>
												</#if>
											</#list>
											</P>
										
									</#list>
								
								</ul>
							</div>
						</div>
						<div class="ft">
						  <p><a href="http://www.tutu6.com/team/go.shtml" target="_blank">查看更多分类&#187;</a></p>
						</div>
					   </div>
					</div>

					<!-- //类目 -->

				</div>
			
				<div class="lf-mantle-r">					
<!-- 幻灯 -->
<div id="lf-mantle-slider">
<!-- 幻灯组件 -->
<div class="lf-mantle-slider cls" id="slider1">
	<!-- 缩略图 -->
	<div class="thumb">
	<ul>
	<#if cj??>
	<#list cj as flash>
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
	<li>4</li>
	</ul>
	</div>
	<!-- //数字面板 -->
	<!-- 大图 -->
	<div class="enlarge">
	<#if cj??>
	<#list cj as flash>
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

		<!-- 出境游 -->
		<div id="lf-mantle-fashion">
			<div class="hd">
				<span class="act">
				<#list cjy_cjyt as cjyt>
				<a href="${cjyt.url}" title="${cjyt.title}" target="${cjyt.target}">${cjyt.title}</a>
				<#if cjyt_has_next>
				&nbsp;&nbsp;|&nbsp;&nbsp;
				</#if>
				</#list>
				</span> 
				<h3>出境游</h3>
			</div>
			<div class="bd">
				<h2>
				<#list cjy_cjys as cjys>
				<#if cjys_index==0>
				<a href="${cjys.url}" title="${cjys.title}" target="${cjys.target}">${cjys.title}</a>
				</#if>
				</#list>
				</h2>
				<div class="ul1 cls">
					<ul>
					<#list cjy_cjyz as cjyz>
					<#if cjyz_index<4>
					<li><strong class="highlight">[${cjyz.subTitle!''}]</strong>  <a href="${cjyz.url}" title="${cjyz.title}" target="${cjyz.target}">${cjyz.title}</a></li>
					</#if>
					</#list>
					</ul>
				</div>
				<div class="ul2 cls">
					<ul>
					<#list cjy_cjyx as cjyx>
					<#if cjyx_index<5>
					<li><a href="${cjyx.url}" title="${cjyx.title}" target="${cjyx.target}"><img src="${base}/${cjyx.picUrl}">
					<#if cjyx.title?length lte 4>
					${cjyx.title[0..]}</br>
					<#elseif cjyx.title?length lte 7>
					${cjyx.title[0..3]}</br>
					${cjyx.title[4..]}
					<#elseif cjyx.title?length lte 14>
					${cjyx.title[0..6]}</br>
					${cjyx.title[7..]}
					<#else>
					${cjyx.title[0..6]}</br>
					${cjyx.title[7..13]}..
					</#if>
					</a><br><strong class="highlight">${cjyx.price}</strong></li>
					</#if>
					</#list>
					</ul>
				</div>
			</div>					
		</div>
		<!-- //出境游 -->


</div>
			
</div>
			<div class="side">
			
				

				<style>#fp-new li {line-height:1.8;}</style>
<div id="fp-new" class="fp-mod5">
	<div class="hd">
		
		<h3>出境热点新闻</h3>
	</div>
	<div class="bd">
		
		<ul>
		<#if cjy_rdxw??>
		<#list cjy_rdxw as rdxw>
			<#if rdxw_index<7>
			<#if rdxw_index==0>
			<li class="first"><a href="${base}/news/content/${rdxw.newsId}.html" title="${rdxw.title}" target="_blank">
			<#else>
			<li><a href="${base}/news/content/${rdxw.newsId}.html" title="${rdxw.title}" target="_blank">
			</#if>
			<#if rdxw.subTitle?length lte 10>
			${rdxw.subTitle}
			<#else>
			${rdxw.subTitle[0..9]}
			</#if> 
			</a>
			</li>
			</#if>
		</#list>
		</#if>
		</ul>
	
	</div>
</div>
				
				<!-- 出境注意事项 -->
				<div class="lf-mod1" id="lf-notice">
<div class="hd">
<h3>出境注意事项</h3></div>
<div class="bd">
<ul>
		<#if cjy_zysx??>
		<#list cjy_zysx as zysx>
			<#if zysx_index<6>
			<#if zysx_index==0>
			<li class="first"><a href="${base}/news/content/${zysx.newsId}.html" title="${zysx.title}" target="_blank">
			<#else>
			<li><a href="${base}/news/content/${zysx.newsId}.html" title="${zysx.title}" target="_blank">
			</#if>
			<#if zysx.subTitle?length lte 10>
			${zysx.subTitle}
			<#else>
			${zysx.subTitle[0..9]}
			</#if> 
			</a>
			</li>
			</#if>
		</#list>
		</#if>

</ul>
</div>
</div>
				<!-- //出境注意事项 -->

				<!-- 热门景点推荐 -->
				<div id="lf-newstore" class="lf-mod1">
					<div class="hd">
						<h3>热门景点推荐</h3>
					</div>
					<div class="bd">
						<ul>
						<#list cjy_jdtj as jdtj>
						<#if jdtj_index<2>
						<li class="first"><a href="${jdtj.url}" title="${jdtj.title}" target="${jdtj.target}"><img src="${base}/${jdtj.picUrl}"></a><h3><a href="${jdtj.url}" target="${jdtj.target}">${jdtj.title}</a></h3><p>
							<#if jdtj.subTitle?length lte 7>
							${jdtj.subTitle}<br>
							<#elseif jdtj.subTitle?length lte 15>
							${jdtj.subTitle[0..6]}<br>
							${jdtj.subTitle[7..]}
							<#else>
							${jdtj.subTitle[0..6]}<br>
							${jdtj.subTitle[7..13]}..
							</#if>
						</p></li>
						</#if>
						</#list>
					</ul>
					</div>
				</div>
				<!-- //热门景点推荐 -->

			</div>
		</div>
		<!-- //第一屏 -->

<script type="text/javascript">
(function(){BBoard.render("popularU","popularUPrev","popularUNext");var A=G("slider1");new PicSlide({container:A,pics:Dom.getElementsByClassName("enlarge",A)[0].getElementsByTagName("img"),pages:Dom.getElementsByClassName("thumb",A)[0].getElementsByTagName("li"),autoPlay:true,interval:5000,delay:50,effect:"fade",eventType:"mouseover"}).run();})();
</script>

		<!-- 居家时尚 -->
		<div class="protal-column-t1">
			<div class="main">
			
				<div id="lf-fitment">
	<div class="hd cls">
<h3>居家时尚</h3>
<span class="act">
<#list cjy_djt as djt>
<a href="${djt.url}" title="${djt.title}" target="${djt.target}">${djt.title}</a>
<#if djt_has_next>
&nbsp;&nbsp;|&nbsp;&nbsp;
</#if>
</#list>
</span> </div>
<div class="bd">
<div class="cls" id="lf-fitment-r2">
<ul>
<li class="nobd">
<div class="img">
<#list cjy_dj1z as dj1z>
<#if dj1z_index==0>
<a href="${dj1z.url}" title="${dj1z.title}" target="${dj1z.target}"><img src="${base}/${dj1z.picUrl}">${dj1z.title}</a>
</#if>
</#list>
</div>
<#list cjy_dj1ys as dj1ys>
<#if dj1ys_index==0>
<h3><a href="${dj1ys.url}" title="${dj1ys.title}" target="${dj1ys.target}">${dj1ys.title}</a></h3>
</#if>
</#list>
<ul>
<#list cjy_dj1yx as dj1yx>
<#if dj1yx_index<2>
<li><a href="${dj1yx.url}" title="${dj1yx.title}" target="${dj1yx.target}">[${dj1yx.title}]</a></li>
<#else>
<li><span class="highlight">[${dj1yx.subTitle!''}]</span>&nbsp;&nbsp;<a href="${dj1yx.url}" title="${dj1yx.title}" target="${dj1yx.target}">${dj1yx.title}</a></li>
</#if>
</#list>
</ul>
</li>
<li class="nobd">
<div class="img">
<#list cjy_dj2z as dj2z>
<#if dj2z_index==0>
<a href="${dj2z.url}" title="${dj2z.title}" target="${dj2z.target}"><img src="${base}/${dj2z.picUrl}">${dj2z.title}</a>
</#if>
</#list>
</div>
<#list cjy_dj2ys as dj2ys>
<#if dj2ys_index==0>
<h3><a href="${dj2ys.url}" title="${dj2ys.title}" target="${dj2ys.target}">${dj2ys.title}</a></h3>
</#if>
</#list>
<ul>
<#list cjy_dj2yx as dj2yx>
<#if dj2yx_index<2>
<li><a href="${dj2yx.url}" title="${dj2yx.title}" target="${dj2yx.target}">[${dj2yx.title}]</a></li>
<#else>
<li><span class="highlight">[${dj2yx.subTitle!''}]</span>&nbsp;&nbsp;<a href="${dj2yx.url}" title="${dj2yx.title}" target="${dj2yx.target}">${dj2yx.title}</a></li>
</#if>
</#list>
</ul>
</li>
<li class="nobd">
<div class="img">
<#list cjy_dj3z as dj3z>
<#if dj3z_index==0>
<a href="${dj3z.url}" title="${dj3z.title}" target="${dj3z.target}"><img src="${base}/${dj3z.picUrl}">${dj3z.title}</a>
</#if>
</#list>
</div>
<#list cjy_dj3ys as dj3ys>
<#if dj3ys_index==0>
<h3><a href="${dj3ys.url}" title="${dj3ys.title}" target="${dj3ys.target}">${dj3ys.title}</a></h3>
</#if>
</#list>
<ul>
<#list cjy_dj3yx as dj3yx>
<#if dj3yx_index<2>
<li><a href="${dj3yx.url}" title="${dj3yx.title}" target="${dj3yx.target}">[${dj3yx.title}]</a></li>
<#else>
<li><span class="highlight">[${dj3yx.subTitle!''}]</span>&nbsp;&nbsp;<a href="${dj3yx.url}" title="${dj3yx.title}" target="${dj3yx.target}">${dj3yx.title}</a></li>
</#if>
</#list>
</ul>
</li>
<li class="nobd">
<div class="img">
<#list cjy_dj4z as dj4z>
<#if dj4z_index==0>
<a href="${dj4z.url}" title="${dj4z.title}" target="${dj4z.target}"><img src="${base}/${dj4z.picUrl}">${dj4z.title}</a>
</#if>
</#list>
</div>
<#list cjy_dj4ys as dj4ys>
<#if dj4ys_index==0>
<h3><a href="${dj4ys.url}" title="${dj4ys.title}" target="${dj4ys.target}">${dj4ys.title}</a></h3>
</#if>
</#list>
<ul>
<#list cjy_dj4yx as dj4yx>
<#if dj4yx_index<2>
<li><a href="${dj4yx.url}" title="${dj4yx.title}" target="${dj4yx.target}">[${dj4yx.title}]</a></li>
<#else>
<li><span class="highlight">[${dj4yx.subTitle!''}]</span>&nbsp;&nbsp;<a href="${dj4yx.url}" title="${dj4yx.title}" target="${dj4yx.target}">${dj4yx.title}</a></li>
</#if>
</#list>
</ul>
</li>
</ul></div></div>
</div>
			
			</div>
			<div class="side">
			
	<!-- 尚品折扣 -->
	<div id="lf-cmd1" class="lf-mod2">
					<div class="hd">
						<h3>精彩推荐</h3>
					</div>
					<div class="bd">
						<ul class="ul-t1 cls">
<#list cjy_jctj as jctj>
<#if jctj_index==0>
<li class="first"><a href="${jctj.url}" title="${jctj.title}" target="${jctj.target}"><img src="${base}/${jctj.picUrl}"><span class="highlight">[${jctj.subTitle!''}]</span>&nbsp;&nbsp;${jctj.title}</a></li>
<#elseif jctj_index<4>
<li><a href="${jctj.url}" title="${jctj.title}" target="${jctj.target}"><img src="${base}/${jctj.picUrl}"><span class="highlight">[${jctj.subTitle!''}]</span>&nbsp;&nbsp;${jctj.title}</a> </li>
</#if>
</#list>
</ul>					</div>
				</div>
	<!-- //尚品折扣 -->


	<!-- 热门签证 -->
	<div id="lf-cmd2" class="lf-mod2">
					<div class="hd">
						<h3>热门签证</h3>
					</div>
					<div class="bd">
						<ul class="ul-t2">
<#list cjy_rmqz as rmqz>
<#if rmqz_index==0>
<li class="first"><span class="highlight">[${rmqz.subTitle!''}]</span>&nbsp;&nbsp;<a href="${rmqz.url}" title="${rmqz.title}" target="${rmqz.target}">${rmqz.title}</a></li>
<#else>
<li><span class="highlight">[${rmqz.subTitle!''}]</span>&nbsp;&nbsp;<a href="${rmqz.url}" title="${rmqz.title}" target="${rmqz.target}">${rmqz.title}</a></li>
</#if>
</#list>
</ul>					</div>
				</div>
	<!-- //热门签证 -->

			</div>
		</div>
		<!-- //居家时尚 -->

		<!-- 通栏 -->
		<div class="protal-column-t0">
			<div class="main">
				<div id="lf-notestick">
					<ul class="cls">
<#list cjy_zj as zj>
<#if zj_index<6>
<li class="s${zj_index+1}"><a href="${zj.url}" title="${zj.title}" target="${zj.target}"><img src="${base}/${zj.picUrl}">
	<#if zj.title?length lte 9>
	${zj.title[0..]}
	<#else>
	${zj.title[0..8]}
	</#if>
</a><br><strong class="highlight">${zj.price}</strong></li>
</#if>
</#list>					
</ul>
				</div>
			</div>
		</div>
		<!-- //通栏 -->

		<!-- 家有爱宠 -->
		<div class="protal-column-t1">
			<div class="main">
			
			
					<!-- 家有爱宠left -->
					<div id="lf-pet">
						<div class="hd" style="background-position: 0px -200px;">
						<span class="act">
						<#list cjy_hwt as hwt>
						<a href="${hwt.url}" title="${hwt.title}" target="${hwt.target}">${hwt.title}</a>
						<#if hwt_has_next>
						&nbsp;&nbsp;|&nbsp;&nbsp;
						</#if>
						</#list>
						</span> 
<h3>家有爱宠</h3></div>
<div class="bd cls">
<div class="cls" id="lf-pet-r1">
<div class="l">
<div class="img">
<#list cjy_hwz as hwz>
<#if hwz_index==0>
<a href="${hwz.url}" title="${hwz.title}" target="${hwz.target}"><img src="${base}/${hwz.picUrl}">${hwz.title}</a>
</#if>
</#list>
</div></div>
<div class="r">
<#list cjy_hwy1 as hwy1>
<#if hwy1_index==0>
<h2><a href="${hwy1.url}" title="${hwy1.title}" target="${hwy1.target}">${hwy1.title}</a></h2>
</#if>
</#list>
<p align="center">
<#list cjy_hwy2 as hwy2>
[<a href="${hwy2.url}" title="${hwy2.title}" target="${hwy2.target}"><span class="h">${hwy2.title}</span></a>]
<#if (hwy2_has_next)&&((hwy2_index%2)==0)>
&nbsp;&nbsp;
<#elseif hwy2_has_next>
<br>
</#if>
</#list>
</p>
<ul class="ul1 cls">
<#list cjy_hwy3 as hwy3>
<#if hwy3_index==0>
<li class="first"><span class="highlight">[${hwy3.subTitle!''}]</span>&nbsp;&nbsp;<a href="${hwy3.url}" title="${hwy3.title}" target="${hwy3.target}">${hwy3.title}</a></li>
<#elseif hwy3_index<8>
<li><span class="highlight">[${hwy3.subTitle!''}]</span>&nbsp;&nbsp;<a href="${hwy3.url}" title="${hwy3.title}" target="${hwy3.target}">${hwy3.title}</a></li>
</#if>
</#list>
</ul>
<ul class="ul2 cls">
<#list cjy_hwy4 as hwy4>
<#if hwy4_index==0>
<li class="first"><a href="${hwy4.url}" title="${hwy4.title}" target="${hwy4.target}"><img src="${base}/${hwy4.picUrl}">${hwy4.title}</a></li>
<#elseif hwy4_index<5>
<li><a href="${hwy4.url}" title="${hwy4.title}" target="${hwy4.target}"><img src="${base}/${hwy4.picUrl}">${hwy4.title}</a></li>
</#if>
</#list>
</ul></div></div></div>
					</div>
					<!-- //家有爱宠left -->
			
			</div>
			<div class="side">
			
			
				<!-- 小宠当家 -->
				<div id="lf-cmd6" class="lf-mod2">
					<div class="hd">
						<h3>港澳台湾</h3>
					</div>
					<div class="bd">
						<div class="ad">
<#list cjy_gats as gats>
<#if gats_index==0>
<a href="${gats.url}" title="${gats.title}" target="${gats.target}"><img alt="" src="${base}/${gats.picUrl}"></a>
</#if>
</#list>
						
						</div>
<ul class="ul-t3">
<#list cjy_gatx as gatx>
<#if gatx_index==0>
<li class="first"><span class="highlight">[${gatx.subTitle!''}]</span>&nbsp;&nbsp;<a href="${gatx.url}" title="${gatx.title}" target="${gatx.target}">${gatx.title}</a></li> 
<#else>
<li ><span class="highlight">[${gatx.subTitle!''}]</span>&nbsp;&nbsp;<a href="${gatx.url}" title="${gatx.title}" target="${gatx.target}">${gatx.title}</a></li> 
</#if>
</#list>
</ul>					</div>
				</div>
				<!-- //小宠当家 -->
			
				<!-- 宠物情缘 -->
				<div id="lf-cmd2" class="lf-mod2">
					<div class="hd">
						<h3>东亚背包</h3>
					</div>
					<div class="bd">
						<ul class="ul-t2">
<#list cjy_dybb as dybb>
<#if dybb_index==0>
<li class="first"><span class="highlight">[${dybb.subTitle!''}]</span>&nbsp;&nbsp;<a href="${dybb.url}" title="${dybb.title}" target="${dybb.target}">${dybb.title}</a></li> 
<#else>
<li ><span class="highlight">[${dybb.subTitle!''}]</span>&nbsp;&nbsp;<a href="${dybb.url}" title="${dybb.title}" target="${dybb.target}">${dybb.title}</a></li> 
</#if>
</#list>
</ul>
					</div>
				</div>
				<!-- //宠物情缘 -->			
			
			</div>
		</div>
		<!-- //家有爱宠 -->

		<!-- 亲亲宝贝 -->
		<div class="protal-column-t1">
			<div class="main">
		
				
					
					<div id="lf-baby-r2">
<ul class="cls">
<#list cjy_hwx as hwx>
<#if hwx_index<10>
<li><a href="${hwx.url}" title="${hwx.title}" target="${hwx.target}"><img src="${base}/${hwx.picUrl}">${hwx.title}</a></li>
</#if>
</#list>
</ul></div>

			
			</div>
			<div class="side">								
				<!-- 温馨小店 -->
				<div id="lf-cmd6" class="lf-mod3">
					<div class="hd">
						<h3>畅游欧洲</h3>
					</div>
					<div class="bd">
							<div class="ad">
<#list cjy_cyozs as cyozs>
<#if cyozs_index==0>
<a href="${cyozs.url}" title="${cyozs.title}" target="${cyozs.target}"><img alt="" src="${base}/${cyozs.picUrl}"></a>
</#if>
</#list>
							</div>
<ul class="ul-t3">
<#list cjy_cyozz as cyozz>
<#if cyozz_index==0>
<li class="first"><span class="highlight">[${cyozz.subTitle!''}]</span>&nbsp;&nbsp;<a href="${cyozz.url}" title="${cyozz.title}" target="${cyozz.target}">${cyozz.title}</a></li> 
<#else>
<li ><span class="highlight">[${cyozz.subTitle!''}]</span>&nbsp;&nbsp;<a href="${cyozz.url}" title="${cyozz.title}" target="${cyozz.target}">${cyozz.title}</a></li> 
</#if>
</#list>
</ul>
<ul class="ul-t1 cls">
<#list cjy_cyozx as cyozx>
<#if cyozx_index==0>
<li class="first"><a href="${cyozx.url}" title="${cyozx.title}" target="${cyozx.target}"><img src="${base}/${cyozx.picUrl}"><span class="highlight">[${cyozx.subTitle!''}]</span>&nbsp;&nbsp;${cyozx.title}</a></li>
<#elseif cyozx_index==1>
<li><a href="${cyozx.url}" title="${cyozx.title}" target="${cyozx.target}"><img src="${base}/${cyozx.picUrl}"><span class="highlight">[${cyozx.subTitle!''}]</span>&nbsp;&nbsp;${cyozx.title}</a> </li>
</#if>
</#list>
</ul>				</div>
				</div>
				<!-- //温馨小店 -->			
			
			</div>
		</div>
		<!-- //亲亲宝贝 -->						


	</div>
	<!-- footer -->
	<@footer />
	<!-- //footer -->
</div>

<!-- 出境分类浮层 -->
<div class="panel panel-t0" id="dlg_nav" style="display: none; width: 239px; position: absolute;">
<div class="panel-content">
<div class="bd"><!-- content -->
	<style>
			.panel-temp-0809279801 {position:relative;zoom:1;width:239px;height:163px;}
			.panel-temp-0809279801 .bg {width:239px;height:163px;position:absolute;background:url("http://www.tutu6.com/public/20081205/image/panel_lf_t.png") no-repeat;_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="http://www.tutu6.com/public/20081205/image/panel_lf_t.png");_background:none;}
			.panel-temp-0809279801 .inner {position:relative;padding:15px 15px 15px 28px;z-index:10;zoom:1;}
			.panel-temp-0809279801 .inner li {width:49%;float:left;font-size:12px;line-height:1.8;}
			.panel-temp-0809279801 .inner li a {color:#ff6600;}
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
</div></div><!-- //content --></div></div><span class="co1"><span></span></span><span class="co2"><span></span></span><span class="cue"></span><span class="sd"></span><span class="resize"></span></div><!-- //出境分类浮层 -->
<script type="text/javascript">
document.DOMLoaded(function(){var tree=G("tree-nav");if(!tree){return ;}var h3s=tree.getElementsByTagName("h3");if(h3s.length<1){return ;}var popup=new LayerPopup(G("dlg_nav"),{showTimeout:250,hideTimeout:250,autoPosition:false});Dom.getArray(h3s).each(function(el,index){el=el.getElementsByTagName("a")[0];BBEvent.observe(el,"mouseover",function(e){with({i:index}){var uls=popup.getPopup().getElementsByTagName("ul");Dom.getArray(uls).each(function(el,index){el.className="unselected";if(i==index){el.className="selected";}});}popup.hide();popup.delayShow(el.offsetWidth-3,-18,null,null,el);});BBEvent.observe(el,"mouseout",function(e){popup.delayHide();});});});
</script>

</body></html>