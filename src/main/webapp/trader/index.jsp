<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script>
window.onerror = new Function("return(false);");
</script>
<html>
  <head>    
    <title>门户管理系统</title>  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="旅游分销平台">
	
	<link rel="stylesheet" type="text/css" href="../resources/ext/resources/css/ext-all.css" />
	<link rel="stylesheet" type="text/css" href="../resources/css/jobber/tasks.css"  />
	<link rel="stylesheet" type="text/css" href="../resources/css/jobber/yeoougrid.css"  />
	<link rel="stylesheet" type="text/css" href="../resources/css/jobber/msg.css"  />	
	
 	<script type="text/javascript" src="../scripts/share/ext-base.js"></script>
    <script type="text/javascript" src="../scripts/share/ext-all.js"></script>
    <script type="text/javascript" src="../scripts/util/TabCloseMenu.js"></script>
    <script type="text/javascript" src="../scripts/util/Portal.js"></script>
    <script type="text/javascript" src="../scripts/util/JsonUtil.js"></script>
    <script type="text/javascript" src="../scripts/util/ExtendFormat.js"></script>
    <script type="text/javascript" src="../scripts/util/Format.js"></script>
    <script type="text/javascript" src="../scripts/share/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="scripts/main.js"></script>
	<script type="text/javascript" src="scripts/managerInf/managerInf.js"></script>
	<script type="text/javascript" src="scripts/company/company.js"></script>
	
	<script language="JavaScript">
//window.onload = getMsg;
window.onresize = resizeDiv;
window.onerror = function(){}
//短信提示使用(asilas添加)
var divTop,divLeft,divWidth,divHeight,docHeight,docWidth,objTimer,i = 0;
function getMsg()
{
	try{
	divTop = parseInt(document.getElementById("msgDiv").style.top,10)
	divLeft = parseInt(document.getElementById("msgDiv").style.left,10)
	divHeight = parseInt(document.getElementById("msgDiv").offsetHeight,10)
	divWidth = parseInt(document.getElementById("msgDiv").offsetWidth,10)
	docWidth = document.body.clientWidth;
	docHeight = document.body.clientHeight;
	document.getElementById("msgDiv").style.top = parseInt(document.body.scrollTop,10) + docHeight + 10;//  divHeight
	document.getElementById("msgDiv").style.left = parseInt(document.body.scrollLeft,10) + docWidth - divWidth
	document.getElementById("msgDiv").style.visibility="visible"
	objTimer = window.setInterval("moveDiv()",10)
	}
	catch(e){}
}

function resizeDiv()
{
	i+=1;
	if(i===1000) {closeDiv();i=0}	
	try{
	divHeight = parseInt(document.getElementById("msgDiv").offsetHeight,10)
	divWidth = parseInt(document.getElementById("msgDiv").offsetWidth,10)
	docWidth = document.body.clientWidth;
	docHeight = document.body.clientHeight;
	document.getElementById("msgDiv").style.top = docHeight - divHeight + parseInt(document.body.scrollTop,10)
	document.getElementById("msgDiv").style.left = docWidth - divWidth + parseInt(document.body.scrollLeft,10)
	}
	catch(e){}
}

function moveDiv()
{
	try
	{
	if(parseInt(document.getElementById("msgDiv").style.top,10) <= (docHeight - divHeight + parseInt(document.body.scrollTop,10)))
	{
	window.clearInterval(objTimer)
	objTimer = window.setInterval("resizeDiv()",1)
	}
	divTop = parseInt(document.getElementById("msgDiv").style.top,10)
	document.getElementById("msgDiv").style.top = divTop - 1
	}
	catch(e){}
}
function closeDiv()
{
	document.getElementById('msgDiv').style.visibility='hidden';
	document.title='斯派德科技欢迎您！';
	if(objTimer) window.clearInterval(objTimer);
	window.clearInterval(turn2);
}
</script>
  </head>
<body>
<!-- EXAMPLES -->

	<ul id="config" class="x-hidden">
		<li id="con-all">
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-show-active"/>
			<a id="comBtn" href="#" >公司信息设置</a>
		</li>
 		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-show-active"/>
			<a id="siteBtn" href="#">网站信息设置</a>
		</li>
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-show-active"/>
			<a id="ipRouterBtn" href="#">IP转向设置</a>
		</li>  
	</ul>
	<ul id="areaManage" class="x-hidden">
		<li >
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-edit"/>
			<a id="areaBtn" href="#">地域系统</a>
		</li>
	  	<li >
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-edit"/>
			<a id="continentBtn" href="#">大洲信息</a>
		</li>
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-edit"/>
			<a id="nationBtn" href="#">国家信息</a>
		</li>
		<li >
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-edit"/>
			<a id="provinceBtn" href="#">省份信息</a>
		</li>
		<li >
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-edit"/>
			<a id="cityBtn" href="#">城市信息</a>
		</li>
	</ul> 
 	<ul id="regionManage" class="x-hidden">
<!--  	<li >
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-show-all"/>
			<a id="airtick" href="#">机票信息</a>
		</li>-->
		<li >
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-show-all"/>
			<a id="regionBtn" href="#">类别信息</a>
		</li>
