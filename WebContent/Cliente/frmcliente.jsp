<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Clientes</title>
</head>
<body>

<c:import url="/includes/menu.jsp"></c:import><br />

<h1> Cadastro de Clientes</h1>
<form action="clientecontroller.do" method="post">
 <input type="hidden" name="id" value="${requestScope.cliente.id }"/> <br />
 Descriçao: <input type="text" name="nome" value="${requestScope.cliente.nome}"/> <br />
 Cpf: <input type="text" name="cpf" value="${requestScope.cliente.cpf}"/> <br />
 Email: <input type="text" name="email" value="${requestScope.cliente.email}"/> <br />
 Telefone: <input type="text" name="telefone" value="${requestScope.cliente.telefone}"/> <br />
 Endereço: <input type="text" name="endereco" value="${requestScope.cliente.endereco}"/> <br />
 
<c:if test="${cliente.status == 'A' || cliente.status == null}">

   <input type="radio" name="status" value="A" checked="checked">
	<label >Ativo</label><br>

	<input type="radio" name="status" value="I">
	<label >Inativo</label><br>

</c:if>

<c:if test="${cliente.status == 'I'}">

<input type="radio" name="status" value="A" >
	<label >Ativo</label><br>

	<input type="radio" name="status" value="I" checked="checked" >
	<label >Inativo</label><br>

</c:if> 
	
 <input type="submit" value="Gravar" />

</form>

</body>
</html>