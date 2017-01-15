<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>城市景点——要旅游 先兔游</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="verify-v1" content="5QXKet/F/qJ+oH49AY1ee56bDt4iH19AXI/CzWn1DHw=" />
<META content="${webSite.desc}" name=description>
<META content="${webSite.keywords}" name=keywords>
<SCRIPT src="${base}/resources/20081205/script/prototype.js" type=text/javascript></SCRIPT>

<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-8094470-3");
pageTracker._trackPageview();
} catch(err) {}</script>

<SCRIPT src="${base}/public/20081205/script/effect_switcher_1104.js" type=text/javascript></SCRIPT>
<link rel="shortcut icon" href="../favicon.ico" />
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/ent.css" rel="stylesheet" type="text/css" 

media="screen">
<link href="${base}/public/20081205/css/destinationGuideIndex.css" type="text/css" 

rel="stylesheet">
<link href="${base}/public/20081205/css/general_destinationGuide.css" type="text/css" 

rel="stylesheet">
<link  rel="stylesheet" type="text/css" href="${base}/public/20081205/css/mall_default_v3

[1].css" />
	
<script type="text/javascript" src="${base}/public/20081205/script/core-mini.js"></script>

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
		
		<!-- {start:header tab -->
		<@banner />
		<!-- {end:header tab -->

	</div>
	
	<div class="default">
	  <div class="body">
			<!--内容开始-->
			 <div class="body-skin">
	

<div class="section">
    
<div class="parent01 destinationGuideMap cf">
<div class="map" id="maps">
<ul class="cf" id="tab07_ttl">
<li class="s1 selected"><h2>国内游</h2></li>
<li class="s1"><h2>国外游</h2></li>
</ul>
<div class="map_pic">
<div class="selected"><img src="${base}/public/20081205/image/IN_chinamap.gif" alt="中国旅游

地图" usemap="#Map" border="0"></div>
<div class="unselected"><img src="${base}/public/20081205/image/IN_worldmap.gif" alt="世界旅

游地图" usemap="#Map2" border="0"></div>
<script type="text/javascript">

	(function(){
		var C = new TabView(G("maps"), {
			events: ["click"],
			preventDefault: true
		});
		
		
	})();
</script>
</div>
<!-- map Tab -->
</div>

<script language="javascript" type="text/javascript">
function SearchKeyword()
{
    var kw = document.getElementById("keyWord2").value;
    var theIndex;
    kw = kw.substr(kw.search("[^ ]"));theIndex = kw.search(" $");
	
    while (theIndex != -1)
    {
        kw = kw.substring(0, theIndex);theIndex = kw.search(" $");
    }
    document.getElementById("keyWord2").value = kw;
    if (document.getElementById("keyWord2").value == "中文"||document.getElementById("keyWord2").value =="")
        {
            document.getElementById("keyWord2").value="";
        }
    else
        {   document.getElementById("innerSearch").method="get";         	
            document.getElementById("innerSearch").submit();
        }    
}
function setEnterfunc()
{
    if(document.getElementById("keyWord2").value=='中文'){document.getElementById("keyWord2").value='';}
}
function clearEnterfunc()
{
    if(document.getElementById("keyWord2").value==''){document.getElementById("keyWord2").value='中文';}
	document.onkeydown = "";
}

</script>

<div class="search">
<dl>
<dt>景区/景点搜索：</dt>
<dd>
<form id="innerSearch" name="innerSearch" method="post" action="${base}/search.shtml">
<input name="keyWord2" id="keyWord2" onclick="javascript:setEnterfunc();" onblur="javascript:clearEnterfunc();" value="中文" type="text">
<button type="button" class="btn06" onclick="javascript:SearchKeyword();">搜索</button>
</form>
</dd>
</dl>
<dl>
<dt>热门关键字：</dt>
<dd>
<ul>
<li>
<#if csjd_class_gj1??>
<em><font color="#ee6911">[${csjd_class_gj1.title}]</font></em>
</#if>
<#if csjd_gj1??>
	<#list csjd_gj1 as gj1>
		<#if gj1_index<5>
		<a href="${gj1.url}" target="${gj1.target}">${gj1.title}</a>&nbsp;
		</#if>
	</#list>
</#if>
</li>
<li>
<#if csjd_class_gj2??>
<em><font color="#ee6911">[${csjd_class_gj2.title}]</font></em>
</#if>
<#if csjd_gj2??>
	<#list csjd_gj2 as gj2>
		<#if gj2_index<5>
		<a href="${gj2.url}" target="${gj2.target}">${gj2.title}</a>&nbsp;
		</#if>
	</#list>
</#if>
</li>
<li>
<#if csjd_class_gj3??>
<em><font color="#ee6911">[${csjd_class_gj3.title}]</font></em>
</#if>
<#if csjd_gj3??>
	<#list csjd_gj3 as gj3>
		<#if gj3_index<5>
		<a href="${gj3.url}" target="${gj3.target}">${gj3.title}</a>&nbsp;
		</#if>
	</#list>
</#if>
</li>
<li>
<#if csjd_class_gj4??>
<em><font color="#ee6911">[${csjd_class_gj4.title}]</font></em>
</#if>
<#if csjd_gj4??>
	<#list csjd_gj4 as gj4>
		<#if gj4_index<5>
		<a href="${gj4.url}" target="${gj4.target}">${gj4.title}</a>&nbsp;
		</#if>
	</#list>
</#if>
</li>
<li>
<#if csjd_class_gj5??>
<em><font color="#ee6911">[${csjd_class_gj5.title}]</font></em>
</#if>
<#if csjd_gj5??>
	<#list csjd_gj5 as gj5>
		<#if gj5_index<5>
		<a href="${gj5.url}" target="${gj5.target}">${gj5.title}</a>&nbsp;
		</#if>
	</#list>
</#if>
</li>
<li>
<#if csjd_class_gj6??>
<em><font color="#ee6911">[${csjd_class_gj6.title}]</font></em>
</#if>
<#if csjd_gj6??>
	<#list csjd_gj6 as gj6>
		<#if gj6_index<5>
		<a href="${gj6.url}" target="${gj6.target}">${gj6.title}</a>&nbsp;
		</#if>
	</#list>
</#if>
</li>

</ul></dd>
</dl>
</div>
</div>



<map name="Map" id="Map">
<area shape="rect" coords="326,44,373,61" 

href="http://www.tutu6.com/destination/proviceDetail/402880e51a513d28011a52cc0f1600c1.html" 

title="黑龙江">

<area shape="rect" coords="326,75,365,90" 

href="http://www.tutu6.com/destination/proviceDetail/402880e51a513d28011a52c40b9300b6.html" 

title="吉林">
<area shape="rect" coords="313,98,351,115" 

href="http://www.tutu6.com/destination/proviceDetail/402880e61a513c59011a514a41b5001a.html" 

title="辽宁">

<area shape="rect" coords="270,108,307,125" 

href="http://www.tutu6.com/destination/cityDetail/ba5390b81eb07211011eb3ddd78e0021.html" 

title="北京">

<area shape="rect" coords="292,124,329,142" 

href="http://www.tutu6.com/destination/cityDetail/ba5390b81eb07211011eb3defe920025.html" 

title="天津">
<area shape="rect" coords="210,105,262,124" 

href="http://www.tutu6.com/destination/proviceDetail/402880e61a513c59011a51bf927b00bf.html" 

title="内蒙古">
<area shape="rect" coords="254,124,290,142" 

href="http://www.tutu6.com/destination/proviceDetail/402880e51a513d28011a51a23a560075.html" 

title="河北">
<area shape="rect" coords="67,98,106,115" 

href="http://www.tutu6.com/destination/proviceDetail/402880e61a513c59011a518ba5b20086.html" 

title="新疆">
<area shape="rect" coords="131,147,169,164" 

href="http://www.tutu6.com/destination/proviceDetail/402880e61a513c59011a5155009f0031.html" 

title="青海">
<area shape="rect" coords="205,132,243,150" 

href="http://www.tutu6.com/destination/proviceDetail/402880e61a513c59011a51514b86002b.html" 

title="宁夏">
<area shape="rect" coords="245,146,282,161" 

href="http://www.tutu6.com/destination/proviceDetail/402880e61a513c59011a517e025d006d.html" 

title="山西">
<area shape="rect" coords="286,145,324,161" 

href="http://www.tutu6.com/destination/proviceDetail/402880e61a513c59011a5172fa25004f.html" 

title="山东">
<area shape="rect" coords="182,152,220,170" 

href="http://www.tutu6.com/destination/proviceDetail/402880e51a513d28011a515ee3240039.html" 

title="甘肃">
<area shape="rect" coords="304,163,342,181" 

href="http://www.tutu6.com/destination/proviceDetail/402880e51a512576011a512b60fb0004.html" 

title="江苏">
<area shape="rect" coords="82,184,120,201" 

href="http://www.tutu6.com/destination/proviceDetail/402880e61a513c59011a519afe490099.html" 

title="西藏">
<area shape="rect" coords="175,191,215,208" 

href="http://www.tutu6.com/destination/proviceDetail/402880e61a513c59011a516825ed0039.html" 

title="四川">
<area shape="rect" coords="256,166,296,183" 

href="http://www.tutu6.com/destination/proviceDetail/402880e51a513d28011a5188048f0061.html" 

title="河南">
<area shape="rect" coords="215,172,253,188" 

href="http://www.tutu6.com/destination/proviceDetail/402880e61a513c59011a517b6dd70063.html" 

title="陕西">
<area shape="rect" coords="289,185,327,201" 

href="http://www.tutu6.com/destination/proviceDetail/402880e51a513d28011a513ea52c0001.html" 

title="安徽">
<area shape="rect" coords="253,190,290,205" 

href="http://www.tutu6.com/destination/proviceDetail/402880e51a513d28011a51a5ef7e0081.html" 

title="湖北">
<area shape="rect" coords="216,200,250,215" 

href="http://www.tutu6.com/destination/cityDetail/ba5390b81eb07211011eb3dfa9060027.html" 

title="重庆">
<area shape="rect" coords="332,182,369,199" 

href="http://www.tutu6.com/destination/cityDetail/ba5390b81eb07211011eb3de5ac50023.html" 

title="上海">
<area shape="rect" coords="311,207,348,224" 

href="http://www.tutu6.com/destination/proviceDetail/402880e61a513c59011a513fb8af0002.html" 

title="浙江">
<area shape="rect" coords="272,209,305,223" 

href="http://www.tutu6.com/destination/proviceDetail/402880e61a513c59011a5146d3c7000e.html" 

title="江西">
<area shape="rect" coords="204,227,242,242" 

href="http://www.tutu6.com/destination/proviceDetail/402880e51a513d28011a5170b8050057.html" 

title="贵州">
<area shape="rect" coords="295,229,334,246" 

href="http://www.tutu6.com/destination/proviceDetail/402880e51a513d28011a515ab36c002f.html" 

title="福建">
<area shape="rect" coords="245,228,283,243" 

href="http://www.tutu6.com/destination/proviceDetail/402880e51a513d28011a52b4991b0094.html" 

title="湖南">
<area shape="rect" coords="167,244,208,263" 

href="http://www.tutu6.com/destination/proviceDetail/402880e61a513c59011a519fb18800a1.html" 

title="云南">
<area shape="rect" coords="331,247,369,262" 

href="http://www.tutu6.com/destination/proviceDetail/402880e61a513c59011a51892c07007b.html" 

title="台湾">
<area shape="rect" coords="258,247,298,265" 

href="http://www.tutu6.com/destination/proviceDetail/402880e51a513d28011a514878eb0019.html" 

title="广东">
<area shape="rect" coords="215,252,254,269" 

href="http://www.tutu6.com/destination/proviceDetail/402880e51a513d28011a5164c8e80048.html" 

title="广西">
<area shape="rect" coords="287,266,325,284" 

href="http://www.tutu6.com/destination/cityDetail/ba5390b81eb07211011eb3e85c65002f.html" 

title="香港">
<area shape="rect" coords="250,272,285,288" 

href="http://www.tutu6.com/destination/cityDetail/ba5390b81eb07211011eb3e8fdf00031.html" 

title="澳门">
<area shape="rect" coords="213,290,251,308" 

href="http://www.tutu6.com/destination/proviceDetail/402880e51a513d28011a52bb3f4100a3.html" 

title="海南">
</map>
<map name="Map2" id="Map2">
<area shape="rect" coords="264,118,302,136" 

href="http://www.tutu6.com/destination/continent/402880e51a512576011a512a33590002.html" 

title="亚洲">
<area shape="rect" coords="317,217,368,232" 

href="http://www.tutu6.com/destination/continent/402880e71c404e33011c40a9a86200aa.html" 

title="大洋洲">
<area shape="rect" coords="197,109,236,129" 

href="http://www.tutu6.com/destination/continent/402880e71c404e33011c40b981c100df.html" 

title="欧洲">
<area shape="rect" coords="189,168,228,186" 

href="http://www.tutu6.com/destination/continent/402880e71c404e33011c40a5544a009b.html" 

title="非洲">
<area shape="rect" coords="71,123,118,140" 

href="http://www.tutu6.com/destination/continent/402880e71c404e33011c408a4411003c.html" 

title="北美洲">
<area shape="rect" coords="109,196,160,214" href="http://www.tutu6.com/destination/continent/402880e71c404e33011c40b10fc400c1.html" title="南美洲">
</map>

   
 
<DIV class=flash-box>
<DIV id=SwitchPicContainer></DIV>
<DIV class=text>
<H3 id=SwitchTitle_title></H3>
<P id=SwitchTitle_stitle></P>
<DIV class=pager id=FlashSwitchPageNav></DIV>
<DIV class=bg></DIV></DIV></DIV>
<#if csjd??>
<SCRIPT>
	var PicData = new Array() ;
	var i = 0 ;
	<#list csjd as flash>
	<#if flash_has_next>
		PicData[i] = new Array() ;
		PicData[i]['img'] = '${base}/${flash.picUrl}' ;
		PicData[i]['title'] = '${flash.title}' ;
		PicData[i]['stitle'] = '${flash.subTitle}' ;
		PicData[i]['link'] = '${flash.url}' ;
		PicData[i]['blockid'] = '1566' ;
		i++
	<#else>
		PicData[i] = new Array() ;
		PicData[i]['img'] = '${base}/${flash.picUrl}' ;
		PicData[i]['title'] = '${flash.title}' ;
		PicData[i]['stitle'] = '${flash.subTitle}' ;
		PicData[i]['link'] = '${flash.url}' ;
		PicData[i]['blockid'] = '1566' ;
	</#if>
	</#list>
	FlashSwitch.init({Container:"SwitchPicContainer" ,
								Data:PicData,
								PageNav:"FlashSwitchPageNav",
								Titles:['title','stitle'],
								TimeIntval:2000
					}) ;
  </SCRIPT>
 </#if>    
</div>
<div class="section">
    <ul class="brand-popularize">
    <#list csjd_banner as banner>
    <#if banner_index<5><li><a href="${banner.url}" title="${banner.title}" target="${banner.target}"><img width="180" height="100" src="${base}/${banner.picUrl}" /></a></li></#if>
    </#list>
    </ul>
</div>
<div class="section">
    <div class="category">
        <div class="hd">
            <h3><span>热门景点精选类目推荐</span></h3>
        </div>
        <div class="bd">
            <ul class="class-list">
            	<#if csjd_jxtj??>
            		<#list csjd_jxtj as jxtj>
            		<#if jxtj_index<15>
            		<li>
                	<dl class="class-list-0${jxtj_index+1}">
                		<dt><a>${jxtj.st.name}</a></dt>
                		<dd>
                		<#if jxtj.scList??>
                			<#list jxtj.scList as scenery>
                			<#if scenery_index<12>
                				<a href="${base}/scenery/sceneryDetail/${scenery.sceneryId}.html"  title="${scenery.name}" target="_blank">
                				<#if scenery.name?length lte 4>
                				${scenery.name}
                				<#else>
                				${scenery.name[0..3]}
                				</#if>
                				</a>
                			</#if>
                			</#list>
                		</#if>
                		</dd>
                	</dl>
                	</li>
                	</#if>
            		</#list>
            	</#if>
            </ul>
        </div>
    </div>
</div>
<div class="field field-media">
<!--AdForward Begin:-->

<!--AdForward End-->
</div>
<div class="field tree-trunk-01">
    <h2>游山</h2>
    <ul class="ridge">
    <#list csjd_yst as yst>
    <li><a href="${yst.url}" title="${yst.title}" target="${yst.target}"><span>${yst.title}</span></a></li>
    </#list>
    </ul>
    <div class="stook">
        <div class="straw">
        <#list csjd_ysz as ysz>
        <#if ysz_index==0>
	    <a href="${ysz.url}" title="${ysz.title}" target="${ysz.target}" ><img width="220" height="350" src="${base}/${ysz.picUrl}"/></a>
	    </#if>
	    </#list>
        </div>
        <div class="paddy">
            <div class="hulled">
                <div class="millet">
                    <ul>
                        <#list csjd_yszs as yszs>
				        <#if yszs_index<4>
					    <li>
                        <span class="millet-img"><a href="${yszs.url}" title="${yszs.title}" target="${yszs.target}"><img width="100" height="100" src="${base}/${yszs.picUrl}"/></a></span>
                        <span class="millet-hx"><a href="${yszs.url}" title="${yszs.title}" target="${yszs.target}">${yszs.title}</a></span>
                        <span class="millet-price" target="${yszs.target}"></span>
                        </li>
					    </#if>
					    </#list>
                    </ul>
                </div>
                <div class="maise">
	                <#list csjd_yszzz as yszzz>
			        <#if yszzz_index==0>
			        <a href="${yszzz.url}" title="${yszzz.title}" target="${yszzz.target}"><img width="200" height="90" src="${base}/${yszzz.picUrl}"/></a>
				    </#if>
				    </#list>
                </div>
                <div class="rye">
                    <ul>
                    <#list csjd_yszzy as yszzy>
			        <#if yszzy_index<4>
				    <li><a href="${yszzy.url}" title="${yszzy.title}" target="${yszzy.target}"><span>${yszzy.title}</span></a></li>
				    </#if>
				    </#list>
                    </ul>
                </div>
                <div class="poppy">
                    <ul>
                        <#list csjd_yszx as yszx>
				        <#if yszx_index<3>
					    <li>
                        <span class="poppy-img" ><a href="${yszx.url}"  title="${yszx.title}" target="${yszx.target}"><img width="40" height="40" src="${base}/${yszx.picUrl}"/></a></span>
                        <span class="poppy-hx"><a href="${yszx.url}"  title="${yszx.title}" target="${yszx.target}">
                        	<#if yszx.title?length lte 12>
                        	${yszx.title}
                        	<#else>
                        	${yszx.title[0..11]}
                        	</#if>
                        </a></span>
                        </li>
					    </#if>
					    </#list>
                    </ul>
                </div>
            </div>
            <div class="husked">
                <div class="rice">
                    <h3>游山榜中榜</h3>
                    <ol>
					<#list csjd_ysys as ysys>
			        <#if ysys_index<10>
				    <li><span class="no">${ysys_index+1}.</span><a href="${ysys.url}" title="${ysys.title}" target="${ysys.target}">${ysys.title}</a></li>
				    </#if>
				    </#list>
                    </ol>
                </div>
                <div class="surf">
                    <ul>
                    <#list csjd_ysyx as ysyx>
			        <#if ysyx_index==0>
				    <li><a href="${ysyx.url}" title="${ysyx.title}" target="${ysyx.target}"><img width="250" height="60" src="${base}/${ysyx.picUrl}" /></a></li>
				    </#if>
				    </#list>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="field tree-trunk-04 tree-trunk-sport">
    <h2>玩水</h2>
    <ul class="ridge">
    <#list csjd_wst as wst>
    <li><a href="${wst.url}" title="${wst.title}" target="${wst.target}"><span>${wst.title}

</span></a></li>
    </#list>
    </ul>
    <div class="stook">
        <div class="straw">
        <#list csjd_wsz as wsz>
        <#if wsz_index==0>
	    <a href="${wsz.url}" title="${wsz.title}" target="${wsz.target}" ><img 

width="220" height="350" src="${base}/${wsz.picUrl}"/></a>
	    </#if>
	    </#list>
        </div>
        <div class="paddy">
            <div class="hulled">
                <div class="maise">
                <#list csjd_wszs as wszs>
		        <#if wszs_index==0>
		        <a href="${wszs.url}"  title="${wszs.title}" target="${wszs.target}"><img width="430" height="90" src="${base}/${wszs.picUrl}"/></a>
			    </#if>
			    </#list>
                </div>
                <div class="rye">
                    <ul>
                    <#list csjd_wszz as wszz>
			        <#if wszz_index<6>
				    <li><a href="${wszz.url}" title="${wszz.title}" target="${wszz.target}"><span>${wszz.title}</span></a></li>
				    </#if>
				    </#list>
                    </ul>
                </div>
                <div class="millet">
                    <ul>
                        <#list csjd_wszx as wszx>
				        <#if wszx_index<4>
					    <li>
                        <span class="millet-img"><a href="${wszx.url}"  title="${wszx.title}" target="${wszx.target}"><img width="100" height="100" src="${base}/${wszx.picUrl}"/></a></span>
                        <span class="millet-hx"><a href="${wszx.url}"  title="${wszx.title}" target="${wszx.target}">${wszx.title}</a></span>
                        <span class="millet-price"></span>
                        </li>
					    </#if>
					    </#list>
                    </ul>
                </div>
            </div>
            <div class="husked">
                <div class="rice">
                    <h3>最热玩水推荐榜</h3>
                    <ol>
                    <#list csjd_wsys as wsys>
			        <#if wsys_index<10>
			        	<#if wsys.subTitle=="" >
			        	<li><span class="no">${wsys_index+1}.</span><a href="${wsys.url}" title="${wsys.title}" target="${wsys.target}">${wsys.title}</a></li>
			        	<#else>
			        	<li><span class="no">${wsys_index+1}.</span><a href="${wsys.url}" title="${wsys.title}" target="${wsys.target}">${wsys.title}【${wsys.subTitle!''}】</a></li>
			        	</#if>
				    </#if>
				    </#list>
                    </ol>
                </div>
                <div class="surf">
                    <ul>
                    <#list csjd_wsyx as wsyx>
			        <#if wsyx_index==0>
			        <li><a href="${wsyx.url}" title="${wsyx.title}" target="${wsyx.target}"><img width="250" height="60" src="${base}/${wsyx.picUrl}" /></a></li>
				    </#if>
				    </#list> 
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="field field-media">
<!--AdForward Begin:-->

<!--AdForward End-->
</div>
<div class="field tree-trunk-04">
    <h2>海滨海岛</h2>
    <ul class="ridge">
    <#list csjd_hdt as hdt>
    <li><a href="${hdt.url}" title="${hdt.title}" target="${hdt.target}"><span>${hdt.title}</span></a></li>
    </#list>
    </ul>
    <div class="stook">
        <div class="straw">
        <#list csjd_hdz as hdz>
        <#if hdz_index==0>
	    <a href="${hdz.url}" title="${hdz.title}" target="${hdz.target}" ><img width="220" height="350" src="${base}/${hdz.picUrl}"/></a>
	    </#if>
	    </#list>
        </div>
        <div class="paddy">
            <div class="hulled">
                <div class="maise">
                <#list csjd_hdzs as hdzs>
		        <#if hdzs_index==0>
		        <a href="${hdzs.url}"  title="${hdzs.title}" target="${hdzs.target}"><img width="430" height="90" src="${base}/${hdzs.picUrl}"/></a>
			    </#if>
			    </#list>
                </div>
                <div class="rye">
                    <ul>
                    <#list csjd_hdzz as hdzz>
			        <#if hdzz_index<6>
				    <li><a href="${hdzz.url}" title="${hdzz.title}" target="${hdzz.target}"><span>${hdzz.title}</span></a></li>
				    </#if>
				    </#list>
                    </ul>
                </div>
                <div class="millet">
                    <ul>
                        <#list csjd_hdzx as hdzx>
				        <#if hdzx_index<4>
					    <li>
                        <span class="millet-img"><a href="${hdzx.url}"  title="${hdzx.title}" target="${hdzx.target}"><img width="100" height="100" src="${base}/${hdzx.picUrl}"/></a></span>
                        <span class="millet-hx"><a href="${hdzx.url}"  title="${hdzx.title}" target="${hdzx.target}">${hdzx.title}</a></span>
                        <span class="millet-price"></span>
                        </li>
					    </#if>
					    </#list>
                    </ul>
                </div>
            </div>
            <div class="husked">
                <div class="rice">
                    <h3>热门海滨海岛推荐</h3>
                    <ol>
                    <#list csjd_hdys as hdys>
			        <#if hdys_index<10>
				    <li><span class="no">${hdys_index+1}.</span><a href="${hdys.url}" title="${hdys.title}" target="${hdys.target}">${hdys.title}</a></li>
				    </#if>
				    </#list>
                    </ol>
                </div>
                <div class="surf">
                    <ul>
                    <#list csjd_hdyx as hdyx>
			        <#if hdyx_index==0>
			        <li><a href="${hdyx.url}" title="${hdyx.title}" target="${hdyx.target}"><img width="250" height="60" src="${base}/${hdyx.picUrl}" /></a></li>
				    </#if>
				    </#list>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="field tree-trunk-01 tree-trunk-shoes">
    <h2>名城古镇</h2>
    <ul class="ridge">
    <#list csjd_gzt as gzt>
    <li><a href="${gzt.url}" title="${gzt.title}" target="${gzt.target}"><span>${gzt.title}</span></a></li>
    </#list>
    </ul>
    <div class="stook">
        <div class="straw">
        <#list csjd_gzz as gzz>
        <#if gzz_index==0>
	    <a href="${gzz.url}" title="${gzz.title}" target="${gzz.target}" ><img width="220" height="350" src="${base}/${gzz.picUrl}"/></a>
	    </#if>
	    </#list>
        </div>
        <div class="paddy">
            <div class="hulled">
                <div class="millet">
                    <ul>
                      	<#list csjd_gzzs as gzzs>
				        <#if gzzs_index<4>
					    <li>
                        <span class="millet-img"><a href="${gzzs.url}" title="${gzzs.title}" target="${gzzs.target}"><img width="100" height="100" src="${base}/${gzzs.picUrl}"/></a></span>
                        <span class="millet-hx"><a href="${gzzs.url}" title="${gzzs.title}" target="${gzzs.target}">[${gzzs.subTitle!''}]${gzzs.title}</a></span>
                        <span class="millet-price" target="${gzzs.target}"></span>
                        </li>
					    </#if>
					    </#list>  
                    </ul>
                </div>
                <div class="maise">
                	<#list csjd_gzzzz as gzzzz>
			        <#if gzzzz_index==0>
			        <a href="${gzzzz.url}" title="${gzzzz.title}" target="${gzzzz.target}"><img width="200" height="90" src="${base}/${gzzzz.picUrl}"/></a>
				    </#if>
				    </#list>
                </div>
                <div class="rye">
                    <ul>
                    <#list csjd_gzzzy as gzzzy>
			        <#if gzzzy_index<4>
				    <li><a href="${gzzzy.url}" title="${gzzzy.title}" target="${gzzzy.target}"><span>[${gzzzy.subTitle!''}]${gzzzy.title}</span></a></li>
				    </#if>
				    </#list>
                    </ul>
                </div>
                <div class="poppy">
                    <ul>
                        <#list csjd_gzzx as gzzx>
				        <#if gzzx_index<4>
					    <li>
                        <span class="poppy-img" ><a href="${gzzx.url}"  title="${gzzx.title}" target="${gzzx.target}"><img width="40" height="40" src="${base}/${gzzx.picUrl}"/></a></span>
                        <span class="poppy-hx"><a href="${gzzx.url}"  title="${gzzx.title}" target="${gzzx.target}">${gzzx.title}</a></span>
                        <span class="poppy-price"></span>
                        </li>
					    </#if>
					    </#list> 
                    </ul>
                </div>
            </div>
            <div class="husked">
                <div class="rice">
                    <h3>古镇名城榜单</h3>
                    <ol>
                    <#list csjd_gzys as gzys>
			        <#if gzys_index<10>
				    <li><span class="no">${gzys_index+1}.</span><a href="${gzys.url}" title="${gzys.title}" target="${gzys.target}">[${gzys.subTitle!''}]${gzys.title}</a></li>
				    </#if>
				    </#list>
                    </ol>
                </div>
                <div class="surf">
                    <ul>
                    <#list csjd_gzyx as gzyx>
			        <#if gzyx_index==0>
				    <li><a href="${gzyx.url}" title="${gzyx.title}" target="${gzyx.target}"><img width="250" height="60" src="${base}/${gzyx.picUrl}" /></a></li>
				    </#if>
				    </#list>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="field tree-trunk-03">
    <h2>情人蜜月</h2>
    <ul class="ridge">
    <#list csjd_myt as myt>
    <li><a href="${myt.url}" title="${myt.title}" target="${myt.target}"><span>${myt.title}</span></a></li>
    </#list>
    </ul>
    <div class="stook">
        <div class="straw">
        <#list csjd_myz as myz>
        <#if myz_index==0>
	    <a href="${myz.url}" title="${myz.title}" target="${myz.target}" ><img width="220" height="250" src="${base}/${myz.picUrl}"/></a>
	    </#if>
	    </#list>
        </div>
        <div class="paddy">
            <div class="hulled">
                <div class="millet">
                    <ul>
                       <#list csjd_myzs as myzs>
				        <#if myzs_index<4>
					    <li>
                        <span class="millet-img"><a href="${myzs.url}" title="${myzs.title}" target="${myzs.target}"><img width="100" height="100" src="${base}/${myzs.picUrl}"/></a></span>
                        <span class="millet-hx"><a href="${myzs.url}" title="${myzs.title}" target="${myzs.target}">
							<#if myzs.title?length lte 8>
                        	${myzs.title}</br>
                        	<#elseif myzs.title?length lte 16>
                        	${myzs.title[0..7]}</br>
                        	${myzs.title[8..]}
                        	<#else>
                        	${myzs.title[0..7]}</br>
                        	${myzs.title[8..15]}
                        	</#if>
						</a></span>
                        <span class="millet-price" target="${myzs.target}"></span>
                        </li>
					    </#if>
					    </#list>  
                    </ul>
                </div>
                <div class="rye">
                    <ul>
                    	<#list csjd_myzx as myzx>
				        <#if myzx_index<6>
                        <li><a href="${myzx.url}" title="${myzx.title}" target="${myzx.target}"><span>『${myzx.subTitle!''}』<span class="h">${myzx.title}</span></span></a></li>
					    </#if>
					    </#list>
                    </ul>
                </div>
            </div>
            <div class="husked">
                <div class="rice">
                    <h3>蜜月圣地TOP 6</h3>
                    <ol>
                    <#list csjd_myys as myys>
			        <#if myys_index<6>
				    <li><span class="no">${myys_index+1}.</span><a href="${myys.url}" title="${myys.title}" target="${myys.target}">${myys.title}</a></li>
				    </#if>
				    </#list>
                    </ol>
                </div>
                <div class="surf">
                    <ul>
                    <#list csjd_myyx as myyx>
			        <#if myyx_index==0>
				    <li><a href="${myyx.url}" title="${myyx.title}" target="${myyx.target}"><img width="250" height="60" src="${base}/${myyx.picUrl}" /></a></li>
				    </#if>
				    </#list>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="field field-media">
<!--AdForward Begin:-->

<!--AdForward End-->
</div>
<div class="field tree-trunk-05">
    <h2>家庭亲子</h2>
    <ul class="ridge">
        <#list csjd_qzt as qzt>
	    <li><a href="${qzt.url}" title="${qzt.title}" 

target="${qzt.target}"><span>${qzt.title}</span></a></li>
	    </#list>
    </ul>
    <div class="stook">
        <div class="straw">
        <#list csjd_qzz as qzz>
        <#if qzz_index==0>
	    <a href="${qzz.url}" title="${qzz.title}" target="${qzz.target}" ><img width="220" height="250" src="${base}/${qzz.picUrl}"/></a>
	    </#if>
	    </#list>
        </div>
        <div class="paddy">
            <div class="hulled">
                <div class="millet">
                    <ul>
                      	<#list csjd_qzzs as qzzs>
				        <#if qzzs_index<4>
					    <li>
                        <span class="millet-img"><a href="${qzzs.url}" title="${qzzs.title}" target="${qzzs.target}"><img width="100" height="100" src="${base}/${qzzs.picUrl}"/></a></span>
                        <span class="millet-hx"><a href="${qzzs.url}" title="${qzzs.title}" target="${qzzs.target}">
							<#if qzzs.title?length lte 8>
                        	${qzzs.title}</br>
                        	<#elseif qzzs.title?length lte 16>
                        	${qzzs.title[0..7]}</br>
                        	${qzzs.title[8..]}
                        	<#else>
                        	${qzzs.title[0..7]}</br>
                        	${qzzs.title[8..15]}
                        	</#if>
						</a></span>
                        <span class="millet-price" target="${qzzs.target}"></span>
                        </li>
					    </#if>
					    </#list>  
                    </ul>
                </div>
                <div class="rye">
                    <ul>
                    	<#list csjd_qzzx as qzzx>
				        <#if qzzx_index<6>
                        <li><a href="${qzzx.url}" title="${qzzx.title}" target="${qzzx.target}"><span><font color="#FF0000"></font>${qzzx.title}</span></a></li>
					    </#if>
					    </#list>
                    </ul>
                </div>
            </div>
            <div class="husked">
                <div class="surf">
                    <ul>
                    <#list csjd_qzy as qzy>
			        <#if qzy_index<3>
				    <li><a href="${qzy.url}" title="${qzy.title}" target="${qzy.target}"><img width="250" height="60" src="${base}/${qzy.picUrl}" /></a></li>
				    </#if>
				    </#list>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="field tree-trunk-08">
    <h2>民族风情</h2>
    <ul class="ridge">
    	<#list csjd_fqt as fqt>
	    <li><a href="${fqt.url}" title="${fqt.title}" target="${fqt.target}"><span>${fqt.title}</span></a></li>
	    </#list>
    </ul>
    <div class="stook">
        <div class="straw">
        <#list csjd_fqz as fqz>
        <#if fqz_index==0>
	    <a href="${fqz.url}" title="${fqz.title}" target="${fqz.target}" ><img width="220" height="250" src="${base}/${fqz.picUrl}"/></a>
	    </#if>
	    </#list>
        </div>
        <div class="paddy">
            <div class="hulled">
                <div class="millet">
                    <ul>
                     	<#list csjd_fqzs as fqzs>
				        <#if fqzs_index<4>
					    <li>
                        <span class="millet-img"><a href="${fqzs.url}" title="${fqzs.title}" target="${fqzs.target}"><img width="100" height="100" src="${base}/${fqzs.picUrl}"/></a></span>
                        <span class="millet-hx"><a href="${fqzs.url}" title="${fqzs.title}" target="${fqzs.target}">
							<#if fqzs.title?length lte 8>
                        	${fqzs.title}</br>
                        	<#elseif fqzs.title?length lte 16>
                        	${fqzs.title[0..7]}</br>
                        	${fqzs.title[8..]}
                        	<#else>
                        	${fqzs.title[0..7]}</br>
                        	${fqzs.title[8..15]}
                        	</#if>
						</a></span>
                        <span class="millet-price" target="${fqzs.target}"></span>
                        </li>
					    </#if>
					    </#list>  
                    </ul>
                </div>
                <div class="rye">
                    <ul>
                    	<#list csjd_fqzx as fqzx>
				        <#if fqzx_index<6>
                        <li><a href="${fqzx.url}" title="${fqzx.title}" target="${fqzx.target}"><span><span class='h'>${fqzx.subTitle!''}</span>${fqzx.title}</span></a></li>
					    </#if>
					    </#list>
                    </ul>
                </div>
            </div>
            <div class="husked">
                <div class="rice">
                    <h3>最新风情觅踪信息</h3>
                    <ol>
                    <#list csjd_fqy as fqy>
			        <#if fqy_index<8>
				    <li><span class="no">${fqy_index+1}.</span><a href="${fqy.url}" title="${fqy.title}" target="${fqy.target}">${fqy.title}<span class="h">${fqy.subTitle!''}</span></a></li>
				    </#if>
				    </#list>
                    </ol>
                </div>							
            </div>
        </div>
    </div>
</div>
<div class="field tree-trunk-09">
    <h2>奢华体验</h2>
    <ul class="ridge">
    	<#list csjd_tyt as tyt>
	    <li><a href="${tyt.url}" title="${tyt.title}" target="${tyt.target}"><span>${tyt.title}</span></a></li>
	    </#list>
    </ul>
    <div class="stook">
        <div class="straw">
        <#list csjd_tyz as tyz>
        <#if tyz_index==0>
	    <a href="${tyz.url}" title="${tyz.title}" target="${tyz.target}" ><img width="220" height="250" src="${base}/${tyz.picUrl}"/></a>
	    </#if>
	    </#list>
        </div>
        <div class="paddy">
            <div class="hulled">
                <div class="millet">
                    <ul>
                     	<#list csjd_tyzs as tyzs>
				        <#if tyzs_index<4>
					    <li>
                        <span class="millet-img"><a href="${tyzs.url}" title="${tyzs.title}" target="${tyzs.target}"><img width="100" height="100" src="${base}/${tyzs.picUrl}"/></a></span>
                        <span class="millet-hx"><a href="${tyzs.url}" title="${tyzs.title}" target="${tyzs.target}">
							<#if tyzs.title?length lte 8>
                        	${tyzs.title}</br>
                        	<#elseif tyzs.title?length lte 16>
                        	${tyzs.title[0..7]}</br>
                        	${tyzs.title[8..]}
                        	<#else>
                        	${tyzs.title[0..7]}</br>
                        	${tyzs.title[8..15]}
                        	</#if>
						</a></span>
                        <span class="millet-price" target="${tyzs.target}"></span>
                        </li>
					    </#if>
					    </#list>    
                    </ul>
                </div>
                <div class="rye">
                    <ul>
                    	<#list csjd_tyzx as tyzx>
				        <#if tyzx_index<6>
                        <li><a href="${tyzx.url}" title="${tyzx.title}" target="${tyzx.target}"><span>${tyzx.title}</span></a></li>
					    </#if>
					    </#list>
                    </ul>
                </div>
            </div>
            <div class="husked">
                <div class="rice">
                    <h3>体验排行榜中榜</h3>
                    <ol>
                    <#list csjd_tyy as tyy>
			        <#if tyy_index<8>
				    <li><span class="no">${tyy_index+1}.</span><a href="${tyy.url}" title="${tyy.title}" target="${tyy.target}"><span class="h">${tyy.title}</span></a></li>
				    </#if>
				    </#list>
                    </ol>
                </div>							
            </div>
        </div>
    </div>
</div>

<div class="field tree-trunk-06">
    <h2>历史古迹</h2>
    <ul class="ridge">
    	<#list csjd_gjt as gjt>
	    <li><a href="${gjt.url}" title="${gjt.title}" target="${gjt.target}"><span>${gjt.title}</span></a></li>
	    </#list>
    </ul>
    <div class="stook">
        <div class="straw">
        <#list csjd_gjz as gjz>
        <#if gjz_index==0>
	    <a href="${gjz.url}" title="${gjz.title}" target="${gjz.target}" ><img src="${base}/${gjz.picUrl}"/></a>
	    </#if>
	    </#list>
        </div>
        <div class="paddy">
            <div class="hulled">
                <div class="millet">
                    <ul>
                      	<#list csjd_gjzs as gjzs>
				        <#if gjzs_index<4>
					    <li>
                        <span class="millet-img"><a href="${gjzs.url}" title="${gjzs.title}" target="${gjzs.target}"><img src="${base}/${gjzs.picUrl}"/></a></span>
                        <span class="millet-hx"><a href="${gjzs.url}" title="${gjzs.title}" target="${gjzs.target}">
							<#if gjzs.title?length lte 8>
                        	${gjzs.title}</br>
                        	<#elseif gjzs.title?length lte 16>
                        	${gjzs.title[0..7]}</br>
                        	${gjzs.title[8..]}
                        	<#else>
                        	${gjzs.title[0..7]}</br>
                        	${gjzs.title[8..15]}
                        	</#if>
						</a></span>
                        <span class="millet-price" target="${gjzs.target}"></span>
                        </li>
					    </#if>
					    </#list>  
                    </ul>
                </div>
                <div class="rye">
                    <ul>
                    	<#list csjd_gjzx as gjzx>
				        <#if gjzx_index<6>
                        <li><a href="${gjzx.url}" title="${gjzx.title}" target="${gjzx.target}"><span>${gjzx.title}<font color="#FF0000">${gjzx.subTitle!''}</font></span></a></li>
					    </#if>
					    </#list>
                    </ul>
                </div>
            </div>
            <div class="husked">
                <div class="surf">
                    <ul>
                    <#list csjd_gjy as gjy>
			        <#if gjy_index<3>
				    <li><a href="${gjy.url}" title="${gjy.title}" target="${gjy.target}"><img src="${base}/${gjy.picUrl}" /></a></li>
				    </#if>
				    </#list>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="field tree-trunk-07">
    <h2>奇幻探险</h2>
    <ul class="ridge">
    	<#list csjd_txt as txt>
	    <li><a href="${txt.url}" title="${txt.title}" target="${txt.target}"><span>${txt.title}</span></a></li>
	    </#list>
    </ul>
    <div class="stook">
        <div class="straw">
        <#list csjd_txz as txz>
        <#if txz_index==0>
	    <a href="${txz.url}" title="${txz.title}" target="${txz.target}" ><img src="${base}/${txz.picUrl}"/></a>
	    </#if>
	    </#list>
        </div>
        <div class="paddy">
            <div class="hulled">
                <div class="millet">
                    <ul>
                        <#list csjd_txzs as txzs>
				        <#if txzs_index<4>
					    <li>
                        <span class="millet-img"><a href="${txzs.url}" title="${txzs.title}" target="${txzs.target}"><img src="${base}/${txzs.picUrl}"/></a></span>
                        <span class="millet-hx"><a href="${txzs.url}" title="${txzs.title}" target="${txzs.target}">
                        	<#if txzs.title?length lte 8>
                        	${txzs.title}</br>
                        	<#elseif txzs.title?length lte 16>
                        	${txzs.title[0..7]}</br>
                        	${txzs.title[8..]}
                        	<#else>
                        	${txzs.title[0..7]}</br>
                        	${txzs.title[8..15]}
                        	</#if>
                        </a></span>
                        <span class="millet-price" target="${txzs.target}"></span>
                        </li>
					    </#if>
					    </#list>
                    </ul>
                </div>
                <div class="rye">
                    <ul>
                    	<#list csjd_txzx as txzx>
				        <#if txzx_index<6>
				        	<#if txzx.subTitle==''>
				        	<li><a href="${txzx.url}" title="${txzx.title}" target="${txzx.target}"><span>${txzx.title}</font></span></a></li>
				        	<#else>
				        	<li><a href="${txzx.url}" title="${txzx.title}" target="${txzx.target}"><span>【${txzx.subTitle!''}】${txzx.title}</font></span></a></li>
				        	</#if>                        
					    </#if>
					    </#list>
                    </ul>
                </div>
            </div>
            <div class="husked">
                <div class="surf">
                    <ul>
                    <#list csjd_txy as txy>
			        <#if txy_index<3>
				    <li><a href="${txy.url}" title="${txy.title}" target="${txy.target}"><img width="250" height="60" src="${base}/${txy.picUrl}" /></a></li>
				    </#if>
				    </#list>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>




</div>
		</div>
					
	


   	</div>


	<!-- footer -->
	<@footer />
	<!-- //footer -->
</div>


</body></html>