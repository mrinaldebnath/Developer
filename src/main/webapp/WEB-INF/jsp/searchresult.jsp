<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Developer List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>Email</th></tr>  
   <c:forEach var="emp" items="${msg}">   
   <tr>  
   <td>${emp.email}</td>  
     
   </tr>  
   </c:forEach>  
   </table>  
   <br/>  
   
   <c:choose>
       <c:when test="${pageid > '1' }">
              <a href="/DevelopersInfo/searchresult/${pageid-1}?total=${total}&totalRow=${totalRow}">Prev</a>
       </c:when>
  </c:choose>`
  
   
   
   <c:forEach var="i" begin="${startpageid}" end="${endpageid}" step="1">

            <a href="/DevelopersInfo/searchresult/${i}?total=${total}&totalRow=${totalRow}">${i}</a>
            </c:forEach>
            
             <c:choose>
       <c:when test="${pageid < total }">
              <a href="/DevelopersInfo/searchresult/${pageid+1}?total=${total}&totalRow=${totalRow}">Next</a>
       </c:when>
  </c:choose>`
           
<br/>



   <br/>
   
   
   <a href="/DevelopersInfo/search">go to searchpage</a>  <br/>
   <button onclick="location.href = 'http://localhost:8080/DevelopersInfo/';" type="button" id="index">Home</button>