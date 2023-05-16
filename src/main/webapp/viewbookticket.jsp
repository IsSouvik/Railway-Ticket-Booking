<%@page import="dto.Trainticket"%>
<%@page import="java.util.List"%>
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
	<%List<Trainticket>list=user.getticket();
	if(list==null){
		response.getWriter().print("<h1 style='color:red'>No Ticket Booked</h1>");
		request.getRequestDispatcher("userhome.html").include(request, response);	
	}else{
	%>
	<table border="1">
	<tr>
	<th>PNR</th>
	<th>Train No</th>
	<th>User Name</th>
	<th>Source</th>
	<th>Destination</th>
	<th>Number Of Seats</th>
	<th>Price</th>
	<th>DOJ</th>
	<th>DOB</th>
	<th>Status</th>
	</tr>
	<%for(Trainticket ticket:list) {%>
	<tr>
	<th><%=ticket.getPnr() %></th>
	<th><%=ticket.getTrainnumber() %></th>
	<th><%=user.getFirstName()+" "+user.getLastName() %></th>
	<th><%=ticket.getSource() %></th>
	<th><%=ticket.getDestination() %></th>
	<th><%=ticket.getNumberofseats()%></th>
	<th><%=ticket.getAmount() %></th>
	<th><%=ticket.getDateofjourney() %></th>
	<th><%=ticket.getDateofbooking()%></th>
	<th><%=ticket.getStatus()%></th>
	<th>
	<%if(ticket.getStatus().equals("Booked")){ %>
     <a href="cancelticket?pnr=<%=ticket.getPnr()%>"><button>cancel</button></a>
	<%}else{%>
	<button disabled="disabled">Cancel</button>
	<%} %>
	</th>
	</tr>
	<%} %>
	</table>
	<%}} %>
</body>
</html>