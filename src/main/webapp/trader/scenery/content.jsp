<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@page import="com.yeoou.tour.model.Scenery"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>content template</title>
	
	</head>
<%
	Scenery scenery = new Scenery();
	scenery = (Scenery) request.getAttribute("scenery");
	String content = scenery.getContent();
	String name = scenery.getName();
	if(content == null||content.equals("")) content = "无信息";
 %>
<body>	
	<form action="updateContent.shtml" method="POST">
	<table border="0" align="center">
  <tr>
    <td width="100"><s:hidden name="sceneryId" value="${sceneryId}"></s:hidden></td>
  </tr>
  <tr>
  	<td style="text-align:center;"><%=name%>景点详细介绍：</td>
  </tr>
  <tr>
    <td><FCK:editor instanceName="content" value="<%=content%>" height="600" width="800"></FCK:editor></td>
  </tr>
  <tr>
    <td style="text-align:center;"><button type="submit" id="Btn1" name="formSubmit" value="true" >确认</button></td>
  </tr>
</table>
	</form>
</body>
</html>