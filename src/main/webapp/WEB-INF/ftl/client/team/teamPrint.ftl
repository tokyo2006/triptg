<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>



<script type="text/javascript" language="javascript1.2">

function Hidebz()
        {
             var p=document.getElementById("xc");
            var chk=document.getElementById("chkPrice");
            if(chk.checked)
                p.style.display="none";
            else
                p.style.display="";
        }
</script>
<META content="${base}/${webSite.desc}" name=description>
<META content="${base}/${webSite.keywords}" name=keywords>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${team.teamName}打印————要旅游先兔游</title>
<link href="${base}/public/20081205/css/teamPrint.css" type="text/css" rel="stylesheet">

<style>
.h1 {
	FONT-WEIGHT: bold; FONT-SIZE: 18px
}
.h2 {
	FONT-SIZE: 14px
}
.line {
	LINE-HEIGHT: 150%
}
UNKNOWN {
	COLOR: #000000; TEXT-DECORATION: none
}
UNKNOWN {
	COLOR: #ffffff; BACKGROUND-COLOR: #0000ff; TEXT-DECORATION: none
}
UNKNOWN {
	COLOR: #000000; TEXT-DECORATION: none
}
</style>


</head><body style="padding: 0px; margin-top: 5px;">

<table align="center" border="0" cellpadding="2" cellspacing="2" width="90%">
  <tbody>
  <tr>
    <td align="left" valign="center" width="260px"><img alt="兔游旅行网" src="${base}/${webSite.logo}"></td>
    <td class="h2" align="right" valign="center"><a title="打印本页" onclick="javascript:window.print();return false;" href="#">
	  [<font style="font-weight: bold; color: rgb(255, 0, 0);">打印本页</font>]</a>&nbsp;<input id="chkPrice" onclick="Hidebz();" type="checkbox"><font color="#ff6600">只打印行程</font></td>
   </tr>
	<tr>
    <td colspan="2" height="8">&nbsp;</td>
  </tr>
  <tr>
    <td class="h1 line" colspan="2" align="left" valign="top">${team.teamName}</td>
  </tr></tbody></table>
  
<table align="center" border="0" cellpadding="2" cellspacing="2" width="90%">
  <tbody>
  <tr>
    <td class="h2" valign="center" width="20%"><img alt="" src="${base}/public/20081205/image/biao.gif" width="20" height="20">&nbsp;线路简介</td>
    <td class="h2" align="right">${team.area.name}出发</td></tr>
  <tr>
    <td colspan="2">
      <div style="border-top: 1px dotted rgb(204, 204, 204);"><img src="" border="0" width="1" height="1"></div></td></tr>
  <tr>
    <td colspan="2">
      <table border="0" cellpadding="1" cellspacing="1" width="100%">
        <tbody>
        <tr>
          <td>旅行社：苏州青年旅行社</td></tr>
        <tr>
          <td>价　格：${team.doorDisPrice}&nbsp;元起</td></tr>
        <tr>
          <td>日　期：<#if team.correct!=0><#list dateList as teamDate>${teamDate}<#if teamDate_index gt 9>&nbsp;...&nbsp;<#break></#if><#if teamDate_has_next>&nbsp;，&nbsp;</#if></#list><#else>请来电咨询</#if></td></tr>
        <tr>
          <td>&nbsp;</td></tr>
        <tr>
          <td>
            <div style="border-top: 1px dotted rgb(204, 204, 204);"><img src="" border="0" width="1" height="1"></div></td></tr>
        <tr>
          <td align="right">预订电话： ${webSite.zjphone}  </td></tr>
        <tr>
          <td align="right">工作时间： 平时 9:00 - 20:00 双休日 9:00 - 18:00 
      </td></tr></tbody></table></td></tr></tbody></table>
	  
