<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=x-gbk">
<title>帮助中心_兔游网</title>
<link rel="shortcut icon" href="../favicon.ico" />
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/help.css" rel="stylesheet" type="text/css" media="screen">
<style>
.bbp-custom-t2 #bb-main .bb-c {width:690px;}
.bbp-custom-t2 .bb-c {width:250px;}
</style>
<style>
.bbp-custom-t2 #bb-main .bb-c {width:690px;}
.bbp-custom-t2 .bb-c {width:250px;}
</style>
</head>
<body>
<#include "*/header.ftl">
<#include "*/footer.ftl">
<div id="doc3" class="bbp-custom-t2">

	<div id="hd">
		
		<!-- {start:global master header -->
		<@header />
		<!-- }end:global master header -->

	</div>

	<div id="bd">
		
		<!-- help header -->
		<div class="help-search">
			<span class="tl"></span>
			<span class="tr"></span>
				<div class="form">
					<p>如果不能在帮助中心找到答案，或者您有其它建议、投诉，您还可以：<a href="http://www.tutu6.com/html/contact.html" target="_blank">联系我们</a>
					</p>
				</div>
			<span class="bl"></span>
			<span class="br"></span>
		</div>
		<!-- //help header -->

		<!-- help nav -->
		<div class="help-nav">
			<a href="http://www.tutu6.com">兔游</a>&nbsp;&nbsp;&gt;&nbsp;&nbsp;
			<#if (helpList??)&&(helpList.size()>0)>
			帮助中心
			<#elseif (newsList??)&&(newsType??)>
			<a href="${base}/homePage/help/402881e41fcf8690011fcf8b3c390001.html">帮助中心</a>&nbsp;&nbsp;&gt;&nbsp;&nbsp;
			${newsType.name}
			</#if>
		</div>		
