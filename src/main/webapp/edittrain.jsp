 <%@page import="java.util.Arrays"%>
<%@page import="dto.Train"%>
<%@page import="dao.TrainDao"%>
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
TrainDao dao=new TrainDao();
Train train=dao.fetch(Integer.parseInt(request.getParameter("trainnumber")));

%>

<form action="updatetrain" method="post">
Train Number: <input type="number" name="tnumber" value="<%=train.getNumber()%>" readonly="readonly"><br>
Train Name: <input type="text" name="tname" value="<%=train.getName()%>"><br>
Number of Seats: <input type="number" name="tseat" value="<%=train.getSeat()%>"><br>
Stations: <textarea rows="5" cols="30" name="tstation" ><%=train.getStations()%></textarea><br>
Ticket Price: <textarea rows="5" cols="30" name="tprice" ><%=train.getPrice()%></textarea><br>
Time: <textarea rows="5" cols="30" name="ttime" ><%=train.getTime()%></textarea><br>
Days: <textarea rows="5" cols="30" name="tday" ><%=train.getDays()%></textarea><br>
<button type="reset">Cancel</button><button>Update</button>
</form>
<br>
<a href="managementhome.html"><button>Home</button></a>
</body>
</html>