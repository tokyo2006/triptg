<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
<title>兔游网——要旅游 先兔游</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="verify-v1" content="5QXKet/F/qJ+oH49AY1ee56bDt4iH19AXI/CzWn1DHw=" />
<META content="${webSite.desc}" name=description>
<META content="${webSite.keywords}" name=keywords>
<link rel="shortcut icon" href="../favicon.ico" />

<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/fp1.css" rel="stylesheet" type="text/css" media="screen">
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
<#include "*/banner.ftl">
<#include "*/header.ftl">
<#include "*/footer.ftl">
<body>
<div id="doc3">
	<div id="hd"><!-- {start:global master header --><@header /><!-- }end:global master header -->
	</div>
	<div id="bd">
		
		<!-- {start:header -->
		<@banner />
		<!-- }end:header -->

		<div class="fp-column-t1" style="margin-bottom: 0pt;">
			<div class="main">

				<!-- mantle -->
				<div id="fp-mantle" class="cls">
					<div class="l">
						
						<!-- notice -->
						<div id="fp-notice">
							<div class="hd">
								
								<h3>公告栏</h3>
							</div>
							<div class="bd">
								<p class="img"><a href="#" target="_blank"><img alt="公告" 

src="${base}/public/20081205/image/ya_nt.gif"></a></p>
								<ul>
									<#if szsy_ggl??>
									<#list szsy_ggl as ggl>
									<#if ggl_index==0>
									<li><a class="highlight" 
									<#else>
									<li><a 
									</#if>
									href="${ggl.url}" title="${ggl.title}" target="${ggl.target}">
									<#if ggl.title?length lte 12>
									${ggl.title}
									<#else>
									${ggl.title[0..11]}...
									</#if>
									</a></li>
									</#list>
									</#if>
								</ul>
							</div>
						</div>
						<!-- //notice -->
						
						<!-- popular -->
						<div id="fp-popular">
							<div class="bd">
								<div class="bbp-navset" id="tabview1">
									<ul class="bbp-nav cls">
										<LI class="first selected s1"><A href="#"><EM>人气景点</EM></A> </LI>
										<LI class=s2><A href="#"><EM>热点城市</EM></A> </LI>
									</ul>
									<div class="bbp-content">
										<div class="unselected">
											<ul class="ul1 cls"> 
												<#if szsy_rqjd??> 
												<#list szsy_rqjd as rqjd>
												<#if rqjd_index<12>
												<li><a href="${rqjd.url}" title="${rqjd.title}" 

target="${rqjd.target}"><IMG width="60" height="60" src="${base}/${rqjd.picUrl}">${rqjd.title}</a></li>
												</#if>
												</#list>
												</#if> 
											</ul>
											<ul class="ul2">
												<#list szsy_rqjddb as rqjddb>
												<#if rqjddb_index==0>
													<li><a class="highlight" 
												<#elseif rqjddb_index<3>
													<li><a 
												</#if>
													title="${rqjddb.title}" href="${rqjddb.url}" 

target="${rqjddb.target}">
													<#if rqjddb.title?length lte 16>
													${rqjddb.title}
													<#else>
													${rqjddb.title[0..15]}...
													</#if>
													</a></li>
												</#list>
											</ul>
										</div><!--//unselected -->
										<div class="selected">
											<ul class="ul1 cls">
												<#list szsy_rdcs as rdcs>
												<#if rdcs_index<12>
												<li><a href="${rdcs.url}" title="${rdcs.title}"  

target="${rdcs.target}"><img width="60" height="60" src="${base}/${rdcs.picUrl}">${rdcs.title}</a></li>
												</#if>
												</#list>
											</ul>
											<ul class="ul2">
												<#list szsy_rdcsdb as rdcsdb>
												<#if rdcsdb_index==0>
													<li><a class="highlight" 
												<#elseif rdcsdb_index<3>
													<li><a 
												</#if>
													title="${rdcsdb.title}" href="${rdcsdb.url}" 

