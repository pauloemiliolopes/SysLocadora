<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Baixa de Contas á Receber</title>
</head>
<c:import url="/includes/menu.jsp"></c:import><br />



</head>

<body>

<form action="financeirocontroller.do" method="get">
					<label>Digite o Nome do Cliente : </label>
					<input type="text" name="buscarcliente"/>
					<input type="submit" class="btn btn-success" value="Buscar">
				</form> <br/>

<br/>
<br/>

<table class="table table-striped" border='1'>
		<tr bgcolor='EAEAEA' class="success">
			<td>Codigo</td>
			<td>Codigo Locaçao</td>
	        <td>Nome do Cliente</td>
	        <td>Data Emissao</td>
			<td>Data Vencimento</td>
			<td>Valor</td>
			<td>Valor Baixa</td>
			<td>Saldo</td>
			<td><center>Ação</center></td>
			

		</tr>

		<c:forEach items="${requestScope.listafinanceiro}" var="financeiro">


			<tr>
			<td>${financeiro.id}</td>
				<td>${financeiro.locacao.id}</td>
				<td>${financeiro.locacao.cliente.nome}</td>
				<td><fmt:formatDate
						value="${financeiro.dataemissao.time}"
						pattern="dd/MM/YYYY" /></td>
				<td><fmt:formatDate
						value="${financeiro.datavencimento.time}"
						pattern="dd/MM/YYYY" /></td>
				<td>${financeiro.valor}</td>
				<td>${financeiro.valorbaixa}</td>
				<td>${financeiro.saldo}</td>
				<td><center>
				<a href="financeirocontroller.do?acao=bp&id=${financeiro.id}"><button class="btn btn-primary" >Baixa Parcial</button></a>
				<a href="financeirocontroller.do?acao=bt&id=${financeiro.id}"><button class="btn btn-primary" >Baixa Total</button></a></center>
				</td>

			</tr>

		</c:forEach>
	</table><br/><br/><br/><br/>
	
<center><span class="text-danger">${requestScope.msg}</span></center><br/><br/>
<center><span class="text-danger">${requestScope.msg1}</span></center><br/><br/>
</body>
</html>