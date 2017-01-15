<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>自由行——要旅游 先兔游</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="verify-v1" content="5QXKet/F/qJ+oH49AY1ee56bDt4iH19AXI/CzWn1DHw=" />
<META content="${webSite.desc}" name=description>
<META content="${webSite.keywords}" name=keywords>
<link rel="shortcut icon" href="../favicon.ico" />
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/bbp-panel4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/portal-free.css" rel="stylesheet" type="text/css" media="screen">
<link type="text/css" rel="stylesheet" href="${base}/public/20081205/css/global_v4.css">
<link rel="stylesheet" href="${base}/public/20081205/css/reset-grids.css">
<link rel="stylesheet" href="${base}/public/20081205/css/sale.css">
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
						  <h3>自由行热点</h3>
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
<#if zyx??>
<#list zyx as flash>
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
<#if zyx??>
<#list zyx as flash>
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

					<!-- 自由天下 -->
					<div id="lf-mantle-fashion">
						<div class="hd">
						<span class="act">
							<#list zyx_jcqt as jcqt>
							<a href="${jcqt.url}" title="${jcqt.title}" target="${jcqt.target}">${jcqt.title}</a>
							<#if jcqt_has_next>
							&nbsp;&nbsp;|&nbsp;&nbsp;
							</#if>
							</#list>
						</span> 
<h3>自由天下</h3></div>
<div class="bd">
<h2>
									<#list zyx_jcqs as jcqs>
									<#if jcqs_index==0>
									<a href="${jcqs.url}" title="${jcqs.title}" target="${jcqs.target}">${jcqs.title}</a>
									</#if>
									</#list>
</h2>
<div class="ul1 cls">
<ul>
<#list zyx_jcqz as jcqz>
<#if jcqz_index<4>
<li><strong class="highlight">[${jcqz.subTitle}]</strong>&nbsp;&nbsp;<a href="${jcqz.url}" title="${jcqz.title}" target="${jcqz.target}">${jcqz.title}</a></li>
</#if>
</#list>
</ul></div>
<div class="ul2 cls">
<ul>
<#list zyx_jcqx as jcqx>
<#if jcqx_index<5>
<li><a href="${jcqx.url}" title="${jcqx.title}" target="${jcqx.target}"><img height="80" width="80" src="${base}/${jcqx.picUrl}">
<#if jcqx.title?length lte 4>
${jcqx.title[0..]}</br>
<#elseif jcqx.title?length lte 7>
${jcqx.title[0..3]}</br>
${jcqx.title[4..]}
<#elseif jcqx.title?length lte 14>
${jcqx.title[0..6]}</br>
${jcqx.title[7..]}
<#else>
${jcqx.title[0..6]}</br>
${jcqx.title[7..13]}..
</#if>
</a><br><strong class="highlight">${jcqx.price}</strong></li>
</#if>
</#list>
</ul>
</div>
</div>					
</div>
					<!-- //自由天下 -->


				</div>
			
			</div>
			<div class="side">
			
				

				<style>#fp-new li {line-height:1.8;}</style>
<div id="fp-new" class="fp-mod5">
	<div class="hd">
		<span class="act">
		<a href="${base}/news/contentList/f2d041b81fe688c0011fef5c6cb70148.html" target="_blank">更多</a>
		</span>
		<h3>航空酒店</h3>
	</div>
	<div class="bd">
		
		<ul>
		<#if zyx_hkjd??>
		<#list zyx_hkjd as hkjd>
		<#if hkjd_index==0>
		<li class="first"><a href="${base}/news/content/${hkjd.newsId}.html" title="${hkjd.title}" target="_blank">
			<#if hkjd.subTitle?length lte 10>
			${hkjd.subTitle}
			<#else>
			${hkjd.subTitle[0..9]}
			</#if> 
		</a></li>
		<#else>
		<li><a href="${base}/news/content/${hkjd.newsId}.html" title="${hkjd.title}" target="_blank">
			<#if hkjd.subTitle?length lte 10>
			${hkjd.subTitle}
			<#else>
			${hkjd.subTitle[0..9]}
			</#if>
		</a></li>
		</#if>
		</#list>
		</#if>
		</ul>
	
	</div>
</div>
				
			

				<!-- 热点推荐 -->
				<div id="lf-newstore" class="lf-mod1">
					<div class="hd">
						
						<h3>热点推荐</h3>
					</div>
					<div class="bd">
						<ul>
<#list zyx_rdtj as rdtj>
<li class="first"><a href="${rdtj.url}" title="${rdtj.title}" target="${rdtj.target}"><img height="50" width="50" src="${base}/${rdtj.picUrl}"></a> 
<h3><a href="${rdtj.url}" title="${rdtj.title}" target="${rdtj.target}">${rdtj.subTitle!''}</a></h3>
<p>${rdtj.title}</p></li>
</#list>
</ul>
					</div>
				</div>
				<!-- //热点推荐 -->

			</div>
		</div>
		<!-- //第一屏 -->

