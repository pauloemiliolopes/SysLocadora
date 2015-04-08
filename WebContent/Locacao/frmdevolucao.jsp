<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Realizar Devolução</title>
</head>
<body>

<c:import url="/includes/menu.jsp"></c:import>

<br/>
<br/>
 
	<table class="table">

		<tr>
			<td class="success">Numero da Locação :</td>
			<td>${locacao.id}</td>
			<td><center><a
					href="locacaocontroller.do?acao=fimdev&id=${locacao.id }"><button
							class="btn btn-success">Finalizar Devolução</button></a></center></td>
			
			</tr>
			<tr>
			<td class="success">Nome do Cliente :</td>
			<td>${locacao.cliente.nome}</td>
			<td><span class="text-danger">${requestScope.msg}</span><br/><br/>
	            <span class="text-danger">${requestScope.msg1}</span>
			</td>
			
		</tr>
		<tr>
		    <td class="success">Valor :</td>
			<td>${locacao.valor}</td>
			<td class="success">Data da Locação :</td>
			<td><fmt:formatDate value="${locacao.dataloc.time}"
					pattern="dd/MM/YYYY" /></td>
			


	</table>

	<br/>
<br/><br/>
<br/>

	<table class="table table-striped" border='1'>
		<tr bgcolor='EAEAEA' class="success">
			<td>Codigo</td>
			<td>Nome do Filme</td>
			<td>Quantidade</td>
			<td>Preço</td>
			<td>Total do Item</td>
			<td>Data Prevista para Devolução</td>
			<td>Data Devolução</td>
			
			
			<td>Ação</td>

		</tr>

		<c:forEach items="${requestScope.listaItens}" var="itenslocacao">


			<tr>
				<td>${itenslocacao.id}</td>
				<td>${itenslocacao.filme.nome}</td>
				<td>${itenslocacao.qtd}</td>
				<td>${itenslocacao.filme.preco}</td>
				<td>${itenslocacao.filme.preco * itenslocacao.qtd}</td>
				

				<td><fmt:formatDate
						value="${itenslocacao.dataprevdevolucao.time}"
						pattern="dd/MM/YYYY" /></td>
				<td><fmt:formatDate
						value="${itenslocacao.datadevolucao.time}"
						pattern="dd/MM/YYYY" /></td>
						
					

				<td><a
					href="itenscontroller.do?acao=devitem&id=${itenslocacao.id }"><button
							class="btn btn-danger">Devolver</button></a> 
			

				</td>
	
			</tr>

		</c:forEach>
	</table>


	




</body>
</html>