<!-- //help nav -->

		<div id="bb-main">
			<div class="bb-c">
				<#if (helpList??)&&(helpList.size()>0)>
				<!-- mantle 
				<div class="help-guide">
					<h3>新手帮助</h3>

					<a href="/145203185557.html#1.0.0" target="_blank" class="s1">我要买</a>
					<a href="/145203185557.html#1.0.1" target="_blank" class="s2">成为买家</a>
					<a href="/145203185557.html#1.1.0" target="_blank" class="s3">找商品</a>
					<a href="/145203185557.html#1.2.0" target="_blank" class="s4">确认购买</a>
					<a href="/145203185557.html#1.4.0" target="_blank" class="s5">收货与评价</a>
					<a href="/145203185557.html#0.0.0" target="_blank" class="s6">我要卖</a>
					<a href="/145203185557.html#0.0.1" target="_blank" class="s7">成为卖家</a>
					<a href="/145203185557.html#0.1.0" target="_blank" class="s8">发布商品</a>
					<a href="/145203185557.html#0.2.0" target="_blank" class="s9">装修店铺</a>
					<a href="/145203185557.html#0.3.0" target="_blank" class="s10">发货与评价</a>

				</div>

				 //mantle -->

				<!-- help category -->
				<div class="help-category">
					<div class="hd">
						<h3>帮助分类</h3>
					</div>
					<div class="bd">
					
						<#list helpList as help>
							<#if ((help_index%4)==0)&&help_has_next>
								<!-- item -->
								<div class="itembox itembox-zebra">
								<span class="tl"></span>
								<span class="tr"></span>
								<ul class="cls">
									<li>
									<h3><a href="${base}/homePage/help/${help.nt.typeId}.html" target="_self">${help.nt.name}</a></h3>
									<p>
									<#if help.news??>
									<#list help.news as helpC>
										<#if helpC_has_next>
										<a href="${base}/homePage/helpContent/${helpC.newsId}.html" target="_self">${helpC.title}</a><br>
										<#else>
										<a href="${base}/homePage/helpContent/${helpC.newsId}.html" target="_self">${helpC.title}</a>
										</#if>
								  	</#list>
								  	</#if>								
									</p>
							<#elseif ((help_index%4)==0)&&(!help_has_next)>
								<!-- item -->
								<div class="itembox itembox-zebra">
								<span class="tl"></span>
								<span class="tr"></span>
								<ul class="cls">
									<li>
									<h3><a href="${base}/homePage/help/${help.nt.typeId}.html" target="_self">${help.nt.name}</a></h3>
									<p>
									<#if help.news??>
									<#list help.news as helpC>
										<#if helpC_has_next>
										<a href="${base}/homePage/helpContent/${helpC.newsId}.html" target="_self">${helpC.title}</a><br>
										<#else>
										<a href="${base}/homePage/helpContent/${helpC.newsId}.html" target="_self">${helpC.title}</a>
										</#if>
								  	</#list>
								  	</#if>								
									</p>
									</li>
								</ul>
								<span class="bl"></span><span class="br"></span>
								</div><!-- //item -->
							<#elseif (help_index%4)==1>
									</li>
									<li>
									<h3><a href="${base}/homePage/help/${help.nt.typeId}.html" target="_self">${help.nt.name}</a></h3>
									<p>
									<#if help.news??>
									<#list help.news as helpC>
										<#if helpC_has_next>
										<a href="${base}/homePage/helpContent/${helpC.newsId}.html" target="_self">${helpC.title}</a><br>
										<#else>
										<a href="${base}/homePage/helpContent/${helpC.newsId}.html" target="_self">${helpC.title}</a>
										</#if>
								  	</#list>
								  	</#if>
								  	</p>
								  	</li>
								</ul>
								<span class="bl"></span><span class="br"></span>
								</div><!-- //item -->
							<#elseif ((help_index%4)==2)&&help_has_next>
								<!-- item -->
								<div class="itembox">
								<ul class="cls">
									<li>
									<h3><a href="${base}/homePage/help/${help.nt.typeId}.html" target="_self">${help.nt.name}</a></h3>
									<p>
									<#if help.news??>
									<#list help.news as helpC>
										<#if helpC_has_next>
										<a href="${base}/homePage/helpContent/${helpC.newsId}.html" target="_self">${helpC.title}</a><br>
										<#else>
										<a href="${base}/homePage/helpContent/${helpC.newsId}.html" target="_self">${helpC.title}</a>
										</#if>
									 </#list>
									 </#if>
									 </p>
							<#elseif ((help_index%4)==2)&&(!help_has_next)>
								<!-- item -->
								<div class="itembox">
								<ul class="cls">
									<li>
										<h3><a href="${base}/homePage/help/${help.nt.typeId}.html" target="_self">${help.nt.name}</a></h3>
										<p>
										<#if help.news??>
										<#list help.news as helpC>
											<#if helpC_has_next>
											<a href="${base}/homePage/helpContent/${helpC.newsId}.html" target="_self">${helpC.title}</a><br>
											<#else>
											<a href="${base}/homePage/helpContent/${helpC.newsId}.html" target="_self">${helpC.title}</a>
											</#if>
									  	</#list>
									  	</#if>
									  	</p>
									</li>
								</ul>
								</div><!-- //item -->
							<#elseif (help_index%4)==3>
									</li>
									<li>
										<h3><a href="${base}/homePage/help/${help.nt.typeId}.html" target="_self">${help.nt.name}</a></h3>
										<p>
										<#if help.news??>
										<#list help.news as helpC>
											<#if helpC_has_next>
											<a href="${base}/homePage/helpContent/${helpC.newsId}.html" target="_self">${helpC.title}</a><br>
											<#else>
											<a href="${base}/homePage/helpContent/${helpC.newsId}.html" target="_self">${helpC.title}</a>
											</#if>
									  	</#list>
									  	</#if>
									</p>
									</li>
								</ul>
								</div><!-- //item -->
							</#if>
						</#list>
						
					</div>
					
				</div>
				<!-- //help category -->
			<#elseif (newsList??)&&(newsType??)>
				<h1>${newsType.name}</h1>
				<div id="ac_list" class="list">
				<div id="list-original">
					<ul>
					<#list newsList as helpC>
					<li><a href="${base}/homePage/helpContent/${helpC.newsId}.html">${helpC.title}</a></li>
					</#list>
					</ul>
				</div>
				</div>
			</#if>
			</div>
		</div>
		<div class="bb-c">
		
			<!-- 常见问题 -->	
			<div class="help-mod1">
				<div class="hd">
					<h3>常见问题</h3>
				</div>
				<div class="bd">
					<ul class="help-ul1">
						<#if helpL1??>
						<#list helpL1 as helpL>
							<#if helpL_index==0>
							<li class="first"><a href="${base}/homePage/helpContent/${helpL.newsId}.html" target="_blank">${helpL.subTitle}</a></li>
							<#else>
							<li><a href="${base}/homePage/helpContent/${helpL.newsId}.html" target="_blank">${helpL.subTitle}</a></li>
							</#if>
						</#list>
						</#if>
					</ul>
				</div>
			</div>
			<!-- //常见问题 -->	

			<!-- 出境签证 -->	
			<div class="help-mod1">
				<div class="hd">
					<h3>出境签证</h3>
				</div>
				<div class="bd">
					<ul class="help-ul1">
						<#if helpL2??>
						<#list helpL2 as helpL>
							<#if helpL_index==0>
							<li class="first"><a href="${base}/homePage/helpContent/${helpL.newsId}.html" target="_blank">${helpL.subTitle}</a></li>
							<#else>
							<li><a href="${base}/homePage/helpContent/${helpL.newsId}.html" target="_blank">${helpL.subTitle}</a></li>
							</#if>
						</#list>
						</#if>
					</ul>
				</div>
			</div>
			<!-- //出境签证 -->	

			<!-- 常用链接 -->
			<div class="help-mod2">
				<span class="tl"></span>
				<span class="tr"></span>
				<div class="bd">
					<ul class="help-ul2">
						<li><a href="${base}/indexZb.html" target="_blank">周边旅游 </a></li>
						<li><a href="${base}/indexGny.html" target="_blank">国内长线游</a></li>
						<li><a href="${base}/indexCjy.html" target="_blank">出境旅游</a></li>
						<li><a href="${base}/indexQy.html" target="_blank">企业团队游</a></li>
						<li><a href="${base}/visa/visaList.html" target="_blank">签证信息</a></li>
					</ul>
					<!--<p class="help-misc-s2">如果不能在帮助中心找到答案，或者您有其它建议、投诉，您还可以：<br><a href="" target="_blank">点此提交您的问题&gt;&gt;</a>
					</p>-->
				</div>

				<span class="bl"></span>
				<span class="br"></span>
			</div>
			<!-- //常用链接 -->

			<!-- 联系我们 -->	
			<div class="help-mod3">
				<span class="tl"></span>
				<span class="tr"></span>
				<div class="hd">
					<h3>联系我们</h3>
				</div>
				<div class="bd">
					<p class="help-misc-s1">
					<ul class="relation-ul">
					<li class="inf-li1"><span>电话：</span>(+86) 0512-68555888</li>
					<li>(+86) 0512-68555999</li>
					<li>(+86) 0512-67861871</li>
					<li>(+86) 0512-67861872</li>
					<li class="inf-li2"><span>传真：</span>(+86) 0512-67861873</li>
					<li class="inf-li2"><span class="sp-qq">QQ：</span>1005038884</li>
					<li>1143582424</li>
					<li class="inf-li2"><span class="sp-msn">MSN：</span>tutu6_01@live.cn</li>
					<li>tutu6_02@live.cn</li>
					<li>tutu6_03@live.cn</li>
					</ul>
					<!--<a href="http://co.youa.baidu.com/content/hi/index.html" target="_blank">百度Hi</a>-->
					</p>
				</div>
				<span class="bl"></span>
				<span class="br"></span>
			</div>
			<!-- //联系我们 -->	

			<!-- 旅游须知 -->	
			<div class="help-mod1">
				<div class="hd">
					<h3>旅游须知</h3>
				</div>
				<div class="bd">
					<ul class="help-ul1">
						<#if helpL3??>
						<#list helpL3 as helpL>
							<#if helpL_index==0>
							<li class="first"><a href="${base}/homePage/helpContent/${helpL.newsId}.html" target="_blank">${helpL.subTitle}</a></li>
							<#else>
							<li><a href="${base}/homePage/helpContent/${helpL.newsId}.html" target="_blank">${helpL.subTitle}</a></li>
							</#if>
						</#list>
						</#if>
					</ul>				
				</div>
			</div>
			<!-- //旅游须知 -->	


		</div>


	</div>


	<!-- footer -->
	<@footer />
	<!-- //footer -->

</div>
</body>
</html>