<script type="text/javascript">
(function(){BBoard.render("popularU","popularUPrev","popularUNext");var A=G("slider1");new PicSlide({container:A,pics:Dom.getElementsByClassName("enlarge",A)[0].getElementsByTagName("img"),pages:Dom.getElementsByClassName("thumb",A)[0].getElementsByTagName("li"),autoPlay:true,interval:5000,delay:50,effect:"fade",eventType:"mouseover"}).run();})();
</script>

    </div>
	<div id="Content">

<div class="grid-c2f">
    <div class="col-main">
        <div class="main-wrap">
			<!--出境背包 start-->
            <div class="main woman">
                <div class="m-hd"><h2>出境背包</h2></div>
                <div class="m-con">
                    <div class="m-con-in">
                    <div class="clear"></div>
                      <div id="SlideWoman" class="slide-main tb-slide">
                        <ul style="height: 250px;" class="Slides tb-slide-list">
                        <#list zyx_cjbbz as cjbbz>
						<#if cjbbz_index==0>
						<li><a href="${cjbbz.url}" title="${cjbbz.title}" target="${cjbbz.target}"><img name="" src="${base}/${cjbbz.picUrl}" alt="${cjbbz.title}" /></a></li>
						</#if>
						</#list>
                        </ul>
                      </div>
                      <ul class="prod-list">
						<#list zyx_cjbbys as cjbbys>
						<#if cjbbys_index<5>
						<li><a href="${cjbbys.url}" title="${cjbbys.title}" target="${cjbbys.target}"><strong>
							<#if cjbbys.title?length lte 12>
							${cjbbys.title}
							<#else>
							${cjbbys.title[0..10]}..
							</#if>
						</strong><b>
						<img src="${base}/${cjbbys.picUrl}" alt="${cjbbys.title}"></b><del></del><ins>${cjbbys.price}</ins></a>
						</li>
						</#if>
						</#list>	
                      </ul>
							<div class="list-containt">
                       			<ul class="quick-entra">
                       			<#list zyx_cjbbyxz as cjbbyxz>
								<#if cjbbyxz_index<4>
								<li><a href="${cjbbyxz.url}" title="${cjbbyxz.title}" target="${cjbbyxz.target}"><img src="${base}/${cjbbyxz.picUrl}" alt="${cjbbyxz.title}"></a></li>
								</#if>
								</#list>
                        		</ul>
							</div>
                        <ul class="link-list">
                         	<#list zyx_cjbbyxy as cjbbyxy>
								<#if cjbbyxy_index<5>
								<li><a href="${cjbbyxy.url}" title="${cjbbyxy.title}" target="${cjbbyxy.target}">${cjbbyxy.title}</a></li>
								</#if>
								</#list>   
                        </ul>
                        <div class="m2">
                            <div class="m2-hd"><h3>热点推荐</h3></div>
                            <div class="m2-con">
                                <div class="m2-con-in">
                                    <div class="big-pic">
                                    <#list zyx_rdtj2zs as rdtj2zs>
									<#if rdtj2zs_index==0>
									<a href="${rdtj2zs.url}" title="${rdtj2zs.title}" target="${rdtj2zs.target}"><img src="${base}/${rdtj2zs.picUrl}" alt="${rdtj2zs.title}"></a>
									</#if>
									</#list>
			                        </ul>
                                    </div>
                                    <ul class="link-list">
									<#list zyx_rdtj2zx as rdtj2zx>
									<#if rdtj2zx_index<2>
									<li><a href="${rdtj2zx.url}" title="${rdtj2zx.title}" target="${rdtj2zx.target}" class="cate-link"></a><a href="${rdtj2zx.url}" target="${rdtj2zx.target}">${rdtj2zx.title}</a></li>
									</#if>
									</#list> 	
                                    </ul>
                                    <ul class="prod-list">
                                    <#list zyx_rdtj2y as rdtj2y>
									<#if rdtj2y_index<5>
									<li><a href="${rdtj2y.url}" title="${rdtj2y.title}" target="${rdtj2y.target}"><strong>
										<#if rdtj2y.title?length lte 6>
										${rdtj2y.title}</br>
										<#elseif rdtj2y.title?length lte 12>
										${rdtj2y.title[0..5]}</br>
										${rdtj2y.title[6..]}
										<#else>
										${rdtj2y.title[0..5]}</br>
										${rdtj2y.title[6..11]}
										</#if>
									</strong><b><img src="${base}/${rdtj2y.picUrl}" alt=""></b><del></del><ins></ins></a></li>
									</#if>
									</#list> 
									</ul>
                                </div>
                            </div>
                            <div class="m2-ft"><span></span></div>
                        </div>
                    </div>
                </div>
                <div class="m-ft"><span></span></div>
            </div>
            <!--出境背包 end-->
        </div>
    </div>
    <div class="col-sub">
		<!--国外见闻 start-->
        <div class="side hot-sale1">
            <div class="m-hd"><h2>国外见闻</h2></div>
            <div class="m-con">
                <div class="m-con-in">
                    <div class="tab-box" id="WomanTab">
                        <ul class="tab">
                            <li class="selected"><a href="#" target="_blank">图文</a></li>
                            <li><a href="#" target="_blank">游记攻略</a></li>
                         
                        </ul>
						<div>
                        <div class="panel selected"> 
                            <ul class="prod-list">
							<#list zyx_tw as tw>
							<#if tw_index<3>
							<li><a href="${tw.url}" title="${tw.title}" target="${tw.target}"><strong>${tw.title}</strong>
							<b><img name="" src="${base}/${tw.picUrl}" alt="${tw.title}"></b>
							<ins>
							<#if tw.subTitle?length lte 6>
							${tw.subTitle!''}
							<#else>
							${tw.subTitle[0..5]}
							</#if>
							</ins>
							</a></li>	
							</#if>
							</#list> 		
                            </ul>
                        </div>
                        <div  class="panel unselected">
                            <ul class="prod-list">
                            <#list zyx_yjgl as yjgl>
							<#if yjgl_index<3>
							<li><a href="${yjgl.url}" title="${yjgl.title}" target="${yjgl.target}"><strong>${yjgl.title}</strong><b><img name="" src="${base}/${yjgl.picUrl}" alt="${yjgl.title}"></b>
							<ins>
							<#if yjgl.subTitle?length lte 6>
							${yjgl.subTitle!''}
							<#else>
							${yjgl.subTitle[0..5]}
							</#if>
							</ins>
							</a></li>	
							</#if>
							</#list>    
                            </ul>                          
                        </div>
						
						</div>
						
                  </div>
                </div>
            </div>
            <div class="m-ft"><span></span></div>
        </div>
		
