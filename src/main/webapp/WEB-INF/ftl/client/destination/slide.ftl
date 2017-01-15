<#-- teamfooter -->
<#macro slide>





<#if areaTeamList??>
<div class="slide_module">
<div class="mod_title">
<span class="act"><a href="${base}/team/go.shtml" target="_blank">更多</a></span>
<h3>线路报价</h3></div>
<div class="mod_content">
<div class="show team">
<#list areaTeamList as team>
<div class="sightPic"><div class="img"><a href="${base}/team/teamContent/${team.teamId}" title="${team.teamName}" target="_blank"><img src="${base}/${team.breUrl}" onload="resizeImg(this);" height=120px><img src="${base}/public/20081205/image/blank.gif" class="blank"></a></div><a href="${base}/team/teamContent/${team.teamId}" title="${team.teamName}" target="_blank"><#if team.teamName?length lt 10 >${team.teamName}<#else>${team.teamName[0..8]}..</#if></a><br /><em>${team.doorDisPrice}元</em></div>
</#list>
</div></div></div>
</#if>

<#if rightNews??>
<div class="slide_module">
<div class="mod_title">
<h3>旅游咨询</h3></div>
<div class="mod_content">
<#if picNews??>
<#if (picNews?size gt 1)>
<div class="show">
<#list picNews as news>
<p><a href="${base}/news/content/${news.newsId}.html" target="_blank"><img src="${base}/${news.picUrl}"><br>${news.subTitle}</a></p>
</#list>
</div>
</#if>
</#if>
<ul>
<#list rightNews as news>
<#if (news_index gt 4)><#break></#if>
<li><a href="${base}/news/content/${news.newsId}.html" target="_blank">${news.title}</a></li>
</#list>
</ul>
</div></div>
</#if>


<#if rightScenreyList??>
<div class="slide_module">
<div class="mod_title">
<span class="act"><a href="${base}/scenery/sceneryList.shtml?areaId=${areaId}" target="_blank">更多</a></span>
<h3>人气景点</h3></div>
<div class="mod_content">
<div class="show pic">
<#list rightScenreyList as sightPic>
<div class="sightPic"><div class="img"><a href="${base}/scenery/sceneryDetail/${sightPic.sceneryId}.html" target="_blank"><img src="${base}/${sightPic.displayUrl}" onload="resizeImg(this);" height=120px><img src="${base}/public/20081205/image/blank.gif" class="blank"></a></div><a href="${base}/scenery/sceneryDetail/${sightPic.sceneryId}.html" target="_blank"><#if sightPic.name?length lt 8 >${sightPic.name}<#else>${sightPic.name[0..6]}..</#if></a></div>
</#list>
</div></div></div>
</#if>


<#if rightHelps??>
<div class="slide_module">
<div class="mod_title">
<span class="act"><a href="${base}/homePage/help/402881e41fcf8690011fcf8b3c390001.html" target="_blank">更多</a></span>
<h3>帮助信息</h3></div>
<div class="mod_content">
<ul>
<#list rightHelps as helps>
<li><a href="${base}/homePage/helpContent/${helps.newsId}.html" target="_blank">${helps.title}</a></li>
</#list>
</ul></div></div>
</#if>


</#macro>