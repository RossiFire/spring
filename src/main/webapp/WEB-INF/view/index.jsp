<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
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
    <title>Home</title>
    <style type="text/css">
    	h1{
    		text-align : center;
    	}
    	a{
    		position: absolute;
    		top: 50%;
    		left: 50%;
    		text-transform: uppercase;
    		border-radius: 0px;
    		margin-top: 20px;
    		margin-left: -50px;
    	}
    </style>
</head>

<body>
		<h1>RENTALCARR</h1>

	<img alt="" src="https://picsum.photos/1350/500">

		<a href="login/form" class="btn btn-warning">Accedi</a>
	
</body>
</html>
