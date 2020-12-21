<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%> 
 
<!doctype html>
<html lang="it">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link href="<c:url value="/static/css/bootstrap.min.css" />" rel="stylesheet">
    
    <!-- Altri CSS -->
    <link href="<c:url value="/static/css/main.css" />" rel="stylesheet">
    <link href="<c:url value="/static/css/open-iconic-bootstrap.css" />" rel="stylesheet">
	<script src="<c:url value="/static/js/filter.js" />"></script>
    <title><tiles:insertAttribute name="titolo" /></title>
  </head>
  <body>
  
  <!-- Image and text -->
<ul class="nav" style="background-color: #472999 !important; color: black !important">
  <li class="nav-item">
    <a class="nav-link active" href="/RentalCarApp">Logout</a>
  </li>
    <li class="nav-item">
    <a class="nav-link active" href="/RentalCarApp/utenti">Customer</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="/RentalCarApp/mezzi">Parco Auto</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="/RentalCarApp/profilo">Utente</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="/RentalCarApp/prenotazioni">Prenotazioni</a>
  </li>
</ul>

	<div class="container-fluid">
<tiles:insertAttribute name="content" />
	
<tiles:insertAttribute name="footer" />
	</div>

  
  
  
  
  
  
  
  </body>
</html>