<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
table {
  border-collapse: collapse;
  width: 100%;
}
html, body {
  padding: 0;
  margin: 0;
  text-align: center;
         font-family: garamond;
  width: 100%;
  height: 100%;
  background-color: #ccc;
  background-image:
    repeating-linear-gradient(90deg, transparent 0, transparent 10px, #ccc 11px, #ccc 50px),
    repeating-linear-gradient(180deg, grey 0, grey 10px, transparent 11px, transparent 50px);
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {background-color: #f2f2f2;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Buses</title>
</head>
<body>
 <jsp:include page="menu.jsp"/>
 <a href="addBusDetails.htm"> Add Buses </a>


 <h3>List of Buses are:</h3>
  <form action="updateBusDetails.htm" method="get">
  <div style="overflow-x:auto;">
<table >
<tr>
					<th>Route Id </th>
					<th>Bus Id</th>
					<th>From</th>
					<th>Dest</th>
					<th>Departure Time</th>
					<th>Arrival Time</th>
					<th>Travel Class</th>
					<th>Total Seats</th>
					<th>Amount</th>
					<th>Departure Date</th>
					<th>Arrival Date</th>
</tr>

<c:forEach var="bus" items="${sessionScope.listOfBusDetails}">
<tr>
					<td>${bus.route_id}</td>
                    <td>${bus.bus_id}</td>
                    <td>${bus.from}</td>
                    <td>${bus.dest}</td>
                    <td>${bus.deptTime}</td>
                    <td>${bus.arrivalTime}</td>
                    <td>${bus.travelClass}</td>
                    <td>${bus.totalSeats}</td>
                    <td>${bus.amount}</td>
                    <td>${bus.deptDate}</td>
                    <td>${bus.arrDate}</td>
                    <td><a href="updateBusDetails.htm?id=${bus.route_id}&action=update">Update Bus</a></td>
                    <td><a href="deleteBusDetails.htm?id=${bus.route_id}">Delete Bus</a></td>
</tr>
</c:forEach>
</table>
</div>
</form>
</body>
</html>