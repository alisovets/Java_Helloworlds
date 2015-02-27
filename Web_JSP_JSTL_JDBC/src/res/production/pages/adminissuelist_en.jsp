<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ include file="header.jsp" %>			
		
	<style type="text/css">
		input.fill {width: 100%; border: 0px;}
		select{width: 100%; border: 0px;}
		td.invisible{border: 0px;}
	</style>
	
	<title>Issues</title>
	
	<%@ include file="languageSelector.jsp" %>
	<p>This will be english!</p>	

	<p>Administrator  <c:out value="${sessionScope.administrator.name}"/>! </p> 
	<h4>You can modify list of publications:</h4>
			
	<table width="80%" border="1">
		<tr>
			<th width="2%"> # </th>
			<th width="2%">id</th>
			<th width="2%">Act ual</th>
			<th width="35%">Issues</th>
			<th width="35%">Publisher</th>
			<th>Periodicity</th>
			<th>Price</th>
			<th></th>
		</tr>
		
		<c:set value="1" var="index" />
		<c:forEach items="${requestScope.issueList}" var="issue">
			
			<tr>
  				<td> <c:out value="${index}" /> </td>
				<td><c:out value="${issue.id}"/></td>
				
				<td>
  					<c:if test="${issue.actual}" >
						<form method="post">						
							<input type="hidden" name="action" value="issueNotActual" />
							<input type="hidden" name="issueId" value="<c:out value="${issue.id}"/>">
							<input type="submit" value="Да"/>
						</form>	
					</c:if>
					<c:if test="${!issue.actual}" >
						<form method="post">	
							<input type="hidden" name="action" value="issueActual" />
							<input type="hidden" name="issueId" value="<c:out value="${issue.id}"/>">
							<input type="submit" value="Нет"/>
						</form>	
					</c:if>
				
				</td>
				
				<form method="post">
  					<input type="hidden" name="action" value="updateIssue" />
  					<input type="hidden" name="issueId" value="<c:out value="${issue.id}"/>" />
  					<input type="hidden" name="actual" value="<c:out value="${issue.actual}"/>" />
	  				<td><input class="fill" type="text" name="name" value="<c:out value="${issue.name}" />" /></td>
	  				<td><input class="fill" type="text" name="publisher" value="<c:out value="${issue.publisher}" />"/></td>
	  				
	  				<td>
	  					<select name="periodicity" >
	  					
	  		
	  					    <c:if test="${'DAILY' == issue.periodicity}" >
  								<option value="DAILY" selected>
  							</c:if>
  							<c:if test="${'DAILY' != issue.periodicity}" >
  								<option value="DAILY">
  							</c:if>
  							<c:out value="${requestScope.periodicities[0].toLocaleString(sessionScope.locale)}" /> </option>
  							
  							<c:if test="${'WEEKLY' == issue.periodicity}" >
  								<option value="WEEKLY" selected>
  							</c:if>
  							<c:if test="${'WEEKLY' != issue.periodicity}" >
  								<option value="WEEKLY">
  							</c:if>
  							<c:out value="${requestScope.periodicities[1].toLocaleString(sessionScope.locale)}" /> </option>
  							
  							<c:if test="${'MONTHLY' == issue.periodicity}" >
  								<option value="MONTHLY" selected>
  							</c:if>
  							<c:if test="${'MONTHLY' != issue.periodicity}" >
  								<option value="MONTHLY">
  							</c:if>
  							<c:out value="${requestScope.periodicities[2].toLocaleString(sessionScope.locale)}" /> </option>
  							
  							
						</select> 
	  				</td>
	  				<td><input class="fill" type="text" name="price" value="<c:out value="${issue.getLocalePrice(sessionScope.locale)}" />"/></td>
	  				<td><input type="submit" value="apply"/></td>
  				</form>
			</tr>	
			
			<c:set value="${index + 1}" var="index" />
		</c:forEach >
		<tr>
			<form method="post">
  				<input type="hidden" name="action" value="addIssue">
  				<td></td><td>+</td><td></td>	
  				<td><input class="fill" type="text" name="name" value="<c:out value="${requestScope.name}" />" /></td>
  				<td><input class="fill" type="text" name="publisher" value="<c:out value="${requestScope.publisher}" />" /></td>
  				<td>
  					<select name="periodicity">
  					 	<c:if test="${'DAILY' == requestScope.periodicity}" >
							<option value="DAILY" selected>
						</c:if>
						<c:if test="${'DAILY' != requestScope.periodicity}" >
							<option value="DAILY">
						</c:if>
						<c:out value="${requestScope.periodicities[0].toLocaleString(sessionScope.locale)}" /> </option>
						
						<c:if test="${'WEEKLY' == requestScope.periodicity}" >
							<option value="WEEKLY" selected>
						</c:if>
						<c:if test="${'WEEKLY' != requestScope.periodicity}" >
							<option value="WEEKLY">
						</c:if>
						<c:out value="${requestScope.periodicities[1].toLocaleString(sessionScope.locale)}" /></option>
						
						<c:if test="${'MONTHLY' == requestScope.periodicity}" >
							<option value="MONTHLY" selected>
						</c:if>
						<c:if test="${'MONTHLY' != requestScope.periodicity}" >
							<option value="MONTHLY">
						</c:if>
						<c:out value="${requestScope.periodicities[2].toLocaleString(sessionScope.locale)}" /></option>
					</select> 
  				</td>
  				<td><input class="fill" type="text" name="price" value="<c:out value="${requestScope.price}" />" /></td>
  				<td><input type="submit" value=" Add "/></td>
			</form> 
		</tr>	
	</table>
	</table>
		
<%@ include file="footer.jsp" %>