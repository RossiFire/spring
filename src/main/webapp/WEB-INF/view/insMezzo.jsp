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
	<form:form modelAttribute="newMezzo" method="POST">
		<div class="form-group">
			<label for="n"><spring:message code="insMezzo.form.casaCostr.label" /></label>
			<form:input type="text" path="casaCostr" placeholder="Casa Costruttrice" id="n" />
		</div>
		<div class="form-group">
			<label for="c"><spring:message code="insMezzo.form.modello.label" /></label>
			<form:input type="text" path="modello" placeholder="Modello" id="c"/>
		</div>
		<div class="form-group">
			<label for="p"><spring:message code="insMezzo.form.targa.label" /></label>
			<form:input type="text" path="targa" placeholder="Inserisci Targa" id="p" />
		</div>
		<label><spring:message code="insMezzo.form.tipomezzo.label" /></label>
			<form:select path="tipomezzo.id" class="form-control">
				<c:forEach var="tipoMezzo" items="${tipiMezzi}">
							<form:option value="${tipoMezzo.id}" label="${tipoMezzo.tipo}"/>
				</c:forEach>
			</form:select>
		<form:button type="submit" class="btn btn-primary"><spring:message code="insUtente.form.btnAdd.label" /></form:button>
	</form:form>
</div>