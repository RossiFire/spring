<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="jumbotron jumbotron-billboard">
	<div class="img"></div>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<h2>${titolo}</h2>
			</div>
		</div>
	</div>
</div>

<div class="container">
	<a href="?language=it" style="color: green">Italiano</a>
		<a href="?language=en" style="color: red">Inglese</a>
	<form:form modelAttribute="insUtente" method="POST" action="aggiungi">
		<div class="form-group">
			<label for="n"><spring:message code="insUtente.form.nome.label" /></label>
			<form:input type="text" path="nome" placeholder="Inserisci nome" id="n" />
		</div>
		<div class="form-group">
			<label for="c"><spring:message code="insUtente.form.cognome.label" /></label>
			<form:input type="text" path="cognome" placeholder="Inserisci cognome" id="c"/>
		</div>
		<div class="form-group">
			<label for="d">Data di Nascita</label>
			<form:input path="nascita" placeholder="Inserisci data" id="d" />
		</div>
		<div class="form-group">
			<label for="p"><spring:message code="insUtente.form.password.label" /></label>
			<form:input type="password" path="password"
				placeholder="Inserisci password" id="p" />
		</div>
		<form:button type="submit" class="btn btn-primary"><spring:message code="insUtente.form.btnAdd.label" /></form:button>
	</form:form>
</div>