<#-- teamfooter -->
<#macro teamfooter>

<#if teamList??>						

<div id="hotest">
<h3>推荐线路</h3>
<ul>
<#list teamList as team>
<li><div class="img"><a href="${base}/team/teamContent/${team.teamId}" target="_blank"><img src="${base}/${team.breUrl}" onload="resizeImg(this);" height=90px alt="${team.teamName}"></a><img src="${base}/public/20081205/image/blank.gif" class="blank"></div><a href="${base}/team/teamContent/${team.teamId}" target="_blank" title="${team.teamName}"><#if team.teamName?length lt 8 >${team.teamName}<#else>${team.teamName[0..6]}..</#if><br><em>${team.doorDisPrice}元</em></a></li>
</#list>
</ul></div>

</#if>

</#macro>