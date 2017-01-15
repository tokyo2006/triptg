<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
<title>国内旅游——要旅游 先兔游</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="verify-v1" content="5QXKet/F/qJ+oH49AY1ee56bDt4iH19AXI/CzWn1DHw=" />
<META content="${webSite.desc}" name=description>
<META content="${webSite.keywords}" name=keywords>
<link rel="shortcut icon" href="../favicon.ico" />
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/bbp-panel4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/portal-digital.css" rel="stylesheet" type="text/css" media="screen">
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
	#tabview_con1 li img {width:100px;height:100px;}
	body {font-size:13px;}
	#doc3 {width:950px;}
	.protal-column-t1 .main {width:720px;}
	.protal-column-t1 .side {width:220px;}
	.lf-mantle-l {width:190px;}
	.lf-mantle-r {width:520px;}
	.dg-mantle-slider .thumb li {_margin-top:6px;}
	#hi .info .rt .old-show div h3 {font-size:14px;}
</style>
<script type="text/javascript" src="${base}/public/20081205/script/core-mini.js"></script>
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
			
				<div class="lf-mantle-l">
					
					<!-- 类目 -->
					<div id="lf-category"><span class="x1"><span class="x1a"></span></span><span class="x2"><span class="x2a"></span></span>
					<div class="mod-content">
					<div class="hd">
					<!-- 
					<span class="act">
					<a class="previous" id="popularUPrev" href="javascript:void(0)">&lt;Previous</a>
					<a class="next" id="popularUNext" href="javascript:void(0)">Next&gt;</a>
					</span> -->
					 
					<h3>国内类别</h3></div>
					<div class="bd">
					
					
					<div class="tree">
					<ul id="tree-nav">
						<#list regions as regionP>
							<#if regionP.name!="其他">
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
							</#if>
						</#list>
					</ul></div></div>
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
<div class="dg-mantle-slider cls" id="slider1">
<!-- 缩略图 -->
<div class="thumb">
<ul>
<#if gn??>
<#list gn as flash>
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
<#if gn??>
<#list gn as flash>
<#if flash_index<4>
	<#if flash_index==0>
	<a href="${flash.url}" title="${flash.title}" target="${flash.target}"><img style="opacity: 1;" src="${base}/${flash.picUrl}"></a>
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

<!-- 兔游特惠 -->
<div id="lf-mantle-dg">
<div class="hd">
<span class="act">
<#if gny_tht??>
<#list gny_tht as tht>
<#if tht_index<6>
<a href="${tht.url}" target="${tht.target}">${tht.title}</a>&nbsp;&nbsp;&nbsp;
</#if>
</#list>
</#if>
<a href="http://www.tutu6.com/team/s.shtml?teamFlag=2&showType=1" target="_blank">更多&#187;</a>
</span> 
<h3>兔游特惠</h3></div>
<div class="bd">
<div class="ul2 cls">
<ul class="first">
<#list gny_zxtu as zxtu>
<#if zxtu_index<6>
<li><a href="${zxtu.url}" title="${zxtu.title}" target="${zxtu.target}"><img src="${base}/${zxtu.picUrl}">
	<#if zxtu.title?length lte 12>
	${zxtu.title}
	<#else>
	${zxtu.title[0..11]}
	</#if>
</a><br><em>兔游价：${zxtu.price}</em> 
</li>
</#if>
</#list>
</ul>
</div>
</div>
</div>
<!-- //兔游特惠 -->


				</div>
			
			</div>
			<div class="side">
			
				<!-- 国内旅游新闻 -->
			<style>#fp-new li {line-height:1.8;}</style>
<div id="fp-new" class="fp-mod5">
	<div class="hd">
		<span class="act"><a href="${base}/news/contentList/f2d041b81fe688c0011fefb460f30195.html" target="_blank">更多</a></span>
		<h3>国内旅游新闻</h3>
	</div>
	<div class="bd">
		
		<ul>
			<#if gny_gnxw??>
			<#list gny_gnxw as gnxw>
				<#if gnxw_index<6>
				<#if gnxw_index==0>
				<li class="first"><a href="${base}/news/content/${gnxw.newsId}.html" title="${gnxw.title}" target="_blank">
				<#else>
				<li><a href="${base}/news/content/${gnxw.newsId}.html" title="${gnxw.title}" target="_blank">
				</#if>
				<#if gnxw.subTitle?length lte 10>
				${gnxw.subTitle}
				<#else>
				${gnxw.subTitle[0..9]}
				</#if> 
				</a>
				</li>
				</#if> 
			</#list>
			</#if>
		</ul>
	
	</div>
