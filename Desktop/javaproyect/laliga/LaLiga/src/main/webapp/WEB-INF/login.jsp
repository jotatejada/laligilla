<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Pizzara - Login</title>
		
		<c:set var="path" value="${pageContext.request.contextPath}"
	scope="request" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
		
		<style type="text/css">

			#login{
				width: 400px;
				margin-left: auto;
				margin-right: auto;
				margin-top: 100px;
			}
			
			#btn-login{
				width: 100%;
			}
		</style>
	</head>
	<body>
		<section id="login" class="panel panel-primary">
			
			<c:if test='${not empty param["semacesso"]}'>
				<div class="alert alert-warning">
					Chacho te has equivocao!
				</div>
			</c:if>
			
			<c:if test='${not empty param["saiu"]}'>
				<div class="alert alert-info">
					Fuera Ángel!
				</div>
			</c:if>
			
			<form action="${path}/autenticar" method="post">
				<div class="panel-heading">
					Admin - La Liga
				</div>
				
				<div class="panel-body">
					<label for="username"></label>
					<input id="username" name="username" class="form-control" required>
					
					<label for="password"></label>
					<input type="password" id="password" name="password" class="form-control" required>
				</div>
				
				<div class="panel-footer">
					<button id="btn-login" class="btn btn-primary">Entrar</button>
				</div>
				
				<input type="hidden" id="_csrf" name="_csrf" value="${_csrf.token}">
			</form>
		</section>
	
		
	</body>
</html>