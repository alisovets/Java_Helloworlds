<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

	<style type="text/css">
		h6.err {color:red;}
	</style>
		
	<title>Autorization</title>
	<fmt:setLocale value="ru-UA"/>
	
	<%@ include file="languageSelector.jsp" %>
	
	<h4>Autorization</h4>

	<c:if test="${not empty requestScope.errmessage}" >
  	<h6 class="err"><c:out value="${requestScope.errmessage}" /></h6>
	</c:if>
	
	<form name="input" method="post">
	<input type="hidden" name="action" value="login">
	<table>
		<tr>
			<td>login </td>
			<td><input type="text" name="login" value="<c:out value="${requestScope.login}" />" /></td>
		</tr>
		
		<tr>
			<td>password </td>
			<td><input type="password" name="password" /></td>
		</tr>
		<tr>
			<td> </td>
			<td><input type="submit" value="Submit" /></td>
		</tr>			
		
	</table>
	</form>
		
<%@ include file="footer.jsp" %>		 	
	
