<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ include file="header.jsp" %>			
	<title>Order</title>
	<p>This will be english!</p>

	<h4>Order subscription to periodicals</h4>

	<p>for <c:out value="${sessionScope.order.subscriptYear}" /> г. </p>
	
	<p>Subscriber: <c:out value="${sessionScope.consumer.name}"/></p>
	<table width="80%">
		<tr>
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
				Total cost:<c:out value="${sessionScope.order.getLocaleFullCost(sessionScope.locale)}"/>
			</td>
		</tr>
	
		<tr>
			<td align="right">
				<form  method="post">		
					<input type="hidden" name="action" value="saveOrder">
					<input type="submit" value="Save order"/>
				</form>	
			</td>
		</tr>
		
	</table>	

<%@ include file="footer.jsp" %>