<!-- 		<li >
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-show-all"/>
			<a id="traintick" href="#">火车信息</a>
		</li>
		<li >
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-show-all"/>
			<a id="shiptick" href="#">轮船信息</a>
		</li>-->
	</ul>
	<ul id="sceneryManage" class="x-hidden">
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-by-date"/>
			<a id="keyBtn" href="#">关键词信息</a>
		</li>
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-by-date"/>
			<a id="sceneryTypeBtn" href="#">景点类型信息</a>
		</li>
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-by-date"/>
			<a id="sceneryBtn" href="#">景点信息</a>
		</li>
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-by-date"/>
			<a id="sceneryRegionBtn" href="#">景点类别关联</a>
		</li>
	</ul>
<!-- 	<ul id="accountmanage" class="x-hidden">
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-show-complete"/>
			<a id="accounttick" href="#">账单信息</a>
		</li>		
	</ul> -->
	<ul id="visaManage" class="x-hidden">
		<li >
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-by-category"/>
			<a id="visaBtn" href="#">签证信息</a>
		</li>
	</ul> 
 	<ul id="shopManage" class="x-hidden">
		<li >
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-active"/>
			<a id="shopBtn" href="#">门市信息</a>
		</li>
 		<li >
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-active"/>
			<a id="agencyBtn" href="#" >旅行社信息</a>
		</li> 
	</ul>
 	<ul id="ticketManage" class="x-hidden">
  		<li >
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-show-all"/>
			<a id="planeBtn" href="#">机票信息</a>
		</li>
		<li >
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-show-all"/>
			<a id="busBtn" href="#">汽车信息</a>
		</li>
 		<li >
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-show-all"/>
			<a id="trainBtn" href="#">火车信息</a>
		</li>
		<li >
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-show-all"/>
			<a id="shipBtn" href="#">轮船信息</a>
		</li>
	</ul>
	<ul id="newsManage" class="x-hidden">
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-edit"/>
			<a id="newsTypeBtn" href="#">新闻类别信息</a>
		</li>
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-edit"/>
			<a id="newsBtn" href="#">新闻信息</a>
		</li>
	</ul>
	<ul id="tripManage" class="x-hidden">
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-by-date"/>
			<a id="jobberLineBtn" href="#">批发商行程</a>
		</li>
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-by-date"/>
			<a id="lineBtn" href="#">行程信息</a>
		</li>
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-by-date"/>
			<a id="assembleBtn" href="#">出发集合地</a>
		</li>
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-by-date"/>
			<a id="jobberTeamBtn" href="#">批发商开班</a>
		</li>
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-by-date"/>
			<a id="teamBtn" href="#">开班信息</a>
		</li>
	</ul> 
	<ul id="orderMange" class="x-hidden">
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-no-group"/>
			<a id="orderBtn" href="#">订单管理</a>
		</li>		 
	</ul>
	<ul id="commentMange" class="x-hidden">
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-no-group"/>
			<a id="commentBtn" href="#">留言管理</a>
		</li>		 
	</ul>
	<ul id="managerMange" class="x-hidden">
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-no-group"/>
			<a id="managerBtn" href="#">管理员管理</a>
		</li>		 
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-no-group"/>
			<a id="managerInfBtn" href="#">用户信息修改</a>
		</li>		
	</ul>
	<ul id="flashMange" class="x-hidden">
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-no-group"/>
			<a id="flashTypeBtn" href="#">Flash类别表</a>
		</li>		 
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-no-group"/>
			<a id="flashBtn" href="#">Flash表</a>
		</li>		
	</ul>
	<ul id="pageMange" class="x-hidden">
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-no-group"/>
			<a id="pageBtn" href="#">总页面表</a>
		</li>		 
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-no-group"/>
			<a id="pageRegionBtn" href="#">页面类别表</a>
		</li>
		<li>
			<img src="../resources/ext/resources/images/yeoou/s.gif" class="icon-no-group"/>
			<a id="pageContentBtn" href="#">页面内容表</a>
		</li>		
	</ul> 
	<!-- 
  	<div id="north" style="height:5px">
  	</div>
  	-->
  	<div id="center1">
  	</div>
  	<div id="south">
  	</div>
  	<div id="msgDiv" class="msgDiv">
		<table class="msgTab" cellSpacing="0" cellPadding="0" width="100%" bgColor="#cfdef4" border="0">
		  <tr>
		    <td class="msgTd1" width="30" height="24">&nbsp;</td>
		    <td class="msgTd2" vAlign="center"width="100%">订单消息提示：</td>
		    <td class="msgTd3" vAlign="center" align="right" width="19">
		    	<SPAN title="关闭" style="FONT-WEIGHT: bold; FONT-SIZE: 12px; CURSOR:pointer; COLOR: red; MARGIN-RIGHT: 4px" onclick="closeDiv()">× </SPAN>
		        <!-- <IMG title=关闭 style="CURSOR: hand" onclick=closeDiv() hspace=3 src="msgClose.jpg"> -->
		    </td>
		  </tr>
		  <tr>
		    <td class="msgTd4" colSpan="3" height="90">
		    	<div class="innerDiv">
		        	您有<FONT id="undoNum" color=#ff0000>0 </FONT>条未处理订单消息 <BR>
		            其中<FONT id="newNum" color=#ff0000>0 </FONT>条今日新订单消息 <BR><BR>
		            <div class="innerDiv2" align="center">
		            	<A href="javascript:updateTab('tabOrder',Ext.getDom('orderBtn').innerHTML,'order/default.jsp','');closeDiv();"><FONT color="#ff0000">点击查看订单 </FONT></A>
		            </div>
		        </div>
		    </td>    
		  </tr>
		</table>
	</div>
 </body>
</html>
