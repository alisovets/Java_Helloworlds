<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"      
 %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <%@ page import="java.util.List, model.Issue, utils.RequestProcessor" %>
 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Issues</title>
	</head>
	
	<body>
	
		<h2>Привет, <c:out value="${sessionScope.consumer.name}"/>! </h2> 
		<h4>Вы можете оформить подписку на следующие издания:</h4>
		
		
		
		<table width="80%" border="1">
			<tr>
				<th>N п/п </th>
				<th>Издание</th>
				<th>Издатель</th>
				<th>Периодичность</th>
				<th>Цена подписки</th>
				<th>Выбор</th>
			</tr>
			
			
			
			
			
										
			<c:set value="1" var="index" />
			<c:forEach items="${requestScope.issueList}" var="issue">
				<tr>
				
					
					
					<td width="1%"> <c:out value="${index}" /> </td>
					<td width="35%"> <c:out value="${issue.name}" /> </td>
					<td width="35%"> <c:out value="${issue.publisher}" /> </td>
					<td align="center"> <c:out value="${issue.periodicity}" /> </td>
					<td align="right"> <c:out value="${issue.price}" /> </td>
					<td align="right">
						<table>
							<tr>
								<td>
									<c:if test="${issue.selected}" >
										X </td><td>
										<form  method="post">
											<input type="hidden" name="issueId" value="<c:out value="${issue.id}" />" >
											<input type="hidden" name="action" value="deselectissue">
											<input type="submit" value="-"/>
										</form>						                                             
									</c:if>
									<c:if test="${not issue.selected}" >
										</td><td>
										
										<form  method="post">
											<input type="hidden" name="issueId" value="<c:out value="${issue.id}" />" >
											<input type="hidden" name="action" value="selectissue">
											<input type="submit" value="+"/>
										</form> 
									</c:if>									
								</td>
							</tr>
					 	</table>						                                  
					</td>
				</tr>
				<c:set value="${index + 1}" var="index" />
  			</c:forEach >
		</table>
		
		<c:if test="${sessionScope.selectedIds.size() > 0}" >
		<table width="80%">
			<tr>
				<th align="right">
					<form  method="post">		
						<input type="hidden" name="action" value="createOrder">
						<input type="submit" value="Оформить заказ"/>
					</form>	
				</th>
			</tr>
		</table>
		</c:if>	
	</body>
</html>