target="${rdcsdb.target}">
													<#if rdcsdb.title?length lte 16>
													${rdcsdb.title}
													<#else>
													${rdcsdb.title[0..15]}...
													</#if>
													</a></li>
												</#list>
											</ul>
										</div><!--//selected -->
									</div><!--//bbp-content -->							
								</div><!--//bbp-navset -->	
							</div><!--//bd -->	
						</div>
						<!-- //fp-popular -->
						
					</div><!--//l -->
					
					<div class="r">

						<!-- slider -->
						<div class="slider">
						<!-- 幻灯组件 -->
						<div class="fp-mantle-slider cls" id="slider1">
							<!-- 缩略图 -->
							<div class="thumb">
								<ul>
								<#if sy??>
									<#list sy as flash>
										<#if flash_index<4>
											<#if flash_index==0>
											<LI class=first>
											<#else>
											<LI>
											</#if>
											<A href="${flash.url}" title="${flash.title}" target=${flash.target}

><IMG src="${base}/${flash.breUrl}"></A></LI>
										</#if>
									</#list>
								</#if>
								</ul>
							</div><!-- //缩略图 -->
						
							<!-- 数字面板 -->
							<div class="number">
								<ul>
									<li class="first selected">1</li>
									<li>2</li>
									<li>3</li>
									<li>4 </li>
								</ul>
							</div><!-- //数字面板 -->
							<!-- 大图 -->
							<div class="enlarge"> 
							<#if sy??>
								<#list sy as flash>
								<#if flash_index<4>
								<A href="${flash.url}" title="${flash.title}" target=${flash.target}><IMG class=img 

src="${base}/${flash.picUrl}"></A>
								</#if>
								</#list>
							</#if>
							</div><!-- //大图 -->	
						</div><!-- //幻灯组件 -->
						</div><!-- //slider -->

						<!-- scroller -->
						<div class="fp-mod2" id="fp-scroller">
							<div class="bd">
								<div class="bbp-navset" id="tabview2">
									<UL class="bbp-nav cls">
									  <LI class="selected"><A href="${base}/indexZb.html"><EM>周边推荐</EM></A></LI>
									  <LI><A href="${base}/indexGny.html"><EM>国内推荐</EM></A> </LI>
									  <LI><A href="${base}/indexCjy.html"><EM>出境推荐</EM></A> </LI>
									  <LI><A href="${base}/indexZyx.html"><EM>自由行推荐</EM></A> </LI>
									 </UL>
									<div class="bbp-content">
										<div class="selected">
											<ul class="cls">
												<#list szsy_zbtj as zbtj>
												<#if zbtj_index<12>
												<li><a href="${zbtj.url}" title="${zbtj.title}" 

target="${zbtj.target}"><img src="${base}/${zbtj.picUrl}">
													<#if zbtj.title?length lte 8>
													<br>${zbtj.title}
													<#elseif zbtj.title?length lte 16>
													${zbtj.title[0..7]}<br>
													${zbtj.title[8..]}
													<#else>
													${zbtj.title[0..7]}<br>
													${zbtj.title[8..13]}.. 
													</#if>	
												</a>
												<EM>兔游价:${zbtj.price}</EM> 
												</li>
												</#if>
												</#list>
											</ul>
										</div><!-- //selected -->
										<div class="unselected">
											<ul class="cls">
												<#list szsy_gntj as gntj>
												<#if gntj_index<12>
												<li><a href="${gntj.url}" title="${gntj.title}" 

target="${gntj.target}"><img src="${base}/${gntj.picUrl}">
													<#if gntj.title?length lte 8>
													<br>${gntj.title}
													<#elseif gntj.title?length lte 16>
													${gntj.title[0..7]}<br>
													${gntj.title[8..]}
													<#else>
													${gntj.title[0..7]}<br>
													${gntj.title[8..13]}.. 
													</#if>	
												</a>
												<EM>兔游价:${gntj.price}</EM> 
												</li>
												</#if>
												</#list>
											</ul>
										</div>
										<div class="unselected">
											<ul class="cls">
												<#list szsy_cjtj as cjtj>
												<#if cjtj_index<12>
												<li><a href="${cjtj.url}" title="${cjtj.title}" 

target="${cjtj.target}"><img src="${base}/${cjtj.picUrl}">
													<#if cjtj.title?length lte 8>
													<br>${cjtj.title}
													<#elseif cjtj.title?length lte 16>
													${cjtj.title[0..7]}<br>
													${cjtj.title[8..]}
													<#else>
													${cjtj.title[0..7]}<br>
													${cjtj.title[8..13]}.. 
													</#if>	
												</a>
												<EM>兔游价:${cjtj.price}</EM> 
												</li>
												</#if>
												</#list>
											</ul>
										</div>
										<div class="unselected">
											<ul class="cls">
												<#list szsy_zyxtj as zyxtj>
												<#if zyxtj_index<12>
												<li><a href="${zyxtj.url}" title="${zyxtj.title}" 

