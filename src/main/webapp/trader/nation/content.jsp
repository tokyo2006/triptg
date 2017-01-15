<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@page import="com.yeoou.tour.model.Nation"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Content template</title>
	
	<link rel="stylesheet" type="text/css" href="../../resources/ext/resources/css/ext-all.css" />

    <script type="text/javascript" src="../../scripts/share/ext-base.js"></script>
    <script type="text/javascript" src="../../scripts/share/ext-all.js"></script>
	<script type="text/javascript" src="../../scripts/util/JsonUtil.js"></script>
    <script type="text/javascript" src="../../scripts/share/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../../scripts/util/ExtendFormat.js"></script>
    <script type="text/javascript" src="../scripts/continent/fileUp.js"></script>
	
</head>
<%
	Nation nation = new Nation();
	nation = (Nation) request.getAttribute("nation");
	String content = nation.getContent();
	if(content == null||content.equals("")) content = "无信息";
 %>
<body>	
	<form action="updateNation.shtml" method="POST">
	<table border="0">
  <tr>
    <td width="100"><s:hidden name="nationId" value="${nationId}"></s:hidden></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>是否热点:</td>
    <td><s:checkbox label= "是否热点"  name="isTopStr"  value="%{nation.isTop}" /></td>
  </tr>
  <tr>
    <td style="vertical-align:top">概况:</td>
    <td><s:textarea rows="15" cols="83" name="gloze" value="%{nation.gloze}" label="行政常识"></s:textarea></td>
  </tr>
   <tr>
    <td style="vertical-align:top">地图:</td>
    <td>
    	<div id="mapDiv"></div>
    	<div id="mapUrlDiv" style="visibility:hidden"></div>
    	<div><button type="button" id="Btn2" name="fileUpBtn" onclick="javascript:fileUp(0);" value="true" >上传图片</button> </div>
    </td>
  </tr>
  <tr>
    <td style="vertical-align:top">热点介绍:</td>
    <td><s:textarea rows="15" cols="83" name="mapTopic" value="%{nation.mapTopic}" label="热点介绍"></s:textarea></td>
  </tr>
 <!--  <tr>
    <td style="vertical-align:top">简介:</td>
    <td><s:textarea rows="15" cols="83" name="synopsis" value="%{nation.synopsis}" label="简介"></s:textarea></td>
  </tr>-->
  <tr>
    <td style="vertical-align:top">详细介绍：</td>
    <td><FCK:editor instanceName="content" value="<%=content%>" height="600" width="700"></FCK:editor></td>
  </tr>
  <tr>
    <td></td>
    <td style="text-align:center;"><button type="submit" id="Btn1" name="formSubmit" value="true" >确认</button></td>
  </tr>
</table>
	</form>
	<script type="text/javascript">
 		mapUrl="<%=nation.getMapUrl()%>";
 		if(mapUrl==="null"){
 			mapUrl='';
 		}	
 		initMap(mapUrl);
	</script>
</body>
</html>