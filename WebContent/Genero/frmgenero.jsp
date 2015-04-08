<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Generos</title>
</head>
<body>

<c:import url="/includes/menu.jsp"></c:import><br />
<div class="container">
	<form  role="form" action="generocontroller.do" method="post">
		<h1> Cadastro de Generos</h1>
		<div  class="form-group">
	 		<input type="hidden" name="id" value="${requestScope.genero.id }"/> <br />
	 		<label for="nome">Descriçao :</label> 
	 		<input id="nome" type="text" size="40" class="form-control" name="nome" value="${requestScope.genero.nome}"/> <br />
	 		<br/>
	 	</div>
		<div  class="form-group">
			<c:if test="${genero.status == 'A' || genero.status == null}">
			   <input type="radio" name="status" value="A" checked="checked">
				<label >Ativo</label>
				<input type="radio" name="status" value="I">
				<label >Inativo</label>
			</c:if>
			<c:if test="${genero.status == 'I'}">
			<input type="radio" name="status" value="A" >
				<label >Ativo</label>
				<input type="radio" name="status" value="I" checked="checked" >
				<label >Inativo</label>
			</c:if> 
		</div>
		<br/>
		<button class="btn btn-success">Gravar</button>
	</form>
</div>
</body>
</html>