<table align="center" border="0" cellpadding="2" cellspacing="2" width="90%">
  <tbody>
  <tr>
    <td class="h2" valign="center"><img alt="" src="${base}/public/20081205/image/biao.gif" width="20" height="20">&nbsp;行程介绍</td></tr>
  <tr>
    <td>
      <div style="border-top: 1px dotted rgb(204, 204, 204);"><img src="" border="0" width="1" height="1"></div></td></tr>
  <tr>
    <td valign="top">
      
		<#list returnLineContent as content>
		
	
			<table margin-top="10px" align="center" border="0" cellpadding="2" cellspacing="2" width="99%">
        <tbody> <tr bgcolor="#ecf3f9">
          <td width="12%"><font style="font-weight: bold; color: rgb(255, 123, 0);">&nbsp;第&nbsp;${content_index+1}&nbsp;天</font></td>
          <td align="right"><font color="#3366ff">${content.title!""}</font></td></tr>
        <tr bgcolor="#f6f6f6">
          <td></td>
          <td>${content.trips}<br />
          餐饮：${content.eating}<br />
          住宿：${content.living}</td></tr></tbody></table>
			</#list>
	
			
	
	  
	  
	  
	  </td>
  </tr>
  <tr><td style="COLOR: #999;text-align:right">以上行程、交通仅供参考，最终确认以所签协议为准。</td></tr>
  </tbody></table>
  
  
  <table id="xc" align="center" border="0" cellpadding="2" cellspacing="2" width="90%">
  <tbody>

  <tr>
    <td class="h2" valign="center"><img alt="" src="${base}/public/20081205/image/biao.gif" width="20" height="20">&nbsp;接待标准</td></tr>
  <tr>
    <td>
      <div style="border-top: 1px dotted rgb(204, 204, 204);"><img src="" border="0" width="1" height="1"></div></td></tr>
  <tr>
    <td valign="top">
      
		  <#if line.feature??>
		  <#if line.feature!="">
			<table margin-top="10px" align="center" border="0" cellpadding="2" cellspacing="2" width="99%">
        <tbody> 
		
		<tr bgcolor="#ecf3f9">
          <td valign="top" width="12%"><font style="font-weight: bold; color: rgb(255, 123, 0);">&nbsp;行程特色</font></td>
          <td><font color="#3366ff">${line.feature}</font></td></tr>
     </tbody></table>
     </#if>
     </#if>
	<#if line.feeClude??>
	<#if line.feeClude!="">
			<table margin-top="10px" align="center" border="0" cellpadding="2" cellspacing="2" width="99%">
        <tbody> 
		
		<tr bgcolor="#ecf3f9">
          <td valign="top" width="12%"><font style="font-weight: bold; color: rgb(255, 123, 0);">&nbsp;报价包含</font></td>
          <td><font color="#3366ff">${line.feeClude}</font></td></tr>
     </tbody></table>
     </#if>
	</#if>
	
	<#if line.feeUnclude??>
	<#if line.feeUnclude!="">
			<table margin-top="10px" align="center" border="0" cellpadding="2" cellspacing="2" width="99%">
        <tbody> 
		
		<tr bgcolor="#ecf3f9">
          <td valign="top" width="12%"><font style="font-weight: bold; color: rgb(255, 123, 0);">&nbsp;报价不包含</font></td>
          <td><font color="#3366ff">${line.feeUnclude}</font></td></tr>
     </tbody></table>
	</#if>
	</#if>
	<#if line.purchase??>
	<#if line.purchase!="">
			<table margin-top="10px" align="center" border="0" cellpadding="2" cellspacing="2" width="99%">
        <tbody> 
		
		<tr bgcolor="#ecf3f9">
          <td valign="top" width="12%"><font style="font-weight: bold; color: rgb(255, 123, 0);">&nbsp;购物活动</font></td>
          <td><font color="#3366ff">${line.purchase}</font></td></tr>
     </tbody></table>
	</#if>
	</#if>
	<#if line.selfBuy??>
	<#if line.selfBuy!="">	
		<table margin-top="10px" align="center" border="0" cellpadding="2" cellspacing="2" width="99%">
        <tbody> 
		
		<tr bgcolor="#ecf3f9">
          <td valign="top" width="12%"><font style="font-weight: bold; color: rgb(255, 123, 0);">&nbsp;自费项目</font></td>
          <td><font color="#3366ff">${line.selfBuy}</font></td></tr>
     </tbody></table>
	</#if>
	</#if>
	<#if line.safe??>	
	<#if line.safe!="">	
		<table margin-top="10px" align="center" border="0" cellpadding="2" cellspacing="2" width="99%">
        <tbody> 
		
		<tr bgcolor="#ecf3f9">
          <td valign="top" width="12%"><font style="font-weight: bold; color: rgb(255, 123, 0);">&nbsp;安全守则</font></td>
          <td><font color="#3366ff">${line.safe}</font></td></tr>
     </tbody></table>
	</#if>
	</#if>
	<#if line.remark??>	
	<#if line.remark!="">
		<table margin-top="10px" align="center" border="0" cellpadding="2" cellspacing="2" width="99%">
        <tbody> 
		
		<tr bgcolor="#ecf3f9">
          <td valign="top" width="12%"><font style="font-weight: bold; color: rgb(255, 123, 0);">&nbsp;旅游备注</font></td>
          <td><font color="#3366ff">${line.remark}</font></td></tr>
     </tbody></table>
     </#if>
	</#if>
	</td>
  </tr></tbody></table>
</body></html>