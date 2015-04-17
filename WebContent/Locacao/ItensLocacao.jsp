<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adicionar Itens na Loca��o</title>


</head>
<body>

	<c:import url="/includes/menu.jsp"></c:import>


	<h1>Adicionar Itens da Loca��o</h1>


	<table class="table table-striped">

		<tr>
			<td class="success">Numero da Loca��o :</td>
			<td>${locacao.id}</td>
			<td class="success">Nome do Cliente :</td>
			<td>${locacao.cliente.nome}</td>
		</tr>
		<tr>
			<td class="success">Data da Loca��o :</td>
			<td><fmt:formatDate value="${locacao.dataloc.time}"
					pattern="dd/MM/YYYY" /></td>
			<td class="success">Valor :</td>
			<td>${locacao.valor}</td>

			<td><a href="itenscontroller.do?acao=exc&id=${locacao.id}"><button
						class="btn btn-danger">Cancelar Loca��o</button></a></td>
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
		
		
		 <label for="dp">Data Previs�o de Devolu��o:</label> 
		<input type="text" id="dp" disabled="disabled" name="dataprev" value="<fmt:formatDate value="${requestScope.itenslocacao.dataprevdevolucao.time}" pattern="dd/MM/YYYY"/>" /><br />
		<input type="hidden" value="itensvenda" name="acao" /> <input
			type="submit" class="btn btn-success" value="Adicionar Iten na Loca��o" />

	</form>

	<br />
	<br />
	<br />

	<table class="table table-striped" border='1'>
		<tr bgcolor='EAEAEA' class="success">
			<td>Codigo</td>
			<td>Nome do Filme</td>
			<td>Quantidade</td>
			<td>Pre�o</td>
			<td>Total do Item</td>
			<td>Data Prevista para Devolu��o</td>
			
			<td>A��o</td>

		</tr>

		<c:forEach items="${requestScope.listaItens}" var="itenslocacao">


			<tr>
				<td>${itenslocacao.id}</td>
				<td>${itenslocacao.filme.nome}</td>
				<td>${itenslocacao.qtd}</td>
				<td>${itenslocacao.filme.preco}</td>
				<td>${itenslocacao.valoritem}</td>
				

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
			class="btn btn-success">Finalizar Loca��o</button></a>

	<br />
	<br />




</body>
</html>