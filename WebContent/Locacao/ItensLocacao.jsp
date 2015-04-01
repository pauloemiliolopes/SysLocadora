<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adicionar Itens na Locação</title>


</head>
<body>

	<c:import url="/includes/menu.jsp"></c:import>


	<h1>Adicionar Itens da Locação</h1>


	<table class="table table-striped">

		<tr>
			<td class="success">Numero da Locação :</td>
			<td>${locacao.id}</td>
			<td class="success">Nome do Cliente :</td>
			<td>${locacao.cliente.nome}</td>
		</tr>
		<tr>
			<td class="success">Data da Locação :</td>
			<td><fmt:formatDate value="${locacao.dataloc.time}"
					pattern="dd/MM/YYYY" /></td>
			<td class="success">Valor :</td>
			<td>${locacao.valor}</td>

			<td><a href="itenscontroller.do?acao=exc&id=${locacao.id}"><button
						class="btn btn-danger">Cancelar Locação</button></a></td>
		</tr>


	</table>



	<br />
	<br />

	<form role="form" action="itenscontroller.do" method="get">
		<div class="form-group">
			<label for="busca">Buscar Filme: </label> <input id="locacao"
				type="text" class="form-control" name="buscarfil" /> <br /> <input
				type="submit" class="btn btn-success" value="Buscar">
		</div>
	</form>
	<br />



	<form name="itensvenda" action="itenscontroller.do" method="post">

		<input type="hidden" name="idvenda" value="${requestScope.locacao.id}" />
		<input type="hidden" name="iditensvenda" value="${requestScope.itenslocacao.id}" /> <br /> 
		
		<label	for="filme">Selecione Filme :</label>
		
		 <select name="filmes"> 
		 <c:forEach items="${requestScope.listaFilme}" var="filme">
				<option value="${filme.id}">${filme.nome}</option>
			</c:forEach>
		</select><br />
		
		<label for="qtde">Quantidade :</label>
		<input  type="text" id="qtde" name="qtditem" value="${requestScope.itenslocacao.qtd}" />
		<span class="text-danger">${requestScope.estoque}"</span><br/><br/>
		
		
		 <label for="dp">Data Previsão de Devolução:</label> 
		<input type="text" id="dp" disabled="disabled" name="dataprev" value="<fmt:formatDate value="${requestScope.itenslocacao.dataprevdevolucao.time}" pattern="dd/MM/YYYY"/>" /><br />
		<input type="hidden" value="itensvenda" name="acao" /> <input
			type="submit" class="btn btn-success"
			value="Adicionar Iten na Locação" />

	</form>

	<br />
	<br />
	<br />

	<table class="table table-striped" border='1'>
		<tr bgcolor='EAEAEA' class="success">
			<td>Codigo</td>
			<td>Nome do Filme</td>
			<td>Quantidade</td>
			<td>Preço</td>
			<td>Total do Item</td>
			<td>Data Prevista para Devolução</td>
			
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

				<td><a
					href="itenscontroller.do?acao=excitem&id=${itenslocacao.id }"><button
							class="btn btn-danger">Cancelar Item</button></a> 
			

				</td>
				

			</tr>

		</c:forEach>
	</table>

	<br />
	<br />

	<a href="itenscontroller.do?acao=list"><button
			class="btn btn-success">Finalizar Locação</button></a>

	<br />
	<br />




</body>
</html>