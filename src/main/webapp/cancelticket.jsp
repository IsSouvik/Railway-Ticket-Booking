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
<%
	User user = (User) session.getAttribute("user");
	
	%>
	<%
	if (user == null) {
		response.getWriter().print("<h1 style='color:red'>Session Expired login again</h1>");
		request.getRequestDispatcher("login.html").include(request, response);
	} else {%>
	<form action="cancelticket">
		Enter The PNR No: <input type="text" name="pnr">
		<button>Cancel</button>
		</form>
	<%} %>
</body>
</html>