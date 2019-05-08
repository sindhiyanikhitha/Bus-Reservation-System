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
<title>Bus schedule List</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
var xmlHttp;
xmlHttp = GetXmlHttpObject();
function checkSeats()
{
	if (xmlHttp == null)
    {
        alert("Your browser does not support AJAX!");
        return;
    }
	//alert("hi");
    var check = document.getElementById("link").href;   
    var busId = check.split('=');
    //alert(busId[1]);
    var query = "flid="+busId[1];
    
    xmlHttp.onreadystatechange = function stateChanged()
    {
    	
        if (xmlHttp.readyState == 4)
        {
        	document.getElementById("error").innerHTML=xmlHttp.responseText;
			var node = document.getElementById("error");
    		
    		var txtContent = node.textContent;
    		
    		//alert(txtContent);
    		
        	if(txtContent=="Seats are available")
        	{
        		location.href=("viewCart.htm");
        	}
        }
    };
    xmlHttp.open("POST", "addToCart.htm", true);
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send(query);
 	return false;
 	
	
}
	
	
	 function GetXmlHttpObject()
     {
         var xmlHttp = null;
         try
         {
             // Firefox, Opera 8.0+, Safari
             xmlHttp = new XMLHttpRequest();
         } catch (e)
         {
             // Internet Explorer
             try
             {
                 xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
             } catch (e)
             {
                 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
             }
         }
         return xmlHttp;
     }
     
</script>
</head>
<body>
<div id="content">

<c:choose>
            <c:when test="${!empty sessionScope.username}">
                <jsp:include page="menu.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="menu2.jsp"/>
            </c:otherwise>
</c:choose>
        <h2>Following Buses are available</h2>
        <div style="overflow-x:auto;">
            <table >
            <tr>
            	<th>Route Number</th>
                <th>Bus Name</th>
                <th>Bus Id</th>
                <th>Price</th>
                <th>Departure time</th>
                <th>Destination arrival time </th>
                <th>Available Seats</th>
            </tr>
		<c:forEach var="busdetail" items="${sessionScope.buslist}">
                <tr>
                    <td>${busdetail.route_id}</td>
                    <td>${busdetail.bus_name}</td>
                    <td>${busdetail.bus_id}</td>
                    <td>$${busdetail.amount}</td>
                    <td>${busdetail.deptTime}</td>
                    <td>${busdetail.arrivalTime}</td>
                    <td>${busdetail.availableSeats}</td>
                    <td><a href="addToCart.htm?fid=${busdetail.route_id}"  id="link">Book Ticket</a></td>
                </tr>   
            </c:forEach>
        </table>
        </div>
                    <div id="error" style="color:red"></div>
</div>       
</body>
</html>