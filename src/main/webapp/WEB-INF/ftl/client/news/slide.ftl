<#-- newslide -->
<#macro newslide>

<#if mjfl??>
<div class="slide_module">
<div class="mod_title">
<h3>周边美景</h3></div>
<div class="mod_content">
<div class="show newspic">
<#list mjfl as news>
<p><a href="${base}/news/content/${news.newsId}.html" target="_blank"><img src="${base}/${news.picUrl}"  width=110 height=90><br>${news.subTitle}</a></p>
</#list>
</div></div></div>
</#if>


<#if jczt??>
<div class="slide_module">
<div class="mod_title">

<h3>精彩主题</h3></div>
<div class="mod_content">
<div class="show newspic">
<#list jczt as news>
<p><a href="${base}/news/content/${news.newsId}.html" target="_blank"><img src="${base}/${news.picUrl}"  width=110 height=90><br>${news.subTitle}</a></p>
</#list>
</div></div></div>
</#if>

<#if hotNews??>
<div class="slide_module">
<div class="mod_title">
<h3>旅游咨询</h3></div>
<div class="mod_content">
<#if picHotNews??>
<#if (picHotNews?size gt 1)>
<div class="show">
<#list picHotNews as news>
<p><a href="${base}/news/content/${news.newsId}.html" target="_blank"><img src="${base}/${news.picUrl}"  width=110 height=90><br>${news.subTitle}</a></p>
</#list>
</div>
</#if>
</#if>
<ul>
<#list hotNews as news>
<#if (news_index gt 4)><#break></#if>
<li><a href="${base}/news/content/${news.newsId}.html" target="_blank" title="${news.title}"><#if news.title?length lt 18 >${news.title}<#else>${news.title[0..16]}..</#if></a></li>
</#list>
</ul>
</div></div>
</#if>


<#if gytt??>
<div class="slide_module">
<div class="mod_title">

<h3>光影天堂</h3></div>
<div class="mod_content">
<div class="show newspic">
<#list gytt as news>
<p><a href="${base}/news/content/${news.newsId}.html" target="_blank"><img src="${base}/${news.picUrl}" width=110 height=90><br>${news.subTitle}</a></p>
</#list>
</div></div></div>
</#if>

<#if topTen??>
<div class="slide_module">
<div class="mod_title">

<h3>人气景点</h3></div>
<div class="mod_content">
<div class="show pic">
<#list topTen as sightPic>
<div class="sightPic"><div class="img"><a href="${base}/scenery/sceneryDetail/${sightPic.sceneryId}.html" target="_blank"><img src="${base}/${sightPic.displayUrl}" onload="resizeImg(this);" height=120px><img src="${base}/public/20081205/image/blank.gif" class="blank"></a></div><a href="${base}/scenery/sceneryDetail/${sightPic.sceneryId}.html" target="_blank">${sightPic.name}</a></div>
</#list>
</div></div></div>
</#if>


</#macro>