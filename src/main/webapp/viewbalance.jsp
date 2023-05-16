<%@page import="dto.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% User user=(User)request.getSession().getAttribute("user");%>
	<%if(user==null)
	{
		response.getWriter().print("<h1 style='color:red'>Session Expired Login Again</h1>");
		request.getRequestDispatcher("home.html").include(request, response);
	}else {%>
	<h1>Your Balance is   <%=user.getWallet()%></h1>
	<%} %><br>
	<a href="addmoney.jsp"><button>Add Money</button></a>
</body>
</html>