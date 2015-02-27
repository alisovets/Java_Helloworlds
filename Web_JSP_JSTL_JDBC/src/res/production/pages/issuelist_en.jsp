<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<%@ include file="header.jsp" %>			
	<title>Issues</title>
	
	<p>This will be english!</p>
	
	<h4>Hello <c:out value="${sessionScope.consumer.name}"/>! </h4> 
	<h4>You can subscribe to following publications:</h4>
	
	
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
			<th> # </th>
			<th>Issue</th>
			<th>Publisher</th>
			<th>Periodicity</th>
			<th>Price</th>
			<th>Select</th>
		</tr>
		
		<c:set value="1" var="index" />
		<c:forEach items="${requestScope.issueList}" var="issue">
			<tr>
			
				<td width="1%"> <c:out value="${index}" /> </td>
				<td width="35%"> <c:out value="${issue.name}" /> </td>
				<td width="30%"> <c:out value="${issue.publisher}" /> </td>
				<td align="center"> <c:out value="${issue.periodicity.toLocaleString(sessionScope.locale)}" /> </td>
				<td align="right"> <c:out value="${issue.getLocalePrice(sessionScope.locale)}" /> </td>
				<td align="right">
					<table>
						<tr>
							<td>
								<c:if test="${sessionScope.selectedIds.contains(issue.id)}" >
									V </td><td>
	
									<form  method="post">
										<input type="hidden" name="issueId" value="<c:out value="${issue.id}" />" >
										<input type="hidden" name="action" value="deselectissue">
										<input type="submit" value="-"/>
									</form>						                                             
								</c:if>
								<c:if test="${!sessionScope.selectedIds.contains(issue.id)}" >
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
					<input type="submit" value="Place your order"/>
				</form>	
			</th>
		</tr>
	</table>
	</c:if>	

<%@ include file="footer.jsp" %>