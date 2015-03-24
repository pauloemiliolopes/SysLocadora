<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Clientes </title>
</head>
<body>

<c:import url="/includes/menu.jsp"></c:import><br />
<br/>

<form action="clientecontroller.do" method="get">
					<label>Buscar Cliente por Nome : </label>
					<input type="text" name="buscarcli"/>
					<input type="submit" value="Buscar">
				</form> <br/>

<div class="simplesTable">
<table>
<tr> 
<td>Codigo</td> 
<td>Nome</td>
<td>Cpf</td>
<td>Email</td>
<td>Telefone</td>
<td>Endereço</td>
<td>Status</td>
<td>Ação</td>

</tr>

<c:forEach items="${requestScope.lista}" var="cliente">


<tr> 
<td>${cliente.id}</td> 
<td>${cliente.nome}</td>
<td>${cliente.cpf}</td>
<td>${cliente.email}</td>
<td>${cliente.telefone}</td>
<td>${cliente.endereco}</td>
<td>${cliente.status}</td>
<td>
|
<a href="clientecontroller.do?acao=alt&id=${cliente.id}">Alterar</a>
|
<a href="clientecontroller.do?acao=exc&id=${cliente.id }">Excluir</a>
|
</td>
</tr>
	
</c:forEach>
</table>
</div>
</body>
</html>