target="${zyxtj.target}"><img src="${base}/${zyxtj.picUrl}">
													<#if zyxtj.title?length lte 8>
													<br>${zyxtj.title}
													<#elseif zyxtj.title?length lte 16>
													${zyxtj.title[0..7]}<br>
													${zyxtj.title[8..]}
													<#else>
													${zyxtj.title[0..7]}<br>
													${zyxtj.title[8..13]}.. 
													</#if>	
												</a>
												<EM>兔游价:${zyxtj.price}</EM> 
												</li>
												</#if>
												</#list>
											</ul>
										</div><!-- //unselected -->
									</div><!-- //bbp-content -->
								</div><!-- //bbp-navset -->
							</div><!-- //bd -->
						</div>
						<!-- //scroller -->	

					</div>
				</div>
				<!-- //mantle -->

			</div>
			
			<div class="side">
			<!-- 免费 -->
			<!--<STYLE>
			.mod-free {
				TEXT-ALIGN: center
			}
			.mod-free IMG {
				MARGIN-BOTTOM: 6px
			}
			</STYLE>

			<DIV class="mod-free">
			<A href="#" target=_blank><IMG height=83 alt=立即注册 src="/public/20081205/image/tel.jpg" width=186></A> 
			</DIV>-->
			<!-- //免费 -->
				

				<!-- 国内旅游新闻 -->
				
<div id="fp-innew" class="fp-mod5">
	<div class="hd">
		<span class="act"><a href="${base}/news/contentList/f2d041b81fe688c0011fef5803710140.html" target="_blank">更多</a></span>
		<h3>国内旅游新闻</h3>
	</div>
	<div class="bd">
		<p style="border-bottom: 1px solid rgb(212, 212, 212); margin: 8px 10px 8px; padding-bottom: 8px;">
		<#if sy_gnxws??>
		<#list sy_gnxws as gnxws>
			<#if gnxws_index==0>
			<a href="${gnxws.url}" style="background: transparent url(${base}/${gnxws.picUrl}) no-repeat scroll 0pt center; -moz-background-clip: 

-moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial; font-size: 12px; font-weight: bold; padding-left: 70px; 

height: 68px; line-height: 17px; display: block;" target="_blank">
				
				${gnxws.title}
				
			</a>
			</#if>
		</#list>
		</#if>
		<!--<a href="/homePage/help/402881e41fd426c0011fd46c475f0009.html" style="background: transparent url(/public/20081205/image/bfb_safe.gif) 

no-repeat scroll 0pt center; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial; font-

size: 12px; font-weight: bold; padding-left: 32px; height: 68px; line-height: 27px; display: block;" target="_blank">
		安全支付常识
		</a>-->
		</p>
		<ul>
			<#if sy_gnxw??>
			<#list sy_gnxw as gnxw>
				<#if gnxw_index<5>
				<#if gnxw_index==0>
				<li class="first"><a href="${base}/news/content/${gnxw.newsId}.html" target="_blank">
				<#else>
				<li><a href="${base}/news/content/${gnxw.newsId}.html" title="${gnxw.title}" target="_blank">
				</#if>
				
				<#if gnxw.subTitle?length lte 10>
				${gnxw.subTitle}
				<#else>
				${gnxw.subTitle[0..9]}
				</#if>
				</a></li>
				</#if>
			</#list>
			</#if>
		</ul>
	
	</div>
</div>
				<!-- //国内旅游新闻 -->
				
				<!-- 国外旅游新闻 -->
					<div id="fp-outnew" class="fp-mod5">
	<div class="hd">
		<span class="act"><a href="${base}/news/contentList/f2d041b81fe688c0011fef644d22014e.html" target="_blank">更多</a></span>
		<h3>国外旅游新闻</h3>
	</div>
	<div class="bd">
		<p style="border-bottom: 1px solid rgb(212, 212, 212); margin: 8px 10px 8px; padding-bottom: 8px;">
		<#if sy_gwxws??>
		<#list sy_gwxws as gwxws>
			<#if gwxws_index==0>
			<a href="${gwxws.url}" style="background: transparent url(${base}/${gwxws.picUrl}) no-repeat scroll 0pt center; -moz-background-clip: 

-moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial; font-size: 12px; font-weight: bold; padding-left: 70px; 

height: 68px; line-height: 17px; display: block;" target="_blank">
				
				${gwxws.title}
				
			</a>
			</#if>
		</#list>
		</#if>
		<!--<a href="/homePage/help/402881e41fd426c0011fd46c475f0009.html" style="background: transparent url(/public/20081205/image/bfb_safe.gif) 

no-repeat scroll 0pt center; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial; font-

size: 14px; font-weight: bold; padding-left: 32px; height: 27px; line-height: 27px; display: block;" target="_blank">
		安全支付常识
		</a>-->
		</p>
		<ul>
			<#if sy_gwxw??>
			<#list sy_gwxw as gwxw>
				<#if gwxw_index<5>
				<li><a href="${base}/news/content/${gwxw.newsId}.html" title="${gwxw.title}" target="_blank">
				<#if gwxw.subTitle?length lte 10>
				${gwxw.subTitle}
				<#else>
				${gwxw.subTitle[0..9]}
				</#if>
				</a></li>
				</#if>
			</#list>
			</#if>
		</ul>
	
	</div>
</div>
				<!-- //国外旅游新闻 -->
				
				</div>
		</div>

	<script type="text/javascript">
		(function(){
			var C = new TabView(G("tabview1"), {
				events: ["click"],
				preventDefault: true
			});
			
			var B = new TabView(G("tabview2"), {
				events: ["mouseover", "click"],
				preventDefault: true
			});
			
			var A = G("slider1");
			new PicSlide({
				container: A,
				pics: Dom.getElementsByClassName("enlarge", A)[0].getElementsByTagName("img"),
				pages: Dom.getElementsByClassName("thumb", A)[0].getElementsByTagName("li"),
				autoPlay: true,
				interval: 5000,
				delay: 50,
				eventType: "mouseover",
				effect: "fade"
			}).run();
		})();
	</script>

		<div class="fp-column-t1">
			<div class="main">				



				
				<!-- 周边旅游 -->
				<div id="fp-cm1" class="fp-mod4">
					<div class="hd">
						<span class="act">
						<#list szsy_zbdxt as zbdxt>
						<a href="${zbdxt.url}" title="${zbdxt.title}" target="${zbdxt.target}">${zbdxt.title}

</a>&nbsp;&nbsp;|&nbsp;&nbsp;
						</#list>
						<a href="${base}/indexZb.html" target="_blank">更多信息</a>
						</span>
						<h3>周边短线旅游</h3>
					</div>
					<div class="bd cls">
						<div class="l">
						<#list szsy_zbdxz as zbdxz>
						<#if zbdxz_index==0>
							<a href="${zbdxz.url}" title="${zbdxz.title}" target="${zbdxz.target}"><img alt="${zbdxz.title}" 

src="${base}/${zbdxz.picUrl}"></a> 
						</#if>
						</#list>
						</div>
						<div class="r">
							<ul class="ul1 cls">
								<#list szsy_zbdxyz as zbdxyz>
								<#if zbdxyz_index<5>
								<li><a href="${zbdxyz.url}" title="${zbdxyz.title}" target="${zbdxyz.target}"><img 

alt="${zbdxyz.title}" src="${base}/${zbdxyz.picUrl}">
								<em>
									<#if zbdxyz.title?length lte 5>
									${zbdxyz.title}
									<#else>
									${zbdxyz.title[0..4]}..
									</#if>
								<br>
								<span class="price">
								${zbdxyz.price}
								</span>
								</em>
								</a></li>
								</#if>
								</#list>
							</ul>
							<div class="rowb">
								<#list szsy_zbdxyxz as zbdxyxz>
								<#if zbdxyxz_index==0>
								<p class="img"><a href="${zbdxyxz.url}" title="${zbdxyxz.title}" 

target="${zbdxyxz.target}"><img alt="${zbdxyxz.title}" src="${base}/${zbdxyxz.picUrl}"></a></p>
								</#if>
								</#list>
								<ul class="ul2 cls">
								<#list szsy_zbdxyxy as zbdxyxy>
								<#if zbdxyxy_index<8>
								<li><a href="${zbdxyxy.url}" title="${zbdxyxy.title}" 

