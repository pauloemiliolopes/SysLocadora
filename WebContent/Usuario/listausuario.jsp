<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Generos </title>
</head>
<body>

<c:import url="/includes/menu.jsp"></c:import><br />

<table border='1'>
<tr bgcolor='EAEAEA'> 
<th>Codigo</th> 
<th>Nome</th>
<th>Login</th>
<th>senha</th>
<th>Ação</th>

</tr>

<c:forEach items="${requestScope.lista}" var="usuario">


<tr> 
<td>${usuario.id}</td> 
<td>${usuario.nome}</td>
<td>${usuario.user}</td>
<td>${usuario.senha}</td>
<td>
|
<a href="usuariocontroller.do?acao=alt&id=${usuario.id}">Alterar</a>
|
<a href="usuariocontroller.do?acao=exc&id=${usuario.id }">Excluir</a>
|
</td>

</tr>
	
</c:forEach>
</table>

</body>
</html>