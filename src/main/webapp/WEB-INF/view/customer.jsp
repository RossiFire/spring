<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> 





 	<div class="jumbotron">
		<h2>${titolo}</h2>
	</div>
 	<c:if test="${tipoUtente == 'ADMIN' }">
 <button type="button" class="btn btn-success" style="float: right !important; margin-right:50px; color: black !important"><a href="utenti/aggiungi">Aggiungi Utente</a></button>
	</c:if>
 <h5>Filta per nome</h5>
 <input type="text" id="input" onkeyup="cerca()"/>
 <table class="table">
  <thead>
    <tr>
      <th scope="col">ID<i class="fas fa-sort"></i></th>
      <th scope="col">Nome<i class="fas fa-sort"></i></th>
      <th scope="col">Cognome<i class="fas fa-sort"></i></th>
      <th scope="col">Tipo Utente<i class="fas fa-sort"></i></th>
      <th scope="col">Data<i class="fas fa-sort"></i></th>
      <c:if test="${tipoUtente == 'ADMIN' }">
      <th scope="col">Password<i class="fas fa-sort"></i></th>
      </c:if>
      <th scope="col"></th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${utenti}" var="utente" >
  	 <c:set var = "tipo" scope = "session" value = "${utente.tipoutente.tipo}"/>
      <c:if test = "${tipo == 'CUSTOMER'}">
    	<tr class="righe">
      		<th scope="row">${utente.id}</th>
      		<td class="userName">${utente.nome}</td>
      		<td>${utente.cognome}</td>
      		<td>${utente.tipoutente.tipo}</td>
      		<td>${utente.nascita}</td>
      	<c:if test="${tipoUtente == 'ADMIN' }">
      		<td>${utente.password}</td>
      		<td>
      			<a href="<spring:url value="/utenti/modifica/${utente.id}" /> " class="btn btn-primary table-buttons">
					<span class="oi oi-pencil"></span> Modifica 
      			</a> 
      		</td>
      		<td>
				<a href="<spring:url value="/utenti/elimina/${utente.id}" /> " class="btn btn-danger table-buttons">
					<span class="oi oi-trash"/></span> Elimina 
      			</a> 
      		</td>      	
      	</c:if>
    	</tr>      
      </c:if>
	</c:forEach>
  </tbody>
</table>
 
 
 