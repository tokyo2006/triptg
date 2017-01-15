<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="org.acegisecurity.ui.AbstractProcessingFilter"%>
<%@ page import="org.acegisecurity.AuthenticationException"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="noCache.jsp" %>
<html>
	<head>
		<title>东锦租车平台</title>
<LINK href="resources/css/share/pw.css" type=text/css rel=stylesheet>
<LINK href="resources/css/share/head.css" type=text/css rel=stylesheet>	
<script type="text/javascript">
 function checkuu()
{
    if(checkspace(document.loginfo.j_username.value)) {
	document.loginfo.j_username.focus();
    alert("用户名不能为空！");
	return false;
  }
    if(checkspace(document.loginfo.j_password.value)) {
	document.loginfo.j_password.focus();
    alert("密码不能为空！");
	return false;
  }
	
  }
  
  function checkspace(checkstr) {
  var str = '';
  for(i = 0; i < checkstr.length; i++) {
    str = str + ' ';
  }
  return (str == checkstr);
}
</script>	
<STYLE type=text/css media=screen>
A:link {
	TEXT-DECORATION: underline
}
#loginLeft {
	FLOAT: left; WIDTH: 360px; PADDING-TOP: 32px
}
#loginSide {
	FLOAT: right; WIDTH: 294px; PADDING-TOP: 32px; POSITION: relative
}
#loginHead {
	FONT-WEIGHT: bold; FONT-SIZE: 14px; BACKGROUND: url(resources/images/share/login_head.gif) no-repeat; COLOR: #fff; LINE-HEIGHT: 27px; HEIGHT: 27px
}
#loginHelp LI {
	PADDING-RIGHT: 0px; PADDING-LEFT: 9px; BACKGROUND: url(resources/images/share/login_libg.gif) no-repeat 0px 12px; PADDING-BOTTOM: 0px; COLOR: #888888; LINE-HEIGHT: 20px; PADDING-TOP: 5px
}
#loginMain {
	BORDER-RIGHT: #57a0ed 1px solid; BORDER-TOP: #57a0ed 1px solid; BACKGROUND: #eef5ff; MARGIN-BOTTOM: 25px; PADDING-BOTTOM: 10px; BORDER-LEFT: #57a0ed 1px solid; BORDER-BOTTOM: #57a0ed 1px solid
}
#loginMain LI {
	PADDING-LEFT: 10px; MARGIN-BOTTOM: 5px
}
#loginMain .liNull {
	PADDING-LEFT: 85px
}
#errormsg {
	PADDING-RIGHT: 10px; PADDING-LEFT: 40px; BACKGROUND: url(resources/images/share/login_btn_notice.gif) no-repeat 20px 3px; PADDING-BOTTOM: 10px; LINE-HEIGHT: 140%; PADDING-TOP: 0px
}
.inputMain {
	BORDER-RIGHT: #718da6 1px solid; PADDING-RIGHT: 0px; BORDER-TOP: #718da6 1px solid; PADDING-LEFT: 4px; FONT-SIZE: 14px; PADDING-BOTTOM: 2px; BORDER-LEFT: #718da6 1px solid; WIDTH: 120px; PADDING-TOP: 2px; BORDER-BOTTOM: #718da6 1px solid; HEIGHT: 18px
}
.inputMainBlue {
	BORDER-RIGHT: #6699cc 1px solid; PADDING-RIGHT: 0px; BORDER-TOP: #6699cc 1px solid; PADDING-LEFT: 4px; FONT-SIZE: 14px; PADDING-BOTTOM: 2px; BORDER-LEFT: #6699cc 1px solid; WIDTH: 120px; PADDING-TOP: 2px; BORDER-BOTTOM: #6699cc 1px solid; HEIGHT: 18px
}
#loginAuto DIV {
	MARGIN: 10px 20px 0px 10px
}
</STYLE>
	</head>

	<BODY>

