<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>	
	<style type="text/css">
		h6.err {color:red;}
	</style>
	<title>Вход администратора</title>

	<%@ include file="languageSelector.jsp" %>
	<h4>Вход администратора</h4>

	<c:if test="${not empty requestScope.errmessage}" >
  		<h6 class="err"><c:out value="${requestScope.errmessage}" /></h6>
	</c:if>
	
	<form name="input" method="post">
	<input type="hidden" name="action" value="adminlogin">
	<table>
		<tr>
			<td>Имя учетной записи: </td>
			<td><input type="text" name="adminlogin" value="<c:out value="${requestScope.adminlogin}" />" /></td>
		</tr>
		
		<tr>
			<td>Пароль: </td>
			<td><input type="password" name="password" /></td>
		</tr>
		<tr>
			<td> </td>
			<td><input type="submit" value="Отправить" /></td>
		</tr>
		
		
	</table>
	</form> 	
			
<%@ include file="footer.jsp" %>	