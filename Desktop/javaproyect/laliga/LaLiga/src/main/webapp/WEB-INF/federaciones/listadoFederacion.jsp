<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html">
<title>${titulo}</title>
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
	</head>
<body>
	<h1>Listado de federaciones</h1>

	${titulo}

	<table
		class="table table-hover table-condensed table-striped table-bordered">
		<thead>
			<tr>
				<td style="width: 10%">#</td>
				<td style="width: 10%">Nombre</td>
				<td style="width: 10%">País</td>
				<sec:authorize access="hasRole('ADMIN')">
				<td style="width: 10%">Editar</td>
				<td style="width: 10%">Borrar</td>
				</sec:authorize>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${federaciones}" var="federacion">
				<tr data-id="${federacion.id}">
					<td>${federacion.id}</td>
					<td>${federacion.nombre}</td>
					<td>${federacion.pais}</td>
					<sec:authorize access="hasRole('ADMIN')">
					<td><button type="button" class="btn btn-warning btn-editar">Editar</button></td>
					<td><button type="button" class="btn btn-danger btn-borrar">Borrar</button></td>
					</sec:authorize>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">Federaciones registradas: <span
					id="cantidad-federaciones">${federaciones.size()}</span></td>
			</tr>
			<tr>
				<td colspan="5">
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#modal-federaciones">Registrar Federaciones</button>
				</td>
			</tr>
		</tfoot>
	</table>


	<div class="modal fade" id="modal-federaciones" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="form-federaciones" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Informacion de Federaciones</h4>
					</div>
					<div class="modal-body">
						<label for="nombre">Nombre: </label> <input id="nombre" name="nombre"
							class="form-control"> 
						<label for="nombre">País: </label> <input id="pais" name="pais"
							class="form-control"> 
							
						</select> <input id="id" name="id" type="hidden"> <input id="csrf"
							name="_csrf" type="hidden" value="${_csrf.token}">
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
						<button id="btn-salvar" type="submit" class="btn btn-primary">Guardar
							Informacion</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<a href="${path}/index" class= "btn btn-default" >Página de inicio</a>
	
	<script type="text/javascript">
	
	$(document).ready(function(){
		
		$('.btn-editar').on('click', function(){
				var id = $(this).parents('tr').data('id');
				var url = 'federaciones/'+id;
				
				$.get(url)
					.done(function(federacion){
						$('#id').val(federacion.id);
						$('#nombre').val(federacion.nombre);
						$('#edad').val(federacion.pais);
						$('#form-federaciones .modal-title').text("Editando federaciones...")
						
						$('#modal-federaciones').modal('show');
					});
		});
		
		
		
		$('.btn-borrar').on('click', function(){
			var id = $(this).parents('tr').data('id');
			var csrf = $('#csrf').val();
			
			$.ajax({
				url : "federaciones/"+id,
				type: 'DELETE',
				headers: {'X-CSRF-TOKEN': csrf},
			    success: function(result) {
			    	$('tr[data-id="'+id+'"]').remove();
					var federaciones = parseInt( $('#cantidad-federaciones').text() );
			    	$('#cantidad-federaciones').text(federaciones - 1);
			    }
			});
			
		});
		
	});
	
	</script>
	
	
	
	
	

</body>
</html>