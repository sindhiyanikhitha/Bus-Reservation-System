<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Bus Schedule</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
 <script src="//code.jquery.com/jquery-1.10.2.js"></script>
 <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
 <script>
 $(document).ready(function(){
	    $("#txtFromDate").datepicker({
	        numberOfMonths: 2,
	        minDate: 0,
	        onSelect: function(selected) {
	          $("#txtToDate").datepicker("option","minDate", selected)
	        }
	    });
	    $("#txtToDate").datepicker({ 
	        numberOfMonths: 2,
	        onSelect: function(selected) {
	           $("#txtFromDate").datepicker("option","maxDate", selected)
	        }
	    });  
	});
</script>
</head>
<body>
<form:form action="updateBusDetails.htm" commandName="bd" method="post">
<br><br><br>
Route ID
<form:input type="text" path="route_id" value="${requestScope.bus.route_id}" size="30"/><font color="red"><form:errors path="route_id"/></font>
<br><br><br>
Bus Name
<form:input type="text" path="bus_name" value="${requestScope.bus.bus_name}" size="30"/><font color="red"><form:errors path="bus_name"/></font>
<br><br><br>
Bus Id
<form:input type="text" path="bus_id" value="${requestScope.bus.bus_id}" size="30"/><font color="red"><form:errors path="bus_id"/></font>
<br><br><br>
From
<form:input type="text" path="from" value="${requestScope.bus.from}" size="30"/><font color="red"><form:errors path="from"/></font>
<br><br><br>
Dest
<form:input type="text" path="dest" value="${requestScope.bus.dest}" size="30"/><font color="red"><form:errors path="dest"/></font>
<br><br><br>
Departure Time
<form:input type="text" path="deptTime" value="${requestScope.bus.deptTime}" size="30"/><font color="red"><form:errors path="deptTime"/></font>
<br><br><br>
Arrival Time
<form:input type="text" path="arrivalTime" value="${requestScope.bus.arrivalTime}" size="30"/><font color="red"><form:errors path="arrivalTime"/></font>
<br><br><br>
Travel Class
<form:input type="text" path="travelClass" value="${requestScope.bus.travelClass}" size="30"/><font color="red"><form:errors path="travelClass"/></font>
<br><br><br>
Total Seats
<form:input type="text" path="totalSeats" value="${requestScope.bus.totalSeats}" size="30" readonly="true"/><font color="red"><form:errors path="totalSeats"/></font>
<br><br><br>
Available Seats
<form:input type="text" path="availableSeats" value="${requestScope.bus.availableSeats}" size="30"/><font color="red"><form:errors path="availableSeats"/></font>
<br><br><br>
Amount
<form:input type="text" path="amount" value="${requestScope.bus.amount}" size="30"/><font color="red"><form:errors path="amount"/></font>
<br><br><br>
Departure Date
<form:input type="text" path="deptDate" id="txtFromDate" value="${requestScope.bus.deptDate}" size="30"/><font color="red"><form:errors path="deptDate"/></font>
<br><br><br>
Arrival Date
<form:input type="text" path="arrDate" id="txtToDate" value="${requestScope.bus.arrDate}" size="30"/><font color="red"><form:errors path="arrDate"/></font>
<br><br><br>
<input type="submit" value="Update bus to database" />




</form:form>
<a href="adminHome.htm">Go Back to Menu Page</a>
</body>
</html>