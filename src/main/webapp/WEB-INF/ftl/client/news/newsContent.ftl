<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<META content=兔游全新大型旅游购物网站，为人们提供更便捷、可靠的一站式旅游消费体验，使您旅游省事更安心。name=description>
<META content=兔游网，苏州旅游，苏州青旅 name=keywords>

<title>${news.title}——兔游网</title>

<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/bbp-panel.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/portal-news.css" rel="stylesheet" type="text/css" media="screen">

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

<body class="portal-women">
<#include "*/banner.ftl">
<#include "*/header.ftl">
<#include "*/footer.ftl">
<#include "*/teamfoot.ftl">
<#include "slide.ftl">
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
		

	

		<!-- 主要内容 -->
		<div class="protal-column-t1 column-list">
			<div class="main">
				<div id="article">
				<!-- 正文 -->
				<div class="path">位置： 
				<a href="#">兔游</a> &gt; 
				
				<a href="${base}/news/contentList.shtml?typeId=${newsType.typeId}">${newsType.name}</a> 
				
				</div>
				<div class="title">
					<h3>${news.title}</h3>
<p>发布日期&nbsp;:&nbsp;${news.publishDateStr!""}&nbsp;&nbsp;&nbsp;&nbsp;来源&nbsp;:&nbsp;${news.origin!""}</p>
				</div>
				
				<div class="content">
					
						<div class="richtext">
						
${content}
<#if page??>
<#if page.pageCnt gt 1>
<div id="paging_div" style="padding-left: 18px; font-size: 14px; word-spacing: 4px;" align="right">
<#if pageId!=1>
<a class="paging_pre" href="${base}/news/content/${news.newsId}/#{pageId-1}.html" target="_self">上一页</a>
</#if>
<#list 1..page.pageCnt as i>
<#if i==pageId>
${i}&nbsp;
<#else>
<a class="paging_a" href="${base}/news/content/${news.newsId}/#{i}.html" target="_self">#{i}</a>&nbsp;
</#if>
</#list>
<#if pageId!=page.pageCnt>
<a class="paging_pre" href="${base}/news/content/${news.newsId}/#{pageId+1}.html" target="_self">下一页</a>
</#if>
</div>
</#if>
</#if>
<br>
						
						</div>

				</div>
				<!-- //正文 -->
				</div>



								
				<!-- 热卖单品 -->
				<@teamfooter />
				<!-- //热卖单品 -->
				
			</div>
			
			
			<div class="side">
			<@newslide />
			</div>
		<!-- //主要内容 -->

	</div>


	<!-- footer -->
	<@footer />
	<!-- //footer -->
</div>

</body>

</html>