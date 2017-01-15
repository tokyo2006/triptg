<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@page import="com.yeoou.tour.model.News"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>content template</title>
	
	</head>
<%
	News news = new News();
	news = (News) request.getAttribute("news");
	String content = news.getContent();
	String title = news.getTitle();
	if(content == null||content.equals("")) content = "无信息";
 %>
<body>	
	<form action="updateContent.shtml" method="POST">
	<table border="0" align="center">
  <tr>
    <td width="100"><s:hidden name="newsId" value="${newsId}"></s:hidden></td>
  </tr>
  <tr>
  	<td style="text-align:center;"><%=title%>----新闻内容：</td>
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