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
<div style="text-align: center">
<p>ID: <c:out value="${utente.id}"></c:out></p>
<p>NOME: <c:out value="${utente.nome}"></c:out></p>
<p>COGNOME: <c:out value="${utente.cognome}"></c:out></p>
<p>TIPO UTENTE: <c:out value="${utente.tipoutente.tipo}"></c:out></p>
<p>PASSWORD: <c:out value="${utente.password}"></c:out></p>

</div>