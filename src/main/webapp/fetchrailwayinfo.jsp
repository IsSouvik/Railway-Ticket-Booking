<%@page import="java.util.Arrays"%>
<%@page import="java.lang.annotation.Target"%>
<%@page import="dto.Train"%>
<%@page import="java.util.List"%>
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
	List<Train> list = (List<Train>) request.getAttribute("list");
	%>

	<table border="1">
		<tr>
			<th>Train Number</th>
			<th>Train Name</th>
			<th>From Station</th>
			<th>To Station</th>
			<th>Stations</th>
			<th>Time of Departure</th>
			<th>Time of Arrival</th>
			<th>Ticket Price</th>
			<th>Available days</th>
			<th>Delete</th>
			<th>Edit</th>
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
			<th><%=str[0]%></th>
			<th><%=str[str.length - 1]%></th>
			<th><%=train.getStations()%></th>
			<th><%=str1[0]%></th>
			<th><%=str1[str1.length - 1]%></th>
			<th><%=train.getPrice()%></th>
			<th><%=train.getDays()%></th>
			<th><a href="deletetrain?trainnumber=<%=train.getNumber()%>"><button>Delete</button></a></th>
			<th><a href="edittrain.jsp?trainnumber=<%=train.getNumber()%>"><button>Edit</button></a></th>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>