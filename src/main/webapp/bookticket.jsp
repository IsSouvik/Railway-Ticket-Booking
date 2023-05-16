<%@page import="dto.Train"%>
<%@page import="dao.TrainDao"%>
<%@page import="dto.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Ticket</title>
</head>
<body>
	<%
	User user = (User) session.getAttribute("user");
	
	%>
	<%
	if (user == null) {
		response.getWriter().print("<h1 style='color:red'>Session Expired login again</h1>");
		request.getRequestDispatcher("login.html").include(request, response);
	} else {
	int trainnumber = Integer.parseInt(request.getParameter("tn"));
	TrainDao dao = new TrainDao();
	Train train = dao.fetch(trainnumber);
	if(train==null)
	{
		response.getWriter().print("<h1 style='color:red'>Invalid Train Number</h1>");
		request.getRequestDispatcher("userhome.html").include(request, response);
	}
	else{
	%>
	<h1>
		Hello
		<%=user.getFirstName() + " " + user.getLastName()%></h1>
	<br>
	<form action="bokticket" method="post">
	User Id: <input type="text" name="uid" value="<%=user.getId()%>" readonly="readonly"><br>
	Train Number: <input type="text" name="tn" value="<%=trainnumber%>" readonly="readonly"><br>
	From: <select name="from">
	<%String[]str=train.getStations().split(","); %>
	<%for(int i=0;i<str.length;i++){ %>
	<option><%=str[i] %></option>
	<%} %>
	</select><br>
	To: <select name="to">
	<%for(int i=0;i<str.length;i++){ %>
	<option><%=str[i] %></option>
	<%} %>
	</select><br>
	Date of Journey: <input type="date" name="doj"><br>
	Number of Seats: <input type="number" name="seats"><br>
	<button type="reset">Cancel</button><button>Book</button> 
	</form>
	<br>
	<a href="userhome.html"><button>Back</button></a>
	<%
	}}
	%>
</body>
</html>