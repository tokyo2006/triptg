<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=x-gbk">
<title>帮助中心_兔游网</title>
<link rel="shortcut icon" href="../favicon.ico" />
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/help.css" rel="stylesheet" type="text/css" media="screen">
</head><body>
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
					<p>如果不能在帮助中心找到答案，或者您有其它建议、投诉，您还可以：<a href="http://www.tutu6.com/html/contact.html" target="_blank">联系我们</a></p>
				</div>
			<span class="bl"></span>
			<span class="br"></span>
		</div>
		<!-- //help header -->

		<!-- help nav -->
		<div class="help-nav">
			 <a href="http://www.tutu6.com" target="_blank">兔游</a> &gt;  
			 <a href="${base}/homePage/help/402881e41fcf8690011fcf8b3c390001.html" target="_self">帮助中心</a> &gt; 
			 <#if newsType??> 
			 <a href="${base}/homePage/help/${newsType.typeId}.html">${newsType.name!''}</a> &gt;
			 </#if> 
			 <#if news??> 
			 ${news.title}
			 </#if>	
		</div>		<!-- //help nav -->

		<div id="bb-main">
			<div class="bb-c">
			<#if news??> 
			<h1>${news.title}</h1>
			<div class="cont richtext">
			${news.content!''}
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
				<span class="tl"></span><span class="tr"></span>

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

			<span class="bl"></span><span class="br"></span>
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

			<!-- 预订须知 -->	
			<div class="help-mod1">
				<div class="hd">
					<h3>预订须知</h3>
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
			<!-- //预订须知 -->	


		</div>


		


	</div>

	<!-- footer -->
	<@footer />
	<!-- //footer -->

</div>
</body>
</html>