<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>   
<%@ include file="header.jsp" %>			

	<title>Order</title>

	<p>This will be english!</p>	
	
	<h4>Order subscription to periodicals # <c:out value="${requestScope.order.id}" /> of <c:out value="${requestScope.order.getLocaleCreateDate(sessionScope.locale)}" /></h4> 
	<p> to periodicals ащк <c:out value="${requestScope.order.subscriptYear}" /> year.</p>
	
	
	<p>Subscriber: <c:out value="${sessionScope.consumer.name}"/></p>
	<c:if test="${not empty requestScope.order.paidDate}" >
		<h4>Order has been paid <c:out value="${requestScope.order.getLocalePaidDate(sessionScope.locale)}" /></h4>
	</c:if>
	<c:if test="${empty requestScope.order.paidDate}" >
		<p> order is not paid.</p>
	</c:if>
	<table width="80%>
	<tr>
			<td  width="100%"> </td>
			<td align="right"> 
				<form  method="post">		
					<input type="hidden" name="action" value="orderList">
					<input type="submit" value="To order list"/>
				</form>	
			</td>
		</tr>
	</table>	
	<table width="80%" border="1">
		<tr>
			<th width="1%"> # </th>
			<th width="35%">Issue</th>
			<th width="35%">Publisher</th>
			<th>Price</th>
			<th>Quantity</th>
			<th>Cost</th>
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
				Total cost: <c:out value="${requestScope.order.getLocaleFullCost(sessionScope.locale)}" />
			</td>
		</tr>
	</table>	
	
	
	<table width="80%">
	<c:if test="${empty requestScope.order.paidDate}" >
		<tr>
			<td  width="100%" align="right">order is not paid. </td>
			<td align="right"> 
				<form  method="post">		
					<input type="hidden" name="action" value="payOrder">
					<input type="hidden" name="orderId" value="<c:out value="${requestScope.order.id}" />">
					<input type="submit" value="pay"/>
				</form>	
			</td>
		</tr>
	</c:if>
	</table>
<%@ include file="footer.jsp" %>
