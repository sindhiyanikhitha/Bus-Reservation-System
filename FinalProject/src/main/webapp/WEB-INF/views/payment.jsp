<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Payment Page</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<form action="payment.htm" method="post">
<br><br><br>
	Credit Card Number <input type="text" name="creditCardNumber" value='${cookie.ccn.value}' required />
	<br><br><br>
	Bank Name          <input type="text" name="bankName" required />
	<br><br><br>
	Name as on card    <input type="text" name="fullName" required/>
	<br><br><br>
	Expiration Month   <input type="number" name="expiration_month" required />
	<br><br><br>
	Expiration Year    <input type="number" name="expiration_year" required/> 
	<br><br><br>
	<input type="submit" value="Book Ticket" />

</form>
</body>
</html>