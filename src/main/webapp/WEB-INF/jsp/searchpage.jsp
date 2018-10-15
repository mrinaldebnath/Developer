
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Category</title>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

<script>

var reload = function() {

	$.ajax({
		type : "GET",
		async : !1,
		url : "http://localhost:8080/DevelopersInfo/reload", //assuming your controller is configured to accept requests on this URL
		success : function(result) {
			// do what ever you want with data
		}
	});	
};



</script>
</head>
<body>


<form action = "searchresult/1" method = "POST" target = "_blank">

Select languages ------------------------------------------ <br/>

         <input type = "checkbox" name = "bd"  /> Bangla
         <input type = "checkbox" name = "vn"  /> Vietnammese
         <input type = "checkbox" name = "en" /> English
         <input type = "checkbox" name = "ja" /> Japanese
<br/>        
Select programming languages ------------------------------------------ <br/>

         <input type = "checkbox" name = "php"  /> PHP
         <input type = "checkbox" name = "ruby"  /> Ruby
         <input type = "checkbox" name = "javascript" /> JavaScript
         <input type = "checkbox" name = "python" /> Python
         <input type = "checkbox" name = "scala"  /> Scala
         <input type = "checkbox" name = "kotlin"  /> Kotlin
         <input type = "checkbox" name = "swift" /> Swift    
          
          <br/>
         
         <input type = "submit" value = "Search" />
      </form>
      
      <button onclick="location.href = 'http://localhost:8080/DevelopersInfo/';" type="button" id="index">Home</button> <br>
		<div></div>
		<div></div>
		<button type="button" id="reload" onclick="reload()">Reload Data from Server</button>
      
      
</body>
</html>