<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Sistema de Automação de Locadoras de Filmes</title>
</head>
<body>



<c:import url="/includes/menu.jsp"></c:import><br />

<form action="autcontroller.do">
<h1>Sistema de Automação de Locadoras de Filmes</h1> <br/>
<br/>

</form>
<br>
Usuário : ${sessionScope.usuLogado.nome}

</body>
</html>