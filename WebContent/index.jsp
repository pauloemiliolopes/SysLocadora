<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Sistema de Automa��o de Locadoras de Filmes</title>
</head>
<body>
	<c:import url="/includes/menu.jsp"></c:import>
	
	<div class="container">
	<h1><center>Sistema de Automa��o de Locadoras de Filmes</center></h1>
	
	<center> <img src="telainicial.bmp">
	</center> <br />

	<span> Bem Vindo , ${sessionScope.usuLogado.nome} </span>
	</div>
</body>
</html>