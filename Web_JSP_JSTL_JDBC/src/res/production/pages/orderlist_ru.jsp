<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    
<%@ include file="header.jsp" %>			

	<title>Orders</title>
	<%@ include file="languageSelector.jsp" %>

	<h4>Подписчик: <c:out value="${sessionScope.consumer.name}"/></h4>
	 
			
	<c:if test="${empty requestScope.orderList}" >	
		<p>У Вас пока нет оформленых заказов.</p>
	</c:if>
	
	<c:if test="${not empty requestScope.orderList}" >		
		<form  method="post">		
			<input type="hidden" name="action" value="viewIssue">
			<input type="submit" value="К списку изданий"/>
		</form>	
	
		<h3>Заказы</h3>
		
		<h4>Новые неоплаченные заказы</h4>
		
		<table width="60%" border="1">
			<tr>
				<th width="5%"> </th>
				<th>№ заказа</th>
				<th> Дата оформления </th>
				<th>Просмотр</th>
			</tr>			
			<c:set value="1" var="index" />
			<c:forEach items="${requestScope.orderList}" var="order">
				<c:if test="${empty order.paidDate}" >			
						<tr align="right">
							<td> <c:out value="${index}" /> </td>
							<td> <c:out value="${order.id}" /> </td>
							<td> <c:out value="${order.getLocaleCreateDate(sessionScope.locale)}" /> </td>
							
							<td> 
								<form  method="post">		
									<input type="hidden" name="action" value="viewOrder">
									<input type="hidden" name="orderId" value="<c:out value="${order.id}" />">
									<input type="submit" value="Просмотр"/>
								</form>	 
							</td>
						</tr>
						<c:set value="${index + 1}" var="index" />
				</c:if>		
			</c:forEach>	
		</table>
		
		<h4>Oплаченные заказы</h4>
		
		<table width="60%" border="1">
			<tr>
				<th width="5%"> </th>
				<th>№ заказа</th>
				<th> Дата оформления </th>
				<th> Дата оплаты </th>
				<th>Просмотр</th>
			</tr>			
			<c:set value="1" var="index" />
			<c:forEach items="${requestScope.orderList}" var="order">
				<c:if test="${not empty order.paidDate}" >			
						<tr align="right">
							<td> <c:out value="${index}" /> </td>
							<td> <c:out value="${order.id}" /> </td>
							<td> <c:out value="${order.getLocaleCreateDate(sessionScope.locale)}" /> </td>
							<td> <c:out value="${order.getLocalePaidDate(sessionScope.locale)}" /> </td>
							
							<td > 
								<form  method="post">		
									<input type="hidden" name="action" value="viewOrder">
									<input type="hidden" name="orderId" value="<c:out value="${order.id}" />">
									<input type="submit" value="Просмотр"/>
								</form>	 
							</td>
						</tr>
						<c:set value="${index + 1}" var="index" />
				</c:if>		
			</c:forEach>	
		</table>
	</c:if>
	
	<form  method="post">		
		<input type="hidden" name="action" value="viewIssue">
		<input type="submit" value="К списку изданий"/>
	</form>	

<%@ include file="footer.jsp" %> 
