<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@taglib
uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> <%@ page
language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">

<title>Locação</title>
</head>
<body ng-app="App" ng-controller="VendasCtrl as vm">
	<c:import url="/includes/menu.jsp"></c:import>
	<h2> Realizar Locação </h2> <br />
	    
	<div class="container">
	<form ng-submit="vm.buscarCliente(nome)">
	<h3> Incluir Cliente </h3><br />
		<label for="">Buscar Por Nome</label> <input
			ng-disabled="vm.clients.length" type="text" ng-model="nome"
			required>
		<button ng-disabled="vm.clients.length">Buscar</button>
	</form>
	<table ng-if="nome">
		<tr>
			<th>Nome</th>
			<th>Cpf</th>
		</tr>
		<tr ng-repeat="cliente in vm.clients">
			<td>{{cliente.nome}}</td>
			<td>{{cliente.cpf}}</td>
		</tr>
	</table>
	<h3> Incluir Filmes </h3><br />
	<div ng-if="vm.clients.length">
		<form ng-submit="vm.buscarFilme(filme)">
			<label for="">Buscar Por Nome</label> <input type="text"
				ng-model="filme" required>
			<button>Buscar</button>
		</form>
		<table>
			<tr>
				<th>nome</th>
				<th>valor</th>
			</tr>
			<tr ng-repeat="filme in vm.filmes">
				<td>{{filme.nome}}</td>
				<td>{{filme.preco | currency:" R$ "}}</td>
			</tr>
		</table>
	</div>
	<div ng-if="vm.filmes.length">
		<button ng-click="vm.addFilme(filme)">Add</button>
	</div>
	<div ng-if="vm.filmesAlugados.length">
		<table>
			<tr>
				<th>nome</th>
				<th>valor</th>
				<th>Quantidade</th>
			</tr>
			<tr ng-repeat="filme in vm.filmesAlugados">
				<td>{{filme.nome}}</td>
				<td>{{filme.preco | currency:" R$ "}}</td>
			</tr>
		</table>
		<div>
			<p>Total = {{vm.total | currency:" R$ "}}</p>
			<p>Total de filmes = {{vm.filmesAlugados.length}}</p>
			<div>
				Finalizar Venda : <input type="checkbox" ng-model="chk">
				<button ng-if="chk" ng-click="vm.fecharAluguel()">fechar
					Vendar</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>