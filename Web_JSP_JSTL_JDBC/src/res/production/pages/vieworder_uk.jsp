<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>   
<%@ include file="header.jsp" %>			

	<title>Order</title>

	<p>Тут має бути Українська!</p>
	
	<h4>Заказ-подписка # <c:out value="${requestScope.order.id}" /> от <c:out value="${requestScope.order.getLocaleCreateDate(sessionScope.locale)}" /></h4> 
	<p>на периодические издания на <c:out value="${requestScope.order.subscriptYear}" /> г.</p>
	
	
	<p>Подписчик: <c:out value="${sessionScope.consumer.name}"/></p>
	<c:if test="${not empty requestScope.order.paidDate}" >
		<h4>заказ оплачен <c:out value="${requestScope.order.getLocalePaidDate(sessionScope.locale)}" /></h4>
	</c:if>
	<c:if test="${empty requestScope.order.paidDate}" >
		<p>заказ не оплачен.</p>
	</c:if>
	<table width="80%>
	<tr>
			<td  width="100%"> </td>
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
		<c:forEach items="${requestScope.order.orderItems}" var="item">
			<tr>
			
				<td> <c:out value="${index}" /> </td>
				<td> <c:out value="${item.issue.name}" /> </td>
				<td> <c:out value="${item.issue.publisher}" /> </td>
				<td align="right"> <c:out value="${item.issue.getLocalePrice(sessionScope.locale)}" /> </td>
				<td align="right"><c:out value="${item.quantity}" /></td>
				<td align="right"> <c:out value="${ item.getLocaleCost(sessionScope.locale)}" /> </td>
				
			</tr>
			<c:set value="${index + 1}" var="index" />
		</c:forEach>	
		<tr>
			<td colspan="6" align="right">
				Всего: <c:out value="${requestScope.order.getLocaleFullCost(sessionScope.locale)}" />
			</td>
		</tr>
	</table>	
	
	
	<table width="80%">
	<c:if test="${empty requestScope.order.paidDate}" >
		<tr>
			<td  width="100%" align="right">заказ не оплачен </td>
			<td align="right"> 
				<form  method="post">		
					<input type="hidden" name="action" value="payOrder">
					<input type="hidden" name="orderId" value="<c:out value="${requestScope.order.id}" />">
					<input type="submit" value="Оплатить"/>
				</form>	
			</td>
		</tr>
	</c:if>
	</table>
<%@ include file="footer.jsp" %>
