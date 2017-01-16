<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<% String path = request.getContextPath(); %>
<% if(request.getServerName().equals("localhost")||request.getServerName().equals("192.168.240.129"))
{
    String url = path+"/index.html";
    response.sendRedirect(url);
}


%>
