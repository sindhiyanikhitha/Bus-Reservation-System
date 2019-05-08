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
<title>Bus Booking Service</title>
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
        //AJAX
        
        var xmlHttp;
        xmlHttp = GetXmlHttpObject();
        function names1()
        {
        	if (xmlHttp == null)
            {
                alert("Your browser does not support AJAX!");
                return;
            }
            var destCities = document.getElementById("dest").value;   
            var query = "fromCities="+destCities;
            
            xmlHttp.onreadystatechange = function stateChanged()
            {
                if (xmlHttp.readyState == 4)
                {
                    
                   var dataList = document.getElementById('json-datalist1');
                    var json = JSON.parse(xmlHttp.responseText);
                    for(var i=0;i<json.list.length;i++)
                    	{
                    	
                    	var option = document.createElement("option");
                    	option.value=json.list[i].cityname;
                    	option.textContent = name;
                    	dataList.appendChild(option);
                    	}
                   
                }
            };
        	
            xmlHttp.open("POST", "fromCitieslist.htm", true);
            xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlHttp.send(query);
           return false;
        }
        
        function names() {
           if (xmlHttp == null)
            {
                alert("Your browser does not support AJAX!");
                return;
            }
            var fromCities = document.getElementById("from").value;   
            var query = "fromCities="+fromCities;
            
            xmlHttp.onreadystatechange = function stateChanged()
            {
                if (xmlHttp.readyState == 4)
                {
                    
                   var dataList = document.getElementById('json-datalist');
                    var json = JSON.parse(xmlHttp.responseText);
                    for(var i=0;i<json.list.length;i++)
                    	{
                    	
                    	var option = document.createElement("option");
                    	option.value=json.list[i].cityname;
                    	option.textContent = name;
                    	dataList.appendChild(option);
                    	}
                   
                }
            };
            xmlHttp.open("POST", "fromCitieslist.htm", true);
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



<c:choose>
            <c:when test="${!empty sessionScope.username}">
                <jsp:include page="menu.jsp"/>
            </c:when>
            <c:otherwise>
                <jsp:include page="menu2.jsp"/>
            </c:otherwise>
        </c:choose>
 
<h1>Welcome to Bus Booking Services</h1>
<form:form action="listBusDetails.htm" commandName="busDetails" method="post">



    <b>From:</b>
    <form:input type="text" path="from" size="30" id="from" list="json-datalist" onkeyup="return names()" required="required"/> <font color="red"><form:errors path="from"/></font>
	<datalist id="json-datalist"></datalist>
<br><br><br>
    <b>Dest:</b>
    <form:input type="text" path="dest" id="dest" list="json-datalist1" onkeyup="return names1()" size="30" required="required"/> <font color="red"><form:errors path="dest"/></font>
	<datalist id="json-datalist1"></datalist>
<br><br><br>
    <b>Departure Date:</b>
    <td><form:input type="date" path="deptDate" id="txtFromDate" size="30" required="required"/> <font color="red"><form:errors path="deptDate"/></font>
<br><br><br>
    <b>Travel Class:</b>
    <form:radiobutton value="travelClass" name="Economy" path="travelClass" checked="checked"/>Non AC
    	<form:radiobutton value="travelClass" name="Business" path="travelClass" /> AC
    	<br><br><br>
<input type="submit" value="Search Buses" />


</form:form>
 
    
    
</body>
</html>