<NOSCRIPT>
如果您试用该旅游分销系统，请确认您的javascript已经打开，如没有打开将无法使用该网站，谢谢！
</NOSCRIPT>
<SPAN id=SignupPage>
<form action="../j_acegi_security_check" name="loginfo" method="POST" onSubmit="return checkuu();">
<TABLE id=shlFrame cellSpacing=0 cellPadding=0>
  <TBODY>
  <TR>
    <TD height=1>
      <DIV id=shlHeader><div style="padding-top:10px; color:#CD522E; font-size:22px;">东锦租车平台欢迎你</div></DIV></TD></TR>
  <TR>
    <TD id=shlBody>
      <DIV id=bodyWrapper>
      <TABLE style="WIDTH: 100%; POSITION: absolute; HEIGHT: 100%" cellSpacing=0 
      cellPadding=0 border=0>
        <TBODY>
        <TR>
          <TD id=signupBody  align=left width="50%">
          
<DIV id=loginLeft style="float:right; padding-right:40px;">
<DIV id=loginMain>
<DIV id=loginHead>
<DIV 
style="PADDING-LEFT: 25px; BACKGROUND: url(resources/images/share/login_ico_lock.gif) no-repeat 7px 7px">东锦公司用户登录</DIV></DIV>
<DIV style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 5px; PADDING-TOP: 1px"></DIV>


<DIV id=loginCommon>
<DIV id=errormsg style="DISPLAY: none"><SPAN class=fontOrange id=errorInfo>

</SPAN></DIV>

<UL style="padding-left:20px;">
  <LI><SPAN class="font14 number" style="PADDING-RIGHT: 2px">用户名：</SPAN> 
    <input type='text' name='j_username'> &nbsp;

  <LI><SPAN class="font14 number" style="PADDING-RIGHT: 2px">密&nbsp;&nbsp;&nbsp;码：&nbsp;</SPAN> 
  <input type='password' name='j_password'>
<%String login_error = request.getParameter("login_error");
			if (login_error != null) {
%>
		<LI><font color="red"> 登录失败。原因: 
		<br>用户名或密码错误，请重新登陆！</font>
		<%}

		%>
  <LI class=liNull>
  <input id=Submit tabindex=4 type=image src="resources/images/share/login_submit.gif" value="  登 录  " name=Submit>
  
  </UL>


</DIV></DIV></DIV>

</TD>
          <TD>
            <TABLE height=400 cellSpacing=0 cellPadding=0>
              <TBODY>
              <TR>
                <TD style="BORDER-LEFT: #cccccc 1px solid" 
              width=1></TD></TR></TBODY></TABLE></TD>
          <TD width="50%" height="400px" style="padding-top:0; margin-top:0px;">
          <img src="resources/images/share/SV3OLWSignUpIntaglioImage.jpg"/>
            <TABLE style="MARGIN: 26px">
              <TBODY>
              <TR>
                <TD >
                <font style="font-size:20px; color:#3E87CA">东锦租车公司真诚为您服务</font>               
                </TD>
              </TR>
              <TR>
                <TD style="padding-left:30px; padding-top:15px;" >
      			<ul style="color:#2561C5">
                	<li style="list-style-type:disc; padding-bottom:5px;">东锦租车平台让出行选择更方便快捷</li>
                    <li style="list-style-type:disc;padding-bottom:5px;">租车平台全力提升您的办公效率</li>
                    <li style="list-style-type:disc;padding-bottom:5px;">租车平台为您的业务推广提供最佳方式</li>
                </ul>
      </TD></TR></TBODY></TABLE>
      
      </TD></TR></TBODY></TABLE></DIV></TD></TR>
  <TR>
    <TD height=1>
      <DIV id=shlFooter>© 2008 Yeoou Corporation. All rights reserved. </DIV></TD></TR></TBODY></TABLE>
      </form>
      </SPAN>


</BODY>
</html>
