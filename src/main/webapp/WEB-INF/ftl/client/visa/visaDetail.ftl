<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<META content="${base}/${webSite.desc}" name=description>
<META content="${base}/${webSite.keywords}" name=keywords>
<title>${visa.name}——要旅游 先兔游</title>
<link href="${base}/public/20081205/css/bc4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/bbp-panel4.css" rel="stylesheet" type="text/css" media="screen">
<link href="${base}/public/20081205/css/dcontent.css" rel="stylesheet" type="text/css" media="screen">

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

		<#if visas??>
		<!-- channel nav -->
				<!-- {{start:portal nav -->
		<div id="portal-nav" class="lf-mod4">
		<span class="x1"><span class="x1a"></span></span>
		   <div class="mod-content">
			<div class="bd">
			<#list visas as visaCity>
				<ul class="cls">
					<li>${area.name}签证(${visaCity.areaName}领事馆)：</li>
					
					<#list visaCity.visas as visaClass>
					<#if visaClass.visaId==visa.visaId>
					<li><a href="${base}/visa/visaDetail.shtml?visaId=${visaClass.visaId}"><font style="color:#f97e06;font-weight:bold" >${visaClass.name}</font></a></li>
					<#else>
					<li><a href="${base}/visa/visaDetail.shtml?visaId=${visaClass.visaId}">${visaClass.name}</a></li>
					</#if>
					</#list>
				</ul>
			</#list>
			</div>
		   </div>
		<span class="x2"><span class="x2a"></span></span>
		</div>
		<!-- }}end:portal nav -->
		<!-- //channel nav -->
		</#if>
		<!-- 主要内容 -->
		<div class="protal-column-t1 column-list">
			<div class="main">
				<div id="article">
				<!-- 正文 -->
				<div class="path">位置： 
				<a href="#">兔游</a> &gt; 
				<a href="#">签证</a> &gt; 
				${visa.name}
				
				</div>
				
				<div class="content">
					
						<div class="richtext">
			<p>			
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="15%" valign="top">签证类型：</td>
    <td><font color="red">${visa.name}</font></td>
  </tr>
  <tr>
    <td width="15%" valign="top">所需材料：</td>
    <td>${visa.ziliao}</td>
  </tr>
  <tr>
    <td valign="top">是否面试：</td>
    <td>${visa.interview}</td>
  </tr>
  <tr>
    <td valign="top">办证周期：</td>
    <td>${visa.cycle}</td>
  </tr>
  <tr>
    <td valign="top">签证期限：</td>
    <td>${visa.term}</td>
  </tr>
  <tr>
    <td valign="top">签证费用：</td>
    <td><font color="green">${visa.pay}元</font></td>
  </tr>
  <tr>
    <td valign="top">注意事项：</td>
    <td>${visa.advert}</td>
  </tr>
  <tr>
    <td valign="top">签证说明：</td>
    <td>
    1、“预计工作日”为使馆签发签证时，正常情况下的处理时间；若遇特殊原因如假期、使馆内部人员调整、签证打印机或传真机故障等，则有可能会产生延迟出签的情况；对申请人根据签证预计日期提示，而进行的后续旅程安排所造成的可能经济损失，本公司不承担任何责任。<br />
2、有关签证资料上公布的签证有效期和停留天数，仅做参考而非任何法定承诺，一切均以签证官签发的签证内容及移民局的入境许可为唯一依据。 <br />
3、部分国家的商务签证如客人自备邀请函，价格另议。中东地区及非洲地区的签证价格及申请材料变动较大，详情请来电咨询。 <br />
4、外国护照申请签证，价格另议。 <br />
5、您所申请的签证是否可以成功，取决于相关国家使领馆签证官的直接审核结果；若最终发生拒签状况，申请人应自然接受此结果，同时放弃追究本公司任何责任的权利，本公司将不予退还签证费。</td>
  </tr>
</table>
	</p>					
						</div>

				</div>
				<!-- //正文 -->
				</div>



								
				<!-- 热卖单品 -->
				<@teamfooter />
				<!-- //热卖单品 -->
				
			</div>
			
			
			<div class="side">
			<@slide />	
				
			</div>
		<!-- //主要内容 -->

	</div>


	<!-- footer -->
	<@footer />
	<!-- //footer -->
</div>


</body></html>