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
<title>Your Cart</title>
</head>
<body>
        <c:choose>
            <c:when test="${!empty sessionScope.username}">
                <jsp:include page="menu.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="menu2.jsp"/>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${!empty sessionScope.cart}">
                <h3>Your cart contents</h3>
                <div style="overflow-x:auto;">
                <table >
                    <tr>
                        <th>Bus Number</th>
		                <th>Bus Name</th>
		                <th>Bus Id</th>
		                <th>Price</th>
		                <th>Departure time</th>
		                <th>Destination arrival time </th>
                    </tr>
                    <c:forEach var="bus" items="${sessionScope.cart}">
                <tr>
                    <td>${bus.route_id}</td>
                    <td>${bus.bus_name}</td>
                    <td>${bus.bus_id}</td>
                    <td>${bus.amount}</td>
                    <td>${bus.deptTime}</td>
                    <td>${bus.arrivalTime}</td>
                    <td><a href="removeFromCart.htm?action=remove&id=${bus.route_id}"> [Remove from cart]</a></td>
                </tr>   
            </c:forEach>
                    <tr><td></td><td></td></tr>
                    <tr>
                        <td>Total</td>
                        <td>${sessionScope.total}</td>
                    </tr>
                </table>
                </div>
                <br><br>
                <a href="passenger1.htm"> Proceed to enter details and Payment>></a>
            </c:when>
            <c:otherwise>
                <h3>Oops.. your cart is empty</h3>
                <p><a href="index.htm">Return to main page</a></p>
            </c:otherwise>
        </c:choose>

</body>
</html>