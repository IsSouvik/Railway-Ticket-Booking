<%@page import="java.util.List"%>
<%@page import="dto.Train"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Train Details</title>
</head>
<body>
<%
	List<Train> list = (List<Train>) request.getAttribute("list");
	%>

	<table border="1">
		<tr>
			<th>Train Number</th>
			<th>Train Name</th>
			<th>Seats Available</th>
			<th>From Station</th>
			<th>To Station</th>
			<th>Time of Departure</th>
			<th>Time of Arrival</th>
			<th>Ticket Price</th>
			<th>Available days</th>
			<th>Book</th>
		 
		</tr>
		<%
		for (Train train : list) {
		%>
		<%
		String[] str = train.getStations().split(",");
		String[] str1 = train.getTime().split(",");
		%>
		<tr>
			<th><%=train.getNumber()%></th>
			<th><%=train.getName()%></th>
			<th><%=train.getSeat() %></th>
			<th><%=str[0]%></th>
			<th><%=str[str.length - 1]%></th>
			<th><%=str1[0]%></th>
			<th><%=str1[str1.length - 1]%></th>
			<th><%=train.getPrice()%></th>
			<th><%=train.getDays()%></th>
			<th><a href="bookticket.jsp?tn=<%=train.getNumber()%>"><button>Book</button></a></th>
		</tr>
		<%
		}
		%>
	</table><br>
	<a href="userhome.html"><button>Back</button></a>
</body>
</html>