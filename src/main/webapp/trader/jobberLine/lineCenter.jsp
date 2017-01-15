<%@ page language="java"  pageEncoding="UTF-8"%>
<%
	String param = request.getParameter("params");
	String[] params = param.split("@@@");
	String regionId= params[0];
	String flag = params[1];
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Line grid index</title>

    <link rel="stylesheet" type="text/css" href="../../resources/ext/resources/css/ext-all.css" />
	<link rel="stylesheet" type="text/css" href="../../resources/css/jobber/line.css" />

    <script type="text/javascript" src="../../scripts/share/ext-base.js"></script>
    <script type="text/javascript" src="../../scripts/share/ext-all.js"></script>
    <script type="text/javascript" src="../../scripts/util/TabCloseMenu.js"></script>
	<script type="text/javascript" src="../../scripts/util/JsonUtil.js"></script>
    <script type="text/javascript" src="../../scripts/share/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../scripts/jobberLine/lineCenter.js"></script>
    
    <script type="text/javascript">
		regionId="<%=regionId%>";
		flag="<%=flag%>";
	</script>
</head>
<body>
	
</body>
</html>