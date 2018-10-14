<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>API</title>


<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

<script>
var deletedeveloper = function() {
	
	var emailValueContainer = document.getElementById("deleteemail");	
	var programminglanguageContainer = document.getElementById("deleteprogramminglanguage");
	
	var emailValue = emailValueContainer.value;	
	var programminglanguage = programminglanguageContainer.value;
	var dataToSend = 
	{
		name : 	programminglanguage,
		developers : emailValue
	};

	var responseFromController = $.ajax({
		type : "POST",
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		async : !1,
		url : "http://localhost:8080/DevelopersInfo/apidel", //assuming your controller is configured to accept requests on this URL
		data : JSON.stringify(dataToSend), // This converts the payLoad to Json to pass along to Controller
		success : function(result) {
			return result;
			// do what ever you want with data
		}
	});
	
	emailValueContainer.value="";
	programminglanguageContainer.value="";

};

var getdeveloper = function() {

	var responseFromController = $.ajax({
		type : "GET",
		dataType : 'json',
		async : !1,
		url : "http://localhost:8080/DevelopersInfo/apiget", //assuming your controller is configured to accept requests on this URL
		success : function(result) {
			return result;
			// do what ever you want with data
		}
	});
	
	

};
		
var postdeveloper = function() {
	
	var emailValueContainer = document.getElementById("postemail");	
	var programminglanguageContainer = document.getElementById("postprogramminglanguage");
	
	var emailValue = emailValueContainer.value;	
	var programminglanguage = programminglanguageContainer.value;
	
	var dataToSend = 
	{
		name : 	programminglanguage,
		developers : emailValue
	};

	var responseFromController = $.ajax({
		type : "POST",
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		async : !1,
		url : "http://localhost:8080/DevelopersInfo/apipost", //assuming your controller is configured to accept requests on this URL
		data : JSON.stringify(dataToSend), // This converts the payLoad to Json to pass along to Controller
		success : function(result) {
			return result;
			// do what ever you want with data
		}
	});
	
	emailValueContainer.value="";
	programminglanguageContainer.value="";
	
	
	

};	


var putdeveloper = function() {
	
	var emailValueContainer = document.getElementById("putemail");	
	var outprogramminglanguageContainer = document.getElementById("putoutprogramminglanguage");
	var inprogramminglanguageContainer = document.getElementById("putinprogramminglanguage");
	
	var emailValue = emailValueContainer.value;	
	var outprogramminglanguage = outprogramminglanguageContainer.value;
	var inprogramminglanguage = inprogramminglanguageContainer.value;
	
	var dataToSend = 
	{
		inname : 	inprogramminglanguage,
		outname : 	outprogramminglanguage,
		developers : emailValue
	};

	var responseFromController = $.ajax({
		type : "POST",
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		async : !1,
		url : "http://localhost:8080/DevelopersInfo/apiput", //assuming your controller is configured to accept requests on this URL
		data : JSON.stringify(dataToSend), // This converts the payLoad to Json to pass along to Controller
		success : function(result) {
			return result;
			// do what ever you want with data
		}
	});
	
	emailValueContainer.value="";
	outprogramminglanguageContainer.value="";
	inprogramminglanguageContainer.value="";
	
	
	

};


</script>



</head>
<body>
<table class="messages" id="messages" border="2">
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td><button type="button" id="get" onclick="getdeveloper()">Get</button></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="text" id="postemail" name="postemail" placeholder="Email"/></td>
			<td><input type="text" id="postprogramminglanguage" name="postprogramminglanguage" placeholder="Insert a Programming Lang."/></td>
			<td><button type="button" id="post" onclick="postdeveloper()">Insert</button></td>
		</tr>
		<tr>
			<td><input type="text" id="putemail" name="putemail" placeholder="Email"/></td>
			<td><input type="text" id="putoutprogramminglanguage" name="putoutprogramminglanguage" placeholder="Old Programming Language"/></td>
			<td><input type="text" id="putinprogramminglanguage" name="putinprogramminglanguage" placeholder="New Programming Language"/></td>
			<td><button type="button" id="put" onclick="putdeveloper()">Update</button></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="text" id="deleteemail" name="deleteemail" placeholder="Email"/></td>
			<td><input type="text" id="deleteprogramminglanguage" name="deleteprogramminglanguage" placeholder="Programming Lang"/></td>
			<td><button type="button" id="delete" onclick="deletedeveloper()">Delete</button></td>
			
		</tr>
		</table>
		
		<div></div>
		<div></div>
		
		<button onclick="location.href = 'http://localhost:8080/DevelopersInfo/';" type="button" id="index">Home</button>
</body>
</html>