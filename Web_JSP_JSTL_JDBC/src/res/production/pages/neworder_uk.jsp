<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ include file="header.jsp" %>			
	<title>Order</title>
	<p>Тут має бути Українська</p>
	
	<h4>Заказ-подписка на периодические издания</h4>

	<p>на <c:out value="${sessionScope.order.subscriptYear}" /> г. </p>
	
	<p>Подписчик: <c:out value="${sessionScope.consumer.name}"/></p>
	<table width="80%">
		<tr>
			<td align="right"> 
				<form  method="post">		
					<input type="hidden" name="action" value="orderList">
					<input type="submit" value="К списку заказов"/>
				</form>	
			</td>
		</tr>
	</table>			
	
	<table width="80%" border="1">
		<tr>
			<th width="1%">N п/п </th>
			<th width="35%">Издание</th>
			<th width="35%">Издатель</th>
			<th>Стоимость подписки</th>
			<th>Количество</th>
			<th>Стоимость</th>
		</tr>
		
		<c:set value="1" var="index" />
		<c:forEach items="${sessionScope.order.orderItems}" var="item">
			<tr>
			
				<td> <c:out value="${index}" /> </td>
				<td> <c:out value="${item.issue.name}" /> </td>
				<td> <c:out value="${item.issue.publisher}" /> </td>
				<td align="right"> <c:out value="${item.issue.getLocalePrice(sessionScope.locale)}" /> </td>
				
				<td align="right" >
					<table width = "100%">
						<tr>
							<td>
				 				<form  method="post">
									<input type="hidden" name="issueId" value="<c:out value="${item.issue.id}" />" >
									<input type="hidden" name="action" value="decQuantity">
									<input type="submit" value="-"/>	
								</form>
							</td>
							<td align="right" width = "100%">	
								<c:out value="${item.quantity}" /> 
							</td>
							<td align="right">	
								<form  method="post">
									<input type="hidden" name="issueId" value="<c:out value="${item.issue.id}" />" >
									<input type="hidden" name="action" value="incQuantity">
									<input type="submit" value="+"/>	
								</form>
							</td>
						</tr>
				 	</table>								
				</td>
				<td align="right"> <c:out value="${ item.getLocaleCost(sessionScope.locale)}" /> </td>
				
			</tr>
			<c:set value="${index + 1}" var="index" />
		</c:forEach>	
	</table>	
	
	
	
	<table width="80%">
		<tr>
			<td align="right">
				Всего:<c:out value="${sessionScope.order.getLocaleFullCost(sessionScope.locale)}"/>
			</td>
		</tr>
	
		<tr>
			<td align="right">
				<form  method="post">		
					<input type="hidden" name="action" value="saveOrder">
					<input type="submit" value="Сохранить заказ"/>
				</form>	
			</td>
		</tr>
		
	</table>	

<%@ include file="footer.jsp" %>