target="${zbdxyxy.target}">${zbdxyxy.title}</a> 
								</#if>
								</#list>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<!-- //周边旅游 -->

				<!-- 国内旅游 -->
				<div id="fp-cm2" class="fp-mod4">
					<div class="hd">
						<span class="act">
						<#list szsy_gncxt as gncxt>
						<a href="${gncxt.url}" title="${gncxt.title}" target="${gncxt.target}">${gncxt.title}

</a>&nbsp;&nbsp;|&nbsp;&nbsp;
						</#list>
						<a href="${base}/indexGny.html" target="_blank">更多信息</a>
						</span>
						<h3>国内长线旅游</h3>
					</div>
					<div class="bd cls">
						<div class="l">
						<#list szsy_gncxz as gncxz>
						<#if gncxz_index==0>
						<a href="${gncxz.url}" title="${gncxz.title}" target="${gncxz.target}"><img alt="${gncxz.title}" 

src="${base}/${gncxz.picUrl}"></a> 
						</#if>
						</#list>
						</div>
						<div class="r">
							<ul class="ul1 cls">
							<#list szsy_gncxyz as gncxyz>
							<#if gncxyz_index<5>
							<li><a href="${gncxyz.url}" title="${gncxyz.title}" target="${gncxyz.target}"><img 

alt="${gncxyz.title}" src="${base}/${gncxyz.picUrl}">
								<em>
									<#if gncxyz.title?length lte 5>
									${gncxyz.title}
									<#else>
									${gncxyz.title[0..4]}..
									</#if>
								<br>
								<span class="price">
								${gncxyz.price}
								</span>
								</em>
							</a></li>
							</#if>
							</#list>
							</ul>
							<div class="rowb">
								<#list szsy_gncxyxz as gncxyxz>
								<#if gncxyxz_index==0>
								<p class="img"><a href="${gncxyxz.url}" title="${gncxyxz.title}" 

target="${gncxyxz.target}"><img alt="${gncxyxz.title}" src="${base}/${gncxyxz.picUrl}"></a></p>
								</#if>
								</#list>
								<ul class="ul2 cls">
								<#list szsy_gncxyxy as gncxyxy>
								<#if gncxyxy_index<8>
								<li><a href="${gncxyxy.url}" title="${gncxyxy.title}" 

target="${gncxyxy.target}">${gncxyxy.title}</a> 
								</#if>
								</#list>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<!-- //国内旅游 -->


				<!-- 出境旅游 -->
				<div id="fp-cm3" class="fp-mod4">
					<div class="hd">
						<span class="act">
						<#list szsy_cjyt as cjyt>
						<a href="${cjyt.url}" title="${cjyt.title}" target="${cjyt.target}">${cjyt.title}

</a>&nbsp;&nbsp;|&nbsp;&nbsp;
						</#list>
						<a href="${base}/indexCjy.html" target="_blank">更多信息</a>
						</span>
						<h3>出境旅游</h3>
					</div>
					<div class="bd cls">
						<div class="l">
						<#list szsy_cjyz as cjyz>
						<#if cjyz_index==0>
						<a href="${cjyz.url}" title="${cjyz.title}" target="${cjyz.target}"><img alt="${cjyz.title}" src="${base}/

${cjyz.picUrl}"></a> 
						</#if>
						</#list>
						</div>
						<div class="r">
							<ul class="ul1 cls">
								<#list szsy_cjyyz as cjyyz>
								<#if cjyyz_index<5>
								<li><a href="${cjyyz.url}" title="${cjyyz.title}" target="${cjyyz.target}"><img 

alt="${cjyyz.title}" src="${base}/${cjyyz.picUrl}">
								<em>
									<#if cjyyz.title?length lte 5>
									${cjyyz.title}
									<#else>
									${cjyyz.title[0..4]}..
									</#if>
								<br>
								<span class="price">
								${cjyyz.price}
								</span>
								</em>
								</a></li>
								</#if>
								</#list>
							</ul>
							<div class="rowb">
								<#list szsy_cjyyxz as cjyyxz>
								<#if cjyyxz_index==0>
								<p class="img"><a href="${cjyyxz.url}" title="${cjyyxz.title}" target="${cjyyxz.target}"><img 