</div>
				<!-- //国内旅游新闻 -->

				<!-- 咨询排行榜 -->
				<div class="lf-mod1" id="lf-notice">
<div class="hd">
<h3>咨询排行榜</h3></div>
<div class="bd">
<ul>
	<#if gny_zxph??>
	<#list gny_zxph as zxph>
		<#if zxph_index<6>
		<#if zxph_index==0>
		<li class="first"><a href="${base}/news/content/${zxph.newsId}.html" title="${zxph.title}" target="_blank">
		<#else>
		<li><a href="${base}/news/content/${zxph.newsId}.html" title="${zxph.title}" target="_blank">
		</#if>
		<#if zxph.subTitle?length lte 10>
		${zxph.subTitle}
		<#else>
		${zxph.subTitle[0..9]}
		</#if> 
		</a>
		</li>
		</#if>
	</#list>
	</#if>
</ul>
</div>
</div>
				<!-- //咨询排行榜 -->

				<!-- 人气攻略 -->
				<div class="lf-mod1" id="sj">
<div class="hd">
<h3>人气攻略</h3><b></b></div>
<div class="bd">
<ul>
<#list gny_rq as rq>
<#if rq_index==0 >
<li class="first"><a href="${rq.url}" title="${rq.title}" target="${rq.target}"><img src="${base}/${rq.picUrl}"><br>${rq.subTitle!''}</a></li>
<#elseif rq_index<4>
<li><a href="${rq.url}" title="${rq.title}" target="${rq.target}"><img src="${base}/${rq.picUrl}"><br>${rq.subTitle!''}</a></li>
</#if>
</#list>
</ul></div></div>
				<!-- //人气攻略 -->
			</div>
		</div>
		<!-- //第一屏 -->
<script type="text/javascript">
(function(){BBoard.render("popularU","popularUPrev","popularUNext",5000);new PicSlide({container:G("slider1"),pics:Dom.getElementsByClassName("enlarge",G("slider1"))[0].getElementsByTagName("img"),pages:Dom.getElementsByClassName("thumb",G("slider1"))[0].getElementsByTagName("li"),autoPlay:true,interval:5000,delay:50,eventType:"mouseover",effect:"fade"}).run();})();
</script>
		<!-- 超低价 -->
		<div class="protal-column-t0">
<div class="main" id="low-price">
<div class="hd">
	<div class="search"><em>热门搜索</em>:
	<#list gny_djt as djt>
	<#if djt_has_next >
	<a href="${djt.url}" title="${djt.title}" target="${djt.url}">${djt.title}</a>、
	<#else>
	<a href="${djt.url?default("")}" title="${djt.title}" target="${djt.url}">${djt.title}</a>
	</#if>
	</#list>
	</div>
	<span class="more"><a href="${base}/team/s.shtml" target="_blank">更多&#187;</a></span>
</div>
<div class="product" id="tabview_con1">
<ul class="cls">
<#if gny_tejia??>
	<#list gny_tejia as proList>
	<#if proList_index<36>
	<li><div class="img"><a href="${base}/team/teamContent/${proList.teamId}" target="_blank"><img src="${base}/${proList.breUrl!""}" onload="resizeImg(this);" height=100px></a><img src="${base}/public/20081205/image/blank.gif" class="blank"></div>

<a href="${base}/team/teamContent/${proList.teamId}" title="${proList.teamName}" target="_blank">
	
		<#if (proList.teamName?length) lte 8>
		${proList.teamName}<br>
		<#elseif (proList.teamName?length) lte 15>
		${proList.teamName}
		<#else>
		${proList.teamName[0..13]}...
		</#if>
	</a>
	<!--<br><del></del>-->
	<br><em>兔游价：#{proList.doorDisPrice}元</em></li>
	</#if>
	</#list>
</#if>

</ul>
</div></div>
</div>
		<!-- //超低价 -->
		
		<!-- 通栏 -->
		<!-- 

<DIV class=protal-column-t0>
<DIV class=main>
</DIV></DIV>

-->
		<!-- //通栏 -->
						
		<!-- 通栏 -->
		<!--

<DIV class=protal-column-t0>
<DIV class=main>

</DIV></DIV>

-->
		<!-- //通栏 -->
		<!-- 亲家情-->
