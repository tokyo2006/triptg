<%@ page language="java"  pageEncoding="UTF-8"%>
<%
	String areaId = request.getParameter("params");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Area grid default</title>
	<script type="text/javascript">
 		areaId="<%=areaId%>";
	</script>

    <link rel="stylesheet" type="text/css" href="../../resources/ext/resources/css/ext-all.css" />
	<link rel="stylesheet" type="text/css" href="../../resources/css/jobber/line.css" />
	<link rel="stylesheet" type="text/css" href="../../resources/css/share/comBoxFlag.css" />

    <script type="text/javascript" src="../../scripts/share/ext-base.js"></script>
    <script type="text/javascript" src="../../scripts/share/ext-all.js"></script>
	<script type="text/javascript" src="../../scripts/util/JsonUtil.js"></script>
    <script type="text/javascript" src="../../scripts/share/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../../scripts/util/ExtendFormat.js"></script>
     <script type="text/javascript" src="../../scripts/util/Ext.ux.plugins.js"></script>
    <script type="text/javascript" src="../scripts/news/newsArea.js"></script>
  
</head>
<body>
	<div id="sortPanel" style="height:290; width:560"></div>
</body>
</html>