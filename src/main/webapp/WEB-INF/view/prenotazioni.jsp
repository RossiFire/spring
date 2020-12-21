<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> 





 	<div class="jumbotron">
		<h2>${titolo}</h2>
	</div>
 	<c:if test="${tipoUtente == 'ADMIN' }">
 <button type="button" class="btn btn-success" style="float: right !important; margin-right:50px; color: black !important"><a href="prenotazioni/aggiungi">Aggiungi Prenotazione</a></button>
	</c:if>
 <h5>Filta per Utente</h5>
 <input type="text" id="input" onkeyup="cerca()"/>
 <table class="table">
  <thead>
    <tr>
      <th scope="col">ID<i class="fas fa-sort"></i></th>
      <th scope="col">Utente<i class="fas fa-sort"></i></th>
      <th scope="col">Mezzo<i class="fas fa-sort"></i></th>
      <th scope="col">Data Inizio<i class="fas fa-sort"></i></th>
      <th scope="col">Data Fine<i class="fas fa-sort"></i></th>
      <th scope="col">Approvata<i class="fas fa-sort"></i></th>
            <th scope="col">Approva<i class="fas fa-sort"></i></th>
      <th scope="col"></th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${prenotazioni}" var="prenotazione">
  	 <c:set var = "tipo" scope = "session" value = "${tipoUtente}"/>
    	<tr class="righe">
      		<th scope="row">${prenotazione.id}</th>
      		<td class="userName">${prenotazione.utentePrenotato.nome}</td>
      		<td>${prenotazione.mezzoPrenotato.modello}</td>
      		<td>${prenotazione.dataInizio}</td>
      		<td>${prenotazione.dataFine}</td>
      		<td>${prenotazione.approvata}</td>
      	<c:if test="${tipoUtente == 'ADMIN' }">
      	<td>
      	<c:if test="${!prenotazione.approvata}">
      		<a href="<spring:url value="/prenotazioni/approva/${prenotazione.id}" /> " class="btn btn-warning table-buttons">
				<span class="oi oi-pencil"></span> Approva
      		</a> 
      	</c:if>
      		</td>
      		<td>
      			<a href="<spring:url value="/prenotazioni/modifica/${prenotazione.id}" /> " class="btn btn-primary table-buttons">
					<span class="oi oi-pencil"></span> Modifica 
      			</a> 
      		</td>
      		<td>
				<a href="<spring:url value="/prenotazioni/elimina/${prenotazione.id}" /> " class="btn btn-danger table-buttons">
					<span class="oi oi-trash"/></span> Elimina 
      			</a> 
      		</td>      	
      	</c:if>
    	</tr>      
	</c:forEach>
  </tbody>
</table>
 