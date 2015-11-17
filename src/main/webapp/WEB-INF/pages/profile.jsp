<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@include file="header.jsp"%>
<br>
<br>
<br>
<div class="panel panel-success">
	<div class="panel-heading">
		<h3 class="panel-title">Edit profile:</h3>
	</div>
	<c:url var="saveUrl" value="/profile" />
	<form:form modelAttribute="profile" method="POST" action="${saveUrl}">
		<table class="table table-striped table-bordered">
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<tr>
					<td><form:label path="firstName">First Name:</form:label></td>
					<td><form:input type="text" class="form-control"
							path="firstName" required="true" /></td>
				</tr>
				<tr>
					<td><form:label path="lastName">Surname:</form:label></td>
					<td><form:input type="text" class="form-control"
							path="lastName" required="true" /></td>
				</tr>
			</security:authorize>
			<security:authorize
				access="hasAnyRole('ROLE_TECHNICIAN','ROLE_USER')">
				<tr>
					<td><form:label path="firstName">First Name:</form:label></td>
					<td><c:out value="${profile.firstName}" /></td>
				</tr>
				<tr>
					<td><form:label path="firstName">Surname:</form:label></td>
					<td><c:out value="${profile.lastName}" /></td>
				</tr>
			</security:authorize>
			<tr>
				<td><form:label path="playerEmail">Email:</form:label></td>
				<td><form:input type="email" class="form-control"
						path="playerEmail" required="true" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password:</form:label></td>
				<td><form:input type="text" class="form-control"
						path="password" required="true" /></td>
			</tr>
			<tr>
				<td><form:label path="phoneNumber">Phone number:</form:label></td>
				<td><form:input type="number" class="form-control"
						path="phoneNumber" required="true" /></td>
			</tr>
		</table>
		<form:input type="hidden" class="form-control" path="roleId"
			required="true" />
		<form:input type="hidden" class="form-control" path="playerId"
			required="true" />
		<p style="text-align: center">
			<input type="submit" class="btn btn-success" value="Save" />
		</p>
	</form:form>
</div>
<%@include file="footer.jsp"%>