<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de Locações</title>
</head>
<body>


	<c:import url="/includes/menu.jsp"></c:import>
	<br />
	
	<br/>

<form action="itenscontroller.do" method="get">
					<label>Buscar Locacao por Nome : </label>
					<input type="text" name="buscarcliente"/>
					<input type="submit" class="btn btn-success" value="Buscar">
				</form> <br/>
	
	<div class="simplesTable">
		<table border='1'>
			<tr bgcolor='EAEAEA'>
				<td>Codigo</td>
				<td>Nome Cliente</td>
				<td>Valor</td>
				<td>Data Locação</td>
				<td>Ação</td>

			</tr>

			<c:forEach items="${requestScope.listalocacoes}" var="locacao">


				<tr>
					<td>${locacao.id}</td>
					<td>${locacao.cliente.nome}</td>
					<td>${locacao.valor}</td>
					<td><fmt:formatDate value="${locacao.dataloc.time}"
							pattern="dd/MM/YYYY" /></td>
			
					<td><a href="itenscontroller.do?acao=dev&id=${locacao.id}"><button class="btn btn-danger" >Realizar Devolução</button></a>
					
					</td>

				</tr>

			</c:forEach>
		</table>
	</div>

</body>
</html>