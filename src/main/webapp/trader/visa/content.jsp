<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@page import="com.yeoou.tour.model.Visa"%>

<%
	Visa visa = new Visa();
	visa = (Visa) request.getAttribute("visaContent");
	String content = visa.getZiliao();
	String name = visa.getName();
	if(content == null||content.equals("")) content = "无信息";
 %>
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

<body>	
	<form action="updVisa2.shtml" method="POST">
	<table border="0" align="center">
	  <tr>
	    <td width="100"><s:hidden name="visaId" value="${visaId}"></s:hidden></td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	  	<td style="text-align:center;"><%=name%>----签证材料：</td>
	  </tr>
	  <tr>
	    <td><FCK:editor instanceName="ziliao" value="<%=content%>" height="600" width="700"></FCK:editor></td>
	  </tr>
	  <tr>
	    <td style="text-align:center;"><button type="submit" id="Btn1" name="formSubmit" value="true" >确认</button></td>
	  </tr>
	</table>
	</form>
</body>
</html>