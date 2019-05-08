<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
  text-align: center;
         font-family: garamond;
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
<title>Add Bus</title>
</head>
<body>
<form:form action="addBus.htm" commandName="bus" method="post">

<b>Please enter the details:</b><br><br>

Bus Name: <form:input type="text" path="busName" name="busName" size="30" required="required"/><br><br>

Owner:  &nbsp;&nbsp;&nbsp;&nbsp;     <form:input type="text" path="owner" name="owner" size="30" required="required" /><br><br>

			  <input type="submit" value="Add Bus" />

</form:form>
</body>
</html>