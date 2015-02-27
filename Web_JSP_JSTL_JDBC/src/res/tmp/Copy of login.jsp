<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Login</title>
	</head>
	
	<body>
		<h4>Login</h4>
		
		 <%if (request.getAttribute("errmessage")!= null) {  %>  
			<h6><%=request.getAttribute("errmessage")%></h6>
		 <%}%>
		 
		<form name="input" action="" method="post">
		<input type="hidden" name="action" value="login">
		<table>
			<tr>
				<td>login </td>
				<td><input type="text" name="login" /></td>
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
	</body>
</html>
