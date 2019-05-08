<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
  width: 100%;
  height: 100%;
  text-align: center;
         font-family: garamond;
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
<meta charset="ISO-8859-1">
<title>Passenger List</title>
</head>
<body>
<h3> Below are the Passenger  details</h3>
<div style="overflow-x:auto;">
<table>
<tr>
	<th>Passenger Name</th>
	<th>Passenger Email</th>
	<th>Bus Name</th>
	<th>From</th>
	<th>Dest</th>
	<th>Date</th>
</tr>
<c:forEach var="ticketList" items="${sessionScope.ticketList}">
<tr>
	<td>${ticketList.passengerDetails.firstName}</td>
	<td>${ticketList.passengerDetails.email}</td>
	<td>${ticketList.busDetails.bus_name}</td>
	<td>${ticketList.busDetails.from}</td>
    <td>${ticketList.busDetails.dest}</td>
    <td>${ticketList.busDetails.deptDate}</td>
</tr>
</c:forEach>
</table>
</div>
<br><br><br>
<h3><a href="adminHome.htm">Go Back to Menu Page</a></h3>
</body>
</html>