<script type="text/javascript">

	(function(){
		var C = new TabView(G("WomanTab"), {
			events: ["mouseover"],
			preventDefault: true
		});
		
		
	})();

</script>		
		
        <!--国外见闻 end-->
        <!--超人气景点 start-->
        <div class="side hot-sale2 m_top10">
            <div class="m-hd"><h2>超人气景点TOP5</h2></div>
            <div class="m-con">
                <div class="m-con-in">
                    <ul class="link-list">
                    <#list zyx_rqjd as rqjd>
					<#if rqjd_index==0>
					<li class="first"><a href="${rqjd.url}" title="${rqjd.title}" target="${rqjd.target}">${rqjd.title}</a></li>
					<#else>
					<li><a href="${rqjd.url}" title="${rqjd.title}" target="${rqjd.target}">${rqjd.title}</a></li>
					</#if>
					</#list>    
                    </ul>
                </div>
            </div>
            <div class="m-ft"><span></span></div>
        </div>
<!--超人气景点 end-->
    </div>
</div>


<div class="grid-c2f">
	<div class="col-main">
		<div class="main-wrap">
            <!--国内出游 start-->
            <div class="main man">
        <div class="m-hd"><h2>国内出游</h2></div>
        <div class="m-con">
            <div class="m-con-in">
                <div class="clear"></div>
                <div id="SlideMan" class="slide-main tb-slide">
                    <ul style="height: 250px;" class="Slides tb-slide-list">
					<#list zyx_gncyz as gncyz>
					<#if gncyz_index==0>
					<li><a href="${gncyz.url}" title="${gncyz.title}" target="${gncyz.target}"><img name="" src="${base}/${gncyz.picUrl}" alt="${gncyz.title}" /></a></li>
					</#if>
					</#list>	
                    </ul>
                </div>
                <ul class="prod-list">
                 	<#list zyx_gncyzh as gncyzh>
						<#if gncyzh_index<6 >
						<li><a href="${gncyzh.url}" title="${gncyzh.title}" target="${gncyzh.target}"><strong>${gncyzh.title}</strong><b><img src="${base}/${gncyzh.picUrl}" alt=""></b><del></del><ins>${gncyzh.price}</ins></a></li>
						</#if>
						</#list>    
                </ul>
                <div class="link-list-box">
                    <h3>国内自由行攻略</h3>
                    <ul class="link-list">
                    <#list zyx_gncyy as gncyy>
                    <li><a href="${gncyy.url}" title="${gncyy.title}" target="${gncyy.target}">${gncyy.title}</a></li>
					</#list>    
                    </ul>
                </div>
                <div class="m2">
                    <div class="m2-hd"><h3>驴行神州</h3></div>
                    <div class="m2-con">
                        <div class="m2-con-in">
                        	<div>
                            <div class="big-pic">
							<#list zyx_lxszzs as lxszzs>
							<#if lxszzs_index==0>
							<a href="${lxszzs.url}" title="${lxszzs.title}" target="${lxszzs.target}"><img name="" src="${base}/${lxszzs.picUrl}" alt="${lxszzs.title}"></a>
							</#if>
							</#list>	
								
                            </div>
                            <ul class="link-list">
							<#list zyx_lxszzx as lxszzx>
							<#if lxszzx_index<2>
							<li><a href="${lxszzx.url}" title="${lxszzx.title}" target="${lxszzx.target}" class="cate-link"></a><a href="${lxszzx.url}" target="${lxszzx.target}">${lxszzx.title}</a></li>
							</#if>
							</#list>	
                            </ul>
							</div>
                            <ul class="prod-list">
                            <#list zyx_lxszy as lxszy>
							<#if lxszy_index<5>
							<li><a href="${lxszy.url}" title="${lxszy.title}" target="${lxszy.target}"><strong>
								<#if lxszy.title?length lte 6>
								${lxszy.title}</br>
								<#elseif lxszy.title?length lte 12>
								${lxszy.title[0..5]}</br>
								${lxszy.title[6..]}
								<#else>
								${lxszy.title[0..5]}</br>
								${lxszy.title[6..11]}
								</#if>
							</strong><b><img name="" src="${base}/${lxszy.picUrl}" alt="${lxszy.title}"></b><del></del><ins></ins></a></li>
							</#if>
							</#list>
                            </ul>
                        </div>
                    </div>
                    <div class="m2-ft"><span></span></div>
                </div>
            </div>
        </div>
        <div class="m-ft"><span></span></div>
    </div>
            <!--国内出游 end-->
		</div>
	</div>
	<div class="col-sub">
			<!--热门自由行 start-->
