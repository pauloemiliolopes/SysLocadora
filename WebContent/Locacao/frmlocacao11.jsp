<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" type="text/css" href="Locacao/loc.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:import url="/includes/css.jsp"></c:import>
<c:import url="/includes/js.jsp"></c:import>
<title>Realizar Locação</title>
</head>
<body>
	<div id="headerwrapper">
		<div id="header">
			<c:import url="/includes/menu.jsp"></c:import>
			<br />
		</div>
	</div>



	<div id="corpo">
		<div id="corpo1">

			<div id="cadloc">

				<h1>Iniciar Venda</h1>
				

				 
				<form action="locacaocontroller.do" method="get">
					<label>Buscar Cliente por Nome : </label><input type="text" name="buscar" />
					<button>Buscar</button>
				</form>
						
				<br />

				<table border='1'>
					<tr bgcolor='EAEAEA'>
						<th>Codigo</th>
						<th>Nome</th>
						<th>Cpf</th>
						<th>Email</th>
						<th>Telefone</th>
						<th>Endereço</th>
						<th>Status</th>
						<th>Ação</th>

					</tr>

					<c:forEach items="${requestScope.listaCliente}" var="cliente">


						<tr>
							<td>${cliente.id}</td>
							<td>${cliente.nome}</td>
							<td>${cliente.cpf}</td>
							<td>${cliente.email}</td>
							<td>${cliente.telefone}</td>
							<td>${cliente.endereco}</td>
							<td>${cliente.status}</td>
							<td>| <a
							 href="clientecontroller.do?acao=alt&id=${cliente.id}">Alterar</a>
							|<a href="clientecontroller.do?acao=exc&id=${cliente.id }">Excluir</a>
								|
							</td>

						</tr>

					</c:forEach>
				</table>




			</div>

			<div id="lisloc">
				<h1>Cabeçalho da Venda</h1>


				<form action="locacaocontroller.do" method="post">

					Numero Locação :<input type="text" name="id"
						value="${requestScope.locacao.id}" /> <br /> Selecione Cliente :
					<select name="clientes">
						<c:forEach items="${requestScope.listaCliente}" var="cliente">
							<option value="${cliente.id}">${cliente.nome}</option>
						</c:forEach>
					</select><br /> Data Locação: <input type="text" name="lancamento"
						value="<fmt:formatDate value="${requestScope.locacao.dataloc.time}" pattern="dd/MM/YYYY"/>" /><br />

					Valor : <input type="text" name="id"
						value="${requestScope.locacao.valor}" /> <br /> <input
						type="submit" value="Gravar" />

				</form>



			</div>


		</div>

		<div id="corpo2">
			<div id="cadiloc">
				<h1>Adicionar Itens da Locação</h1>
				<form action="locacaocontroller.do" method="get">
					<label>Buscar Filme por Nome : </label><input type="text" name="buscarfilme" />
					<button>Buscar</button>
				</form>
						<table border='1'>
					<tr bgcolor='EAEAEA'>
						
						<th>Nome</th>
						<th>Preço</th>
						

					</tr>

					<c:forEach items="${requestScope.listfilme}" var="filme">
						<tr>
							<td>${filme.nome}</td>
							<td>${filme.preco}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
			<div id="lisiloc">
				<h1>Lista de Itens Locação</h1>




			</div>

		</div>


	</div>


</body>
</html>