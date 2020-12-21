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
	<form:form modelAttribute="newPrenotazione" method="POST">
		<label>Utente</label>
			<form:select path="utentePrenotato.id" class="form-control">
				<c:forEach var="utente" items="${utenti}">
							<form:option value="${utente.id}" label="${utente.nome}"/>
				</c:forEach>
			</form:select>
			<label>Mezzo</label>
			<form:select path="mezzoPrenotato.id" class="form-control">
				<c:forEach var="mezzo" items="${mezzi}">
							<form:option value="${mezzo.id}" label="${mezzo.casaCostr} ${mezzo.modello}"/>
				</c:forEach>
			</form:select>
		<div class="form-group">
			<label for="p">Data Inizio</label>
			<form:input path="dataInizio" placeholder="Data inizio" id="p" />
		</div>
		<div class="form-group">
			<label for="n">Data Fine</label>
			<form:input path="dataFine" placeholder="Data Fine" id="n" />
		</div>
		<label>Approvata</label>
		<form:select path="approvata">
			<form:option value="true">Si</form:option>
			<form:option value="false">No</form:option>
		</form:select>
		<form:button type="submit" class="btn btn-primary"><spring:message code="insUtente.form.btnAdd.label" /></form:button>
	</form:form>
</div>