<div class="side hot-sale3">
	<div class="m-hd"><h2>热门自由行景点</h2></div>
	<div class="m-con">
		<div class="m-con-in">
			<ul class="prod-list">
			<#list zyx_rmzyx as rmzyx>
			<#if rmzyx_index<4>
			<li><a href="${rmzyx.url}" title="${rmzyx.title}" target="${rmzyx.target}"><strong>${rmzyx.title}</strong><b><img name="" src="${base}/${rmzyx.picUrl}" alt="${rmzyx.title}"></b><ins>${rmzyx.subTitle!''}</ins></a></li>
			</#if>
			</#list>	
			</ul>
			
		</div>
	</div>
	<div class="m-ft"><span></span></div>
</div>
<!--热门自由行 end-->

<div class="side side-pic1 m_top10">
							<#list zyx_yx as yx>
							<#if yx_index==0>
							<a href="${yx.url}" title="${yx.title}" target="${yx.target}"><img name="" src="${base}/${yx.picUrl}" alt="${yx.title}" width="190" height="90"></a>
							</#if>
							</#list>
	
</div>
	</div>
</div>
</div>
	<!-- footer -->
	<@footer />
	<!-- //footer -->
</div>

<!-- 自由行分类浮层 -->
<div class="panel panel-t0" id="dlg_nav" style="display: none; width: 239px; position: absolute;">
<div class="panel-content">
<div class="bd">
<!-- content -->
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
</div></div>
<!-- //content --></div></div><span class="co1"><span></span></span><span class="co2"><span></span></span><span class="cue"></span><span class="sd"></span><span class="resize"></span></div><!-- //自由行分类浮层 -->
<script type="text/javascript">
document.DOMLoaded(function(){var tree=G("tree-nav");if(!tree){return ;}var h3s=tree.getElementsByTagName("h3");if(h3s.length<1){return ;}var popup=new LayerPopup(G("dlg_nav"),{showTimeout:250,hideTimeout:250,autoPosition:false});Dom.getArray(h3s).each(function(el,index){el=el.getElementsByTagName("a")[0];BBEvent.observe(el,"mouseover",function(e){with({i:index}){var uls=popup.getPopup().getElementsByTagName("ul");Dom.getArray(uls).each(function(el,index){el.className="unselected";if(i==index){el.className="selected";}});}popup.hide();popup.delayShow(el.offsetWidth-3,-18,null,null,el);});BBEvent.observe(el,"mouseout",function(e){popup.delayHide();});});});
</script>

</body></html>