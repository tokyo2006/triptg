<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<META content="${base}/${webSite.desc}" name=description>
<META content="${base}/${webSite.keywords}" name=keywords>
<title>${newsType.name}列表——要旅游 先兔游</title>
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
<body>
<#include "*/banner.ftl">
<#include "*/header.ftl">
<#include "*/teamfoot.ftl">
<#include "slide.ftl">
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
		
	
		<!-- 主要内容 -->
		<div class="protal-column-t1 column-list">
			<!-- 文章列表 -->
			<div class="main">
				<div id="article_list">
				<!-- <div class="path">位置：</div> -->
				<h3>${newsType.name}</h3>
<div id="list-original" style="display: none;">
				<ul>
				<#list newsList as news>
<li><a href="${base}/news/content/${news.newsId}.html" target="_blank">${news.title}</a><div class="date">${news.publishDateStr}</div></li>

</#list>
</ul>
</div>
<div id="list-display">
<ul>
	<#list newsList as news>
	<#if (news_index &gt; 0)><#break></#if>
<li><a href="${base}/news/content/${news.newsId}.html" target="_blank">${news.title}</a><div class="date">${news.publishDateStr}</div></li>


</#list>
</ul></div>
<div id="list-page"><div class="global-page">
</div></div>
<script>var pageSize=48; var listRaw=document.getElementById("list-original").getElementsByTagName("li");var itemNumber=(listRaw||listRaw>0)?listRaw.length:null;var totalPage=Math.ceil(itemNumber/pageSize);var currentPage=1;var listContainer=document.getElementById("list-display");var pageContainer=document.getElementById("list-page");function updatePageUI(){if(totalPage>1){var htmlStr='<div class="global-page">';for(i=1,j=totalPage;i<=j;i++){if(i!=currentPage){htmlStr+='<a href="#" onclick="gotoPage('+i+')">'+i+'</a>';}else{htmlStr+='<span class="select">'+i+'</span>';}}
htmlStr+='</div>';pageContainer.innerHTML=htmlStr;}}
function gotoPage(n){currentPage=n;updatePageUI();displayList(n);}
function displayList(n){var from=(n-1)*pageSize+1;var to=Math.min(from+(pageSize-1),listRaw.length);var listHtmlStr='<ul>';for(i=from;i<=to;i++){listHtmlStr+='<li>'+listRaw[i-1].innerHTML+'</li>';}
listHtmlStr+='</ul>';listContainer.innerHTML=listHtmlStr;}
updatePageUI();displayList(1);</script>
			</div>
				<!-- 热卖单品 -->
					<@teamfooter />
				<!-- //热卖单品 -->
			</div>
			
			<!-- //文章列表 -->
			<div class="side">
			<@newslide />
			</div>
			
		<!-- //主要内容 -->

	</div>

	<!-- footer -->
	<@footer />
	<!-- //footer -->
</div>


</body></html>