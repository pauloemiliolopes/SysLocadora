<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>

<title>Cadastro de Clientes</title>
</head>
<body>

<c:import url="/includes/menu.jsp"></c:import>
<div class="container">
<form role="form" action="clientecontroller.do" method="post">
		<h1> Cadastro de Clientes</h1>
		<div  class="form-group">
	 		<input type="hidden" name="id" value="${requestScope.cliente.id }"/> 
	 		<label for="nome"> NOME </label>
	 		<input type="text" id="nome" class="form-control" name="nome" value="${requestScope.cliente.nome}"/>
 		</div>
 		<div  class="form-group">  		
	 		<label for="cpf">CPF</label>
	 		<input type="text" name="cpf" class="form-control" value="${requestScope.cliente.cpf}"/> 
 		</div>
 		<div  class="form-group">
 			<label for="email">EMAIL</label>
 			<input id="email" type="text" class="form-control" name="email" value="${requestScope.cliente.email}"/> 
 		</div>
 		<div  class="form-group">
	 		<label for="teleofone"> TELEFONE</label>
	  		<input type="text" id="teleofone" class="form-control" name="telefone" value="${requestScope.cliente.telefone}"/>
  		</div> 
  		<div class="form-group">
	 		<label for="endereco"> ENDEREÇO </label>
	 		<input id="endereco" type="text" class="form-control" name="endereco" value="${requestScope.cliente.endereco}"/> 
 		</div>
		<div class="form-group">
			<c:if test="${cliente.status == 'A' || cliente.status == null}">
			   <input type="radio" name="status" value="A" checked="checked">
				<label >ATIVO</label>
				<input type="radio" name="status" value="I">
				<label >INATIVO</label>
			</c:if>
		</div>
		
		<div class="form-group">
			<c:if test="${cliente.status == 'I'}">		
			<input type="radio" name="status" value="A" >
				<label >ATIVO</label>			
				<input type="radio" name="status" value="I" checked="checked" >
				<label >INATIVO</label>		
			</c:if>
		</div> 
	<button class="btn btn-success">GRAVAR</button> 
</form>
</div>
</body>
</html>