<div class="protal-column-t0 mod-type">
<div class="main tit" id="mod-mobile">
<h3>亲家情</h3>
<div class="more"><a href="${base}/team/s.shtml" target="_blank">更多&#187;</a></div>
<div class="search"><em>热门搜索</em>: 
<#list gny_qjqt as qjqt>
	<#if qjqt_has_next >
	<a href="${qjqt.url}" title="${qjqt.title}" target="${qjqt.target}">${qjqt.title}</a>、
	<#else>
	<a href="${qjqt.url}" title="${qjqt.title}" target="${qjqt.target}">${qjqt.title}</a>
	</#if>
	</#list>
</div>
</div>
<div class="content">
<div class="img">
<#list gny_qjqz as qjqz>
<#if qjqz_index==0>
<a href="${qjqz.url}" title="${qjqz.title}" target="${qjqz.target}"><img src="${base}/${qjqz.picUrl}"></a>
</#if>
</#list>
</div>
<!-- 亲家情中1-->
<div class="zhuanti">
<#list gny_qjqzs as qjqzs>
			<#if qjqzs_index<2 >
			<p><a href="${qjqzs.url}" title="${qjqzs.title}" target="${qjqzs.target}"><img src="${base}/${qjqzs.picUrl}"></a><br>${qjqzs.title}</p>
			</#if>
			</#list>

<ul>
<#list gny_qjqzx as qjqzx>
			<#if qjqzx_index<2 >
			<li>[${qjqzx.subTitle}] <a href="${qjqzx.url}" title="${qjqzx.title}" target="${qjqzx.target}">${qjqzx.title}</a></li>
			</#if>
			</#list>
</ul>
<div class="fl">&nbsp;</div>
</div>
<!-- //亲家情中1-->
<!-- 亲家情中2-->
<div class="school">
<#list gny_qjqz2 as qjqz2>
<#if qjqz2_index==0>
<a href="${qjqz2.url}" title="${qjqz2.title}" target="${qjqz2.target}"><img src="${base}/${qjqz2.picUrl}"></a> 
</#if>
</#list>
<ul>
<#list gny_qjqy as qjqy>
			<li>[${qjqy.subTitle}] <a href="${qjqy.url}" title="${qjqy.title}" target="${qjqy.target}">${qjqy.title}</a></li>
			</#list>
</ul>
<div class="fl">&nbsp;</div>
</div>
<!-- //亲家情中2-->
<!-- 亲家情右边轮显示-->
<div class="rank" id="tabview3">
<ul class="menu" id="tabview_nav3">
<li class="current"><a href="javascript:void(0)">亲子家庭游</a></li>
<li><a href="javascript:void(0)">情侣蜜月行</a> </li>
</ul>
<ul class="list">
	<#if gny_qzjt??>
	<#list gny_qzjt as qzjt>
		<#if qzjt_index<6>
		<#if (qzjt_index%2)==0>
		<li><a href="${base}/news/content/${qzjt.newsId}.html" title="${qzjt.title}" target="_blank">
		<#else>
		<li class="ev"><a href="${base}/news/content/${qzjt.newsId}.html" title="${qzjt.title}" target="_blank">
		</#if>
		<#if qzjt.subTitle?length lte 10>
		${qzjt.subTitle}
		<#else>
		${qzjt.subTitle[0..9]}
		</#if> 
		</a>
		</li>
		</#if>
	</#list>
	</#if>
</ul>
<ul class="list unselected">
<#if gny_qlmy??>
	<#list gny_qlmy as qlmy>
		<#if qlmy_index<6>
		<#if (qlmy_index%2)==0>
		<li><a href="${base}/news/content/${qlmy.newsId}.html" title="${qlmy.title}" target="_blank">
		<#else>
		<li class="ev"><a href="${base}/news/content/${qlmy.newsId}.html" title="${qlmy.title}" target="_blank">
		</#if>
		<#if qlmy.subTitle?length lte 10>
		${qlmy.subTitle}
		<#else>
		${qlmy.subTitle[0..9]}
		</#if> 
		</a>
		</li>
		</#if> 
	</#list>
	</#if>

</ul></div>
<script defer="defer" type="text/javascript">								
					(function(){
						var tabView = new TabView({
								'TAB_DEACTIVE_CN'     : '',
								'TAB_ACTIVE_CN'       : 'current',
								'events': ['mouseover'],
								'preventDefault': true
							});
						var tabNodes = G('tabview_nav3').getElementsByTagName('li');
						var contentNodes = Dom.getElementsByClassName("list",G("tabview3"));
						for (var i=0, l=tabNodes.length; i<l; i++){
						  tabView.addTab( { tabNode:tabNodes[i], contentNode:contentNodes[i] } );
						}
					})();
				</script>