alt="${cjyyxz.title}" src="${base}/${cjyyxz.picUrl}"></a></p>
								</#if>
								</#list>
								<ul class="ul2 cls">
								<#list szsy_cjyyxy as cjyyxy>
								<#if cjyyxy_index<8>
								<li><a href="${cjyyxy.url}" title="${cjyyxy.title}" 

target="${cjyyxy.target}">${cjyyxy.title}</a> 
								</#if>
								</#list>
								</ul>
							</div>
						</div>					
					</div>
				</div>
				<!-- //出境旅游 -->
			
				<!-- 旅游商品分类 -->
				<div id="fp-category">
<div class="hd"  id=teamClass>
<ul class="cls teamFlag">
<li class="first selected"><a href="${base}/team/s.shtml?teamFlag=1&showType=1" target="_blank">周边短线</a></li>
<li><a href="${base}/team/s.shtml?teamFlag=2&showType=1" target="_blank">国内长线</a></li>
<li><a href="${base}/team/s.shtml?teamFlag=3&showType=1" target="_blank">出境旅游</a></li>
<li><a href="${base}/team/s.shtml?teamFlag=4&showType=1" target="_blank">出境自由行</a></li>

</ul>


<div class="bd">

<div class="bn-list2" class="selected">
<div class="bot"></div>
<#list zhoubianList as region>
<!-- item -->
<div class="item <#if (region_index%2==1)>zebra</#if>">

<h3><a href="${base}/team/s.shtml?regionId=${region.regionId}&amp;showType=1" target="_blank">${region.name}</a></h3>
<ul class="cls">
<#list region.childrenList as childRegion>
<li><a href="${base}/team/s.shtml?regionId=${childRegion.regionId}&amp;showType=1" target="_blank">${childRegion.name}</a></li>
</#list>
</ul></div>
<!-- //item -->
</#list>
</div>

<div class="bn-list2" class="unselected">
<div class="bot"></div>
<#list guoneiList as region>
<!-- item -->
<div class="item <#if (region_index%2==1)>zebra</#if>">

<h3><a href="${base}/team/s.shtml?regionId=${region.regionId}&amp;showType=1" target="_blank">${region.name}</a></h3>
<ul class="cls">
<#list region.childrenList as childRegion>
<li><a href="${base}/team/s.shtml?regionId=${childRegion.regionId}&amp;showType=1" target="_blank">${childRegion.name}</a></li>
</#list>
</ul></div>
<!-- //item -->
</#list>
</div>

<div class="bn-list2" class="unselected">
<div class="bot"></div>
<#list chujingList as region>
<!-- item -->
<div class="item <#if (region_index%2==1)>zebra</#if>">

<h3><a href="${base}/team/s.shtml?regionId=${region.regionId}&amp;showType=1" target="_blank">${region.name}</a></h3>
<ul class="cls">
<#list region.childrenList as childRegion>
<li><a href="${base}/team/s.shtml?regionId=${childRegion.regionId}&amp;showType=1" target="_blank">${childRegion.name}</a></li>
</#list>
</ul></div>
<!-- //item -->
</#list>
</div>

<div class="bn-list2" class="unselected">
<div class="bot"></div>
<#list ziyouxingList as region>
<!-- item -->
<div class="item <#if (region_index%2==1)>zebra</#if>">

<h3><a href="${base}/team/s.shtml?regionId=${region.regionId}&amp;showType=1" target="_blank">${region.name}</a></h3>
<ul class="cls">
<#list region.childrenList as childRegion>
<li><a href="${base}/team/s.shtml?regionId=${childRegion.regionId}&amp;showType=1" target="_blank">${childRegion.name}</a></li>
</#list>
</ul></div>
<!-- //item -->
</#list>
</div>

</div>
</div>
</div>
				<!-- //旅游商品分类 -->
<SCRIPT type=text/javascript>
		(function(){
			var C = new TabView(G("teamClass"), {
				events: ["mouseover"],
				preventDefault: true
			});
		})();
	</SCRIPT>	
				
				
			</div>
			
		
			
			
			<div class="side">

				<!-- 签证信息 -->
				<div class="fp-mod2">
					<div class="hd">
						<span class="act"><a href="${base}/visa/visaList.html" target="_blank">更多</a></span>
						<h3>签证信息</h3>
					</div>
					<div class="bd">
					
						<ul class="fp-ul1">
						<#if sy_qzxx??>
						<#list sy_qzxx as qzxx>
							<#if qzxx_index<10>
							<#if qzxx_index==0>
							<li class="first"><span class="num">${qzxx_index+1}.</span><a href="${base}/visa/visaDetail/

