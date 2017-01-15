<%@ page language="java"  pageEncoding="UTF-8"%>
<%
	String param = request.getParameter("params");
	String[] params = param.split("@@@");
	String node= params[0];
	String regionId= params[1];
	String flag = params[2];
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>region line template</title>
 	<script type="text/javascript">
 		var node="<%=node%>";
		var regionId="<%=regionId%>";
		var flag="<%=flag%>";
	</script>
    
	<link rel="stylesheet" type="text/css" href="../../resources/ext/resources/css/ext-all.css" />
	<link rel="stylesheet" type="text/css" href="../../resources/css/jobber/line.css" />
	<link rel="stylesheet" type="text/css" href="../../resources/css/share/comBoxFlag.css" />

    <script type="text/javascript" src="../../scripts/share/ext-base.js"></script>
    <script type="text/javascript" src="../../scripts/share/ext-all.js"></script>
    <script type="text/javascript" src="../../scripts/util/TabCloseMenu.js"></script>
	<script type="text/javascript" src="../../scripts/util/JsonUtil.js"></script>
    <script type="text/javascript" src="../../scripts/share/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../../scripts/util/ExtendFormat.js"></script>
    <script type="text/javascript" src="../../scripts/util/Ext.ux.plugins.js"></script>
    <script type="text/javascript" src="../scripts/team/teamRegion.js"></script>
    <script type="text/javascript" src="../scripts/team/localdata.js"></script>
    
</head>
<body>
	<div id="teamgrid"></div>
	<div id="areaPanel" style="height:290; width:560"></div>
	<div id="regionPanel" style="height:290; width:560"></div>
	<div id="vehicle"></div>
</body>
</html>