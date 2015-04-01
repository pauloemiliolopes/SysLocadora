<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Locação</title>
</head>
<body>

<c:import url="/includes/css.jsp"></c:import>

	<br />
	<br />
	<h1>Dados da Locação</h1>
	<table class="table table-hover">

		<tr>
			<td>Numero da Locação :</td>
			<td>${locacao.id}</td>
			<td>Nome do Cliente :</td>
			<td>${locacao.cliente.nome}</td>
		</tr>
		<tr>
			<td>Data da Locação :</td>
			<td><fmt:formatDate value="${locacao.dataloc.time}"
					pattern="dd/MM/YYYY" /></td>
			<td>Valor :</td>
			<td>${locacao.valor}</td>
		</tr>


	</table>

	<br />
	<br />

    <h3>Itens Locação</h3>
    
    
    <table class="table table-striped" border='1'>
		<tr bgcolor='EAEAEA' class="success">
			<td>Codigo</td>
			<td>Nome do Filme</td>
			<td>Quantidade</td>
			<td>Preço</td>
			<td>Total do Item</td>
			<td>Data Prevista para Devolução</td>
			
			

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

				
	
			</tr>

		</c:forEach>
	</table>
    
    
    

	<br />
	<br />
	<input type="submit" value="Imprimir" onclick="window.print()" />

	<br />

	<form action="itenscontroller.do">
		<br /> <br /> <br /> <br /> <br /> <br /> <input type="hidden"
			name="acao" value="sair" /> <input type="submit"
			class="btn btn-success" value="Finalizar" />



	</form>

</body>
</html>