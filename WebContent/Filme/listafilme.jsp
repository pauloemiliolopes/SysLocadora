<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Filmes </title>
</head>
<body>

<c:import url="/includes/menu.jsp"></c:import><br />

<table border='1'>
<tr bgcolor='EAEAEA'> 
<th>Codigo</th> 
<th>Nome</th>
<th>Data Lançamento</th> 
<th>Sinopse</th>
<th>Tempo Locação</th>
<th>Quantidade</th>
<th>Preço</th>
<th>Genero</th>
<th>Status</th>
<th>Ação</th>

</tr>

<c:forEach items="${requestScope.lista}" var="filme">


<tr> 
<td>${filme.id}</td> 
<td>${filme.nome}</td>
<td><fmt:formatDate value="${filme.datalanc.time}" pattern="dd/MM/YYYY"/></td>
<td>${filme.sinopse}</td>
<td>${filme.tempoloc}</td>
<td>${filme.qtd}</td>
<td>${filme.preco}</td>

<td>${filme.genero.nome}</td>

<td>${filme.status}</td>
<td>
|
<a href="filmecontroller.do?acao=alt&id=${filme.id}">Alterar</a>
|
<a href="filmecontroller.do?acao=exc&id=${filme.id }">Excluir</a>
|
</td>

</tr>
	
</c:forEach>
</table>

</body>
</html>