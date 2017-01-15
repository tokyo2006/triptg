<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<% String path = request.getContextPath(); %>
<% if(request.getServerName().equals("localhost"))
{
    String url = path+"/index.html";
    response.sendRedirect(url);
}


%>
