<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Unused Programming Language List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>Id</th><th>Languages</th><th>Programming Languages</th></tr>  
   <c:forEach var="emp" items="${msg}">   
   <tr>  
   <td>${emp}</td>  
   </tr>  
   </c:forEach>  
   </table>  
   <br/>  
   
   <c:choose>
       <c:when test="${pageid > '1' }">
              <a href="/DevelopersInfo/show/${pageid-1}?total=${total}">Prev</a>
       </c:when>
  </c:choose>`
   <input type="hidden" name="totalPages" value="${total}"/>
   
   
   <c:forEach var="i" begin="${startpageid}" end="${endpageid}" step="1">

            <a href="/DevelopersInfo/show/${i}?total=${total}">${i}</a>
            </c:forEach>
            
             <c:choose>
       <c:when test="${pageid < total }">
              <a href="/DevelopersInfo/show/${pageid+1}?total=${total}">Next</a>
       </c:when>
  </c:choose>`
           
<br/>

   <br/>
  
   
   
   <a href="/DevelopersInfo/search">go to searchpage</a>  <br/>
   <button onclick="location.href = 'http://localhost:8080/DevelopersInfo/';" type="button" id="index">Home</button>