${qzxx.visaId}.html" title="${qzxx.name}" target="_blank">
							<#else>
							<li><span class="num">${qzxx_index+1}.</span><a href="${base}/visa/visaDetail/${qzxx.visaId}.html" 

title="${qzxx.name}" target="_blank">
							</#if>
							<#if qzxx.name?length lte 10>
							${qzxx.name}
							<#else>
							${qzxx.name[0..9]}...
							</#if> 
							</a>
							</li>
							</#if>
						</#list>
						</#if>
					</ul>					
					</div>
				</div>
				<!-- //签证信息 -->
				
				<!-- 帮助信息 -->
				<div class="fp-mod1">
					<div class="hd">
						<span class="act"><a href="${base}/homePage/help/402881e41fcf8690011fcf8b3c390001.html" target="_blank">更多

</a></span>
						<h3>旅游帮助信息</h3>
					</div>
					<div class="bd">
					
						<ul class="fp-ul1">
						<#if rightHelps??>
						<#list rightHelps as help>
							
							<#if help_index==0>
							<li class="first"><span class="num">${help_index+1}.</span><a href="${base}/homePage/helpContent/

${help.newsId}.html" title="${help.title}" target="_blank">
							<#else>
							<li><span class="num">${help_index+1}.</span><a href="${base}/homePage/helpContent/

${help.newsId}.html" title="${help.title}" target="_blank">
							</#if>
							<#if help.title?length lte 10>
							${help.title}
							<#else>
							${help.title[0..9]}...
							</#if> 
							</a>
							</li>
							
						</#list>
						</#if>
					</ul>					
					</div>
				</div>
				<!-- //帮助信息 -->
				<!-- 兔游阳光服务承诺 -->
				<div class="fp-mod2">
					<div class="hd">
						
						<h3>兔游阳光服务承诺</h3>
					</div>
					<div class="bd">
					
						<ul class="fp-ul1">
						
						
							<li class="first"><span class="num">阳光价格：</span>兔游旅游网的价格为明码实价，且实时更新，保持为该

产品的实际销售价格。在网站上销售的产品以标价为准（节假日或特殊原因价格调整除外）。</li>
							
							<li><span class="num">阳光行程：</span>所有的自费项目都准确明示，不含强迫自费项目。</li>
							
						
						
					</ul>					
					</div>
				</div>
				<!-- //兔游旅游阳光服务承诺 -->
<!-- ad3 -->
				<!-- 广告位190x220 -->
				<#if szsy_yx??>
				<#list szsy_yx as yx>
				<div class="fp-ad">
					<a href="${yx.url}" title="${yx.title}" target="${yx.target}"><img src="${yx.picUrl}" 8220="" flip=""></a> 
				</div>
				</#list>
				</#if>
				<!-- //ad3 -->
			</div>
		</div>




	</div>
	
   <style>
		.link {padding:20px 0px 20px 0px;}
    		.linkH {float:left;padding-right:10px;color:#666;font-size:14px;font-weight:bold;}
    		.linkC {float:left;}
    </style>
    <div class="link">
    	<div class="linkH"><span>友情链接:</span></div>
    	<div class="linkC">
    		<a href="http://www.jnjhw.com" title="最容易使用的交换技能平台" target="_blank"><font style="color:#666;">技能交换网</font></a>
    		<a href="http://www.njjhw.com" title="南京交换网" target="_blank"><font style="color:#666;">南京交换网</font></a>
    		<a href="http://suzhou.focus.cn" title="焦点苏州房地产网" target="_blank"><font style="color:#666;">焦点苏州房地产网</font></a>
    		<a href="http://www.qiandaohu.cc" title="千岛湖旅游网" target="_blank"><font style="color:#666;">千岛湖旅游网</font></a>
		<a href="http://www.zhugevillage.cn" title="诸葛八卦村" target="_blank"><font style="color:#666;">诸葛八卦村</font></a>
    		<a href="http://www.njjhw.com" title="台湾会馆苏州馆" target="_blank"><font style="color:#666;">岱湖山庄度假中心</font></a>
    	</div>
    </div>

	<!-- footer -->
	<@footer />
	<!-- //footer -->
	

</div>

</body>
</html>