<!-- //亲家情右边轮显示-->
</div>
<div class="main special">
<div class="t">
<h3>精彩主题</h3></div>
<div class="list">
<ul>
	<#if gny_jczt??>
	<#list gny_jczt as jczt>
		<#if jczt_index<9>
		<li><a href="${base}/news/content/${jczt.newsId}.html" title="${jczt.title}" target="_blank"><img style="filter:alpha(style=1,opacity=95,startX=85 , startY=0 , finishX=100, finishY=0)" src="${base}/${jczt.picUrl}"><br>
		<#if jczt.subTitle?length lte 6>
		${jczt.subTitle}<br>
		<#elseif jczt.subTitle?length lte 12>
		${jczt.subTitle[0..5]}<br>
		${jczt.subTitle[6..]}
		<#else>
		${jczt.subTitle[0..5]}<br>
		${jczt.subTitle[6..]}
		</#if> 
		</a><br><em></em></li>
		</#if>
	</#list>
	</#if>

</ul>
</div>
</div>
</div>
<!-- //亲家情 -->
		<!-- 自由行小包团-->
		<div class="protal-column-t0 mod-type">
<div class="main tit" id="mod-computer">
<h3>自由行小包团</h3>
<div class="more"><a href="${base}/team/s.shtml" target="_blank">更多&#187;</a></div>
<div class="search"><em>热门搜索</em>: 
<#list gny_zxt as zxt>
	<#if zxt_has_next >
	<a href="${zxt.url}" title="${zxt.title}" target="${zxt.target}">${zxt.title}</a>、
	<#else>
	<a href="${zxt.url}" title="${zxt.title}" target="${zxt.target}">${zxt.title}</a>
	</#if>
	</#list>
</div></div>
<div class="content">
<div class="img">
<#list gny_zxz as zxz>
<#if zxz_index==0>
<a href="${zxz.url}" title="${zxz.title}" target="${zxz.target}"><img src="${base}/${zxz.picUrl}"></a>
</#if>
</#list>
</div>
<!-- 自小中1-->
<div class="guide">
<#list gny_zxzs as zxzs>
			<#if zxzs_index<2 >
			<p><a href="${zxzs.url}" title="${zxzs.title}" target="${zxzs.target}"><img src="${base}/${zxzs.picUrl}"></a><br>${zxzs.title}</p>
			</#if>
			</#list>
<ul>
<#list gny_zxzx as zxzx>
			<#if zxzx_index<2 >
			<li>[${zxzx.subTitle}] <a href="${zxzx.url}" title="${zxzx.title}" target="${zxzx.target}">${zxzx.title}</a></li>
			</#if>
			</#list>
</ul>
<div class="fl">&nbsp;</div>
</div>
<!-- //自小中1-->
<!-- 自小中2-->
<div class="school">
<#list gny_zxz2 as zxz2>
<#if zxz2_index==0>
<a href="${zxz2.url}" title="${zxz2.title}" target="${zxz2.target}"><img src="${base}/${zxz2.picUrl}"></a> 
</#if>
</#list>
<ul>
<#list gny_zxy as zxy>
			<li>[${zxy.subTitle}] <a href="${zxy.url}" title="${zxy.title}" target="${zxy.target}">${zxy.title}</a></li>
			</#list>
</ul>
<div class="fl">&nbsp;</div>
</div>
<!-- //自小中2-->
<!-- 自由行小包团右轮显-->
<div class="rank" id="tabview5">
<ul class="menu" id="tabview_nav5">
<li class="current"><a href="javascript:void(0)">背包自由行</a> 
</li><li><a href="javascript:void(0)">小包团旅游</a> </li></ul>
<ul class="list">
	<#if gny_bbzy??>
	<#list gny_bbzy as bbzy>
		<#if bbzy_index<6>
		<#if (bbzy_index%2)==0>
		<li><a href="${base}/news/content/${bbzy.newsId}.html" title="${bbzy.title}" target="_blank">
		<#else>
		<li class="ev"><a href="${base}/news/content/${bbzy.newsId}.html" title="${bbzy.title}" target="_blank">
		</#if>
		<#if bbzy.subTitle?length lte 10>
		${bbzy.subTitle}
		<#else>
		${bbzy.subTitle[0..9]}
		</#if> 
		</a>
		</li>
		</#if>
	</#list>
	</#if>
