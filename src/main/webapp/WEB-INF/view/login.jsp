<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 




<c:url var="loginUrl" value="/utenti"/>


<form action="${loginUrl}" method="POST">

	<c:if test="${param.error != null }">
		<div class="alert alert-danger">
			<p>ERRORE</p>
		</div>
	</c:if>
	<c:if test="${param.forbidden != null }">
		<div class="alert alert-danger">
			<p>FORBIDDEN</p>
		</div>
	</c:if>
	<c:if test="${param.logout != null }">
		<div class="alert alert-danger">
			<p>LOGOUT</p>
		</div>
	</c:if>
	
	<input type="text" id="username" name="username" placeholder="Inserisci username" />
	<input type="password" id="password" name="password" placeholder="Inserisci password" />
	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		
	<input type="submit" value="log in" />


</form>