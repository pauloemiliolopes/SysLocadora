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
<h1> Cadastro de Generos</h1>
<form action="generocontroller.do" method="post">
 <input type="hidden" name="id" value="${requestScope.genero.id }"/> <br />
 Descriçao: <input type="text" name="nome" value="${requestScope.genero.nome}"/> <br />
 Status: <input type="text" name="status" value="${requestScope.genero.status }"/> <br />
 <input type="submit" value="Gravar" />

</form>

</body>
</html>