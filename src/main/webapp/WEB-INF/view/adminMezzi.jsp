<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> 





 	<div class="jumbotron">
		<h2>${titolo}</h2>	
	</div>
 	<c:if test="${tipoUtente == 'ADMIN' }">
 <button type="button" class="btn btn-success" style="float: right !important; margin-right:50px; color: black !important"><a href="mezzi/aggiungi">Aggiungi Mezzo</a></button>
 	</c:if>
 <h5>Filta per nome</h5>
 <input type="text" id="input" onkeyup="cerca()"/>
 <table class="table">
  <thead>
    <tr>
      <th scope="col">ID<i class="fas fa-sort"></i></th>
      <th scope="col">Casa Costruttrice<i class="fas fa-sort"></i></th>
      <th scope="col">Modello<i class="fas fa-sort"></i></th>
      <th scope="col">Tipo Mezzo<i class="fas fa-sort"></i></th>
      <th scope="col">Targa<i class="fas fa-sort"></i></th>
      <th scope="col"></th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${mezzi}" var="mezzo" >
    	<tr class="righe">
      		<th scope="row">${mezzo.id}</th>
      		<td class="userName">${mezzo.casaCostr}</td>
      		<td>${mezzo.modello}</td>
      		<td>${mezzo.tipomezzo.tipo}</td>
      		<td>${mezzo.targa}</td>
      	<c:if test="${tipoUtente == 'ADMIN' }">
      		<td>
      			<a href="<spring:url value="/mezzi/modifica/${mezzo.id}" /> " class="btn btn-primary table-buttons">
					<span class="oi oi-pencil"></span> Modifica 
      			</a> 
      		</td>
      		<td>
				<a href="<spring:url value="/mezzi/elimina/${mezzo.id}" /> " class="btn btn-danger table-buttons">
					<span class="oi oi-trash"/></span> Elimina 
      			</a> 
      		</td>
      	</c:if>
    	</tr>      
	</c:forEach>
  </tbody>
</table>
 