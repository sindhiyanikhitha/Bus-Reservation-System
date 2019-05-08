<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up</title>
    </head>
    <body>
    <script>
		function checknew()
		{
			document.getElementById("error").innerHTML="";
			document.getElementById("duplicateuser").innerHTML= "";
			}
    
    	function registerUser(){
    		var isValid = true;
    		var node = document.getElementById("error");
    		
    		var txtContent = node.textContent;
    		
    		
    		if(txtContent=="Username already exists")
    			{
    			isValid = false;
    			document.getElementById("duplicateuser").innerHTML= "";
    			alert("Please enter unique username");
    			}
    		return isValid;
    		
    	}
        //AJAX
        var xmlHttp;
        xmlHttp = GetXmlHttpObject();
        function checkUser() {
           if (xmlHttp == null)
            {
                alert("Your browser does not support AJAX!");
                return;
            }
            var username = document.getElementById("username").value;
            var query = "action=ajaxCheck&username="+username;
            xmlHttp.onreadystatechange = function stateChanged()
            {
                if (xmlHttp.readyState == 4)
                {
                   
                    var json = JSON.parse(xmlHttp.responseText);
                    document.getElementById("error").innerHTML="";
                    document.getElementById("error").innerHTML = json.message;
                    
                }
            };
            xmlHttp.open("POST", "signup.htm", true);
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
        
        <h1>Sign up</h1>
        <form:form action="signup.htm?action=signup" onSubmit =" return registerUser()" commandName="users" method="post">
        <br><br><br>
            <p>Username: <form:input type="text" path="username" id="username" onblur="return checkUser()" onclick="return checknew()" required="required" />
            <div id="error" style="color:red"></div>
            <br><br><br>
            <p>Password: <form:input type="password" path="password" required="required"/></p>
            <br><br><br>
            <input type="submit" value="Sign Up">
            <div id="duplicateuser"></div>
        </form:form>
    </body>
</html>