</ul>
<ul class="list unselected">
<#if gny_btly??>
	<#list gny_btly as btly>
		<#if btly_index<6>
		<#if (btly_index%2)==0>
		<li><a href="${base}/news/content/${btly.newsId}.html" title="${btly.title}" target="_blank">
		<#else>
		<li class="ev"><a href="${base}/news/content/${btly.newsId}.html" title="${btly.title}" target="_blank">
		</#if>
		<#if btly.title?length lte 10>
		${btly.title}
		<#else>
		${btly.title[0..9]}
		</#if> 
		</a>
		</li>
		</#if>
	</#list>
	</#if>

</ul></div>
<script defer="defer" type="text/javascript">								
					(function(){
						var tabView = new TabView({
								'TAB_DEACTIVE_CN'     : '',
								'TAB_ACTIVE_CN'       : 'current',
								'events': ['mouseover'],
								'preventDefault': true
							});
						var tabNodes = G('tabview_nav5').getElementsByTagName('li');
						var contentNodes = Dom.getElementsByClassName("list",G("tabview5"));
						for (var i=0, l=tabNodes.length; i<l; i++){
						  tabView.addTab( { tabNode:tabNodes[i], contentNode:contentNodes[i] } );
						}
					})();
				</script>
<!-- //自由行小包团右轮显-->
</div>
<div class="main special" id="sp-computer">
<div class="t">
<h3>光影天堂</h3></div>
<div class="list">
<ul>
<#if gny_gytt??>
	<#list gny_gytt as gytt>
		<#if gytt_index<9>
		<li><a href="${base}/news/content/${gytt.newsId}.html" title="${gytt.title}" target="_blank"><img style="filter:alpha(style=1,opacity=95,startX=85 , startY=0 , finishX=100, finishY=0)" src="${base}/${gytt.picUrl}"><br>
		<#if gytt.subTitle?length lte 6>
		${gytt.subTitle}<br>
		<#elseif gytt.subTitle?length lte 12>
		${gytt.subTitle[0..5]}<br>
		${gytt.subTitle[6..]}
		<#else>
		${gytt.subTitle[0..5]}<br>
		${gytt.subTitle[6..11]}
		</#if> 
		</a><br><em></em></li>
		</#if>
	</#list>
	</#if>
</ul></div></div></div>
		<!-- //自由行小包团 -->
		

	</div>


	<!-- footer -->
	<@footer />
	<!-- //footer -->
</div>
<p>&nbsp;</p>
<!-- 国内分类导航 -->
<div class="panel panel-t0" id="dlg_nav" style="display: none; width: 239px; position: absolute;">
<div class="panel-content">
<div class="bd">
<!-- content -->
	<style>
			.panel-temp-0809279801 {position:relative;zoom:1;width:239px;height:163px;}
			.panel-temp-0809279801 .bg {width:239px;height:163px;position:absolute;background:url("http://www.tutu6.com/public/20081205/image/panel_dg_t.png") no-repeat;_filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled=true, sizingMethod=scale, src="http://www.tutu6.com/public/20081205/image/panel_dg_t.png");_background:none;}
			.panel-temp-0809279801 .inner {position:relative;padding:15px 15px 15px 28px;z-index:10;zoom:1;}
			.panel-temp-0809279801 .inner li {width:49%;float:left;font-size:12px;line-height:1.8;}
			.panel-temp-0809279801 .inner li a {color:#597683;}
	</style>

<div class="panel-temp-0809279801">
<div class="bg"></div>
<div class="inner">
<#list regions as regionP>
	<#if regionP.name!="其他">
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
	</#if>
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
<span class="cue"></span>
<span class="sd"></span>
<span class="resize"></span>
</div>
<!-- //国内分类导航 -->
<script type="text/javascript">
document.DOMLoaded(function(){var tree=G("tree-nav");if(!tree){return ;}var h3s=tree.getElementsByTagName("h3");if(h3s.length<1){return ;}var popup=new LayerPopup(G("dlg_nav"),{showTimeout:250,hideTimeout:250,autoPosition:false});Dom.getArray(h3s).each(function(el,index){el=el.getElementsByTagName("a")[0];BBEvent.observe(el,"mouseover",function(e){with({i:index}){var uls=popup.getPopup().getElementsByTagName("ul");Dom.getArray(uls).each(function(el,index){el.className="unselected";if(i==index){el.className="selected";}});}popup.hide();popup.delayShow(el.offsetWidth-3,-18,null,null,el);});BBEvent.observe(el,"mouseout",function(e){popup.delayHide();});});});
</script>

</body></html>